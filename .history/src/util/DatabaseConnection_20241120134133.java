package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/emploidutemps_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Hendhend123*";

    // Méthode pour établir une connexion avec la base de données
    public static Connection getConnection() throws SQLException {
        try {
            // Charger explicitement le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Si le driver n'est pas trouvé, affichez un message d'erreur
            throw new SQLException("MySQL JDBC Driver non trouvé.", e);
        }

        // Retourner la connexion après avoir chargé le driver
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
