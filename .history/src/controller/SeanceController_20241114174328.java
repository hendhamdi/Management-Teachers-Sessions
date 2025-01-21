package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Seance;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

public class SeanceController {

    @FXML
    private ComboBox<String> classeComboBox;

    @FXML
    private ComboBox<String> matiereComboBox;

    @FXML
    private TableView<Seance> resultList;

    @FXML
    private TableColumn<Seance, Integer> idColumn;

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

    // Liste observable des séances
    private ObservableList<Seance> seancesList = FXCollections.observableArrayList();

    // Méthode pour rechercher les séances

    @FXML
private void chercherSeance() {
    String classe = classeComboBox.getValue();
    String matiere = matiereComboBox.getValue();

    System.out.println("Classe sélectionnée: " + classe);
    System.out.println("Matière sélectionnée: " + matiere);

    if (classe == null || matiere == null) {
        showAlert("Erreur", "Veuillez sélectionner à la fois une classe et une matière.", Alert.AlertType.ERROR);
        return;
    }

    // Clear the previous results
    seancesList.clear();

    // Connexion à la base de données pour récupérer les séances
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emploidutemps_db", "root", "password");
         PreparedStatement ps = conn.prepareStatement(
                 "SELECT s.id, s.classe, s.matiere, s.jour, s.heure, e.nom, e.contact " +
                         "FROM seances s JOIN enseignants e ON s.id_enseignant = e.id " +
                         "WHERE s.classe = ? AND s.matiere = ?")) {

        ps.setString(1, classe);
        ps.setString(2, matiere);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) { // Si aucun résultat n'est trouvé
            showAlert("Aucun Résultat", "Aucune séance trouvée pour la combinaison sélectionnée.", Alert.AlertType.INFORMATION);
        }

        while (rs.next()) {
            Seance seance = new Seance(
                    rs.getInt("id"),
                    rs.getString("classe"),
                    rs.getString("matiere"),
                    rs.getString("jour"),
                    rs.getString("heure"),
                    rs.getString("nom"),
                    rs.getString("contact")
            );
            System.out.println("Séance trouvée: " + seance); // Ajoute un message pour chaque séance trouvée
            seancesList.add(seance);
        }
    } catch (SQLException e) {
        showAlert("Erreur", "Erreur de connexion à la base de données : " + e.getMessage(), Alert.AlertType.ERROR);
    }

    // Mise à jour du tableau
    resultList.setItems(seancesList);
}
