package net.worldticket.rest;

import net.worldticket.service.SheepStatusesDto;
import net.worldticket.service.ShepherdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class ShepherdController {

    private ShepherdService service;

    @Autowired
    public ShepherdController(ShepherdService service) {
        this.service = service;
    }

    @GetMapping(value = "/rest/status")
    public SheepStatusesDto getStatus() {
        return service.getStatus();
    }

    @GetMapping(value = "/rest/order/{qty}")
    public void order(@PathVariable Long qty) {
        service.orderSheeps(qty);
    }


    @GetMapping(value = "/rest/balance")
    public BigInteger getBalance() {
        return service.getBalance();
    }

}