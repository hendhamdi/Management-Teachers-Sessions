package model;

public class Seance {
    private int id;
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private String matriculeEnseignant;
    private String nomEnseignant;
    private String contactEnseignant;

    // Constructeur
    public Seance(String classe, String matiere, String jour, String heure, String matriculeEnseignant) {
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.matriculeEnseignant = matriculeEnseignant;
    }

    // Getters et setters (si nécessaire)
    public int getId() {
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

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public String getContactEnseignant() {
        return contactEnseignant;
    }
}
