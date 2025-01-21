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
    private TableColumn<Seance, String> classeColumn;

    @FXML
    private TableColumn<Seance, String> matiereColumn;

    @FXML
    private TableColumn<Seance, String> jourColumn;

    @FXML
    private TableColumn<Seance, String> heureColumn;

    @FXML
    private TableColumn<Seance, String> nomColumn;

    @FXML
    private TableColumn<Seance, String> contactColumn;

    // Liste observable des séances
    private ObservableList<Seance> seancesList = FXCollections.observableArrayList();

    // Méthode pour rechercher les séances
    @FXML
    private void chercherSeance() {
        String classe = classeComboBox.getValue();
        String matiere = matiereComboBox.getValue();

        if (classe == null || matiere == null) {
            return;
        }

        // Clear previous results
        seancesList.clear();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emploidutemps_db", "root",
                "password");
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT e.nom, e.contact, s.classe, s.matiere, s.jour, s.heure " +
                                "FROM seances s JOIN enseignants e ON s.id_enseignant = e.id " +
                                "WHERE s.classe = ? AND s.matiere = ?")) {

            ps.setString(1, classe);
            ps.setString(2, matiere);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Seance seance = new Seance(
                        rs.getString("classe"),
                        rs.getString("matiere"),
                        rs.getString("jour"),
                        rs.getString("heure"),
                        rs.getString("nom"),
                        rs.getString("contact"));
                seancesList.add(seance);
            }

            resultList.setItems(seancesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Initialisation de la page
    public void initialize() {
        ObservableList<String> classes = FXCollections.observableArrayList("Licence", "Master 1", "Master 2");
        ObservableList<String> matieres = FXCollections.observableArrayList("Dev.Web", "Dev.Mobile", "Anglais",
                "Réseaux", "SE");

        classeComboBox.setItems(classes);
        matiereComboBox.setItems(matieres);

        // Initialisation des colonnes du tableau
        classeColumn.setCellValueFactory(cellData -> cellData.getValue().classeProperty());
        matiereColumn.setCellValueFactory(cellData -> cellData.getValue().matiereProperty());
        jourColumn.setCellValueFactory(cellData -> cellData.getValue().jourProperty());
        heureColumn.setCellValueFactory(cellData -> cellData.getValue().heureProperty());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        contactColumn.setCellValueFactory(cellData -> cellData.getValue().contactProperty());
    }
}
