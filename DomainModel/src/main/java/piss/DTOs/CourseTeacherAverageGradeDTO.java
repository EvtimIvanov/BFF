package piss.DTOs;

import piss.entities.Course;

public class CourseTeacherAverageGradeDTO {
    private String courseName;
    private String teacherName;
    private double averageGrade;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }


    public CourseTeacherAverageGradeDTO(String courseName, String teacherName, double averageGrade) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.averageGrade = averageGrade;
    }

    public CourseTeacherAverageGradeDTO(Course course){
        this.averageGrade = course.getAverageGradeForAll();
        this.courseName = course.getName();
        if(course.getTeacherOfTheCourse()!=null){
        this.teacherName = course.getTeacherOfTheCourse().getName();
        }
        this.teacherName = "";

    }
}
