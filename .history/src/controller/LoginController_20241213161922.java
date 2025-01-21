package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.DatabaseConnection;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessage;
    @FXML
    private Button togglePasswordButton;

    private boolean visiblePasswordField = false;

    public void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.setText("Veuillez remplir tous les champs.");
            return;
        }

        try {
            // Connexion à la base de données
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM admin WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Rediriger vers l'interface enseignant
                Stage stage = (Stage) emailField.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/enseignants.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
            } else {
                errorMessage.setText("Email ou mot de passe incorrect.");
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage.setText("Erreur lors de la connexion.");
        }
    }

    @FXML
    @FXML
    public void togglePasswordVisibility() {
        // Vérifier si le parent est un VBox
        if (passwordField.getParent() instanceof VBox) {
            VBox parent = (VBox) passwordField.getParent();
            if (visiblePasswordField) {
                // Remplacer le champ password par un TextField visible
                TextField visiblePasswordField = new TextField(passwordField.getText());
                visiblePasswordField.setPromptText("Mot de passe");
                parent.getChildren().remove(passwordField);
                parent.getChildren().add(visiblePasswordField);
                this.visiblePasswordField = false;
            } else {
                // Restaurer le champ password
                passwordField.setVisible(true);
                parent.getChildren().remove(passwordField);
                parent.getChildren().add(passwordField);
                this.visiblePasswordField = true;
            }
        } else {
            // Le parent n'est pas un VBox, gérer ce cas
            System.out.println("Le parent n'est pas un VBox.");
        }
    }

}
