package com.jobportal.dao.impl;

import com.jobportal.dao.JobSeekerDAO;
import com.jobportal.model.Employer;
import com.jobportal.model.JobSeeker;
import com.jobportal.util.DatabaseUtil;

import java.sql.*;

public class JobSeekerDaoImp implements JobSeekerDAO {

    String dbUrl = "jdbc:sqlserver://localhost;databaseName=RevHireJobPortalDB;integratedSecurity=True;encrypt=True;TrustServerCertificate=True";

    @Override
    public void registerJobSeeker(JobSeeker jobSeeker) {
        String registerJobSeekerSPQuery = "{call RegisterJobSeeker(?, ?, ?, ?, ?)}";

        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             CallableStatement stmt = conn.prepareCall(registerJobSeekerSPQuery)) {

            stmt.setString(1, jobSeeker.getUsername());
            stmt.setString(2, jobSeeker.getPassword());
            stmt.setString(3, jobSeeker.getEmail());
            stmt.setString(4, jobSeeker.getFullName());
            stmt.setString(5, jobSeeker.getResume());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JobSeeker authenticateJobSeeker(String username, String password) {

        String query = "SELECT * FROM JobSeeker WHERE Username = ? AND Password = ?";
        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    JobSeeker jobSeeker = new JobSeeker();
                    jobSeeker.setId(rs.getInt("job_seeker_id"));
                    jobSeeker.setUsername(rs.getString("Username"));
                    jobSeeker.setPassword(rs.getString("Password"));
                    jobSeeker.setEmail(rs.getString("email"));
                    jobSeeker.setFullName(rs.getString("full_name"));
                    jobSeeker.setResume(rs.getString("resume"));
                    return jobSeeker;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Employer not found
    }
}
