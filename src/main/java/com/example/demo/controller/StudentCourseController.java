package com.example.demo.controller;

import com.example.demo.entity.StudentCourseEntity;
import com.example.demo.service.StudentCourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/student-course")
public class StudentCourseController {
    final StudentCourseService studentCourseService;

    @PostMapping("/add")
    public ResponseEntity<String> addStudentToCourse(@RequestBody StudentCourseEntity studentCourseEntity){
        return ResponseEntity.ok(studentCourseService.addStudentToCourse(studentCourseEntity));
    }

    @GetMapping("/point/{courseId}")
    public ResponseEntity<String> averagePoint(@PathVariable("courseId") Long courseId){
        return ResponseEntity.ok(studentCourseService.calculateAveragePoint(courseId));
    }

    @GetMapping("/list-course-of-student/{id}")
    public ResponseEntity<Map<String, List>> getAllCourseByStudentId(@PathVariable("id") Long studentId){
        return ResponseEntity.ok(studentCourseService.getAllCourseByStudentId(studentId));
    }

    @GetMapping("/list-student-of-course/{id}")
    public ResponseEntity<Map<String, List>> getAllStudentByCourseId(@PathVariable("id") Long courseId){
        return ResponseEntity.ok(studentCourseService.getAllStudentByCourseId(courseId));
    }

}
