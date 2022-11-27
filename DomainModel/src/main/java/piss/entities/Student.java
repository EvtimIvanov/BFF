package piss.entities;

import piss.DTOs.StudentDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Student extends BaseEntity implements Comparable<Student> {

    @Column
    private int age;

    @ManyToMany
    @JsonIgnoreProperties("allStudents")
    private List<Course> studentCourses;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"course", "student"})
    private List<Grade> studentGrades;

    public Student(String name, int age) {
        super(name);
        this.age = age;
        this.studentCourses = new ArrayList<>();
        this.studentGrades = new ArrayList<>();
    }

    public Student(StudentDTO studentDTO){
        super(studentDTO.getName());
        this.age=studentDTO.getAge();
        this.studentCourses = new ArrayList<>();
        this.studentGrades = new ArrayList<>();
    }

    public Student() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addCourse(Course course) {
        for (Course current: studentCourses) {
            if(current.getId() == course.getId()){
                throw new RuntimeException("student is already assigned");
            }
        }
        studentCourses.add(course);
    }

    public List<Course> getStudentCourses() {
        return this.studentCourses;
    }

    public List<Grade> getStudentGrades() {
        return studentGrades;
    }

    public void addGrade(Grade grade) {
        this.studentGrades.add(grade);
    }

    @Override
    public int compareTo(Student o) {
        Integer id1 = this.getId();
        Integer id2 = o.getId();
        return id1.compareTo(id2);
    }


    @Override
    public String toString() {
        return "Student{" +
                "if=" + this.getId() +
                "age=" + age +
                ", studentCourses=" + studentCourses +
                ", studentGrades=" + studentGrades +
                '}';
    }
}
