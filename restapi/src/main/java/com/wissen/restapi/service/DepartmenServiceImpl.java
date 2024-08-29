package com.wissen.restapi.service;

import com.wissen.restapi.entity.Department;
import com.wissen.restapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmenServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        //log the name & code
        System.out.println("name - " + department.getDepartmentName());
        System.out.println("code - " + department.getDepartmentCode());
        return departmentRepository.save(department);
    }
}
