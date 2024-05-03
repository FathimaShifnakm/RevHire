package com.jobportal.service;

import com.jobportal.dao.EmployerDAO;
import com.jobportal.dao.impl.EmployerDaoImpl;
import com.jobportal.model.Application;
import com.jobportal.model.Employer;
import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.util.DatabaseUtil;

import java.sql.*;
import java.util.List;

public class EmployerService {
    private EmployerDAO employerDAO;

    public EmployerService(EmployerDAO employerDAO) {
        this.employerDAO = employerDAO;
    }

    public void registerEmployer(Employer employer) {
        employerDAO.registerEmployer(employer);
    }

    public Employer authenticateEmployer(String username, String password) {
        return employerDAO.authenticateEmployer(username, password);
    }

    public List<Job> getUploadedJobs(int employerId) {
        return employerDAO.getUploadedJobs(employerId);
    }

    public List<JobApplication> viewApplicationsForJob(int jobId) {
        return employerDAO.viewApplicationsForJob(jobId);
    }
}
