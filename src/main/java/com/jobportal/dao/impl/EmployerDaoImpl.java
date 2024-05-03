package com.jobportal.dao.impl;

import com.jobportal.dao.EmployerDAO;
import com.jobportal.model.Application;
import com.jobportal.model.Employer;
import com.jobportal.model.Job;
import com.jobportal.model.JobApplication;
import com.jobportal.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployerDaoImpl implements EmployerDAO {
    String dbUrl = "jdbc:sqlserver://localhost;databaseName=RevHireJobPortalDB;integratedSecurity=True;encrypt=True;TrustServerCertificate=True";

    @Override
    public void registerEmployer(Employer employer) {
        String addEmployerSPQuery = "{call AddEmployer(?, ?, ?, ?)}";

        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             CallableStatement stmt = conn.prepareCall(addEmployerSPQuery)) {

            stmt.setString(1, employer.getUsername());
            stmt.setString(2, employer.getPassword());
            stmt.setString(3, employer.getEmail());
            stmt.setString(4, employer.getCompanyName());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employer authenticateEmployer(String username, String password) {
        String query = "SELECT * FROM Employer WHERE Username = ? AND Password = ?";
        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Employer employer = new Employer();
                    employer.setEmployerId(rs.getInt("EmployerID"));
                    employer.setUsername(rs.getString("Username"));
                    employer.setPassword(rs.getString("Password"));
                    employer.setEmail(rs.getString("Email"));
                    employer.setCompanyName(rs.getString("CompanyName"));
                    return employer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Employer not found
    }

    @Override
    public List<Job> getUploadedJobs(int employerId) {
        List<Job> jobs = new ArrayList<>();
        String query = "{call GetUploadedJobs(?)}";
        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             CallableStatement stmt = conn.prepareCall(query)) {
            stmt.setInt(1, employerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Job job = new Job(
                            rs.getInt("JobID"),
                            rs.getInt("EmployerID"),
                            rs.getString("CompanyName"),
                            rs.getString("JobTitle"),
                            rs.getString("JobDescription"),
                            rs.getString("Location"),
                            rs.getDouble("Salary"),
                            rs.getInt("ExperienceRequired")
                    );
                    jobs.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public List<JobApplication> viewApplicationsForJob(int jobId) {
        List<JobApplication> applications = new ArrayList<>();
        String query = "{call GetApplicationsForJob(?) }";

        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             CallableStatement stmt = conn.prepareCall(query)) {
            stmt.setInt(1, jobId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    JobApplication application = new JobApplication();
                    application.setJobId(rs.getInt("Job ID"));
                    application.setJobTitle(rs.getString("Job Title"));
                    application.setFullName(rs.getString("Applicant Name"));
                    application.setEmail(rs.getString("Applicant Email"));
                    application.setResume(rs.getString(" Applicant Resume"));
                    applications.add(application);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }
}
