package com.wissen.client.config;

import com.wissen.client.DemoInstanceSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Configuration;

/**
 *  let’s create a Configuration class that instantiates our service instance supplier.
 */
@Configuration
public class DemoServerInstanceConfiguration {

    public ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new DemoInstanceSupplier("example-service");
    }
}
