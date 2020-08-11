package net.worldticket.scheduling;

import net.worldticket.data.CurrentBalance;
import net.worldticket.data.Sheep;
import net.worldticket.data.State;
import net.worldticket.data.repositories.CurrentBalanceRepository;
import net.worldticket.data.repositories.SheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Component
public class SheepUpdateTask {
    private SheepRepository sheepRepository;
    private CurrentBalanceRepository currentBalanceRepository;
    private int chanceSheepDies;
    private Integer priceOfSheep;

    @Autowired
    public SheepUpdateTask(SheepRepository sheepRepository, CurrentBalanceRepository currentBalanceRepository, @Value("${chance.sheep.dies}") int chanceSheepDies, @Value("${price_of_new_sheep}") Integer priceOfSheep) {
        this.sheepRepository = sheepRepository;
        this.currentBalanceRepository = currentBalanceRepository;
        this.chanceSheepDies = chanceSheepDies;
        this.priceOfSheep = priceOfSheep;
    }

    @Scheduled(cron = "${scheduling.update_sheep_cron}")
    @Transactional
    public void updateSheepStates() {
        int balance = currentBalanceRepository.findFirstByOrderByTimestampDesc().getBalance().intValue();
        List<Sheep> aliveSheep = sheepRepository.findAllByState(State.HEALTHY);
        for (Sheep sheep : aliveSheep) {
            int rolled = (int) (Math.random() * 100);
            if (rolled < chanceSheepDies) {
                sheep.setState(State.DEAD);
                if (balance - priceOfSheep < 0) {
                    break;
                }
                balance -= priceOfSheep;
            }
            currentBalanceRepository.save(new CurrentBalance(BigInteger.valueOf(balance)));
            sheepRepository.save(sheep);
        }
    }
}