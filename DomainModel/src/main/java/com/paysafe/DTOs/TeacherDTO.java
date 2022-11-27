package com.paysafe.DTOs;

import com.paysafe.entities.Teacher;

public class TeacherDTO {
    private String name;
    private String degree;
    private String email;

    public TeacherDTO(String name, String degree,String email) {
        this.name = name;
        this.degree = degree;
        this.email = email;
    }
    public TeacherDTO(Teacher teacher) {
        this.name = teacher.getName();
        this.degree = teacher.getDegree().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
