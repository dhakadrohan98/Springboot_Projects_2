package com.wissen.spring.data.jpa.repository;

import com.wissen.spring.data.jpa.entity.Course;
import com.wissen.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
                PageRequest.of(2, 2);

        List<Course> courses = this.courseRepository
                .findAll(secondPageWithTwoRecords)
                .getContent();

        Long totalElements = this.courseRepository
                .findAll(secondPageWithTwoRecords)
                .getTotalElements();

        int totalPages = this.courseRepository
                .findAll(secondPageWithTwoRecords)
                .getTotalPages();

        System.out.println("totalPages = " + totalPages);
        System.out.println("Total Elements " + totalElements);
        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(
                0,
                2,
                Sort.by("title")
        );

        Pageable sortByCreditDesc =
                PageRequest.of(
                        0,
                        6,
                        Sort.by("credit").descending()
                );

        Pageable sortByTitleAndCreditDesc = PageRequest.of(
                0,
                4,
                Sort.by("title")
                        .descending()
                        .and(Sort.by("credit"))
        );

        List<Course> courses = this.courseRepository.findAll(sortByTitleAndCreditDesc)
                .getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void printFindByTitleContainingSortedByTitleDescending() {
        Pageable firstPageThreeRecords =
                PageRequest.of(
                        0,
                        3,
                        Sort.by(Sort.Direction.DESC, "title") // Sort by title in descending order
                );

        //find three courses from first page containing d as title
        List<Course> courses = this.courseRepository
                .findByTitleContaining(
                        "D",
                        firstPageThreeRecords).getContent();

        System.out.println("courses = " + courses);
    }


}