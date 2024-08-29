package com.wissen.restapi.controller;

import com.wissen.restapi.entity.Department;
import com.wissen.restapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    //property based dependency injection
    @Autowired
    private DepartmentService departmenService;

    @PostMapping("/departments")
    public Department saveDepartment(@RequestBody Department department) {
        return departmenService.saveDepartment(department);
    }
}
