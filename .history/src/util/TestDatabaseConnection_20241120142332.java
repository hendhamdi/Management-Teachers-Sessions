package util;

import java.sql.Connection;
import java.sql.SQLException;
import util.DatabaseConnection;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            // Tente de se connecter à la base de données
            Connection connection = DatabaseConnection.getConnection();

            // Vérifie si la connexion a réussi
            if (connection != null) {
                System.out.println("Connexion à la base de données réussie !");
                // Ferme la connexion après le test
                connection.close();
            } else {
                System.out.println("Échec de la connexion à la base de données.");
            }
        } catch (SQLException e) {
            // Si la connexion échoue, afficher l'erreur
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
