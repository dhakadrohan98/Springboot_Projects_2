package com.wissen.restapi.service;

import com.wissen.restapi.entity.Department;
import com.wissen.restapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }

    @Override
    public boolean deleteDepatmentById(Long departmentId) {
        //check whether dept entry with given id exists or not
        if(departmentRepository.findById(departmentId).get() != null) {
            departmentRepository.deleteById(departmentId);
            return true;
        }
        return false;
    }

    //Custom logic to update dept details to given id.
    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department deptToUpdate = departmentRepository.findById(departmentId).get();
        //If department name's object is not null & not an empty string
        if (Objects.nonNull(department.getDepartmentName()) &&
                "".equalsIgnoreCase(department.getDepartmentName()) == false) {
            deptToUpdate.setDepartmentName(department.getDepartmentName());
        }
        //If department address's object is not null & not an empty string
        if (Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            deptToUpdate.setDepartmentAddress(department.getDepartmentAddress());
        }
        //If department code's object is not null & not an empty string
        if (Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
            deptToUpdate.setDepartmentCode(department.getDepartmentCode());
        }
        return departmentRepository.save(deptToUpdate);
    }
}
