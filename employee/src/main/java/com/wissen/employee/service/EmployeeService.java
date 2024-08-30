package com.wissen.employee.service;

import com.wissen.employee.entity.Employee;
import com.wissen.employee.repository.EmployeeRepository;
import com.wissen.employee.response.AddressResponse;
import com.wissen.employee.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public EmployeeResponse getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
        AddressResponse addressResponse = restTemplate.
                getForObject("http://ADDRESS/address/address/{id}", AddressResponse.class, id);

        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }
}
