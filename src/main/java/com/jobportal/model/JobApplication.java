package com.jobportal.model;

public class JobApplication {
    private int jobId;
    private String jobTitle;
    private String fullName;
    private String email;
    private String resume;

    public JobApplication() {
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "jobId=" + jobId +
                ", job Title='" + jobTitle + '\'' +
                ", Applicant Name='" + fullName + '\'' +
                ", Applicant email='" + email + '\'' +
                ", Applicant resume='" + resume + '\'' +
                '}';
    }
}
