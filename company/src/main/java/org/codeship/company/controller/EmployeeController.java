package org.codeship.company.controller;

import jakarta.validation.Valid;
import org.codeship.company.dto.employeeDto.EmployeePartialRequestDto;
import org.codeship.company.dto.employeeDto.EmployeeRequestDTO;
import org.codeship.company.dto.employeeDto.EmployeeResponseDTO;
import org.codeship.company.entity.Employee;
import org.codeship.company.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/v1/getEmployeeById/{id}")
    public ResponseEntity<EmployeeResponseDTO> searchEmployeeById(@PathVariable("id") Long empId) {
        EmployeeResponseDTO employeeResponseDTO = this.employeeService.findEmpById(empId);
        if (employeeResponseDTO != null) {
            return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new EmployeeResponseDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/v1/getAllEmployee")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> employeeResponseDTOList = new ArrayList<>();
        employeeResponseDTOList = this.employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeResponseDTOList, HttpStatus.OK);
    }

    @PostMapping("/v1/createEmployee")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = this.employeeService.saveEmployee(employeeRequestDTO);
        if (employeeResponseDTO != null && employeeResponseDTO.getEmpId() > 0) {
            return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new EmployeeResponseDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/v1/updateEmployee/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable("id") Long empId,
                                                              @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO)
    {
        EmployeeResponseDTO employeeResponseDTO = null;
        employeeResponseDTO = employeeService.updateEmployeeById(empId, employeeRequestDTO);
        if (employeeResponseDTO != null) {
            return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new EmployeeResponseDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/v1/updatePartialEmpInfo")
    public ResponseEntity<EmployeeResponseDTO> updatePartialInfo(@RequestParam("email") String email,
                                                                 @Valid @RequestBody EmployeePartialRequestDto
                                                                         employeePartialRequestDto) {
        EmployeeResponseDTO employeeResponseDTO =
                this.employeeService.updatePartialInfo(email, employeePartialRequestDto);
        if (employeeResponseDTO != null) {
            return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new EmployeeResponseDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/v1/deleteEmployee/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") int empId) {
        this.employeeService.deleteById(Long.valueOf(empId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
