package org.codeship.company.service;

import org.codeship.company.dto.departmentDto.DepartmentRequestDTO;
import org.codeship.company.dto.departmentDto.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDTO saveDept(DepartmentRequestDTO deptDepartmentRequestDTO);

    DepartmentResponseDTO findDeptById(Long deptId);

    List<DepartmentResponseDTO> findAllDepartments();

    DepartmentResponseDTO updateDepartmentById(DepartmentRequestDTO departmentRequestDTO);

    void deleteDeptById(Long deptId);
}
