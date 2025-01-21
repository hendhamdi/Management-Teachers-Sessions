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

    private boolean isPasswordVisible = false;

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
    public void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;

        if (isPasswordVisible) {
            // Créer un champ texte pour afficher le mot de passe
            TextField passwordTextField = new TextField(passwordField.getText());
            passwordTextField.setPrefHeight(passwordField.getPrefHeight());
            passwordTextField.setStyle(passwordField.getStyle());
            passwordTextField.setPromptText("Mot de passe");

            // Obtenir le conteneur parent et remplacer le champ
            VBox parent = (VBox) passwordField.getParent();
            int index = parent.getChildren().indexOf(passwordField);
            parent.getChildren().set(index, passwordTextField);

            togglePasswordButton.setText("🙈");

            // Écouteur pour synchroniser les champs
            passwordTextField.textProperty().addListener((obs, oldText, newText) -> passwordField.setText(newText));
        } else {
            // Rétablir le champ PasswordField
            VBox parent = (VBox) passwordField.getParent();
            int index = parent.getChildren().indexOf(passwordField);
            parent.getChildren().set(index, passwordField);

            togglePasswordButton.setText("👁");
        }
    }

}
