package com.cash.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Totalizer {

    private double credit;
    private double debit;
    private double pending;
    private double balance;

}
