package com.wissen.spring.data.jpa.repository;

import com.wissen.spring.data.jpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    public List<Teacher> findByFirstName(String name);
}
