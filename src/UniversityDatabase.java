import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UniversityDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/university";
    private static final String USER = "root";
    private static final String PASSWORD = "ooptest123";

    public static void addStudent(String name, int age, String major) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO students (name, age, major) VALUES (?, ?, ?)")) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, major);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New student added successfully.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateStudent(String name, int newAge, String newMajor) {
        String updateQuery = "UPDATE students SET age = ?, major = ? WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setInt(1, newAge);
            statement.setString(2, newMajor);
            statement.setString(3, name);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student information updated successfully.");
            } else {
                System.out.println("No student found with the name: " + name);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteStudent(String name) {
        String deleteQuery = "DELETE FROM students WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No student found with the name: " + name);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void showTable() {
        String selectQuery = "SELECT * FROM students";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            System.out.println("List of students:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Age: " + resultSet.getInt("age") +
                        ", Major: " + resultSet.getString("major"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}