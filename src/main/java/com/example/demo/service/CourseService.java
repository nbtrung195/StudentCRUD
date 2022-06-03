package com.example.demo.service;

import com.example.demo.dto.CourseDTO;
import com.example.demo.entity.CourseEntity;
import com.example.demo.repository.CourseRepository;
import com.example.demo.until.Convert;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
@AllArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper mapper = new ModelMapper();
    private final Convert convert;
    public List<CourseDTO> getAll(int pageCurrent, int size){
        Page<CourseEntity> courseEntity = courseRepository.findAll(PageRequest.of(pageCurrent - 1, size, Sort.by("id").descending()));
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (CourseEntity courseEntity1 : courseEntity){
            CourseDTO courseDTO1 = convert.convertCourseEntityToCourseDTO(courseEntity1);
            courseDTOS.add(courseDTO1);
        }
        return courseDTOS;
    }
    public String addItem(CourseEntity courseEntity){
        CourseEntity course = CourseEntity.builder()
                .name(courseEntity.getName())
                .code(courseEntity.getCode())
                .description(courseEntity.getDescription())
                .build();
        courseRepository.save(course);
        return "Add Course Successfully";
    }
    public String updateItem(Long id, CourseEntity entity){
        Optional<CourseEntity> courseEntity = courseRepository.findById(id);
        if (courseEntity.isPresent()){
            CourseEntity course = mapper.map(entity, CourseEntity.class);
            courseRepository.save(course);
            return "Update Successfully";
        }
        return "ID Not Found";
    }
    public String deleteItem(Long id){
        Optional<CourseEntity> courseEntity = courseRepository.findById(id);
        if (courseEntity.isPresent()){
            courseRepository.deleteById(id);
            return "Delete Successfully";
        }
        return "ID Not Found";
    }
    public CourseDTO getItem(Long id){
        Optional<CourseEntity> courseEntity = courseRepository.findById(id);
        if (courseEntity.isPresent()){
            CourseDTO courseDTO = convert.convertCourseEntityToCourseDTO(courseEntity.get());
            return courseDTO;
        }
        return null;
    }

}
