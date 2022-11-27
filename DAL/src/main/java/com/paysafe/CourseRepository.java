package com.paysafe;

import com.paysafe.DTOs.CourseWithAverageGradeDTO;
import com.paysafe.entities.Course;
import com.paysafe.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    @Query("select NEW com.paysafe.DTOs.CourseWithAverageGradeDTO(course.name,course.id)" +
            " from Course course" +
            " where course.teacherOfTheCourse.id = :idOfTeacher")
    List<CourseWithAverageGradeDTO> getAllTeacherCourse(@Param("idOfTeacher") Integer teacherId);


    @Query("select course from Course course where course.name = :courseName")
    Optional<Course> getCourseByName(@Param("courseName") String courseName);

}
