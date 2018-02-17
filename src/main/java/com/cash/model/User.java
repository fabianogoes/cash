package com.cash.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document
public class User {

    @Id
    private String id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @Email(message = "Email should be valid")
    @Indexed
    private String email;

    @Length(min = 6, max = 30, message = "Password should be min 6 and max 30 caracteres")
    private String password;

}
