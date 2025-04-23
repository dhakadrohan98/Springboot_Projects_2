package com.weather.controller;

import com.weather.model.WeatherResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m";

    @GetMapping
    public ResponseEntity<WeatherResponseDTO> getWeatherDetails() {
        log.info("Inside getWeatherDetails() method");
        WeatherResponseDTO weatherResponseDTO = restTemplate.getForObject(url, WeatherResponseDTO.class);
        //String apiKey = "YOUR_API_KEY";
        //String url =
        // "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m&apikey={apiKey}"
        // pass API key as query parameter
        // restTemplate.getForObject(url, WeatherResponseDTO.class, apiKey);
        log.info(weatherResponseDTO.toString());
        return new ResponseEntity<>(weatherResponseDTO, HttpStatus.OK);
    }
}
