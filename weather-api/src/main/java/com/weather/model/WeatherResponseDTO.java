package com.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponseDTO {

    @JsonProperty("latitude")
    private float latitude;
    @JsonProperty("longitude")
    private float longitude;
    @JsonProperty("generationtime_ms")
    private String generationtimeMS;
    @JsonProperty("utc_offset_seconds")
    private int utcOffsetSeconds;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;
    @JsonProperty("elevation")
    private int elevation;
    @JsonProperty("hourly_units")
    private HourlyUnit hourlyUnits;
    @JsonProperty("hourly")
    private Hourly hourly;
}
