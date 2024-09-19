package com.wissen.student.entity;

import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Type;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String std;
    private String subjects;
    private String address;
}
