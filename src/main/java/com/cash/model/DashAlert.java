package com.cash.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DashAlert {
    private String title;
    private String description;
}
