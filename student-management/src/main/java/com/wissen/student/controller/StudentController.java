package com.wissen.student.controller;

import com.wissen.student.entity.Student;
import com.wissen.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> fetchStudentList() {
        List<Student> allStudent = this.studentService.getAllStudent();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> fetchStudentById(@PathVariable("id") Long studentId) {
        Student student = this.studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStud = this.studentService.saveStudent(student);
        return new ResponseEntity<>(savedStud, HttpStatus.CREATED);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long studentId,
                                 @RequestBody Student student)
    {
        Student updateStud = this.studentService.updateStudent(studentId, student);
        return new ResponseEntity<>(updateStud, HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long studentId) {
        this.studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
