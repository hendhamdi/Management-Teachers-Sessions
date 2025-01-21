package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import model.Seance;

public class SeanceController {

    @FXML
    private ComboBox<String> classeComboBox;
    @FXML
    private ComboBox<String> matiereComboBox;
    @FXML
    private TextField idTextField;
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
    private Button chercherButton;
    @FXML
    private Button supprimerButton;

    @FXML
    public void initialize() {
        // Initialiser les ComboBox avec des valeurs
        ObservableList<String> classes = FXCollections.observableArrayList("Licence", "Master 1", "Master 2");
        classeComboBox.setItems(classes);

        ObservableList<String> matieres = FXCollections.observableArrayList("Dev.Web", "Dev.Mobile", "Anglais",
                "Réseaux", "SE");
        matiereComboBox.setItems(matieres);

        // Initialiser la TableView avec les colonnes
        resultList.getColumns().clear();
        resultList.getColumns().add(idColumn);
        resultList.getColumns().add(classeColumn);
        resultList.getColumns().add(matiereColumn);
        resultList.getColumns().add(jourColumn);
        resultList.getColumns().add(heureColumn);
        resultList.getColumns().add(nomEnseignantColumn);
        resultList.getColumns().add(contactEnseignantColumn);
    }

    // Ajouter les actions des boutons
    @FXML
    public void chercherSeance() {
        // Logique pour chercher une séance
    }

    @FXML
    public void supprimerSeance() {
        // Logique pour supprimer une séance
    }
}
