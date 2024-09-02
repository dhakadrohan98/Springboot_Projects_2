package com.wissen.client;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 *  provide a list of service instances that are available
 *  for a given service ID.
 *  It defines how we find available service instances.
 */
public class DemoInstanceSupplier implements ServiceInstanceListSupplier {

    private final String serviceId;

    public DemoInstanceSupplier(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceId() {
        return this.serviceId;
    }

    /**
     * String instanceId,
     * String serviceId,
     * String host,
     * int port,
     * boolean secure
     */
    /**
     * Returns a Flux containing a list of service instances, each representing a mocked
     * server instance with specific host and port.
     *
     * DefaultServiceInstance is the concrete implementation of the
     * ServiceInstance interface
     */
    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId,
                                "localhost", 8080, false),
                        new DefaultServiceInstance(serviceId + "2", serviceId,
                                "localhost", 8081, false)
                )
        );
    }
}
