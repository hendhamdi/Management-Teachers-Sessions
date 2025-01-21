package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Seance;

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

        // Si aucune classe ou matière n'est sélectionnée, on arrête la recherche
        if (classe == null || matiere == null) {
            showAlert("Erreur", "Veuillez sélectionner à la fois une classe et une matière.", Alert.AlertType.ERROR);
            return;
        }

        // Clear the previous results
        seancesList.clear();

        // Connexion à la base de données pour récupérer les séances
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emploidutemps_db", "root",
                "password");
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT s.id, s.classe, s.matiere, s.jour, s.heure, e.nom, e.contact " +
                                "FROM seances s JOIN enseignants e ON s.id_enseignant = e.id " +
                                "WHERE s.classe = ? AND s.matiere = ?")) {

            ps.setString(1, classe);
            ps.setString(2, matiere);

            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) { // Si aucun résultat n'est trouvé
                showAlert("Aucun Résultat", "Aucune séance n'a été trouvée pour cette classe et matière.",
                        Alert.AlertType.INFORMATION);
            }

            while (rs.next()) {
                Seance seance = new Seance(
                        rs.getInt("id"),
                        rs.getString("classe"),
                        rs.getString("matiere"),
                        rs.getString("jour"),
                        rs.getString("heure"),
                        rs.getString("nom"),
                        rs.getString("contact"));
                seancesList.add(seance); // Ajout à la liste observable
            }

            // Remplir le tableau avec les données récupérées
            resultList.setItems(seancesList);

            if (seancesList.size() > 0) {
                showAlert("Succès", "Les séances ont été récupérées avec succès.", Alert.AlertType.INFORMATION);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur de Connexion", "Une erreur s'est produite lors de la récupération des données.",
                    Alert.AlertType.ERROR);
        }
    }

    // Méthode pour afficher les alertes
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Initialisation de la page
    public void initialize() {
        // Remplir les ComboBox avec des données (par exemple, classes et matières
        // disponibles)
        ObservableList<String> classes = FXCollections.observableArrayList("Licence", "Master 1", "Master 2");
        ObservableList<String> matieres = FXCollections.observableArrayList("Dev.Web", "Dev.Mobile", "Anglais",
                "Réseaux", "SE");

        classeComboBox.setItems(classes);
        matiereComboBox.setItems(matieres);

    }
}
