package org.codeship.company.service.impl;

import org.codeship.company.dto.departmentDto.DepartmentRequestDTO;
import org.codeship.company.dto.departmentDto.DepartmentResponseDTO;
import org.codeship.company.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    //To do
    @Override
    public DepartmentResponseDTO saveDept(DepartmentRequestDTO deptDepartmentRequestDTO) {
        return null;
    }

    //To do
    @Override
    public DepartmentResponseDTO findDeptById(Long deptId) {
        return null;
    }

    //To do
    @Override
    public List<DepartmentResponseDTO> findAllDepartments() {
        return List.of();
    }

    //To do
    @Override
    public DepartmentResponseDTO updateDepartmentById(DepartmentRequestDTO departmentRequestDTO) {
        return null;
    }

    @Override
    public void deleteDeptById(Long deptId) {

    }
}
