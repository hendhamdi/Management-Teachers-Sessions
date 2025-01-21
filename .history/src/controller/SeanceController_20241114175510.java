package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Seance;

public class SeanceController {

    @FXML
    private TextField idField;
    @FXML
    private ComboBox<String> classeComboBox;
    @FXML
    private ComboBox<String> matiereComboBox;
    @FXML
    private ComboBox<String> jourComboBox;
    @FXML
    private ComboBox<String> heureComboBox;
    @FXML
    private TextField nomEnseignantField;
    @FXML
    private TextField contactEnseignantField;

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

    // Liste des séances
    private ObservableList<Seance> seancesList = FXCollections.observableArrayList();

    // Initialisation des ComboBox
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

        // Liaison des colonnes du tableau aux attributs de la classe Seance
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
        classeColumn.setCellValueFactory(cellData -> cellData.getValue().classeProperty());
        matiereColumn.setCellValueFactory(cellData -> cellData.getValue().matiereProperty());
        jourColumn.setCellValueFactory(cellData -> cellData.getValue().jourProperty());
        heureColumn.setCellValueFactory(cellData -> cellData.getValue().heureProperty());
        nomEnseignantColumn.setCellValueFactory(cellData -> cellData.getValue().nomEnseignantProperty());
        contactEnseignantColumn.setCellValueFactory(cellData -> cellData.getValue().contactEnseignantProperty());

        // Ajout des séances à la table
        seancesTable.setItems(seancesList);
    }

    @FXML
    private void enregistrerSeance() {
        try {
            // Récupération des valeurs des champs
            int id = Integer.parseInt(idField.getText());
            String classe = classeComboBox.getValue();
            String matiere = matiereComboBox.getValue();
            String jour = jourComboBox.getValue();
            String heure = heureComboBox.getValue();
            String nomEnseignant = nomEnseignantField.getText();
            String contactEnseignant = contactEnseignantField.getText();

            // Vérification si tous les champs sont remplis
            if (classe == null || matiere == null || jour == null || heure == null || nomEnseignant.isEmpty()
                    || contactEnseignant.isEmpty()) {
                throw new IllegalArgumentException("Tous les champs doivent être remplis !");
            }

            // Création d'une nouvelle séance avec les données saisies
            Seance seance = new Seance(id, classe, matiere, jour, heure, nomEnseignant, contactEnseignant);

            // Ajout de la séance à la liste
            seancesList.add(seance);

            // Affichage d'un message de succès
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Enregistrement de séance");
            alert.setHeaderText(null);
            alert.setContentText("Séance enregistrée avec succès !");
            alert.showAndWait();

            // Réinitialisation des champs après l'enregistrement
            resetFields();

        } catch (NumberFormatException e) {
            // Affichage d'un message d'erreur en cas de saisie incorrecte pour l'ID
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un ID valide !");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            // Affichage d'un message d'erreur si les champs ne sont pas correctement
            // remplis
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void resetFields() {
        // Réinitialisation des champs
        idField.clear();
        nomEnseignantField.clear();
        contactEnseignantField.clear();
        classeComboBox.getSelectionModel().clearSelection();
        matiereComboBox.getSelectionModel().clearSelection();
        jourComboBox.getSelectionModel().clearSelection();
        heureComboBox.getSelectionModel().clearSelection();
    }
}
