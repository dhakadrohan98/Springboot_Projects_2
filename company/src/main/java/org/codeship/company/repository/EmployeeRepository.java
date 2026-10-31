package org.codeship.company.repository;

import org.codeship.company.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //JPA query methods
    Optional<Employee> findByEmail(String name);

    List<Employee> findByFirstName(String name);

    List<Employee> findByLastName(String name);

    List<Employee> findByDesignation(String designation);

    List<Employee> findBySalaryGreaterThan(BigDecimal salary);

    boolean existsByEmail(String email);
}
