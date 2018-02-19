package com.cash.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document
public class Category {

    private String id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
