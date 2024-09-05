package com.wissen.departmentservice.controller;

import com.wissen.departmentservice.entity.Department;
import com.wissen.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @Value("${server.port}")
    private String port;

    private int requestCount = 0;

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId){
        requestCount++;
        // Log which instance (port) is handling the request
        logger.info("Request #" + requestCount + " handled by Department Service running on port: " + port);
        Department department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }
}
