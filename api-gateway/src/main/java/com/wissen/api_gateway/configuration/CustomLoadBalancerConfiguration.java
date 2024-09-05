package com.wissen.api_gateway.configuration;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.wissen.api_gateway.loadbalancer.WeightedRoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@LoadBalancerClient(name = "department-service", configuration = CustomLoadBalancerConfiguration.class)
public class CustomLoadBalancerConfiguration {

    @Bean
    public ReactorServiceInstanceLoadBalancer reactorServiceInstanceLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory, DiscoveryClient discoveryClient) {
        String serviceId = "department-service";
        return new WeightedRoundRobinLoadBalancer(serviceId, loadBalancerClientFactory, discoveryClient);
    }
}
