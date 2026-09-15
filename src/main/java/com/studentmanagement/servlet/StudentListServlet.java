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
import java.util.List;

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {

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

        List<Student> students =
                studentService.getAllStudents();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.print("[");

        for (int i = 0; i < students.size(); i++) {

            Student student = students.get(i);

            out.print("{");

            out.print("\"id\":" + student.id + ",");
            out.print("\"name\":\"" + student.name + "\",");
            out.print("\"age\":" + student.age + ",");
            out.print("\"course\":\"" + student.course + "\",");
            out.print("\"email\":\"" + student.email + "\",");
            out.print("\"gender\":\"" + student.gender + "\"");

            out.print("}");

            if (i < students.size() - 1) {
                out.print(",");
            }
        }

        out.print("]");

        out.flush();
    }
}