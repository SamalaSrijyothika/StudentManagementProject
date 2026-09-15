package com.studentmanagement.util;

public class ValidationUtil {

    public static String validateStudent(
            int id,
            String name,
            int age,
            String course,
            String email,
            String gender) {

        if (id <= 0) {
            return "Student ID must be greater than 0.";
        }

        if (name == null || name.trim().isEmpty()) {
            return "Name is required.";
        }

        if (name.trim().length() < 2) {
            return "Name must contain at least 2 characters.";
        }

        if (age < 16 || age > 100) {
            return "Age must be between 16 and 100.";
        }

        if (course == null || course.trim().isEmpty()) {
            return "Course is required.";
        }

        if (course.trim().length() < 2) {
            return "Course name must contain at least 2 characters.";
        }

        if (email == null || email.trim().isEmpty()) {
            return "Email is required.";
        }

        if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            return "Please enter a valid email address.";
        }

        if (gender == null || gender.trim().isEmpty()) {
            return "Gender is required.";
        }

        if (!gender.equals("Male")
                && !gender.equals("Female")
                && !gender.equals("Other")) {

            return "Invalid gender selected.";
        }

        return null;
    }
}