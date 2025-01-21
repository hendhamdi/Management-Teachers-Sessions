package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class SeanceController {

    @FXML
    private ComboBox<String> classeComboBox;
    @FXML
    private ComboBox<String> matiereComboBox;
    @FXML
    private ComboBox<String> jourComboBox;
    @FXML
    private ComboBox<String> heureComboBox;

    @FXML
    private TableView<Seance> seancesTable;
    @FXML
    private TableColumn<Seance, String> classeColumn;
    @FXML
    private TableColumn<Seance, String> matiereColumn;
    @FXML
    private TableColumn<Seance, String> jourColumn;
    @FXML
    private TableColumn<Seance, String> heureColumn;
    @FXML
    private TableColumn<Seance, String> matriculeEnseignantColumn;

    // ObservableList pour les séances à afficher dans le tableau
    private ObservableList<Seance> seancesList = FXCollections.observableArrayList();

    // Données pour les ComboBox
    private ObservableList<String> classes = FXCollections.observableArrayList("Licence", "Master 1", "Master 2");
    private ObservableList<String> matieres = FXCollections.observableArrayList("Dev.Web", "Dev.Mobile", "Anglais",
            "Réseaux", "SE");
    private ObservableList<String> jours = FXCollections.observableArrayList("Lundi", "Mardi", "Mercredi", "Jeudi",
            "Vendredi");
    private ObservableList<String> heures = FXCollections.observableArrayList("08:00", "10:00", "12:00", "14:00",
            "16:00", "18:00");

    @FXML
    public void initialize() {
        // Initialisation des ComboBox avec les données disponibles
        classeComboBox.setItems(classes);
        matiereComboBox.setItems(matieres);
        jourComboBox.setItems(jours);
        heureComboBox.setItems(heures);

        // Initialisation des colonnes du tableau
        classeColumn.setCellValueFactory(cellData -> cellData.getValue().classeProperty());
        matiereColumn.setCellValueFactory(cellData -> cellData.getValue().matiereProperty());
        jourColumn.setCellValueFactory(cellData -> cellData.getValue().jourProperty());
        heureColumn.setCellValueFactory(cellData -> cellData.getValue().heureProperty());
        matriculeEnseignantColumn.setCellValueFactory(cellData -> cellData.getValue().matriculeEnseignantProperty());
    }

    @FXML
    private void enregistrerSeance() {
        String classe = classeComboBox.getValue();
        String matiere = matiereComboBox.getValue();
        String jour = jourComboBox.getValue();
        String heure = heureComboBox.getValue();

        if (classe == null || matiere == null || jour == null || heure == null) {
            showAlert("Erreur", "Tous les champs doivent être remplis.", AlertType.ERROR);
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emploidutemps_db", "root",
                "password")) {
            // Préparer la requête d'insertion dans la base de données
            String sql = "INSERT INTO seances (classe, matiere, jour, heure) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, classe);
            ps.setString(2, matiere);
            ps.setString(3, jour);
            ps.setString(4, heure);

            // Exécuter l'insertion
            ps.executeUpdate();

            // Ajouter la séance à la liste observable
            Seance seance = new Seance(classe, matiere, jour, heure, "Matricule Enseignant");
            seancesList.add(seance);

            // Afficher un message de succès
            showAlert("Succès", "Séance enregistrée avec succès.", AlertType.INFORMATION);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors de l'enregistrement de la séance.", AlertType.ERROR);
        }
    }

    @FXML
    private void ouvrirRequetes() {
        try {
            // Charger le fichier FXML de l'interface des séances
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/seances.fxml"));
            Parent root = fxmlLoader.load();

            // Créer une nouvelle scène et fenêtre
            Stage stage = new Stage();
            stage.setTitle("Interface des Séances");
            stage.setScene(new Scene(root));

            // Afficher la nouvelle fenêtre
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger l'interface", AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Classe Seance
    public static class Seance {
        private StringProperty classe;
        private StringProperty matiere;
        private StringProperty jour;
        private StringProperty heure;
        private StringProperty matriculeEnseignant;

        public Seance(String classe, String matiere, String jour, String heure, String matriculeEnseignant) {
            this.classe = new SimpleStringProperty(classe);
            this.matiere = new SimpleStringProperty(matiere);
            this.jour = new SimpleStringProperty(jour);
            this.heure = new SimpleStringProperty(heure);
            this.matriculeEnseignant = new SimpleStringProperty(matriculeEnseignant);
        }

        public StringProperty classeProperty() {
            return classe;
        }

        public StringProperty matiereProperty() {
            return matiere;
        }

        public StringProperty jourProperty() {
            return jour;
        }

        public StringProperty heureProperty() {
            return heure;
        }

        public StringProperty matriculeEnseignantProperty() {
            return matriculeEnseignant;
        }
    }
}
