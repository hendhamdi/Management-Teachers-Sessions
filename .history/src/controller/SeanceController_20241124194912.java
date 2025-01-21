package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        classeColumn.setCellValueFactory(new PropertyValueFactory<>("classe"));
        matiereColumn.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        jourColumn.setCellValueFactory(new PropertyValueFactory<>("jour"));
        heureColumn.setCellValueFactory(new PropertyValueFactory<>("heure"));
        nomEnseignantColumn.setCellValueFactory(new PropertyValueFactory<>("nomEnseignant"));
        contactEnseignantColumn.setCellValueFactory(new PropertyValueFactory<>("contactEnseignant"));

        // Autres initialisations pour les ComboBox
        ObservableList<String> classes = FXCollections.observableArrayList("Licence", "Master 1", "Master 2");
        classeComboBox.setItems(classes);

        ObservableList<String> matieres = FXCollections.observableArrayList("Dev.Web", "Dev.Mobile", "Anglais",
                "Réseaux", "SE");
        matiereComboBox.setItems(matieres);
    }

    // Ajouter les actions des boutons
    @FXML
    public void chercherSeance() {
        String classe = classeComboBox.getValue();
        String matiere = matiereComboBox.getValue();

        if (classe == null || matiere == null) {
            // Affiche un message d'erreur si les champs sont vides
            System.out.println("Veuillez sélectionner une classe et une matière !");
            return;
        }

        ObservableList<Seance> seances = FXCollections.observableArrayList();
        String query = "SELECT s.id, s.classe, s.matiere, s.jour, s.heure, e.nom AS nomEnseignant, e.contact AS contactEnseignant "
                +
                "FROM seances s " +
                "JOIN enseignants e ON s.id_enseignant = e.id " +
                "WHERE s.classe = ? AND s.matiere = ?";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, classe);
            stmt.setString(2, matiere);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                seances.add(new Seance(
                        rs.getInt("id"),
                        rs.getString("classe"),
                        rs.getString("matiere"),
                        rs.getString("jour"),
                        rs.getString("heure"),
                        rs.getString("nomEnseignant"),
                        rs.getString("contactEnseignant")));
            }

            resultList.setItems(seances);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des données.");
        }
    }

    @FXML
    public void supprimerSeance() {
        // Logique pour supprimer une séance
    }
}
