import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SakilaMovies {
    private static Scanner scanner = new Scanner(System.in);


    public static String getUserInput() {
        System.out.println("Please enter the last name of an actor you wish to see:");
        return scanner.nextLine();
    }

    public static String getUserInputFirstName() {
        System.out.println("Please enter the first name of an actor you wish to see:");
        return scanner.nextLine();
    }

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Need 2 arguments");
            return;
        }
        // Step 1: instantiate the datasource
        BasicDataSource datasource = new BasicDataSource();
        datasource.setUrl("jdbc:mysql://localhost:3306/sakila");
        datasource.setUsername(args[0]);
        datasource.setPassword(args[1]);

        // Step 2: use getConnection method to obtain a connection to the Datasource instance
        try (Connection connection = datasource.getConnection();
             //Step 3: connect the query with a prepared statement with datasource connection
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "SELECT first_name, last_name FROM actor WHERE last_name LIKE ? ORDER BY last_name")) {
            String lastName =  getUserInput();
            preparedStatement.setString(1, "%" + lastName + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    System.out.println("First Name: " + rs.getString("first_name") + " | Last name: " + rs.getString("last_name"));
                }

            }

            String firstName = getUserInputFirstName();
            PreparedStatement ps =
                    connection.prepareStatement(
                            "SELECT first_name, last_name FROM actor WHERE first_name LIKE ? AND last_name LIKE ? ORDER BY first_name");
            ps.setString(2, "%" + lastName + "%");
            ps.setString(1, "%" + firstName + "%");


            try (ResultSet rss = ps.executeQuery()) {
                //
                while (rss.next()) {
                    System.out.println("First Name: " + rss.getString("first_name") + " | Last name: " + rss.getString("last_name"));
                }

            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    // ask user for the first name of that list
//    System.out.println();
//        try (Connection connection = datasource.getConnection();
//             //Step 3: connect the query with a prepared statement with datasource connection
//             PreparedStatement preparedStatement =
//                     connection.prepareStatement(
//                             "SELECT first_name, last_name FROM actor WHERE last_name LIKE ? ORDER BY last_name")) {
//            preparedStatement.setString(1, "%" + getUserInputFirstName() + "%");
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                //
//                while (rs.next()) {
//                    System.out.println("First Name: " + rs.getString("first_name") + " | Last name: " + rs.getString("last_name"));
//                }
//            }
//        }
//        catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }

    }
}
