package model;

import com.mysql.cj.conf.StringProperty;

import javafx.beans.property.SimpleStringProperty;

public class Seance {
    private SimpleStringProperty id;
    private SimpleStringProperty classe;
    private SimpleStringProperty matiere;
    private SimpleStringProperty jour;
    private SimpleStringProperty heure;
    private SimpleStringProperty enseignantNom;
    private SimpleStringProperty enseignantContact;

    // Constructeur et getters/setters

    public Seance(String id, String classe, String matiere, String jour, String heure, String enseignantNom,
            String enseignantContact) {
        this.id = new SimpleStringProperty(id);
        this.classe = new SimpleStringProperty(classe);
        this.matiere = new SimpleStringProperty(matiere);
        this.jour = new SimpleStringProperty(jour);
        this.heure = new SimpleStringProperty(heure);
        this.enseignantNom = new SimpleStringProperty(enseignantNom);
        this.enseignantContact = new SimpleStringProperty(enseignantContact);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty classeProperty() {
        return classe;
    }

    public SimpleStringProperty matiereProperty() {
        return matiere;
    }

    public SimpleStringProperty jourProperty() {
        return jour;
    }

    public SimpleStringProperty heureProperty() {
        return heure;
    }

    public SimpleStringProperty enseignantNomProperty() {
        return enseignantNom;
    }

    public SimpleStringProperty enseignantContactProperty() {
        return enseignantContact;
    }
}
