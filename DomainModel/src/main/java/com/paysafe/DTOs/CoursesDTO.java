package com.paysafe.DTOs;

import com.paysafe.entities.Course;

public class CoursesDTO {

    private int id;
    private String courseName;
    private int totalHours;

    public CoursesDTO(String name, int totalHours, int id) {
        this.id = id;
        this.courseName = name;
        this.totalHours = totalHours;
    }

    public CoursesDTO(Course course) {
        this.courseName = course.getName();
        this.totalHours = course.getTotalHours();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
