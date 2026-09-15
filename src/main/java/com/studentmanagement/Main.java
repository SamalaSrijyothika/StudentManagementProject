package com.studentmanagement;
import java.util.Scanner;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("========================================");

            int choice = readPositiveInteger(
                    scanner,
                    "Enter your choice: "
            );

            // ADD STUDENT
            if (choice == 1) {

                System.out.println();
                System.out.println("========== ADD STUDENT ==========");

                int id = readPositiveInteger(
                        scanner,
                        "Enter Student ID: "
                );

                String name = readNonEmptyString(
                        scanner,
                        "Enter Name: "
                );

                int age = readAge(scanner);

                String course = readNonEmptyString(
                        scanner,
                        "Enter Course: "
                );

                String email = readEmail(scanner);

                String gender = readGender(scanner);

                Student student = new Student(
                        id,
                        name,
                        age,
                        course,
                        email,
                        gender
                );

                service.addStudent(student);
            }

            // VIEW STUDENTS
            else if (choice == 2) {

                service.viewStudents();
            }

            // SEARCH STUDENT
            else if (choice == 3) {

                System.out.println();
                System.out.println("========== SEARCH STUDENT ==========");

                int searchId = readPositiveInteger(
                        scanner,
                        "Enter Student ID to search: "
                );

                service.searchStudent(searchId);
            }

            // UPDATE STUDENT
            else if (choice == 4) {

                System.out.println();
                System.out.println("========== UPDATE STUDENT ==========");

                int updateId = readPositiveInteger(
                        scanner,
                        "Enter Student ID to update: "
                );

                String name = readNonEmptyString(
                        scanner,
                        "Enter New Name: "
                );

                int age = readAge(scanner);

                String course = readNonEmptyString(
                        scanner,
                        "Enter New Course: "
                );

                String email = readEmail(scanner);

                String gender = readGender(scanner);

                service.updateStudent(
                        updateId,
                        name,
                        age,
                        course,
                        email,
                        gender
                );
            }

            // DELETE STUDENT
            else if (choice == 5) {

    System.out.println();
    System.out.println("========== DELETE STUDENT ==========");

    int deleteId = readPositiveInteger(
            scanner,
            "Enter Student ID to delete: "
    );

    System.out.print(
            "Are you sure you want to delete this student? (yes/no): "
    );

    String confirmation = scanner.nextLine();

    if (confirmation.equalsIgnoreCase("yes")) {

        service.deleteStudent(deleteId);

    } else {

        System.out.println("Delete operation cancelled.");
    }
}

            // EXIT
            else if (choice == 6) {

                System.out.println();
                System.out.println("Thank you for using Student Management System.");
                break;
            }

            else {

                System.out.println("Invalid choice. Please enter 1-6.");
            }
        }

        scanner.close();
    }


    // ==========================================
    // READ POSITIVE INTEGER
    // ==========================================

    public static int readPositiveInteger(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {

                int number = Integer.parseInt(input);

                if (number > 0) {
                    return number;
                }

                System.out.println(
                        "Please enter a number greater than 0."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }


    // ==========================================
    // READ AGE
    // ==========================================

    public static int readAge(Scanner scanner) {

        while (true) {

            int age = readPositiveInteger(
                    scanner,
                    "Enter Age: "
            );

            if (age >= 5 && age <= 100) {
                return age;
            }

            System.out.println(
                    "Age must be between 5 and 100."
            );
        }
    }


    // ==========================================
    // READ NON-EMPTY STRING
    // ==========================================

    public static String readNonEmptyString(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "This field cannot be empty."
            );
        }
    }


    // ==========================================
    // READ EMAIL
    // ==========================================

    public static String readEmail(Scanner scanner) {

        while (true) {

            System.out.print("Enter Email: ");

            String email = scanner.nextLine().trim();

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                return email;
            }

            System.out.println(
                    "Invalid email. Example: student@gmail.com"
            );
        }
    }


    // ==========================================
    // READ GENDER
    // ==========================================

    public static String readGender(Scanner scanner) {

        while (true) {

            System.out.println("Select Gender:");
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.println("3. Other");

            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                return "Male";
            }

            if (choice.equals("2")) {
                return "Female";
            }

            if (choice.equals("3")) {
                return "Other";
            }

            System.out.println(
                    "Invalid choice. Please select 1, 2, or 3."
            );
        }
    }
}