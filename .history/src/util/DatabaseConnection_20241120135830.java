import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/emploidutemps_db";
        String user = "root";
        String password = "Hendhend123*";

        try {
            // Charger le driver explicitement
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
