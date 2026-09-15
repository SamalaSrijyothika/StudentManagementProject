package com.studentmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            System.getenv("DB_URL");

    private static final String USER =
            System.getenv("DB_USER");

    private static final String PASSWORD =
            System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found!", e);
        }

        if (URL == null || USER == null || PASSWORD == null) {
            throw new SQLException("Database environment variables are not configured.");
        }

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}