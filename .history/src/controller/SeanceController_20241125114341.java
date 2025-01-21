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
import util.DatabaseConnection;

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
    private TableColumn<Seance, String> matriculeEnseignantColumn;
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
        matriculeEnseignantColumn.setCellValueFactory(new PropertyValueFactory<>("matriculeEnseignant"));
        contactEnseignantColumn.setCellValueFactory(new PropertyValueFactory<>("contactEnseignant"));

        // Initialisation des ComboBox avec des valeurs d'exemple
        ObservableList<String> classes = FXCollections.observableArrayList("Licence", "Master 1", "Master 2");
        classeComboBox.setItems(classes);

        ObservableList<String> matieres = FXCollections.observableArrayList("Dev.Web", "Dev.Mobile", "Anglais",
                "Réseaux", "SE");
        matiereComboBox.setItems(matieres);
    }

    // Recherche des séances par classe et matière
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
        String query = "SELECT s.id, s.classe, s.matiere, s.jour, s.heure, e.nom AS nomEnseignant, e.contact AS contactEnseignant, e.matricule AS matriculeEnseignant "
                + "FROM seances s "
                + "JOIN enseignants e ON s.id_enseignant = e.id "
                + "WHERE s.classe = ? AND s.matiere = ? "
                + "ORDER BY s.jour, s.heure"; // Ordre par jour et heure

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, classe);
            statement.setString(2, matiere);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Seance seance = new Seance(
                        resultSet.getInt("id"),
                        resultSet.getString("classe"),
                        resultSet.getString("matiere"),
                        resultSet.getString("jour"),
                        resultSet.getString("heure"),
                        resultSet.getString("matriculeEnseignant"),
                        resultSet.getString("nomEnseignant"),
                        resultSet.getString("contactEnseignant"));
                seances.add(seance);
            }

            resultList.setItems(seances);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des données.");
        }
    }

    // Recherche de l'emploi du temps de la semaine par classe
    @FXML
    public void chercherEmploiTemps() {
        String classe = classeComboBox.getValue();

        if (classe == null) {
            System.out.println("Veuillez sélectionner une classe !");
            return;
        }

        ObservableList<Seance> seances = FXCollections.observableArrayList();
        String query = "SELECT s.id, s.classe, s.matiere, s.jour, s.heure, e.nom AS nomEnseignant, e.contact AS contactEnseignant, e.matricule AS matriculeEnseignant "
                + "FROM seances s "
                + "JOIN enseignants e ON s.id_enseignant = e.id "
                + "WHERE s.classe = ? "
                + "ORDER BY s.jour, s.heure"; // Ordre par jour et heure

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, classe);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Seance seance = new Seance(
                        resultSet.getInt("id"),
                        resultSet.getString("classe"),
                        resultSet.getString("matiere"),
                        resultSet.getString("jour"),
                        resultSet.getString("heure"),
                        resultSet.getString("matriculeEnseignant"),
                        resultSet.getString("nomEnseignant"),
                        resultSet.getString("contactEnseignant"));

                seances.add(seance);
            }

            resultList.setItems(seances);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des données.");
        }
    }

    // Suppression d'une séance par ID
    @FXML
    public void supprimerSeance() {
        String id = idTextField.getText();
        if (id == null || id.isEmpty()) {
            System.out.println("Veuillez entrer un ID !");
            return;
        }

        String query = "DELETE FROM seances WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the ID parameter for the SQL query
            statement.setInt(1, Integer.parseInt(id));

            // Execute the delete query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Séance supprimée avec succès !");
                // Refresh the table to reflect the changes
                chercherSeance(); // You can also call chercherEmploiTemps() if you want to refresh the schedule
                                  // view
            } else {
                System.out.println("Aucune séance trouvée avec cet ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression de la séance.");
        }
    }

}
