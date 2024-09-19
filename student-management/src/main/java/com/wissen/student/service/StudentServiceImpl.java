package com.wissen.student.service;

import com.wissen.student.entity.Student;
import com.wissen.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long studentId) {
        return this.studentRepository.findById(studentId).get();
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        //get the required student object from db
        Student stud = this.studentRepository.findById(studentId).get();
        //update each & every details of stud from received
        // json object(student)
        stud.setName(student.getName());
        stud.setStd(student.getStd());
        stud.setSubjects(student.getSubjects());
        stud.setAddress(student.getAddress());
        return this.studentRepository.save(stud);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        this.studentRepository.deleteById(studentId);
    }
}
