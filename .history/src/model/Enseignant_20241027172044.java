package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Enseignant {
    private final StringProperty matricule;
    private final StringProperty nom;
    private final StringProperty contact;

    public Enseignant(String matricule, String nom, String contact) {
        this.matricule = new SimpleStringProperty(matricule);
        this.nom = new SimpleStringProperty(nom);
        this.contact = new SimpleStringProperty(contact);
    }

    public String getMatricule() {
        return matricule.get();
    }

    public void setMatricule(String matricule) {
        this.matricule.set(matricule);
    }

    public StringProperty matriculeProperty() {
        return matricule;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public StringProperty contactProperty() {
        return contact;
    }
}
