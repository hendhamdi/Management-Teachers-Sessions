package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Seance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SeanceController {

    @FXML
    private ComboBox<String> classeComboBox;
    @FXML
    private ComboBox<String> matiereComboBox;
    @FXML
    private TextField idTextField;
    @FXML
    private TableView<Seance> resultList;
    @FXML
    private TableColumn<Seance, String> idColumn;
    @FXML
    private TableColumn<Seance, String> classeColumn;
    @FXML
    private TableColumn<Seance, String> matiereColumn;
    @FXML
    private TableColumn<Seance, String> jourColumn;
    @FXML
    private TableColumn<Seance, String> heureColumn;
    @FXML
    private TableColumn<Seance, String> nomEnseignantColumn;
    @FXML
    private TableColumn<Seance, String> contactEnseignantColumn;

    private ObservableList<Seance> seancesList = FXCollections.observableArrayList();

    // Initialisation des données
    @FXML
    public void initialize() {
        // Initialize ComboBox values
        classeComboBox.setItems(FXCollections.observableArrayList("Licence", "Master 1", "Master 2"));
        matiereComboBox
                .setItems(FXCollections.observableArrayList("Dev.Web", "Dev.Mobile", "Anglais", "Réseaux", "SE"));

        // Initialize TableView columns

        // Sample data for testing
        seancesList.add(new Seance(1, "Licence", "Dev.Web", "Lundi", "08:00", "M. Dupont", "123456789"));
        seancesList.add(new Seance(2, "Master 1", "Anglais", "Mardi", "10:00", "Mme. Durand", "987654321"));
        resultList.setItems(seancesList);
    }

    @FXML
    private void chercherSeance() {
        String selectedClasse = classeComboBox.getValue();
        String selectedMatiere = matiereComboBox.getValue();

        // Filter the list based on the selected criteria
        ObservableList<Seance> filteredSeances = FXCollections.observableArrayList();
        for (Seance seance : seancesList) {
            if (seance.getClasse().equals(selectedClasse) && seance.getMatiere().equals(selectedMatiere)) {
                filteredSeances.add(seance);
            }
        }

        // Display filtered list in the table
        resultList.setItems(filteredSeances);
    }

    @FXML
    private void supprimerSeance() {
        String idText = idTextField.getText();

        if (idText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "ID vide", "Veuillez entrer l'ID de la séance à supprimer.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Seance seanceToDelete = null;

            // Find the session by ID
            for (Seance seance : seancesList) {
                if (seance.getId() == id) {
                    seanceToDelete = seance;
                    break;
                }
            }

            if (seanceToDelete != null) {
                seancesList.remove(seanceToDelete);
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Séance supprimée",
                        "La séance a été supprimée avec succès.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "ID introuvable", "Aucune séance trouvée avec cet ID.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "ID invalide", "Veuillez entrer un ID valide.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
