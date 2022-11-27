package com.paysafe.DTOs;

public class CourseWithAverageGradeDTO {
    private double averageGrade;
    private String courseName;
    private int courseId;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseWithAverageGradeDTO(String courseName, int coursId) {
        this.courseId = coursId;
        this.courseName = courseName;
    }
}
