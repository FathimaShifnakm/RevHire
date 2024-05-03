package com.jobportal.service;

import com.jobportal.dao.JobDAO;
import com.jobportal.model.Job;

import java.util.List;

public class JobService {
    private JobDAO jobDAO;

    public JobService(JobDAO jobDAO) {
        this.jobDAO = jobDAO;
    }

    public void postJob(Job job) {
        jobDAO.postJob(job);
    }

    public List<Job> searchJobsByRole(String jobRole) {
        return jobDAO.searchJobsByRole(jobRole);
    }

    public List<Job> getAllJobs() {
        return jobDAO.getAllJobs();
    }

    public void deleteJob(int jobId) {
        jobDAO.deleteJob(jobId);
    }
}
