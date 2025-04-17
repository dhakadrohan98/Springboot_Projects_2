package com.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponseDTO {
    private float latitude;
    private float longitude;
    private double generateTime;
    private int utcOffsetSeconds;
    private String timezone;
    private String timezoneAbbreviation;
    private float elevation;
    private HourlyUnit hourlyUnit;
    private Hourly hourly;
}
