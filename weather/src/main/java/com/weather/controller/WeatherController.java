package com.weather.controller;

import com.weather.model.WeatherResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    private String url = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m";

    @GetMapping
    public ResponseEntity<WeatherResponseDTO> getWeatherDetails() {
        WeatherResponseDTO weatherResponseDTO = restTemplate.getForObject(url, WeatherResponseDTO.class);
        System.out.println(weatherResponseDTO);
        return new ResponseEntity<>(weatherResponseDTO, HttpStatus.OK);
    }
}
