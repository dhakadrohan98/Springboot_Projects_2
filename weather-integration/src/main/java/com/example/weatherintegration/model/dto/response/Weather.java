package com.example.weatherintegration.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private Integer id;
    private String main;
    private String description;
    private String icon;
}
