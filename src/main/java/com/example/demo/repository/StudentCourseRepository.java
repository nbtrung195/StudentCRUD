package com.example.demo.repository;

import com.example.demo.entity.StudentCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, Long> {
    @Query(value =  "SELECT c.name FROM restapi.student_course AS sc " +
                    "INNER JOIN restapi.course AS c ON sc.course_id = c.id " +
                    "WHERE sc.student_id = ?1", nativeQuery = true)
    List<String> getAllCourseByStudentId(Long id);

    @Query(value = "SELECT s.full_name FROM restapi.student_course AS sc " +
            "INNER JOIN restapi.student AS s ON sc.student_id = s.id " +
            "WHERE sc.course_id = ?1", nativeQuery = true)
    List<String> getAllStudentByCourseId(Long id);
}
