package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Seance;
import util.DatabaseConnection;

import java.sql.*;

public class SeanceController {

    @FXML
    private ComboBox<String> classeComboBox, matiereComboBox;
    @FXML
    private TextField idTextField;
    @FXML
    private TableView<Seance> resultList;
    @FXML
    private TableColumn<Seance, Integer> idColumn;
    @FXML
    private TableColumn<Seance, String> classeColumn, matiereColumn, jourColumn, heureColumn, nomEnseignantColumn,
            contactEnseignantColumn;

    private ObservableList<Seance> seancesList = FXCollections.observableArrayList();

    // Initialisation des données pour les ComboBox
    private ObservableList<String> classes;
    private ObservableList<String> matieres;
    private ObservableList<String> jours;
    private ObservableList<String> heures;

    // Méthode d'initialisation
    @FXML
    public void initialize() {
        initializeComboBoxData();
        setupTable();
        loadComboBoxData();
    }

    // Initialisation des listes pour les ComboBox
    private void initializeComboBoxData() {
        // Initialisation des classes
        classes = FXCollections.observableArrayList(
                "Licence",
                "Master 1",
                "Master 2");

        // Initialisation des matières
        matieres = FXCollections.observableArrayList(
                "Dev.Web",
                "Dev.Mobile",
                "Anglais",
                "Réseaux",
                "SE");

        // Initialisation des jours
        jours = FXCollections.observableArrayList(
                "Lundi",
                "Mardi",
                "Mercredi",
                "Jeudi",
                "Vendredi");

        // Initialisation des heures
        heures = FXCollections.observableArrayList(
                "08:00",
                "10:00",
                "12:00",
                "14:00",
                "16:00",
                "18:00");

        // Affecter les données statiques aux ComboBox correspondantes
        classeComboBox.setItems(classes);
        matiereComboBox.setItems(matieres);
    }

    // Configurer les colonnes du tableau
    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        classeColumn.setCellValueFactory(new PropertyValueFactory<>("classe"));
        matiereColumn.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        jourColumn.setCellValueFactory(new PropertyValueFactory<>("jour"));
        heureColumn.setCellValueFactory(new PropertyValueFactory<>("heure"));
        nomEnseignantColumn.setCellValueFactory(new PropertyValueFactory<>("nomEnseignant"));
        contactEnseignantColumn.setCellValueFactory(new PropertyValueFactory<>("contactEnseignant"));

        resultList.setItems(seancesList);
    }

    // Charger les données dans les ComboBox (classes et matières) depuis la base de
    // données
    private void loadComboBoxData() {
        try (Connection connection = DatabaseConnection.getConnection();
                Statement stmt = connection.createStatement()) {

            // Charger les classes
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT classe FROM seances");
            while (rs.next()) {
                classeComboBox.getItems().add(rs.getString("classe"));
            }

            // Charger les matières
            rs = stmt.executeQuery("SELECT DISTINCT matiere FROM seances");
            while (rs.next()) {
                matiereComboBox.getItems().add(rs.getString("matiere"));
            }
        } catch (SQLException e) {
            showError(e.getMessage());
        }
    }

    // Chercher les séances
    @FXML
    public void chercherSeance() {
        String classe = classeComboBox.getValue();
        String matiere = matiereComboBox.getValue();

        if (classe == null || matiere == null) {
            showError("Veuillez sélectionner une classe et une matière.");
            return;
        }

        seancesList.clear();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "SELECT * FROM seances s JOIN enseignants e ON s.id_enseignant = e.id WHERE s.classe = ? AND s.matiere = ?")) {

            stmt.setString(1, classe);
            stmt.setString(2, matiere);
            ResultSet rs = stmt.executeQuery();

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
        } catch (SQLException e) {
            showError(e.getMessage());
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
            showError(e.getMessage());
        }
    }

    // Chercher l'emploi du temps pour une classe
    @FXML
    public void chercherEmploiTemps() {
        String classe = classeComboBox.getValue();

        if (classe == null) {
            showError("Veuillez sélectionner une classe.");
            return;
        }

        seancesList.clear();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "SELECT * FROM seances s JOIN enseignants e ON s.id_enseignant = e.id WHERE s.classe = ? ORDER BY s.jour, s.heure")) {

            stmt.setString(1, classe);
            ResultSet rs = stmt.executeQuery();

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
        } catch (SQLException e) {
            showError(e.getMessage());
        }
    }

    // Afficher un message d'erreur
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Afficher un message d'information
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
