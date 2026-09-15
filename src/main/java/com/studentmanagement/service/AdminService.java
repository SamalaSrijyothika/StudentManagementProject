package com.studentmanagement.service;

import com.studentmanagement.dao.AdminDAO;

public class AdminService {

    private final AdminDAO adminDAO;

    public AdminService() {
        adminDAO = new AdminDAO();
    }

    public boolean login(
            String username,
            String password) {

        if (username == null ||
                username.trim().isEmpty()) {

            return false;
        }

        if (password == null ||
                password.trim().isEmpty()) {

            return false;
        }

        return adminDAO.validateAdmin(
                username.trim(),
                password
        );
    }
}