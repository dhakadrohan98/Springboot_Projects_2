package com.wissen.spring.data.jpa.repository;

import com.wissen.spring.data.jpa.entity.Guardian;
import com.wissen.spring.data.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("rohan@gmail.com")
                .firstName("Rohan")
                .lastName("Dhakad")
//                .guardianName("Ashok")
//                .guardianEmail("ashok@gmail.com")
//                .guardianMobile("9799777979")
                .build();

        this.studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .name("Nikhil")
                .email("nikhil@gmail.com")
                .mobile("9667756798")
                .build();

        Student student = Student.builder()
                .emailId("sourav@gmail.com")
                .firstName("Sourav")
                .lastName("Kumar")
                .guardian(guardian)
                .build();

        this.studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = this.studentRepository.findAll();
        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> students = this.studentRepository.findByFirstName("rohan");
        System.out.println(students);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students = this.studentRepository.findByFirstNameContaining("a");
        System.out.println(students);
    }

    @Test
    public void printStudentBasedOnGuardianName() {
        List<Student> students = this.studentRepository.findByGuardianName("Ashok");
        System.out.println(students);
    }

    @Test
    public void pribtStudentByEmailAddress() {
        Student students = this.studentRepository.getStudentByEmailAddress("rohan@gmail.com");
        System.out.println(students);
    }

    @Test
    public void printStudentFirstNameByEmailAddress() {
        String firstName = this.studentRepository.getStudentFirstNameByEmailAddress(
                "sanjeet@gmail.com");

        System.out.println("firstName = " + firstName);
    }

}