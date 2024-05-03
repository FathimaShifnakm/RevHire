package com.jobportal.dao;

import com.jobportal.model.Application;
import com.jobportal.model.Employer;
import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;

import java.util.List;

public interface EmployerDAO {
    void registerEmployer(Employer employer);
    Employer authenticateEmployer(String username, String password);

    List<Job> getUploadedJobs(int employerId);

    List<JobApplication> viewApplicationsForJob(int jobId);
}
