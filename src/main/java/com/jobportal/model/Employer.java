package com.jobportal.model;


public class Employer {
    private int employerId;
    private String username;
    private String password;
    private String email;
    private String companyName;

    public Employer(String username, String password, String email, String companyName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.companyName = companyName;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public Employer() {

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
