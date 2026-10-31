package org.codeship.company.mapper;

import org.codeship.company.dto.employeeDto.EmployeeResponseDTO;
import org.codeship.company.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "deptId", source = "department.deptId")
    @Mapping(target = "managerId", source = "manager.empId")
    @Mapping(target = "leadEngineerId", source = "leadEngineer.empId")
    EmployeeResponseDTO toResponseDTO(Employee employee);

}
