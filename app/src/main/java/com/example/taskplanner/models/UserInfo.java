package com.example.taskplanner.models;

public class UserInfo {

    private String name,email,password;

    public UserInfo() {
    }

    public UserInfo(String name) {
        this.name = name;
    }

    public UserInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserInfo(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
