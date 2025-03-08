package com.wissen.restapi.controller;

import com.wissen.restapi.entity.Department;
import com.wissen.restapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    //property based dependency injection
    @Autowired
    private DepartmentService departmenService;

    @PostMapping("/departments")
    public Department saveDepartment(@RequestBody Department department) {
        return departmenService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentList() {
        return departmenService.fetchDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) {
        return departmenService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepatmentById(@PathVariable("id") Long departmentId) {
        try {
            boolean result = departmenService.deleteDepatmentById(departmentId);
            if(result) {
                return "Department is deleted successfully!";
            } else {
                return "Dept with given id doesn't exist";
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "Dept with given id doesn't exist";
    }

    //i want dept id & dept object with updated details
    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId,
                                       @RequestBody Department department) {
        return departmenService.updateDepartment(departmentId, department);
    }



}
