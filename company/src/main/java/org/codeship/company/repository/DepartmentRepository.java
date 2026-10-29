package org.codeship.company.repository;

import org.codeship.company.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    //JPA query methods
    Optional<Department> findByDeptName(String deptName);

    boolean existsByDeptName(String deptName);
}
