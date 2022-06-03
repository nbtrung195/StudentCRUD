package com.example.demo.service;

import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.StudentCourseEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentCourseRepository;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class StudentCourseService {
    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    public String addStudentToCourse(StudentCourseEntity studentCourseEntity){
        Optional<StudentEntity> student = studentRepository.findById(studentCourseEntity.getStudentId());
        Optional<CourseEntity> course = courseRepository.findById(studentCourseEntity.getCourseId());
        if (student.isPresent() && course.isPresent()){
            for (StudentCourseEntity c : course.get().getStudentCourseEntities()){
                if (Objects.equals(c.getStudentId(), student.get().getId())){
                    return "Student Has Existed";
                }
            }
            studentCourseEntity.setPoint((studentCourseEntity.getWorkshop1()*2 + studentCourseEntity.getWorkshop2()*2
                    + studentCourseEntity.getWorkshop3()*2 + studentCourseEntity.getFinalExam()*4) / 10);
            studentCourseRepository.save(studentCourseEntity);
            return "Add Successfully";
        }
        return "ID Not Found";
    }
    public String calculateAveragePoint(Long courseId){
        Optional<CourseEntity> course = courseRepository.findById(courseId);
        if (course.isPresent()){
            List<StudentCourseEntity> student = course.get().getStudentCourseEntities();
            float averagePoint = 0;
            for (StudentCourseEntity studentCourse : student){
                averagePoint += studentCourse.getPoint();
            }
            return "Average Point = " + averagePoint/student.size();
        }
        return "Course Not Found";
    }

    public Map<String, List> getAllCourseByStudentId(Long id){
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        if (studentEntity.isPresent()){
            List<String> studentCourseEntities = studentCourseRepository.getAllCourseByStudentId(id);
            Map<String, List> map = new HashMap<>();
            map.put(studentEntity.get().getFullName(), studentCourseEntities);
            return map;
        }
        return null;
    }

    public Map<String, List> getAllStudentByCourseId(Long id){
        Optional<CourseEntity> courseEntity = courseRepository.findById(id);
        if (courseEntity.isPresent()){
            List<String> list = studentCourseRepository.getAllStudentByCourseId(courseEntity.get().getId());
            Map<String, List> map = new HashMap<>();
            map.put(courseEntity.get().getName(), list);
            return map;
        }
        return null;
    }

}