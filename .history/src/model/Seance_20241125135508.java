package model;

import java.util.Optional;

public class Seance {
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private String matriculeEnseignant;
    private Optional<String> nomEnseignant; // Utilisation d'Optional
    private Optional<String> contactEnseignant; // Utilisation d'Optional
    private Integer id; // Utilisation de Integer pour permettre null

    // Constructeur pour la première interface (sans nom et contact enseignant)
    public Seance(String classe, String matiere, String jour, String heure, String matriculeEnseignant) {
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.matriculeEnseignant = matriculeEnseignant;
        this.nomEnseignant = Optional.empty();
        this.contactEnseignant = Optional.empty();
    }

    // Constructeur pour la deuxième interface (avec id, nom et contact enseignant)
    public Seance(int id, String classe, String matiere, String jour, String heure, String matriculeEnseignant,
            Optional<String> nomEnseignant, Optional<String> contactEnseignant) {
        this.id = id;
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.matriculeEnseignant = matriculeEnseignant;
        this.nomEnseignant = nomEnseignant;
        this.contactEnseignant = contactEnseignant;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getClasse() {
        return classe;
    }

    public String getMatiere() {
        return matiere;
    }

    public String getJour() {
        return jour;
    }

    public String getHeure() {
        return heure;
    }

    public String getMatriculeEnseignant() {
        return matriculeEnseignant;
    }

    public Optional<String> getNomEnseignant() {
        return nomEnseignant;
    }

    public Optional<String> getContactEnseignant() {
        return contactEnseignant;
    }

    // Méthode toString pour faciliter l'affichage
    @Override
    public String toString() {
        return "Seance [id=" + id + ", classe=" + classe + ", matiere=" + matiere +
                ", jour=" + jour + ", heure=" + heure + ", matriculeEnseignant=" + matriculeEnseignant +
                ", nomEnseignant=" + nomEnseignant.orElse("Non renseigné") +
                ", contactEnseignant=" + contactEnseignant.orElse("Non renseigné") + "]";
    }
}
