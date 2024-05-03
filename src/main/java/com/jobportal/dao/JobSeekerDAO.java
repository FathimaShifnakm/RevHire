package com.jobportal.dao;

import com.jobportal.model.JobSeeker;

public interface JobSeekerDAO {
    void registerJobSeeker(JobSeeker jobSeeker);
    JobSeeker authenticateJobSeeker(String username, String password);
}
