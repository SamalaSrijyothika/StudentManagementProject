package com.studentmanagement.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/dashboard.html",
        "/students.html",
        "/add-student.html",
        "/edit-student.html",
        "/students",
        "/student",
        "/addStudent",
        "/updateStudent",
        "/deleteStudent",
        "/dashboard"
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        HttpSession session =
                httpRequest.getSession(false);

        boolean loggedIn =
                session != null &&
                Boolean.TRUE.equals(
                        session.getAttribute("loggedIn")
                );

        if (loggedIn) {

            chain.doFilter(
                    request,
                    response
            );

        } else {

            httpResponse.sendRedirect(
                    "index.html"
            );
        }
    }
}