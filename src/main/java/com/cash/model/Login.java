package com.cash.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Login {

    private String email;
    private String password;

}
