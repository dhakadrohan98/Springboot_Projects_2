package com.codeship.weather.controller;

import com.codeship.weather.model.response.WeatherResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final String url = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<WeatherResponseDTO> getWeatherDetails() {
        WeatherResponseDTO responseDTO = restTemplate.getForObject(url, WeatherResponseDTO.class);
        log.info(responseDTO.toString());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
