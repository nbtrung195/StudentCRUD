package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent(@RequestParam("pageCurrent") int pageCurrent, @RequestParam("size") int size){
        return ResponseEntity.ok(studentService.getAll(pageCurrent, size));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.deleteItem(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody StudentEntity student){
        return ResponseEntity.ok(studentService.addItem(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.getItem(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") Long id, @RequestBody StudentEntity student){
        return ResponseEntity.ok(studentService.updateItem(id,student));
    }
}
