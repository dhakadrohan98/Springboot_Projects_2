package com.codeship.weather.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hourly {
    private List<String> time;
    @JsonProperty("temperature_2m")
    private List<Float> temperature2M;
}
