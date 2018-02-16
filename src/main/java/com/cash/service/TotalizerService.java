package com.cash.service;

import com.cash.model.Totalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalizerService {

    @Autowired
    private RegisterService service;

    public Totalizer getTotalizer() {
        double credit = service.findAll().stream().filter(r -> r.getType().equalsIgnoreCase("Credit")).mapToDouble(r -> r.getAmount()).sum();
        double debit = service.findAll().stream().filter(r -> r.getType().equalsIgnoreCase("Debit")).mapToDouble(r -> r.getAmount()).sum();
        double pending = service.findAll().stream().filter(r -> r.getStatus().equalsIgnoreCase("Pending")).mapToDouble(r -> r.getAmount()).sum();
        double balance = credit - debit;

        return Totalizer.builder()
                .credit(credit)
                .debit(debit)
                .pending(pending)
                .balance(balance)
                .build();
    }

}
