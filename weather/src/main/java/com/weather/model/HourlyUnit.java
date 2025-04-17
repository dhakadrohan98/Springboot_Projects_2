package com.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HourlyUnit {
    private String time;
    private String temperature_2m;
}
