package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/emploidutemps_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Hendhend123*";

    public static Connection getConnection() throws SQLException {
        try {
            // Charge explicitement le driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC chargé avec succès !");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver non trouvé.", e);
        }

        // Retourne la connexion
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
