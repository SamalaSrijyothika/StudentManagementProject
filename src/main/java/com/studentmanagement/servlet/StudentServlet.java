package com.studentmanagement.servlet;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

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

        try {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );


            Student student =
                    studentService.searchStudentById(id);


            response.setContentType(
                    "application/json"
            );

            response.setCharacterEncoding("UTF-8");


            PrintWriter out =
                    response.getWriter();


            if (student == null) {

                response.setStatus(
                        HttpServletResponse.SC_NOT_FOUND
                );

                out.print(
                        "{\"error\":\"Student not found\"}"
                );

                return;
            }


            out.print("{");

            out.print("\"id\":" + student.id + ",");

            out.print(
                    "\"name\":\"" +
                    escapeJson(student.name) +
                    "\","
            );

            out.print(
                    "\"age\":" +
                    student.age +
                    ","
            );

            out.print(
                    "\"course\":\"" +
                    escapeJson(student.course) +
                    "\","
            );

            out.print(
                    "\"email\":\"" +
                    escapeJson(student.email) +
                    "\","
            );

            out.print(
                    "\"gender\":\"" +
                    escapeJson(student.gender) +
                    "\""
            );

            out.print("}");

            out.flush();

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST
            );

            response.getWriter().println(
                    "{\"error\":\"Invalid student ID\"}"
            );
        }
    }


    private String escapeJson(String value) {

        if (value == null) {

            return "";

        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}