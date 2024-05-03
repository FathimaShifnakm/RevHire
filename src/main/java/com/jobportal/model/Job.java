package com.jobportal.model;

public class Job {
    private int jobId;
    private int employerId;
    private String companyName;
    private String jobTitle;
    private String jobDescription;
    private String location;
    private double salary;
    private int experienceRequired;

    public Job(int jobId, int employerId, String companyName, String jobTitle, String jobDescription, String location, double salary, int experienceRequired) {
        this.jobId = jobId;
        this.employerId = employerId;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.location = location;
        this.salary = salary;
        this.experienceRequired = experienceRequired;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(int experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", employerId=" + employerId +
                ", companyName='" + companyName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                ", experienceRequired=" + experienceRequired +
                '}';
    }
}
