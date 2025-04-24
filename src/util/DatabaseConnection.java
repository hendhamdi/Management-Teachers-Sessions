package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "DB_URL";
    private static final String USER = "DB_USER";
    private static final String PASSWORD = "DB_PASSWORD";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC chargé avec succès !");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver non trouvé.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
