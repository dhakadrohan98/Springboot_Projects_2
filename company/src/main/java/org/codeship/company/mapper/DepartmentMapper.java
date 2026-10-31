package org.codeship.company.mapper;

import org.codeship.company.dto.departmentDto.DepartmentResponseDTO;
import org.codeship.company.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(target = "deptHeadId", source = "deptHead.empId")
    DepartmentResponseDTO toResponseDto(Department department);
}
