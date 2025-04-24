package com.codeship.weather.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HourlyUnits {
    private String time;
    @JsonProperty("temperature_2m")
    private String temperature2M;
}
