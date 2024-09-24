package com.wissen.spring.data.jpa.repository;

import com.wissen.spring.data.jpa.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByFirstName(String name);

    public List<Student> findByFirstNameContaining(String firstNane);

//    public List<Student> findByLastNameNotNull(String lastName);

    public List<Student> findByGuardianName(String guardianName);

    public List<Student> findByFirstNameAndLastName(String firstName,
                                                    String lastName);

    //JPQL1
    @Query("select s from Student s where s.emailId = ?1")
    public Student getStudentByEmailAddress(String emailId);

    //JPQL2
    @Query("select s.firstName from Student s where s.emailId = ?1")
    public String getStudentFirstNameByEmailAddress(String emailId);

    //Native query using param number
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = ?1",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressNative(String emailId);

    //Native query using One named param
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = :emailId",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressNativeNamedParam(
            @Param("emailId") String emailId);

    //Native query using Two named param
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = :emailId " +
                    "AND s.guardian_name = :guardianName",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressAndGuardianName(
            @Param("emailId") String emailId,
            @Param("guardianName") String guardianName);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where " +
                    "email_address= ?2",
            nativeQuery = true
    )
    public int updateStudentNameByEmailId(String firstName, String emailId);

    @Modifying
    @Transactional
    @Query(
            value = "delete from tbl_student where email_address = ?1",
            nativeQuery = true
    )
    public int deleteStudentByEmailId(String emailId);

}
