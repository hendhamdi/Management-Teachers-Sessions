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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import util.DatabaseConnection;

import java.sql.*;
import model.Seance;

public class SeanceController {

    @FXML
    private ComboBox<String> classeComboBox;
    @FXML
    private TableView<Seance> resultList;
    @FXML
    private TableColumn<Seance, Integer> seanceIdColumn;
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
    private ComboBox<String> classeRechercheComboBox;
    @FXML
    private ComboBox<String> matiereComboBox;
    @FXML
    private ComboBox<String> classeEmploiComboBox;

    private ObservableList<Seance> seancesList = FXCollections.observableArrayList();

    private ObservableList<String> classes = FXCollections.observableArrayList("Licence", "Master 1", "Master 2");
    private ObservableList<String> matieres = FXCollections.observableArrayList("Dev.Web", "Dev.Mobile", "Anglais",
            "Réseaux", "SE", "Francais", "JAVA", "Open source", "pilotage des projets");

    public void initialize() {
        classeRechercheComboBox.setItems(classes);
        matiereComboBox.setItems(matieres);
        classeEmploiComboBox.setItems(classes);

        seanceIdColumn
                .setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        classeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClasse()));
        matiereColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatiere()));
        jourColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJour()));
        heureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeure()));
        nomEnseignantColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomEnseignant()));
        contactEnseignantColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactEnseignant()));

        resultList.setItems(seancesList);
    }

    @FXML
    public void chercherSeance() {
        // Récupérer les valeurs des ComboBox
        String classe = classeRechercheComboBox.getValue();
        String matiere = matiereComboBox.getValue();

        if (classe == null || matiere == null) {
            showError("Veuillez sélectionner une classe et une matière.");
            return;
        }

        seancesList.clear();

        String query = "SELECT " +
                "    s.id AS seance_id, " +
                "    s.classe, " +
                "    s.matiere, " +
                "    s.jour, " +
                "    s.heure, " +
                "    e.matricule, " +
                "    e.nom, " +
                "    e.contact " +
                "FROM " +
                "    seances s " +
                "JOIN " +
                "    enseignants e " +
                "ON " +
                "    s.matricule_enseignant = e.matricule " +
                "WHERE " +
                "    s.classe = ? AND s.matiere = ? " +
                "ORDER BY " +
                "    s.jour, s.heure";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, classe);
            stmt.setString(2, matiere);

            try (ResultSet rs = stmt.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    seancesList.add(new Seance(
                            rs.getInt("seance_id"),
                            rs.getString("classe"),
                            rs.getString("matiere"),
                            rs.getString("jour"),
                            rs.getString("heure"),
                            rs.getString("matricule"),
                            rs.getString("nom"),
                            rs.getString("contact")));
                    found = true;
                }

                if (found) {
                    resultList.setItems(seancesList);
                    resultList.refresh();
                } else {
                    showError("Aucune séance trouvée pour la classe et la matière sélectionnées.");
                }
            }
        } catch (SQLException e) {
            showError("Erreur lors de la recherche des séances : " + e.getMessage());
        }
    }

    @FXML
    public void chercherEmploiTemps() {
        String classe = classeEmploiComboBox.getValue();

        if (classe == null) {
            showError("Veuillez sélectionner une classe.");
            return;
        }

        seancesList.clear();

        String query = "SELECT " +
                "    s.id AS seance_id, " +
                "    s.classe, " +
                "    s.matiere, " +
                "    s.jour, " +
                "    s.heure, " +
                "    e.matricule, " +
                "    e.nom, " +
                "    e.contact " +
                "FROM " +
                "    seances s " +
                "INNER JOIN " +
                "    enseignants e " +
                "ON " +
                "    s.matricule_enseignant = e.matricule " +
                "WHERE " +
                "    s.classe = ? " +
                "ORDER BY " +
                "    CASE " +
                "        WHEN s.jour = 'Lundi' THEN 1 " +
                "        WHEN s.jour = 'Mardi' THEN 2 " +
                "        WHEN s.jour = 'Mercredi' THEN 3 " +
                "        WHEN s.jour = 'Jeudi' THEN 4 " +
                "        WHEN s.jour = 'Vendredi' THEN 5 " +
                "        WHEN s.jour = 'Samedi' THEN 6 " +
                "        WHEN s.jour = 'Dimanche' THEN 7 " +
                "        ELSE 8 " +
                "    END, " +
                "    s.heure";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, classe);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean found = false;

                while (rs.next()) {
                    seancesList.add(new Seance(
                            rs.getInt("seance_id"),
                            rs.getString("classe"),
                            rs.getString("matiere"),
                            rs.getString("jour"),
                            rs.getString("heure"),
                            rs.getString("matricule"),
                            rs.getString("nom"),
                            rs.getString("contact")));
                    found = true;
                }

                if (found) {
                    resultList.setItems(seancesList);
                    resultList.refresh();
                } else {
                    showError("Aucune séance trouvée pour la classe sélectionnée.");
                }
            }
        } catch (SQLException e) {
            showError("Erreur lors de la recherche de l'emploi du temps : " + e.getMessage());
        }
    }

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
                resultList.setItems(seancesList);
                showInfo("Séance supprimée avec succès.");
            } else {
                showError("Aucune séance trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            showError("Erreur lors de la suppression de la séance : " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
