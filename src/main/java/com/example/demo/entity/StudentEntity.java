package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dob;

    private int age;

    private String email;

    @JsonManagedReference(value = "student")
    @OneToMany(mappedBy = "studentEntity")
    private List<StudentCourseEntity> studentCourseEntities;

}
