package com.cash.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.util.Calendar;
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
    @NonNull
    @NotBlank(message = "Required Field.")
    private String title;
    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dueDate = Calendar.getInstance().getTime();

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date payDate;

    @Min(value = 1, message = "Amount should not be less than 1")
    private double amount;

    private String month;

    // PAID, PENDING, DELAYED
    private String status;

    // DEBIT, CREDIT
    private String type;

    // WATER, LIGHT, PHONE, INTERNET, CREDITCARD, FOOD, HEALTH, EDUCATION, BANKRATE, HABITATION
    private String category;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate = Calendar.getInstance().getTime();

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastModifiedDate = Calendar.getInstance().getTime();

    @DBRef
    private User user;

}
