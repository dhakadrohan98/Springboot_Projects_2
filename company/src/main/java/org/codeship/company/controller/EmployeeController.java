package org.codeship.company.controller;

import jakarta.validation.Valid;
import org.codeship.company.dto.EmployeeRequestDTO;
import org.codeship.company.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/v1/createEmployee")
    public ResponseEntity<Long> createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        Long empId = this.employeeService.saveEmployee(employeeRequestDTO);
        if (empId != null && empId > 0) {
            return new ResponseEntity<>(empId, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(empId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
