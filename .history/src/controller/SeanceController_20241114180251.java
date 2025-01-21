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

        // Initialiser les colonnes de la TableView
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
        classeColumn.setCellValueFactory(cellData -> cellData.getValue().classeProperty());
        matiereColumn.setCellValueFactory(cellData -> cellData.getValue().matiereProperty());
        jourColumn.setCellValueFactory(cellData -> cellData.getValue().jourProperty());
        heureColumn.setCellValueFactory(cellData -> cellData.getValue().heureProperty());
        nomEnseignantColumn.setCellValueFactory(cellData -> cellData.getValue().nomEnseignantProperty());
        contactEnseignantColumn.setCellValueFactory(cellData -> cellData.getValue().contactEnseignantProperty());
    }

    @FXML
    private void enregistrerSeance() {
        // Récupérer les informations des ComboBox et TextField
        String classe = classeComboBox.getValue();
        String matiere = matiereComboBox.getValue();
        String jour = jourComboBox.getValue();
        String heure = heureComboBox.getValue();
        String matriculeEnseignant = matriculeEnseignantField.getText();

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de charger l'interface");
            alert.setContentText("Veuillez vérifier le chemin du fichier FXML.");
            alert.showAndWait();
        }
    }

}
