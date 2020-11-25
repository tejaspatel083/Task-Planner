package com.example.taskplanner;

public class UserInfo {

    String name,email;

    public UserInfo() {
    }

    public UserInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserInfo(String name) {
        this.name = name;
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
}
