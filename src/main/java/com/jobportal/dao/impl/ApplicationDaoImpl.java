package com.jobportal.dao.impl;

import com.jobportal.dao.ApplicatoinDAO;
import com.jobportal.model.Application;
import com.jobportal.util.DatabaseUtil;

import java.sql.*;

public class ApplicationDaoImpl implements ApplicatoinDAO {
    String dbUrl = "jdbc:sqlserver://localhost;databaseName=RevHireJobPortalDB;integratedSecurity=True;encrypt=True;TrustServerCertificate=True";

    @Override
    public void applyForJob(Application application) {
        String addApplicationSPQuery = "{call AddApplication(?, ?, ?)}";
        try (Connection conn = DatabaseUtil.getConnection(dbUrl);
             CallableStatement stmt = conn.prepareCall(addApplicationSPQuery)) {
            stmt.setInt(1, application.getJobId());
            stmt.setInt(2, application.getJobSeekerId());
            stmt.setString(3, application.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
