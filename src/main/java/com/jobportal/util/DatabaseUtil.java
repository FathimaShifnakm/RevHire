package com.jobportal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    // Method to establish a database connection
    public static Connection getConnection(String url) throws SQLException {
        try {
            // Load the driver class
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Obtain a connection and return it
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error connecting to the database: " + e.getMessage());
        }
    }

    // Method to close a database connection
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }
}
