package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Seance;

public class SeanceController {

    @FXML
    private ComboBox<String> classeComboBox;
    @FXML
    private ComboBox<String> matiereComboBox;
    @FXML
    private TableView<Seance> resultList;
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
    @FXML
    private TextField idTextField;

    private ObservableList<Seance> seanceList = FXCollections.observableArrayList();

    // Méthode pour initialiser le contrôleur
    @FXML
    public void initialize() {
        // Définir les colonnes de la TableView
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        classeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClasse()));
        matiereColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMatiere()));
        jourColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJour()));
        heureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeure()));
        nomEnseignantColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomEnseignant()));
        contactEnseignantColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactEnseignant()));

        // Remplir la ComboBox (Classes et Matières)
        classeComboBox.setItems(FXCollections.observableArrayList("Classe 1", "Classe 2", "Classe 3"));
        matiereComboBox.setItems(FXCollections.observableArrayList("Math", "Physique", "Informatique"));

        // Ajouter quelques séances pour démonstration
        seanceList.add(new Seance(1, "Classe 1", "Math", "Lundi", "8:00", "M. Dupont", "123456789"));
        seanceList.add(new Seance(2, "Classe 2", "Physique", "Mardi", "10:00", "Mme Martin", "987654321"));

        // Affecter la liste d'éléments à la TableView
        resultList.setItems(seanceList);
    }

    // Méthode pour rechercher les séances
    @FXML
    public void chercherSeance() {
        String selectedClasse = classeComboBox.getValue();
        String selectedMatiere = matiereComboBox.getValue();

        // Simuler la recherche des séances en filtrant la liste
        ObservableList<Seance> filteredList = FXCollections.observableArrayList();
        for (Seance seance : seanceList) {
            if ((selectedClasse == null || seance.getClasse().equals(selectedClasse)) &&
                    (selectedMatiere == null || seance.getMatiere().equals(selectedMatiere))) {
                filteredList.add(seance);
            }
        }
        resultList.setItems(filteredList);
    }

    // Méthode pour supprimer une séance
    @FXML
    public void supprimerSeance() {
        try {
            int idToDelete = Integer.parseInt(idTextField.getText());
            Seance seanceToDelete = null;

            // Chercher la séance à supprimer par ID
            for (Seance seance : seanceList) {
                if (seance.getId() == idToDelete) {
                    seanceToDelete = seance;
                    break;
                }
            }

            // Si la séance est trouvée, la supprimer de la liste
            if (seanceToDelete != null) {
                seanceList.remove(seanceToDelete);
                // Réinitialiser la recherche
                resultList.setItems(seanceList);
                idTextField.clear();
            } else {
                // Si la séance n'est pas trouvée
                Alert alert = new Alert(Alert.AlertType.WARNING, "Séance non trouvée!", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID invalide!", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
