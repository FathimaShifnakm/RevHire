package com.jobportal.dao.impl;

import com.jobportal.dao.JobDAO;
import com.jobportal.model.Job;
import com.jobportal.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAOImpl implements JobDAO {
    String dbUrl = "jdbc:sqlserver://localhost;databaseName=RevHireJobPortalDB;integratedSecurity=True;encrypt=True;TrustServerCertificate=True";

    @Override
    public void postJob(Job job) {
        String storedProcedure = "{call AddJob(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             CallableStatement stmt = conn.prepareCall(storedProcedure)) {
            stmt.setInt(1, job.getEmployerId());
            stmt.setString(2, job.getCompanyName());
            stmt.setString(3, job.getJobTitle());
            stmt.setString(4, job.getJobDescription());
            stmt.setString(5, job.getLocation());
            stmt.setDouble(6, job.getSalary());
            stmt.setInt(7, job.getExperienceRequired());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Job> searchJobsByRole(String jobRole) {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM Job WHERE JobTitle = ?";
        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, jobRole);
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
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM Job";
        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public void deleteJob(int jobId) {
        String query = "{call DeleteJob(?)}";
        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             CallableStatement stmt = conn.prepareCall(query)) {
            stmt.setInt(1, jobId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // logging.
        }
    }
}
