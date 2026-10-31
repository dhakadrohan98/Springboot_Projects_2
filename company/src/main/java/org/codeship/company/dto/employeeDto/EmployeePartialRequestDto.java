package org.codeship.company.dto.employeeDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.codeship.company.entity.Department;

import java.math.BigDecimal;

@Data
public class EmployeePartialRequestDto {

    private Department department;

    @Positive(message = "Salary should be greater than 0.00")
    private BigDecimal salary;

    private String designation;

//    @Positive(message = "Manager id should be positive")
//    private Long managerId;
}
