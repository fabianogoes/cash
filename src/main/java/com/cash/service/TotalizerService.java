package com.cash.service;

import com.cash.model.Register;
import com.cash.model.Totalizer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalizerService {

    public Totalizer getTotalizer(List<Register> registers) {
        double movimentation = registers.stream().mapToDouble(r -> r.getAmount()).sum();
        double credit = registers.stream().filter(r -> r.getType().equalsIgnoreCase("Credit")).mapToDouble(r -> r.getAmount()).sum();
        double debit = registers.stream().filter(r -> r.getType().equalsIgnoreCase("Debit")).mapToDouble(r -> r.getAmount()).sum();
        double pending = registers.stream().filter(r -> r.getStatus().equalsIgnoreCase("Pending")).mapToDouble(r -> r.getAmount()).sum();
        double balance = credit - debit;

        return Totalizer.builder()
                .credit(credit)
                .debit(debit)
                .pending(pending)
                .balance(balance)
                .movimentation(movimentation)
                .build();
    }

}
