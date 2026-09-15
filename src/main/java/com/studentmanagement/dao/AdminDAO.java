package com.studentmanagement.dao;

import com.studentmanagement.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    public boolean validateAdmin(
            String username,
            String password) {

        String sql =
                "SELECT * FROM admin_users " +
                "WHERE username = ? AND password = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                return resultSet.next();
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
}