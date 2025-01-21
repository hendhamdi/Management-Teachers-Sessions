package model;

public class Seance {
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private Enseignant enseignant; // Remplacer le matricule par l'enseignant complet

    // Constructeur, getters et setters
    public Seance(String classe, String matiere, String jour, String heure, Enseignant enseignant) {
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.enseignant = enseignant;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    // Autres getters et setters pour classe, matiere, jour, heure
}
