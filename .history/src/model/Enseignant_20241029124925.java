package model;

public class Enseignant {
    private String matricule;
    private String nom;
    private String contact;

    public Enseignant(String matricule, String nom, String contact) {
        this.matricule = matricule;
        this.nom = nom;
        this.contact = contact;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getContact() {
        return contact;
    }
}
