package com.example.demo.until;

import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.StudentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class Convert {
    private static final ModelMapper mapper = new ModelMapper();
    public StudentDTO convertStudentEntityToStudentDTO(StudentEntity studentEntity){
        return mapper.map(studentEntity, StudentDTO.class);
    }
    public StudentEntity convertStudentBodyToStudentEntity(StudentEntity studentEntity){
        return mapper.map(studentEntity, StudentEntity.class);
    }
    public CourseDTO convertCourseEntityToCourseDTO(CourseEntity courseEntity){
        return mapper.map(courseEntity, CourseDTO.class);
    }
}
