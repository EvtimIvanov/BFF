package com.paysafe.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Course extends BaseEntity implements Comparable<Course> {

    @Column
    private int totalHours;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Teacher teacherOfTheCourse;

    @ManyToMany(mappedBy = "studentCourses",cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("studentCourses")
    private List<Student> allStudents;


    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("studentGrades")
    private List<Grade> allGrades;


    public Course(String name, int totalHours) {
        super(name);
        this.totalHours = totalHours;
        allStudents = new ArrayList<>();
        allGrades = new ArrayList<>();

    }

    public Course() {
        allStudents = new ArrayList<>();
        allGrades = new ArrayList<>();

    }

    public List<Grade> getAllGrades() {
        return allGrades;
    }

    public void setTeacherOfTheCourse(Teacher teacherOfTheCourse) {
        this.teacherOfTheCourse = teacherOfTheCourse;
    }

    public Teacher getTeacherOfTheCourse() {
        return teacherOfTheCourse;
    }

    public void addStudentToTheCourse(Student studentToAdd) {
        for (Student student : allStudents) {
            if (studentToAdd.equals(student)) {
                throw new RuntimeException("student already assigned in course");
            }
        }

        this.allStudents.add(studentToAdd);


    }

    public void addGradeToStudent(Student student, Grade grade) {


        boolean checkIfInCourse = false;
        for (Student studentInCourse : allStudents) {
            if (studentInCourse.getId() == student.getId()) {
                checkIfInCourse = true;
                break;
            }
        }

        if (!checkIfInCourse) {
            throw new NotExistingStudentInCourseException();
        }
        allGrades.add(grade);

    }

    public List<Double> getStudentGrades(Student student) {
        List<Double> allStudentGrades = new ArrayList<>();

        for (Grade grade : allGrades) {
            if (grade.getStudent().equals(student)) {
                allStudentGrades.add(grade.getGrade());
            }
        }
        return allStudentGrades;
    }

    public Collection<Student> getAllStudents() {
        return allStudents;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public double getAverageGradeByStudent(Student student) {
        List<Double> grades = this.getStudentGrades(student);

        double average = 0;

        int sizeOfGrades = grades.size();
        for (int count = 0; count < sizeOfGrades; count++) {
            average += grades.get(count);
        }
        if (sizeOfGrades != 0) {
            average /= sizeOfGrades;
        }

        return average;
    }

    public double getAverageGradeForAll() {
        double averageOfCourse = 0;
        int countToDivide = 0;

        for (Student student : this.allStudents) {
            if (getAverageGradeByStudent(student) != 0) {
                averageOfCourse += getAverageGradeByStudent(student);
                countToDivide++;
            }
        }
        if(countToDivide!=0){
            averageOfCourse= averageOfCourse/countToDivide;
        }

        return averageOfCourse;
    }

    public String getAllStudentsSortedByAverageGrade() {

        List<Map.Entry<Student, Double>> sortedStudents = new ArrayList<>();

        for (Student student : this.allStudents) {
            Double averageGradeOfStudent = getAverageGradeByStudent(student);
            Map.Entry<Student, Double> studentWithGrade;
            studentWithGrade = new AbstractMap.SimpleEntry<>(student, averageGradeOfStudent);
            sortedStudents.add(studentWithGrade);
        }

        Collections.sort(sortedStudents, Map.Entry.comparingByValue());

        StringBuilder result = new StringBuilder();

        for (Map.Entry<Student, Double> el : sortedStudents) {
            result.append(el.getKey().getName() + " " + el.getValue() + "\n");
        }
        return result.toString();
    }

    @Override
    public int compareTo(Course o) {
        return this.getName().compareTo(o.getName());
    }

    public void removeTeacher() {
        this.teacherOfTheCourse = null;
    }
}
