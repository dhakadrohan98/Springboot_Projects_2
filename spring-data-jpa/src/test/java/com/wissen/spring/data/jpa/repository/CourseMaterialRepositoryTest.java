package com.wissen.spring.data.jpa.repository;

import com.wissen.spring.data.jpa.entity.Course;
import com.wissen.spring.data.jpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void SaveCourseMaterial1() {

        Course course = Course.builder()
                .title("Data structures & algorithms")
                .credit(5)
                .build();
        this.courseRepository.save(course);

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();
        this.courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void SaveCourseMaterial2() {

        Course course = Course.builder()
                .title("Operating system")
                .credit(5)
                .build();
        this.courseRepository.save(course);

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("https://www.freecodecamp.org/news/learn-about-operating-systems-in-depth/")
                .course(course)
                .build();
        this.courseMaterialRepository.save(courseMaterial);
    }

    //Cascading is applied here to insert the record in Course table(dependent entity)
    // first & then in CourseMaterial table
    @Test
    public void SaveCourseMaterial() {

        Course course = Course
                .builder()
                .title("Core java")
                .credit(10)
                .build();

        CourseMaterial courseMaterial = CourseMaterial
                .builder()
                .url("https://www.javatpoint.com/java-tutorial")
                .course(course)
                .build();
        this.courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printAllCourse() {
        List<CourseMaterial> courseMaterials =
                this.courseMaterialRepository.findAll();

        System.out.println(courseMaterials);
    }
}