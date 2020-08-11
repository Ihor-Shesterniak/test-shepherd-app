package net.worldticket.service;

import net.worldticket.data.CurrentBalance;
import net.worldticket.data.Sheep;
import net.worldticket.data.State;
import net.worldticket.data.repositories.CurrentBalanceRepository;
import net.worldticket.data.repositories.SheepRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class ShepherdService {

    private SheepRepository sheepRepository;
    private CurrentBalanceRepository currentBalanceRepository;
    private Integer priceOfSheep;

    @Autowired
    public ShepherdService(SheepRepository sheepRepository, CurrentBalanceRepository currentBalanceRepository, @Value("${price_of_new_sheep}") Integer priceOfSheep) {
        this.sheepRepository = sheepRepository;
        this.currentBalanceRepository = currentBalanceRepository;
        this.priceOfSheep = priceOfSheep;
    }

    @Transactional(readOnly = true)
    public SheepStatusesDto getStatus() {
        List<Sheep> aliveSheep = sheepRepository.findAllByState(State.HEALTHY);
        List<Sheep> deadSheep = sheepRepository.findAllByState(State.DEAD);
        return new SheepStatusesDto(aliveSheep.size(), deadSheep.size());
    }

    @Transactional
    public int orderSheeps(Long qty) {
        int balance = currentBalanceRepository.findFirstByOrderByTimestampDesc().getBalance().intValue();
        int sheeps = 0;

        for (int i = 0; i < qty; ++i) {
            sheepRepository.save(new Sheep());
            if (balance - priceOfSheep < 0) {
                break;
            }
            sheeps++;
            balance -= priceOfSheep;
        }

        currentBalanceRepository.save(new CurrentBalance(BigInteger.valueOf(balance)));
        return sheeps;
    }

    @Transactional(readOnly = true)
    public BigInteger getBalance() {
        return currentBalanceRepository.findFirstByOrderByTimestampDesc().getBalance();
    }
}