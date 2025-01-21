package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class EnseignantController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField matriculeField;

    @FXML
    private TextField contactField;

    @FXML
    private TableView<Enseignant> table; // Remplacez `Enseignant` par le nom de votre modèle

    @FXML
    private TableColumn<Enseignant, String> matriculeColumn; // Assurez-vous que les types correspondent
    @FXML
    private TableColumn<Enseignant, String> nomColumn;
    @FXML
    private TableColumn<Enseignant, String> contactColumn;

    @FXML
    private void chercherEnseignant() {
        // Implémentez la logique de recherche ici
        System.out.println("Chercher enseignant clicked");
    }

    @FXML
    private void enregistrerEnseignant() {
        // Implémentez la logique d'enregistrement ici
    }

    @FXML
    private void modifierEnseignant() {
        // Implémentez la logique de modification ici
    }

    @FXML
    private void supprimerEnseignant() {
        // Implémentez la logique de suppression ici
    }
}
