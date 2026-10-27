package org.codeship.company.service;

import org.codeship.company.dto.EmployeeRequestDTO;
import org.codeship.company.dto.EmployeeResponseDTO;
import org.codeship.company.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Long saveEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO findEmpById(Long empId);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO updateEmployeeById(Long empId, EmployeeRequestDTO employeeRequestDTO);

    void deleteById(Long empId);
}
