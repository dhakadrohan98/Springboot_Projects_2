package org.codeship.company.service.impl;

import org.codeship.company.dto.DepartmentResponseDTO;
import org.codeship.company.dto.EmployeeRequestDTO;
import org.codeship.company.dto.EmployeeResponseDTO;
import org.codeship.company.entity.Department;
import org.codeship.company.entity.Employee;
import org.codeship.company.mapper.EmployeeMapper;
import org.codeship.company.repository.DepartmentRepository;
import org.codeship.company.repository.EmployeeRepository;
import org.codeship.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(@Autowired EmployeeRepository employeeRepository,
                               @Autowired DepartmentRepository departmentRepository,
                               @Autowired EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Long saveEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee emp = emp = new Employee();
        emp.setEmail(employeeRequestDTO.getEmail());
        emp.setFirstName(employeeRequestDTO.getFirstName());
        emp.setLastName(employeeRequestDTO.getLastName());
        emp.setAddress(employeeRequestDTO.getAddress());
        emp.setSalary(employeeRequestDTO.getSalary());
        emp.setDesignation(employeeRequestDTO.getDesignation());
       //get DeptId if exists otherwise throw the error
        Long requestedDeptId = employeeRequestDTO.getDeptId();
        Department department = this.departmentRepository.findById(requestedDeptId).get();
        if(department != null) {
            emp.setDepartment(department);
        }
        //save the entity into database
        this.employeeRepository.save(emp);

        //find the empIdBYEmail
        Employee empResponse = employeeRepository.findByEmail(employeeRequestDTO.getEmail()).get();
        if(empResponse != null) {
            return empResponse.getEmpId();
        } else {
            return -1l;
        }
    }

    @Override
    public EmployeeResponseDTO findEmpById(Long empId) {
       Employee employee = this.employeeRepository.findById(empId).get();
       EmployeeResponseDTO employeeResponseDTO = null;
       if(employee != null) {
           employeeResponseDTO = employeeMapper.toResponseDTO(employee);
       }
       return employeeResponseDTO;
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        return List.of();
    }

    @Override
    public EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO employeeRequestDTO) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        return;
    }
}
