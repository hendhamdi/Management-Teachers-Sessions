package util;

import java.sql.Connection;
import java.sql.SQLException;
import util.DatabaseConnection;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            // Tester la connexion à la base de données
            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Connexion réussie à la base de données !");
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }
}