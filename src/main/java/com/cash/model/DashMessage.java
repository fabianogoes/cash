package com.cash.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DashMessage {

    private String title;
    private String description;

}
