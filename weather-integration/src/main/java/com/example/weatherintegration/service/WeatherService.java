package com.example.weatherintegration.service;

import com.example.weatherintegration.model.dto.response.WeatherResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.apikey}")
    private String apiKey;

    private String url = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99";

    public WeatherResponseDto getWeatherDetails() throws HttpClientErrorException.NotFound {
        //processing the url
        url = url + "&appid=" + apiKey;
        //inside try with catch
        WeatherResponseDto responseDto =
                this.restTemplate.getForObject(url, WeatherResponseDto.class);
        if(responseDto == null) {
            return null;
        }
        log.info(responseDto.toString());
        return responseDto;
    }
}

//Tell how to write test case for service layer?

//@WebMvcTest(WeatherService.class)
//@BeforeEach - set the test data, mock object
//@Test
//@Mock restTemplate
//@InjectsMock        WeatherService obj;
////1. given precondition or setup
//when().
////2. action or the behaviour that we are going to test
//
////3. verify the output


