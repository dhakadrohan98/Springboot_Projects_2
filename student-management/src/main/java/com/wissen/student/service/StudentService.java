package com.wissen.student.service;

import com.wissen.student.entity.Student;

import java.util.List;

public interface StudentService {
    //save a single student
    public Student saveStudent(Student student);
    //fetch all students data
    public List<Student> getAllStudent();
    //fetch student details by id
    public Student getStudentById(Long studentId);
    //update the student by id
    public Student updateStudent(Long studentId, Student student);
    //delete the student by id
    public void deleteStudentById(Long studentId);

}
