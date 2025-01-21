package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Seance {

    private StringProperty classe;
    private StringProperty matiere;
    private StringProperty jour;
    private StringProperty heure;
    private StringProperty enseignantNom;

    public Seance(String classe, String matiere, String jour, String heure, String enseignantNom) {
        this.classe = new SimpleStringProperty(classe);
        this.matiere = new SimpleStringProperty(matiere);
        this.jour = new SimpleStringProperty(jour);
        this.heure = new SimpleStringProperty(heure);
        this.enseignantNom = new SimpleStringProperty(enseignantNom);
    }

    public String getClasse() {
        return classe.get();
    }

    public StringProperty classeProperty() {
        return classe;
    }

    public String getMatiere() {
        return matiere.get();
    }

    public StringProperty matiereProperty() {
        return matiere;
    }

    public String getJour() {
        return jour.get();
    }

    public StringProperty jourProperty() {
        return jour;
    }

    public String getHeure() {
        return heure.get();
    }

    public StringProperty heureProperty() {
        return heure;
    }

    public String getEnseignantNom() {
        return enseignantNom.get();
    }

    public StringProperty enseignantNomProperty() {
        return enseignantNom;
    }
}
