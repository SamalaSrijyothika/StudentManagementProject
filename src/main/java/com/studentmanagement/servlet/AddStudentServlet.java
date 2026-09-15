package com.studentmanagement.servlet;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() {
        studentService = new StudentService();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            String name =
                    request.getParameter("name");

            int age = Integer.parseInt(
                    request.getParameter("age")
            );

            String course =
                    request.getParameter("course");

            String email =
                    request.getParameter("email");

            String gender =
                    request.getParameter("gender");


            // Backend validation

            String validationError =
                    ValidationUtil.validateStudent(
                            id,
                            name,
                            age,
                            course,
                            email,
                            gender
                    );


            if (validationError != null) {

                response.setStatus(
                        HttpServletResponse.SC_BAD_REQUEST
                );

                response.getWriter().println(
                        validationError
                );

                return;
            }


            // Create Student object

            Student student =
                    new Student(
                            id,
                            name.trim(),
                            age,
                            course.trim(),
                            email.trim(),
                            gender
                    );


            // Add student

            boolean success =
                    studentService.addStudent(student);


            if (success) {

                response.sendRedirect(
                        "students.html"
                );

            } else {

                response.setStatus(
                        HttpServletResponse.SC_CONFLICT
                );

                response.getWriter().println(
                        "Student ID already exists."
                );
            }


        } catch (NumberFormatException e) {

            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST
            );

            response.getWriter().println(
                    "ID and age must be valid numbers."
            );


        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().println(
                    "Something went wrong while adding the student."
            );
        }
    }
}