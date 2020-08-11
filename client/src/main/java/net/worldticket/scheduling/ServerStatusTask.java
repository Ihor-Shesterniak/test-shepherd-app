package net.worldticket.scheduling;

import com.paazl.gui.GuiInterface;
import com.paazl.gui.OrderRequestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.Map;

@Component
public class ServerStatusTask implements OrderRequestListener {
    private static final Logger LOG = LoggerFactory.getLogger(ServerStatusTask.class);

    private GuiInterface guiInterface;

    @Autowired
    public ServerStatusTask(GuiInterface guiInterface) {
        this.guiInterface = guiInterface;
        this.guiInterface.addOrderRequestListener(this);
    }

    @Scheduled(cron = "${scheduling.server_status.cron}")
    public void getServerStatus() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.getForObject("http://localhost:8080/rest/status", Map.class);
        guiInterface.addServerFeedback(String.format("Server status-> healthy: %s, dead: %s",
                result.get("numberOfHealthySheep"),
                result.get("numberOfDeadSheep")));
        LOG.info("Got servers status: {}", result);
    }

    @Scheduled(cron = "${scheduling.server_status.cron}")
    public void getBalance() {
        RestTemplate restTemplate = new RestTemplate();
        BigInteger balance = restTemplate.getForObject("http://localhost:8080/rest/balance", BigInteger.class);
        guiInterface.addServerFeedback("Current balance... " + balance);
        LOG.info("Got balance: {}", balance);
    }

    @Override
    public void orderRequest(int i) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://localhost:8080/rest/order/" + i, String.class);
        LOG.info(" Quantity of new sheep: {}" + i);
    }

}
