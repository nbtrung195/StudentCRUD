package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_course")
public class StudentCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float point;

    private Float workshop1;

    private Float workshop2;

    private Float workshop3;

    private Float finalExam;

    @Column(name = "course_id")
    private Long courseId;

    @JsonBackReference(value = "course")
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, insertable = false, updatable = false)
    private CourseEntity courseEntity;

    @Column(name = "student_id")
    private Long studentId;

    @JsonBackReference(value = "student")
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, insertable = false, updatable = false)
    private StudentEntity studentEntity;
    // CourseId, studentId, point, workshop1,2,..., finalExam,
    // api all student tham gia courseId
    // api lay all course ma student co studentId tham gia
    // tinh diem trung binh tat ca hoc sinh tham gia courseId
}
