package com.studentmanagement.servlet;

import com.studentmanagement.service.AdminService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AdminService adminService;

    @Override
    public void init() {

        adminService =
                new AdminService();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");


        if (adminService.login(
                username,
                password)) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "loggedIn",
                    true
            );

            session.setAttribute(
                    "username",
                    username
            );


            response.sendRedirect(
                    "dashboard.html"
            );

        } else {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            response.getWriter().println(
                    "Invalid username or password."
            );
        }
    }
}