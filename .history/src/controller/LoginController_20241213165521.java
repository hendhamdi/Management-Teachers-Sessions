package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text eyeIcon;

    private boolean visiblePasswordField = false;

    // Méthode pour gérer la visibilité du mot de passe
    @FXML
    public void togglePasswordVisibility() {
        if (visiblePasswordField) {
            passwordField.setPromptText("Mot de passe");
            passwordField.setStyle("-fx-background-color: white;"); // Style par défaut
            visiblePasswordField = false;
            eyeIcon.setText("👁"); // Changer l'icône en œil fermé
            passwordField.setText(passwordField.getText()); // Réafficher le mot de passe sous forme de points
        } else {
            passwordField.setPromptText("Mot de passe visible");
            passwordField.setStyle("-fx-background-color: lightgrey;"); // Style pour le mot de passe visible
            visiblePasswordField = true;
            eyeIcon.setText("👁️‍🗨️"); // Changer l'icône en œil ouvert
            passwordField.setText(passwordField.getText()); // Réafficher le mot de passe en clair
        }
    }

    // Méthode pour gérer la connexion (code déjà existant)
    public void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            // Gérer les erreurs de connexion
        }

        // Connexion à la base de données et vérification des informations
        // d'identification
        // (Code de connexion à la base de données)
    }
}
