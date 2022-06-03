package com.example.demo.controller;

import com.example.demo.dto.CourseDTO;
import com.example.demo.entity.CourseEntity;
import com.example.demo.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/api/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourse(@RequestParam("pageCurrent") int pageCurrent, @RequestParam("size") int size){
        return ResponseEntity.ok(courseService.getAll(pageCurrent, size));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody CourseEntity courseEntity){
        return ResponseEntity.ok(courseService.addItem(courseEntity));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable("id") Long id, @RequestBody CourseEntity courseEntity){
        return ResponseEntity.ok(courseService.updateItem(id, courseEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long id){
        return ResponseEntity.ok(courseService.deleteItem(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getAllStudentByIdCourse(@PathVariable("id") Long id){
        return ResponseEntity.ok(courseService.getItem(id));
    }

}
