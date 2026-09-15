package com.studentmanagement.service;
import java.util.List;
import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.model.Student;
public class StudentService {

    private StudentDAO studentDAO;

    public StudentService() {
        studentDAO = new StudentDAO();
    }


    // ==========================================
    // ADD STUDENT
    // ==========================================

    public boolean addStudent(Student student) {

    if (studentDAO.existsById(student.id)) {
        return false;
    }

    boolean success = studentDAO.addStudent(student);

    return success;
}


    // ==========================================
    // VIEW STUDENTS
    // ==========================================

    public void viewStudents() {

        List<Student> students =
                studentDAO.getAllStudents();

        if (students.isEmpty()) {

            System.out.println("No students found.");

            return;
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("                 STUDENT LIST");
        System.out.println("==================================================");

        for (Student student : students) {

            System.out.println(
                    "ID     : " + student.id
            );

            System.out.println(
                    "Name   : " + student.name
            );

            System.out.println(
                    "Age    : " + student.age
            );

            System.out.println(
                    "Course : " + student.course
            );

            System.out.println(
                    "Email  : " + student.email
            );

            System.out.println(
                    "Gender : " + student.gender
            );

            System.out.println("------------------------------------------");
        }
    }


    // ==========================================
    // SEARCH STUDENT
    // ==========================================

    public void searchStudent(int id) {

        Student student =
                studentDAO.getStudentById(id);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }

        System.out.println();
        System.out.println("========== STUDENT FOUND ==========");

        System.out.println(
                "ID     : " + student.id
        );

        System.out.println(
                "Name   : " + student.name
        );

        System.out.println(
                "Age    : " + student.age
        );

        System.out.println(
                "Course : " + student.course
        );

        System.out.println(
                "Email  : " + student.email
        );

        System.out.println(
                "Gender : " + student.gender
        );
    }
public Student searchStudentById(int id) {

    return studentDAO.getStudentById(id);
}

    // ==========================================
    // UPDATE STUDENT
    // ==========================================

    public void updateStudent(
            int id,
            String name,
            int age,
            String course,
            String email,
            String gender) {

        boolean success =
                studentDAO.updateStudent(
                        id,
                        name,
                        age,
                        course,
                        email,
                        gender
                );

        if (success) {

            System.out.println(
                    "Student updated successfully!"
            );

        } else {

            System.out.println(
                    "Student not found."
            );
        }
    }
public boolean updateStudentAndReturn(
        int id,
        String name,
        int age,
        String course,
        String email,
        String gender) {

    return studentDAO.updateStudent(
            id,
            name,
            age,
            course,
            email,
            gender
    );
}

    // ==========================================
    // DELETE STUDENT
    // ==========================================

    public void deleteStudent(int id) {

        boolean success =
                studentDAO.deleteStudent(id);

        if (success) {

            System.out.println(
                    "Student deleted successfully!"
            );

        } else {

            System.out.println(
                    "Student not found."
            );
        }
    }
    public boolean deleteStudentAndReturn(int id) {

    return studentDAO.deleteStudent(id);
}
    public List<Student> getAllStudents() {

    return studentDAO.getAllStudents();
}
public int getTotalStudents() {

    return studentDAO.getTotalStudents();
}


public int getTotalCourses() {

    return studentDAO.getTotalCourses();
}


public int getActiveStudents() {

    return studentDAO.getActiveStudents();
}
}