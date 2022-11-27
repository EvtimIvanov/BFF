package com.paysafe.controller;

import com.paysafe.DTOs.*;
import com.paysafe.entities.Course;
import com.paysafe.entities.Teacher;
import com.paysafe.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/courses")
public class CourseController {


    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<CoursesDTO> createCourse(@RequestBody CoursesDTO coursesDTO) {
        this.courseService.create(coursesDTO);
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_Admin')")
    List<CourseTeacherAverageGradeDTO> all() {
        return this.courseService.getAllCourses();
    }


    @PostMapping("/course/{courseId}/addTeacher/{teacherId}")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<CourseTeacherDTO> addTeacherToCourse
            (@PathVariable int teacherId, @PathVariable int courseId) {


        return new ResponseEntity<>(
                courseService.addTeacherToCourseDTO(teacherId, courseId), HttpStatus.OK);
    }

    @PostMapping("/{courseId}/removeTeacher")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<CourseTeacherDTO> removeTeacherFromCourse
            (@PathVariable int courseId) {


        return new ResponseEntity<>(
                courseService.removeTeacher(courseId), HttpStatus.OK);
    }


    @PostMapping("/{courseId}/addStudent/{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_Student','ROLE_Teacher','ROLE_Admin')")
    public ResponseEntity<AddStudentDTO> addStudentToCourse(
            @PathVariable int courseId, @PathVariable int studentId) {
        AddStudentDTO addStudentDTO = new AddStudentDTO(courseId,studentId);
        courseService.addStudentToCourseByCourseId(
                addStudentDTO.getStudentId(),
                addStudentDTO.getCourseId());
        return new ResponseEntity<>(addStudentDTO, HttpStatus.OK);
    }

    @PostMapping("{courseId}/student/{studentId}/addGrade/{grade}")
    @PreAuthorize("hasAnyRole('ROLE_Student','ROLE_Teacher','ROLE_Admin')")
    public ResponseEntity<StudentGradeCourseDTO> addGradeToStudent(@PathVariable int studentId,
                                                    @PathVariable double grade,
                                                    @PathVariable int courseId) {

        courseService.addGradeToStudentInSpecificCourse(studentId, grade, courseId);
        StudentGradeCourseDTO studentGradeCourseDTO = new StudentGradeCourseDTO(
                studentId,courseId,grade
        );

        return new ResponseEntity<>(studentGradeCourseDTO, HttpStatus.OK);
    }

    @GetMapping("/showStudentGroupedByCourse")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<String> showStudentGroupedByCourse() {

        return new ResponseEntity<>(courseService.showStudentsGroupedByCourseAlphabeticallyThenByGrade(), HttpStatus.OK);
    }

    @GetMapping("/showAllCoursesStudentsAndTeachers")
    @PreAuthorize("hasAnyRole('ROLE_Student','ROLE_Teacher','ROLE_Admin')")
    public ResponseEntity<String> showAllCoursesStudentsAndTeachers() {

        return new ResponseEntity<>(courseService.showAllCoursesStudentsAndTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{courseId}/showAverageGradeForCourse")
    @PreAuthorize("hasAnyRole('ROLE_Student','ROLE_Teacher','ROLE_Admin')")
    public ResponseEntity<Double> showAverageGradeForCourse(@RequestParam int courseId) {

        return new ResponseEntity<>(courseService.averageGradeForSpecificCourse(courseId), HttpStatus.OK);
    }

    @GetMapping("/{studentId}/studentAverageGrade")
    @PreAuthorize("hasAnyRole('ROLE_Student','ROLE_Teacher','ROLE_Admin')")
    public ResponseEntity<Double> showStudentAverageGrade(@PathVariable int studentId) {

        return new ResponseEntity<>(courseService.averageGradeForStudent(studentId), HttpStatus.OK);
    }

    @GetMapping("/showCoursesWithTeachers")
    @PreAuthorize("hasAnyRole('ROLE_Student','ROLE_Teacher','ROLE_Admin')")
    public List<CourseTeacherDTO> getTeachersWithCourses() {
        return this.courseService.getAllCoursesWithTeachers();
    }


    @GetMapping("/teacher/{teacherEmail}/coursesWithAverageGrade")
    @PreAuthorize("hasAnyRole('ROLE_Teacher')")
    public List<CourseWithAverageGradeDTO> getTeacherCoursesWithAverageGrade(
            @PathVariable String teacherEmail) {
        return this.courseService.getTeacherCoursesWithAverageGrade(teacherEmail);
    }

    @GetMapping("/{courseName}")
    @PreAuthorize("hasAnyRole('ROLE_Student','ROLE_Teacher','ROLE_Admin')")
    public CourseInfoDTO showCourseInfo(@PathVariable String courseName) {
        Course course = this.courseService.getCourseByName(courseName);

        return new CourseInfoDTO(course);

    }


}
