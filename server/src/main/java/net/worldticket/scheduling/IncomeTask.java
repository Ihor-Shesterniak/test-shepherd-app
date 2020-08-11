package net.worldticket.scheduling;

import net.worldticket.data.repositories.CurrentBalanceRepository;
import net.worldticket.data.repositories.SheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Component
public class IncomeTask {

    private CurrentBalanceRepository currentBalanceRepository;
    private SheepRepository sheepRepository;

    private BigInteger amountPerSheep;

    @Autowired
    public IncomeTask(
            CurrentBalanceRepository currentBalanceRepository,
            SheepRepository sheepRepository,
            @Value("${amount_per_sheep}") BigInteger amountPerSheep) {
        this.currentBalanceRepository = currentBalanceRepository;
        this.sheepRepository = sheepRepository;
        this.amountPerSheep = amountPerSheep;
    }

    @Scheduled(cron = "${scheduling.current_balance_update_cron}")
    @Transactional
    public void updateCurrentBalance() {
    }
}






