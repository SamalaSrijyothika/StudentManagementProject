package com.studentmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Read database details from environment variables
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        // Check whether environment variables are configured
        if (URL == null || USER == null || PASSWORD == null) {
            throw new SQLException(
                "Database environment variables are not configured."
            );
        }

        // Load MySQL JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                "MySQL JDBC Driver not found!",
                e
            );
        }

        // Create and return database connection
        return DriverManager.getConnection(
            URL,
            USER,
            PASSWORD
        );
    }
}