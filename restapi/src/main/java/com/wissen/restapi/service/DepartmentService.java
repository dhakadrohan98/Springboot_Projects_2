package com.wissen.restapi.service;

import com.wissen.restapi.entity.Department;

import java.util.List;

public interface DepartmentService {
    //create department
    Department saveDepartment(Department department);

    //get all department details in a list
    List<Department> fetchDepartmentList();

    //get Dept details by Id
    Department fetchDepartmentById(Long departmentId);

    boolean deleteDepatmentById(Long departmentId);

    //Update Dept details to the given id & with new details of Department object
    Department updateDepartment(Long departmentId, Department department);
}
