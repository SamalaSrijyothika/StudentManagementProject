package com.studentmanagement.servlet;

import com.studentmanagement.service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() {

        studentService = new StudentService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int totalStudents =
                studentService.getTotalStudents();

        int totalCourses =
                studentService.getTotalCourses();

        int activeStudents =
                studentService.getActiveStudents();


        response.setContentType(
                "application/json"
        );

        response.setCharacterEncoding("UTF-8");


        PrintWriter out =
                response.getWriter();


        out.print("{");

        out.print(
                "\"totalStudents\":" +
                totalStudents +
                ","
        );

        out.print(
                "\"totalCourses\":" +
                totalCourses +
                ","
        );

        out.print(
                "\"activeStudents\":" +
                activeStudents
        );

        out.print("}");

        out.flush();
    }
}