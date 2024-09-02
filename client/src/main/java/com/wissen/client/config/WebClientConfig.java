package com.wissen.client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@LoadBalancerClient(name = "example-service", configuration = DemoServerInstanceConfiguration.class)
public class WebClientConfig {
    /**
     * This class has one role: create a load-balanced WebClient builder to make remote
     * requests. Notice that our annotation uses a pseudo name for the service.
     **/
    @LoadBalanced
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
