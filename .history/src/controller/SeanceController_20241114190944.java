package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Seance;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class SeanceController {

    @FXML
    private ComboBox<String> classeComboBox;
    @FXML
    private ComboBox<String> matiereComboBox;
    @FXML
    private ComboBox<String> matiereComboBox1; // pour l'emploi du temps
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
    @FXML
    private TextField idTextField;

    private ObservableList<Seance> seancesList = FXCollections.observableArrayList();

    // Méthode pour rechercher les séances en fonction de la classe et de la matière
    @FXML
    private void chercherSeance() {
        String selectedClasse = classeComboBox.getValue();
        String selectedMatiere = matiereComboBox.getValue();

        // Logique de recherche des séances dans la base de données
        // Pour l'instant, ajoutons des données fictives
        seancesList.clear(); // Effacer la liste avant de remplir

        if (selectedClasse != null && selectedMatiere != null) {
            // Ajouter des séances fictives en fonction de la classe et de la matière
            seancesList.add(new Seance(1, selectedClasse, selectedMatiere, "Lundi", "8h00", "M. Dupont", "0123456789"));
            seancesList
                    .add(new Seance(2, selectedClasse, selectedMatiere, "Mardi", "10h00", "Mme Durand", "0987654321"));
        }

        // Mettre à jour la TableView avec les séances trouvées
        resultList.setItems(seancesList);
    }

    // Méthode pour supprimer une séance en fonction de l'ID
    @FXML
    private void supprimerSeance() {
        String idString = idTextField.getText();
        if (!idString.isEmpty()) {
            try {
                int idToDelete = Integer.parseInt(idString);
                seancesList.removeIf(seance -> seance.getId() == idToDelete);
            } catch (NumberFormatException e) {
                System.out.println("ID invalide : " + idString);
            }
        }
    }

    // Initialisation de la TableView avec les colonnes
    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        classeColumn.setCellValueFactory(cellData -> cellData.getValue().classeProperty());
        matiereColumn.setCellValueFactory(cellData -> cellData.getValue().matiereProperty());
        jourColumn.setCellValueFactory(cellData -> cellData.getValue().jourProperty());
        heureColumn.setCellValueFactory(cellData -> cellData.getValue().heureProperty());
        nomEnseignantColumn.setCellValueFactory(cellData -> cellData.getValue().nomEnseignantProperty());
        contactEnseignantColumn.setCellValueFactory(cellData -> cellData.getValue().contactEnseignantProperty());

        // Exemple de données à afficher
        seancesList.add(new Seance(1, "Classe A", "Mathématiques", "Lundi", "8h00", "M. Dupont", "0123456789"));
        seancesList.add(new Seance(2, "Classe B", "Physique", "Mardi", "10h00", "Mme Durand", "0987654321"));
        resultList.setItems(seancesList);
    }
}
