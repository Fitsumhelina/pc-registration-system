package main.java.com.pc_registration.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5000/pc_registration"; // Change DB name
    private static final String USER = "postgres";  // Change username
    private static final String PASSWORD = "permin";  // Change password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
