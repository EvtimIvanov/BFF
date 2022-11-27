package com.paysafe.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
public class Grade {


    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private int id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"studentCourses", "studentGrades"})
    private Student student;


    @ManyToOne
    @JsonIgnoreProperties({"allStudents", "allGrades"})
    private Course course;

    @Column
    private Double grade;

    public Grade(Student student, Course course, Double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Grade() {

    }

    public Double getGrade() {
        return grade;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}
