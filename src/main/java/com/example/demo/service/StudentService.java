package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.until.Convert;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.repository.StudentRepository;
import java.util.ArrayList;
import java.util.Optional;
@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final Convert convert;
    public ArrayList<StudentDTO> getAll(int pageCurrent, int size){
        Page<StudentEntity> students = studentRepository.findAll(PageRequest.of(pageCurrent - 1,size, Sort.by("id").descending()));
        ArrayList<StudentDTO> response = new ArrayList<>();
        for (StudentEntity student : students){
            StudentDTO studentResponse = convert.convertStudentEntityToStudentDTO(student);
            response.add(studentResponse);
        }
        return response;
    }
    public String deleteItem(Long id){
        Optional<StudentEntity> student = studentRepository.findById(id);
        if (student.isPresent()){
            studentRepository.deleteById(id);
            return "Delete Student Successfully";
        }
        return "ID not found";
    }
    public String addItem(StudentEntity student){
        studentRepository.save(student);
        return "Add Student Successfully";
    }
    public StudentDTO getItem(Long id){
        Optional<StudentEntity> student = studentRepository.findById(id);
        if (student.isPresent()){
            return convert.convertStudentEntityToStudentDTO(student.get());
        }
        return null;
    }
    public String updateItem(Long id, StudentEntity student){
        Optional<StudentEntity> myStudent = studentRepository.findById(id);
        if (myStudent.isPresent()){
            StudentEntity studentEntity = convert.convertStudentBodyToStudentEntity(student);
            studentRepository.save(studentEntity);
            return "Update Successfully";
        }
        return "ID not found";
    }
}
