package model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Seance {
    private int id;
    private StringProperty classe;
    private StringProperty matiere;
    private StringProperty jour;
    private StringProperty heure;
    private StringProperty nomEnseignant;
    private StringProperty contactEnseignant;

    // Constructeur
    public Seance(int id, String classe, String matiere, String jour, String heure, String nomEnseignant,
            String contactEnseignant) {
        this.id = id;
        this.classe = new SimpleStringProperty(classe);
        this.matiere = new SimpleStringProperty(matiere);
        this.jour = new SimpleStringProperty(jour);
        this.heure = new SimpleStringProperty(heure);
        this.nomEnseignant = new SimpleStringProperty(nomEnseignant);
        this.contactEnseignant = new SimpleStringProperty(contactEnseignant);
    }

    // Getter et Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasse() {
        return classe.get();
    }

    public void setClasse(String classe) {
        this.classe.set(classe);
    }

    public String getMatiere() {
        return matiere.get();
    }

    public void setMatiere(String matiere) {
        this.matiere.set(matiere);
    }

    public String getJour() {
        return jour.get();
    }

    public void setJour(String jour) {
        this.jour.set(jour);
    }

    public String getHeure() {
        return heure.get();
    }

    public void setHeure(String heure) {
        this.heure.set(heure);
    }

    public String getNomEnseignant() {
        return nomEnseignant.get();
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant.set(nomEnseignant);
    }

    public String getContactEnseignant() {
        return contactEnseignant.get();
    }

    public void setContactEnseignant(String contactEnseignant) {
        this.contactEnseignant.set(contactEnseignant);
    }
}
