package org.codeship.company.dto.employeeDto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class EmployeeRequestDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Size(max = 150, message = "Email cannot exceed 150 characters")
    private String email;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    private String lastName;

    @Size(max=155, message = "Address can't exceed 255 characters")
    private String address;

    @Positive(message = "Department ID must be positive")
    private Long deptId;

    @Positive(message = "Manager ID must be positive")
    private Long managerId;

    @Positive(message = "Lead engineer ID must be positive")
    private Long leadEngineerId;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.00", message = "Salary cannot be negative & should be in decimal format")
    private BigDecimal salary;

    @NotBlank(message = "Designation is required")
    @Size(max = 100, message = "Designation cannot exceed 100 characters")
    private String designation;

    public EmployeeRequestDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getLeadEngineerId() {
        return leadEngineerId;
    }

    public void setLeadEngineerId(Long leadEngineerId) {
        this.leadEngineerId = leadEngineerId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

//    {
//             "email": "rohan@example.com",
//            "firstName": "Rohan",
//            "lastName": "Sharma",
//            "address": "Indore",
//            "deptId": 1,
//            "managerId": 2,
//            "leadEngineerId": 3,
//            "salary": 120000.00,
//            "designation": "Software Engineer"
//    }
}
