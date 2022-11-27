package com.paysafe.entities;

import javax.persistence.*;

@Entity
@Table
public class Teacher extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Column
    private String email;


    public Teacher(String name, Degree degree) {
        super(name);

        this.degree = degree;

    }

    public Teacher() {

    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Degree getDegree() {
        return degree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
