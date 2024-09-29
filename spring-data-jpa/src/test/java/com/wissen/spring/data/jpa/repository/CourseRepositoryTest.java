package com.wissen.spring.data.jpa.repository;

import com.wissen.spring.data.jpa.entity.Course;
import com.wissen.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void printCourses() {
        List<Course> courses = this.courseRepository.findAll();
        System.out.println(courses);
    }

    // A teacher will be created, a course will be created taught by newly created
    // teacher.
    @Test
    public void saveCourseWithTeacher() {

        Teacher teacher = Teacher
                .builder()
                .firstName("Lakshmi")
                .lastName("Singh")
                .build();

        //Wrong code don't refere
        //fetch first teacher from Lakshmi as first name
//        List<Teacher> teacherList = this.teacherRepository.findByFirstName("lakshmi");
//        Teacher teacher = teacherList.get(0);
//        System.out.println(teacher);

        Course course = Course
                .builder()
                .title("Springboot")
                .credit(12)
                .teacher(teacher)
                .build();

        this.courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1,2);

        List<Course> courses = this.courseRepository
                .findAll(firstPageWithThreeRecords)
                .getContent();

        Long totalElements = this.courseRepository
                .findAll(firstPageWithThreeRecords)
                        .getTotalElements();

        int totalPages = this.courseRepository
                .findAll(firstPageWithThreeRecords)
                        .getTotalPages();

        System.out.println("totalPages = " + totalPages);
        System.out.println("Total Elements " + totalElements);
        System.out.println("courses = " + courses);

    }
}