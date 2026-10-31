package org.codeship.company.dto.employeeDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EmployeeResponseDTO {

    private Long empId;
    private String email;
    private String firstName;
    private String lastName;
    private String address;

    private Long deptId;
    private Long managerId;
    private Long leadEngineerId;

    private BigDecimal salary;
    private String designation;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EmployeeResponseDTO() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

//    {
//            "empId": 101,
//            "email": "rohan@example.com",
//            "firstName": "Rohan",
//            "lastName": "Sharma",
//            "address": "Indore",
//            "deptId": 1,
//            "managerId": 102,
//            "leadEngineerId": 103,
//            "salary": 120000.00,
//            "designation": "Software Engineer",
//            "createdAt": "2026-10-25T10:30:00",
//            "updatedAt": "2026-10-25T10:30:00"
//    }
}
