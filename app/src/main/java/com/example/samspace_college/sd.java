package com.example.samspace_college;

public class sd {

    String name;
    String email;
    String role;
    String grade;


    public sd(String name, String email, String grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
