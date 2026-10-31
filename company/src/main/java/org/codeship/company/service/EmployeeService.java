package org.codeship.company.service;

import org.codeship.company.dto.employeeDto.EmployeePartialRequestDto;
import org.codeship.company.dto.employeeDto.EmployeeRequestDTO;
import org.codeship.company.dto.employeeDto.EmployeeResponseDTO;
import org.codeship.company.entity.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO updatePartialInfo(String email, EmployeePartialRequestDto employeePartialRequestDto);

    EmployeeResponseDTO findEmpById(Long empId);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO updateEmployeeById(Long empId, EmployeeRequestDTO employeeRequestDTO);

    void deleteById(Long empId);
}
