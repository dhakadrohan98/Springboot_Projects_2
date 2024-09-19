package com.wissen.student.controller;

import com.wissen.student.entity.Student;
import com.wissen.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> fetchStudentList() {
        return this.studentService.getAllStudent();
    }

    @GetMapping("/student/{id}")
    public Student fetchStudentById(@PathVariable("id") Long studentId) {
        return this.studentService.getStudentById(studentId);
    }

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {
//        StringBuilder sb = new StringBuilder();
//        List<String> subjects = student.getSubjects();
//        for(String subject : subjects) {
//            sb.append(subject);
//        }
//        String subj = sb.toString();
        return this.studentService.saveStudent(student);
    }

    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable("id") Long studentId,
                                 @RequestBody Student student)
    {
        return this.studentService.updateStudent(studentId, student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") Long studentId) {
        this.studentService.deleteStudentById(studentId);
    }

}
