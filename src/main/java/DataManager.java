import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManager {
    //refactor the data access logic to this class
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
                             "SELECT first_name, last_name, title FROM film JOIN film_actor ON film.film_id = film_actor.film_id JOIN actor ON film_actor.actor_id = actor.actor_id WHERE actor.first_name LIKE ? ")) {
            String lastName = SakilaMovies.getUserInput();
            preparedStatement.setString(1, "%" + lastName + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    System.out.println("First Name: " + rs.getString("first_name") + " | Last name: " + rs.getString("last_name") + " | " + rs.getString("title"));
                }

            }

            String firstName = SakilaMovies.getUserInputFirstName();
            PreparedStatement ps =
                    connection.prepareStatement(
                            "SELECT first_name, last_name, title FROM film JOIN film_actor ON film.film_id = film_actor.film_id JOIN actor ON film_actor.actor_id = actor.actor_id WHERE actor.first_name LIKE ? AND actor.last_name LIKE ?");
            ps.setString(2, "%" + lastName + "%");
            ps.setString(1, "%" + firstName + "%");


            try (ResultSet rss = ps.executeQuery()) {
                //
                while (rss.next()) {
                    System.out.println("First Name: " + rss.getString("first_name") + " | Last name: " + rss.getString("last_name") + " | " + rss.getString("title"));
                }

            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }










}
