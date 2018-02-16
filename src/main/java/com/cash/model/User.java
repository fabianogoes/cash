package com.cash.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document
public class User {

    @Id
    private String id;
    private String name;
    @Indexed
    private String email;
    private String password;

}
