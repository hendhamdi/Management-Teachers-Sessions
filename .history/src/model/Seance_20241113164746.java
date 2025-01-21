package model;

public class Seance {
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private String idEnseignant; // Matricule de l'enseignant

    // Constructor
    public Seance(String classe, String matiere, String jour, String heure, String idEnseignant) {
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.idEnseignant = idEnseignant;
    }

    // Getters et Setters
    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(String idEnseignant) {
        this.idEnseignant = idEnseignant;
    }
}
