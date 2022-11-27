package com.paysafe.entities;

public enum UserRole {
    Student("ROLE_Student"),
    Teacher("ROLE_Teacher"),
    Admin("ROLE_Admin");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
