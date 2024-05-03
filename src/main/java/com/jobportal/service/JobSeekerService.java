package com.jobportal.service;

import com.jobportal.dao.JobSeekerDAO;
import com.jobportal.model.Employer;
import com.jobportal.model.Job;
import com.jobportal.model.JobSeeker;

import java.util.List;

public class JobSeekerService {
    private JobSeekerDAO jobSeekerDAO;

    public JobSeekerService(JobSeekerDAO jobSeekerDAO) {
        this.jobSeekerDAO = jobSeekerDAO;
    }

    public void registerJobSeeker(JobSeeker jobSeeker) {
        jobSeekerDAO.registerJobSeeker(jobSeeker);
    }

    public JobSeeker authenticateJobSeeker(String username, String password) {
        return jobSeekerDAO.authenticateJobSeeker(username, password);
    }
}
