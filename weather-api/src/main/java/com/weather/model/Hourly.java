package com.weather.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hourly {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private List<String> time;
    private List<Double> temperature_2m;
}
