package org.codeship.company.mapper;

import javax.annotation.processing.Generated;
import org.codeship.company.dto.departmentDto.DepartmentResponseDTO;
import org.codeship.company.entity.Department;
import org.codeship.company.entity.Employee;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-31T16:35:36+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.2.1 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentResponseDTO toResponseDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();

        departmentResponseDTO.setDeptHeadId( departmentDeptHeadEmpId( department ) );
        departmentResponseDTO.setDeptId( department.getDeptId() );
        departmentResponseDTO.setDeptName( department.getDeptName() );
        departmentResponseDTO.setDescription( department.getDescription() );
        departmentResponseDTO.setLocation( department.getLocation() );
        departmentResponseDTO.setActive( department.isActive() );
        departmentResponseDTO.setCreatedAt( department.getCreatedAt() );
        departmentResponseDTO.setUpdatedAt( department.getUpdatedAt() );

        return departmentResponseDTO;
    }

    private Long departmentDeptHeadEmpId(Department department) {
        Employee deptHead = department.getDeptHead();
        if ( deptHead == null ) {
            return null;
        }
        return deptHead.getEmpId();
    }
}
