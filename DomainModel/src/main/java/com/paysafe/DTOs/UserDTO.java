package com.paysafe.DTOs;

import com.paysafe.entities.Users;
import org.h2.engine.User;

public class UserDTO {
    private int id;
    private String email;
    private String role;
    private String userName;

    public UserDTO(int id, String email, String role, String userName) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.userName = userName;
    }
    public UserDTO(Users user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getUserRole().getName();
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
