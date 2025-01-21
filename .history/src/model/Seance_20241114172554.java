package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Seance {

    private IntegerProperty id;
    private StringProperty classe;
    private StringProperty matiere;
    private StringProperty jour;
    private StringProperty heure;
    private StringProperty nomEnseignant;
    private StringProperty contactEnseignant;

    public Seance(int id, String classe, String matiere, String jour, String heure, String nomEnseignant,
            String contactEnseignant) {
        this.id = new SimpleIntegerProperty(id);
        this.classe = new SimpleStringProperty(classe);
        this.matiere = new SimpleStringProperty(matiere);
        this.jour = new SimpleStringProperty(jour);
        this.heure = new SimpleStringProperty(heure);
        this.nomEnseignant = new SimpleStringProperty(nomEnseignant);
        this.contactEnseignant = new SimpleStringProperty(contactEnseignant);
    }

    // Getter and Setter methods
    public IntegerProperty idProperty() {
        return id;
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

    public StringProperty nomEnseignantProperty() {
        return nomEnseignant;
    }

    public StringProperty contactEnseignantProperty() {
        return contactEnseignant;
    }

    public int getId() {
        return id.get();
    }

    public String getClasse() {
        return classe.get();
    }

    public String getMatiere() {
        return matiere.get();
    }

    public String getJour() {
        return jour.get();
    }

    public String getHeure() {
        return heure.get();
    }

    public String getNomEnseignant() {
        return nomEnseignant.get();
    }

    public String getContactEnseignant() {
        return contactEnseignant.get();
    }
}
