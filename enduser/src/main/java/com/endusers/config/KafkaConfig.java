package com.endusers.config;

import com.endusers.constants.AppConstants;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {

    @KafkaListener(topics = AppConstants.LOCATION_UPDATE, groupId = AppConstants.GROUP_ID)
    public void updatedLocation(String value) {
        System.out.println(value);
    }
}
