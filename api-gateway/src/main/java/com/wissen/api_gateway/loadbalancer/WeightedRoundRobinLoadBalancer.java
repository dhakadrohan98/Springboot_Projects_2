package com.wissen.api_gateway.loadbalancer;

import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WeightedRoundRobinLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private final AtomicInteger position;
    private final String serviceId;
    private final LoadBalancerClientFactory clientFactory;
    private final DiscoveryClient discoveryClient;
    private final Map<String, Integer> instanceWeights;

    public WeightedRoundRobinLoadBalancer(String serviceId, LoadBalancerClientFactory clientFactory, DiscoveryClient discoveryClient) {
        this.serviceId = serviceId;
        this.clientFactory = clientFactory;
        this.discoveryClient = discoveryClient;
        this.position = new AtomicInteger(0);
        this.instanceWeights = new HashMap<>();
    }

    private void initializeWeights(List<ServiceInstance> instances) {
        instanceWeights.clear(); // Clear existing weights to prevent stale data
        for (ServiceInstance instance : instances) {
            // Combine instance ID, host, and port to create a unique key
            String instanceKey = instance.getInstanceId() + "-" + instance.getHost() + ":" + instance.getPort();

            // Assign weight based on the port (customizable)
            if (instance.getPort() == 8080) {
                instanceWeights.put(instanceKey, 3); // Higher weight for instance running on port 8080
            } else if (instance.getPort() == 8082) {
                instanceWeights.put(instanceKey, 1); // Lower weight for instance running on port 8082
            }
        }
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        // Fetch instances from discoveryClient
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        if (instances.isEmpty()) {
            return Mono.just(new EmptyResponse());
        }

        // Initialize weights based on the current instances
        initializeWeights(instances);

        // Calculate the total weight
        int totalWeight = instances.stream()
                .mapToInt(instance -> {
                    String instanceKey = instance.getInstanceId() + "-" + instance.getHost() + ":" + instance.getPort(); // Use instanceId along with host:port
                    return instanceWeights.getOrDefault(instanceKey, 1);  // Use unique key to get the weight
                })
                .sum();

        // Calculate the position
        int pos = Math.abs(this.position.incrementAndGet()) % totalWeight;

        // Iterate over instances and select the one based on the weighted round robin algorithm
        for (ServiceInstance instance : instances) {
            String instanceKey = instance.getInstanceId() + "-" + instance.getHost() + ":" + instance.getPort();  // Unique key using instanceId
            int weight = instanceWeights.getOrDefault(instanceKey, 1);  // Get the weight for this instance
            if (pos < weight) {
                return Mono.just(new DefaultResponse(instance));
            }
            pos -= weight;
        }

        // Fallback to the first instance if no other instance is selected
        return Mono.just(new DefaultResponse(instances.get(0)));
    }
}

