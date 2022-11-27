package piss.DTOs;

import piss.entities.Student;

public class StudentDTO {

    private int id;
    private String name;
    private int age;
    private String email;

    public StudentDTO(Student student) {
        this.name = student.getName();
        this.age = student.getAge();
        this.id = student.getId();
        this.email = student.getEmail();
    }

    public StudentDTO(String name, int age, int id, String email) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
