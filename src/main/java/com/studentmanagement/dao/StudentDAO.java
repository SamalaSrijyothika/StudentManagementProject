package com.studentmanagement.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.studentmanagement.model.Student;
import com.studentmanagement.util.DatabaseConnection;
public class StudentDAO {

    // ==========================================
    // CHECK STUDENT ID
    // ==========================================

    public boolean existsById(int id) {

        String sql = "SELECT id FROM students WHERE id = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // ==========================================
    // ADD STUDENT
    // ==========================================

    public boolean addStudent(Student student) {

        String sql = "INSERT INTO students " +
                     "(id, name, age, course, email, gender) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(1, student.id);
            statement.setString(2, student.name);
            statement.setInt(3, student.age);
            statement.setString(4, student.course);
            statement.setString(5, student.email);
            statement.setString(6, student.gender);

            statement.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // ==========================================
    // GET ALL STUDENTS
    // ==========================================

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet =
                    statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Student student = new Student(

                        resultSet.getInt("id"),

                        resultSet.getString("name"),

                        resultSet.getInt("age"),

                        resultSet.getString("course"),

                        resultSet.getString("email"),

                        resultSet.getString("gender")
                );

                students.add(student);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return students;
    }


    // ==========================================
    // FIND STUDENT BY ID
    // ==========================================

    public Student getStudentById(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Student(

                        resultSet.getInt("id"),

                        resultSet.getString("name"),

                        resultSet.getInt("age"),

                        resultSet.getString("course"),

                        resultSet.getString("email"),

                        resultSet.getString("gender")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // ==========================================
    // UPDATE STUDENT
    // ==========================================

    public boolean updateStudent(
            int id,
            String name,
            int age,
            String course,
            String email,
            String gender) {

        String sql = "UPDATE students " +
                     "SET name = ?, age = ?, course = ?, " +
                     "email = ?, gender = ? " +
                     "WHERE id = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, course);
            statement.setString(4, email);
            statement.setString(5, gender);
            statement.setInt(6, id);

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // ==========================================
    // DELETE STUDENT
    // ==========================================

    public boolean deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    public int getTotalStudents() {

    String sql = "SELECT COUNT(*) FROM students";

    try (
        Connection connection =
                DatabaseConnection.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(sql);

        ResultSet resultSet =
                statement.executeQuery()
    ) {

        if (resultSet.next()) {

            return resultSet.getInt(1);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return 0;
}


public int getTotalCourses() {

    String sql =
            "SELECT COUNT(DISTINCT course) FROM students";

    try (
        Connection connection =
                DatabaseConnection.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(sql);

        ResultSet resultSet =
                statement.executeQuery()
    ) {

        if (resultSet.next()) {

            return resultSet.getInt(1);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return 0;
}


public int getActiveStudents() {

    /*
     * At the moment our database does not have
     * an active/inactive column.
     *
     * So every student is considered active.
     */

    return getTotalStudents();
}
}