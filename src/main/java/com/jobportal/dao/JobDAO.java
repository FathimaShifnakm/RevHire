package com.jobportal.dao;

import com.jobportal.model.Job;

import java.util.List;

public interface JobDAO {
    void postJob(Job job);

    List<Job> searchJobsByRole(String jobRole);

    List<Job> getAllJobs();

    void deleteJob(int jobId);
}
