package com.weather.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class appconfig {

    @Bean
    public RestTemplate restTemplate() {
        log.info("rest template object is created");
        return new RestTemplate();
    }
}
