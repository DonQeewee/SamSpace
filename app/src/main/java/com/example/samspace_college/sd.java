package com.example.samspace_college;

public class sd {

    String name;
    String email;
    String grade;
    int image;

    public sd(String name, String email, String grade, int image) {
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
