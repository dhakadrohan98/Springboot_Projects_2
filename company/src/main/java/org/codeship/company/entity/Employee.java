package org.codeship.company.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name= "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(length = 255)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "dept_id", nullable = true)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_engineer")
    private Employee leadEngineer;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salary;

    @Column(nullable = false, length = 100)
    private String designation;

    @Column(name= "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name= "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public Employee() {

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Employee getLeadEngineer() {
        return leadEngineer;
    }

    public void setLeadEngineer(Employee leadEngineer) {
        this.leadEngineer = leadEngineer;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
