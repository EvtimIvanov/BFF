package piss.DTOs;

import piss.entities.Course;
import piss.entities.Student;

import java.util.*;

public class CourseInfoDTO {


    private int id;
    private String courseName;
    private String teacherName;

    private List<StudentGradesDTO> studentsWithGrades;

    private double averageGradeOfCourse;

    public CourseInfoDTO(Course course) {
        this.averageGradeOfCourse = course.getAverageGradeForAll();
        this.courseName = course.getName();
        this.id = course.getId();
        studentsWithGrades = new ArrayList<>();
        fillStudentsGrades(course);
        if (course.getTeacherOfTheCourse() != null) {
            this.teacherName = course.getTeacherOfTheCourse().getName();
        } else {
            this.teacherName = "No teacher";
        }
    }

    public void fillStudentsGrades(Course course) {

        for (Student student : course.getAllStudents()) {
            List<Double> grades = course.getStudentGrades(student);
            StudentGradesDTO newStudent = new StudentGradesDTO(
                    student.getId(),
                    student.getName(),
                    grades
            );

            studentsWithGrades.add(newStudent);
        }

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String techerName) {
        this.teacherName = techerName;
    }


    public double getAverageGradeOfCourse() {
        return averageGradeOfCourse;
    }

    public void setAverageGradeOfCourse(double averageGradeOfCourse) {
        this.averageGradeOfCourse = averageGradeOfCourse;
    }

    public List<StudentGradesDTO> getStudentsWithGrades() {
        return studentsWithGrades;
    }

    public void setStudentsWithGrades(List<StudentGradesDTO> studentsWithGrades) {
        this.studentsWithGrades = studentsWithGrades;
    }
}
