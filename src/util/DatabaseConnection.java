package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/emploidutemps_db";
    private static final String USER = "YourUserName";
    private static final String PASSWORD = "YourPassword";

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
