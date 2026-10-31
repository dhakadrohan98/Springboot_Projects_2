package org.codeship.company.service.impl;

import org.codeship.company.dto.employeeDto.EmployeePartialRequestDto;
import org.codeship.company.dto.employeeDto.EmployeeRequestDTO;
import org.codeship.company.dto.employeeDto.EmployeeResponseDTO;
import org.codeship.company.entity.Department;
import org.codeship.company.entity.Employee;
import org.codeship.company.exception.ResourceNotFoundException;
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
    private void mapEmpRequestDtoToEmployeeEntity(Employee emp, EmployeeRequestDTO employeeRequestDTO) {
        emp.setEmail(employeeRequestDTO.getEmail());
        emp.setFirstName(employeeRequestDTO.getFirstName());
        emp.setLastName(employeeRequestDTO.getLastName());
        emp.setAddress(employeeRequestDTO.getAddress());
        emp.setSalary(employeeRequestDTO.getSalary());
        emp.setDesignation(employeeRequestDTO.getDesignation());
        //get DeptId if exists otherwise throw the exception ResourceNotFoundException
        Long requestedDeptId = employeeRequestDTO.getDeptId();
        if (requestedDeptId == null) {
            emp.setDepartment(null);
        } else if(requestedDeptId != null) {
            Department department = this.departmentRepository.findById(requestedDeptId)
                    .orElseThrow(() -> new ResourceNotFoundException(("Requested dept id does not exists : " + requestedDeptId)));
            emp.setDepartment(department);
        }
    }

    @Override
    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO) throws ResourceNotFoundException {
        Employee emp = emp = new Employee();
        //map all fields from emp request dto to employee entity with its department
        this.mapEmpRequestDtoToEmployeeEntity(emp, employeeRequestDTO);
        // save() returns the saved entity, including its generated ID
        Employee savedEmployee = this.employeeRepository.save(emp);
        if (savedEmployee != null) {
            return this.employeeMapper.toResponseDTO(savedEmployee);
        } else {
            return null;
        }
    }

    @Override
    public EmployeeResponseDTO findEmpById(Long empId) throws ResourceNotFoundException {
        Employee employee = this.employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException(("Requested employee does not exist : " + empId)));
        EmployeeResponseDTO employeeResponseDTO = null;
        if (employee != null) {
            employeeResponseDTO = employeeMapper.toResponseDTO(employee);
        }
        return employeeResponseDTO;
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employeeList = this.employeeRepository.findAll();
        List<EmployeeResponseDTO> employeeResponseDTOList = new ArrayList<>();

        for (Employee emp : employeeList) {
            employeeResponseDTOList.add(employeeMapper.toResponseDTO(emp));
        }
        return employeeResponseDTOList;
    }

    @Override
    public EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = null;
        Employee emp = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requested employee doesn't exist" + employeeRequestDTO.getEmail()));
        if(emp != null) {
            mapEmpRequestDtoToEmployeeEntity(emp, employeeRequestDTO);
            emp = this.employeeRepository.save(emp);
            employeeResponseDTO = employeeMapper.toResponseDTO(emp);
        }
        //map emp with response DTOs
        return employeeResponseDTO;
    }

    @Override
    public EmployeeResponseDTO updatePartialInfo(String email, EmployeePartialRequestDto employeePartialRequestDto) {
        EmployeeResponseDTO employeeResponseDTO = null;
        //find the associated employee first
        Employee employee = this.employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Resource is not found with email: " + email));
        if(employee != null) {
            //set all fields from partial request object
            employee.setDepartment(employeePartialRequestDto.getDepartment());
            employee.setSalary(employeePartialRequestDto.getSalary());
            employee.setDesignation(employeePartialRequestDto.getDesignation());
            Employee savedEmployee = this.employeeRepository.save(employee);
            employeeResponseDTO = this.employeeMapper.toResponseDTO(savedEmployee);
        }
        return employeeResponseDTO;
    }

    @Override
    public void deleteById(Long id) {
        this.employeeRepository.deleteById(id);
    }
}
