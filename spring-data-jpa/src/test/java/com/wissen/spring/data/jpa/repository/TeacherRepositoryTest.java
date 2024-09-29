package com.wissen.spring.data.jpa.repository;

import com.wissen.spring.data.jpa.entity.Course;
import com.wissen.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course courseComputeMath = Course
                .builder()
                .title("Computational Mathematics")
                .credit(8)
                .build();

        Course courseAlgo = Course
                .builder()
                .title("Algorithmic thinking")
                .credit(10)
                .build();

        Course courseDBMS = Course
                .builder()
                .title("DBMS")
                .credit(6)
                .build();

        List<Course> list = new ArrayList<>();
        list.add(courseComputeMath);
        list.add(courseAlgo);
        list.add(courseDBMS);
        Teacher teacher = Teacher
                .builder()
                .firstName("Mazhar Iman")
                .lastName("Khan")
//                .courses(list)
                .build();
        this.teacherRepository.save(teacher);

    }
}