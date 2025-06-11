import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SakilaMovies {
    //create 2 Model Classes
    //include a method to search for actors by name and return a list of actors
    //add method that returns a list of films by actor ID

    //prompt user to search for the actor by name and display the results of the search
    //add another prompt to ask the user to enter an actor id then search for and display a list of movies that the actor has appeared in
    private static Scanner scanner = new Scanner(System.in);


    public static String getUserInput() {
        System.out.println("Please enter the last name of an actor you wish to see:");
        return scanner.nextLine();
    }

    public static String getUserInputFirstName() {
        System.out.println("Please enter the first name of an actor you wish to see:");
        return scanner.nextLine();
    }

}
