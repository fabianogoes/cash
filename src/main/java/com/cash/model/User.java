package com.cash.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    private String id;
    private String name;
    private String login;
    private String password;
    private String email;

}
