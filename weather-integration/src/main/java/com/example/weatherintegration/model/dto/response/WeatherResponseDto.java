package com.example.weatherintegration.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WeatherResponseDto {
    private List<Weather> weather;
}
