package org.codeship.company.mapper;

import javax.annotation.processing.Generated;
import org.codeship.company.dto.employeeDto.EmployeeResponseDTO;
import org.codeship.company.entity.Department;
import org.codeship.company.entity.Employee;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-31T16:35:35+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.2.1 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeResponseDTO toResponseDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();

        employeeResponseDTO.setDeptId( employeeDepartmentDeptId( employee ) );
        employeeResponseDTO.setManagerId( employeeManagerEmpId( employee ) );
        employeeResponseDTO.setLeadEngineerId( employeeLeadEngineerEmpId( employee ) );
        employeeResponseDTO.setEmpId( employee.getEmpId() );
        employeeResponseDTO.setEmail( employee.getEmail() );
        employeeResponseDTO.setFirstName( employee.getFirstName() );
        employeeResponseDTO.setLastName( employee.getLastName() );
        employeeResponseDTO.setAddress( employee.getAddress() );
        employeeResponseDTO.setSalary( employee.getSalary() );
        employeeResponseDTO.setDesignation( employee.getDesignation() );
        employeeResponseDTO.setCreatedAt( employee.getCreatedAt() );
        employeeResponseDTO.setUpdatedAt( employee.getUpdatedAt() );

        return employeeResponseDTO;
    }

    private Long employeeDepartmentDeptId(Employee employee) {
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        return department.getDeptId();
    }

    private Long employeeManagerEmpId(Employee employee) {
        Employee manager = employee.getManager();
        if ( manager == null ) {
            return null;
        }
        return manager.getEmpId();
    }

    private Long employeeLeadEngineerEmpId(Employee employee) {
        Employee leadEngineer = employee.getLeadEngineer();
        if ( leadEngineer == null ) {
            return null;
        }
        return leadEngineer.getEmpId();
    }
}
