package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.property.SimpleStringProperty;
import util.DatabaseConnection;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;

import model.Seance;

public class SeanceController {

    @FXML
    private ComboBox<String> classeComboBox; // Le ComboBox pour les classes
    @FXML
    private TableView<Seance> resultList; // La table des résultats
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
    @FXML
    private TextField idTextField;
    @FXML
    private Button chercherSeanceButton;
    @FXML
    private Button chercherEmploiTempsButton;
    @FXML
    private ComboBox<String> classeRechercheComboBox; // ComboBox pour rechercher les séances
    @FXML
    private ComboBox<String> matiereComboBox; // ComboBox pour les matières
    @FXML
    private ComboBox<String> classeEmploiComboBox; // ComboBox pour l'emploi du temps

    private ObservableList<Seance> seancesList = FXCollections.observableArrayList(); // Liste des séances

    private ObservableList<String> classes = FXCollections.observableArrayList("Licence", "Master 1", "Master 2");
    private ObservableList<String> matieres = FXCollections.observableArrayList("Dev.Web", "Dev.Mobile", "Anglais",
            "Réseaux", "SE");

    public void initialize() {
        classeRechercheComboBox.setItems(classes);
        matiereComboBox.setItems(matieres);
        classeEmploiComboBox.setItems(classes);

        // Initialisation des colonnes du TableView
        classeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClasse()));
        matiereColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatiere()));
        jourColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJour()));
        heureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeure()));
        nomEnseignantColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomEnseignant()));
        contactEnseignantColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactEnseignant()));

        // Lier la liste des séances au TableView
        resultList.setItems(seancesList);
    }

    // Chercher les séances
    @FXML
    public void chercherSeance() {
        String classe = classeRechercheComboBox.getValue();
        String matiere = matiereComboBox.getValue();

        if (classe == null || matiere == null) {
            showError("Veuillez sélectionner une classe et une matière.");
            return;
        }

        seancesList.clear();

        String query = "SELECT * FROM seances s JOIN enseignants e ON s.matricule_enseignant = e.id WHERE s.classe = ? AND s.matiere = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, classe);
            stmt.setString(2, matiere);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    seancesList.add(new Seance(
                            rs.getInt("s.id"),
                            rs.getString("s.classe"),
                            rs.getString("s.matiere"),
                            rs.getString("s.jour"),
                            rs.getString("s.heure"),
                            rs.getString("e.matricule"),
                            rs.getString("e.nom"),
                            rs.getString("e.contact")));
                }
            }
        } catch (SQLException e) {
            showError("Erreur lors de la recherche des séances : " + e.getMessage());
        }
    }

    // Supprimer une séance
    @FXML
    public void supprimerSeance() {
        String idText = idTextField.getText();

        if (idText.isEmpty()) {
            showError("Veuillez entrer un ID.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM seances WHERE id = ?")) {

            stmt.setInt(1, Integer.parseInt(idText));
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                seancesList.removeIf(seance -> seance.getId() == Integer.parseInt(idText));
                idTextField.clear();
                showInfo("Séance supprimée avec succès.");
            } else {
                showError("Aucune séance trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            showError("Erreur lors de la suppression de la séance : " + e.getMessage());
        }
    }

    // Chercher l'emploi du temps pour une classe
    @FXML
    public void chercherEmploiTemps() {
        String classe = classeEmploiComboBox.getValue();

        if (classe == null) {
            showError("Veuillez sélectionner une classe.");
            return;
        }

        seancesList.clear();

        String query = "SELECT * FROM seances s JOIN enseignants e ON s.matricule_enseignant = e.id WHERE s.classe = ? ORDER BY s.jour, s.heure";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, classe);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    seancesList.add(new Seance(
                            rs.getInt("s.id"),
                            rs.getString("s.classe"),
                            rs.getString("s.matiere"),
                            rs.getString("s.jour"),
                            rs.getString("s.heure"),
                            rs.getString("e.matricule"),
                            rs.getString("e.nom"),
                            rs.getString("e.contact")));
                }
            }
        } catch (SQLException e) {
            showError("Erreur lors de la recherche de l'emploi du temps : " + e.getMessage());
        }
    }

    // Afficher un message d'erreur
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Afficher un message d'information
    private void showInfo(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
