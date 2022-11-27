package piss.DTOs;

import java.util.List;

public class StudentGradesDTO {
    private int id;
    private String name;
    private List<Double> grades;

    public StudentGradesDTO(int id, String name, List<Double> grades) {
        this.id = id;
        this.name = name;
        this.grades = grades;
    }

    public StudentGradesDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }
}
