package org.codeship.company.service.impl;

import org.codeship.company.dto.EmployeeRequestDTO;
import org.codeship.company.dto.EmployeeResponseDTO;
import org.codeship.company.entity.Department;
import org.codeship.company.entity.Employee;
import org.codeship.company.mapper.EmployeeMapper;
import org.codeship.company.repository.DepartmentRepository;
import org.codeship.company.repository.EmployeeRepository;
import org.codeship.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository,
                               @Autowired EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapper = employeeMapper;
    }

    //map all fields of employeeRequestDTO with Employee object
    private void mapEmpResponseDtoToEmployeeEntity(Employee emp, EmployeeRequestDTO employeeRequestDTO) {
        if(emp != null) {
            emp.setEmail(employeeRequestDTO.getEmail());
            emp.setFirstName(employeeRequestDTO.getFirstName());
            emp.setLastName(employeeRequestDTO.getLastName());
            emp.setAddress(employeeRequestDTO.getAddress());
            emp.setSalary(employeeRequestDTO.getSalary());
            emp.setDesignation(employeeRequestDTO.getDesignation());
            //get DeptId if exists otherwise throw the error
            Long requestedDeptId = employeeRequestDTO.getDeptId();
//            Department department = this.departmentRepository.findById(requestedDeptId).get();
//            if(department != null) {
//                emp.setDepartment(department);
//            }
        }
    }

    @Override
    public Long saveEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee emp = emp = new Employee();
        //map all fields from emp request dto to employee entity with its department
        this.mapEmpResponseDtoToEmployeeEntity(emp, employeeRequestDTO);
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
        List<Employee> employeeList = this.employeeRepository.findAll();
        List<EmployeeResponseDTO> employeeResponseDTOList = new ArrayList<>();

        for(Employee emp: employeeList) {
            employeeResponseDTOList.add(employeeMapper.toResponseDTO(emp));
        }
        return employeeResponseDTOList;
    }

    @Override
    public EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = null;
        Employee emp = this.employeeRepository.findById(id).get();
        //map all fields from emp request dto to employee entity with its department
        this.mapEmpResponseDtoToEmployeeEntity(emp, employeeRequestDTO);
        if(emp != null) {
            this.employeeRepository.save(emp);
            employeeResponseDTO = employeeMapper.toResponseDTO(emp);
        }
        //map emp with response DTOs
        return employeeResponseDTO;
    }

    @Override
    public void deleteById(Long id) {
        this.employeeRepository.deleteById(id);
    }
}
