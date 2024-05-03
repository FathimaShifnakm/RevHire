package com.jobportal.model;

public class JobSeeker {
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String resume;

    public JobSeeker(String username, String password, String email, String fullName, String resume) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.resume = resume;
    }

    public JobSeeker() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
