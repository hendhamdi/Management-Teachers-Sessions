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
    private ComboBox<String> jourComboBox;
    @FXML
    private ComboBox<String> heureComboBox;
    @FXML
    private TextField matriculeEnseignantField;
    @FXML
    private TableView<Seance> seancesTable;
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

    // Initialisation des données pour les ComboBox
    private ObservableList<String> classes = FXCollections.observableArrayList("Licence", "Master 1", "Master 2");
    private ObservableList<String> matieres = FXCollections.observableArrayList("Dev.Web", "Dev.Mobile", "Anglais",
            "Réseaux", "SE");
    private ObservableList<String> jours = FXCollections.observableArrayList("Lundi", "Mardi", "Mercredi", "Jeudi",
            "Vendredi");
    private ObservableList<String> heures = FXCollections.observableArrayList("08:00", "10:00", "12:00", "14:00",
            "16:00", "18:00");

    @FXML
    public void initialize() {
        // Liaison des données aux ComboBox
        classeComboBox.setItems(classes);
        matiereComboBox.setItems(matieres);
        jourComboBox.setItems(jours);
        heureComboBox.setItems(heures);

    }

    @FXML
    private void enregistrerSeance() {
        // Récupérer les informations des ComboBox et TextField
        String classe = classeComboBox.getValue();
        String matiere = matiereComboBox.getValue();
        String jour = jourComboBox.getValue();
        String heure = heureComboBox.getValue();
        String matriculeEnseignant = matriculeEnseignantField.getText();

        // Vérification des valeurs saisies
        if (classe == null || matiere == null || jour == null || heure == null || matriculeEnseignant.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Entrée invalide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        // Créer une nouvelle séance
        Seance nouvelleSeance = new Seance(0, classe, matiere, jour, heure, "Nom Enseignant", "Contact Enseignant");

        // Ajouter la séance dans la TableView
        seancesTable.getItems().add(nouvelleSeance);

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Enregistrement de Séance");
        alert.setHeaderText(null);
        alert.setContentText("Séance enregistrée avec succès !");
        alert.showAndWait();
    }

    @FXML
    private void chercherSeance() {
        // Récupérer les informations des ComboBox
        String classe = classeComboBox.getValue();
        String matiere = matiereComboBox.getValue();

        // Vérification des valeurs saisies
        if (classe == null || matiere == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Entrée invalide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une classe et une matière.");
            alert.showAndWait();
            return;
        }

        // Recherche des séances en fonction des ComboBox sélectionnées
        ObservableList<Seance> resultats = FXCollections.observableArrayList();

        // Exemple de recherche simulée dans une liste de séances existantes
        for (Seance seance : getSeances()) {
            if (seance.getClasse().equals(classe) && seance.getMatiere().equals(matiere)) {
                resultats.add(seance);
            }
        }

        // Mettre à jour la TableView avec les résultats
        seancesTable.setItems(resultats);
    }

    // Exemple de méthode pour récupérer une liste de séances (à remplacer par la
    // logique de base de données)
    private ObservableList<Seance> getSeances() {
        ObservableList<Seance> seances = FXCollections.observableArrayList();

        // Ajouter des séances simulées
        seances.add(new Seance(1, "Licence", "Dev.Web", "Lundi", "08:00", "Nom Enseignant", "Contact Enseignant"));
        seances.add(new Seance(2, "Master 1", "Dev.Mobile", "Mardi", "10:00", "Nom Enseignant", "Contact Enseignant"));
        seances.add(new Seance(3, "Master 2", "Anglais", "Mercredi", "14:00", "Nom Enseignant", "Contact Enseignant"));

        return seances;
    }
}
