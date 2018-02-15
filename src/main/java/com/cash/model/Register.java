package com.cash.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document
public class Register {

    private String id;

    @Indexed
    private String title;
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dueDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date payDate;

    private double amount;

    private String month;

    // PAID, PENDING, DELAYED
    private String status;

    // DEBIT, CREDIT
    private String type;

    // WATER, LIGHT, PHONE, INTERNET, CREDITCARD, FOOD, HEALTH, EDUCATION, BANKRATE, HABITATION
    private String category;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    private Date createdDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    private Date lastModifiedDate;



}
