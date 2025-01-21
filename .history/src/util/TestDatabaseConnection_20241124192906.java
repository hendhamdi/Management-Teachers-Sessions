package util;

import java.sql.Connection;
import java.sql.SQLException;
import util.DatabaseConnection;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            System.out.println("Chargement du driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver chargé avec succès !");

            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Connexion réussie à la base de données !");
            connection.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Driver non trouvé : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }
}
