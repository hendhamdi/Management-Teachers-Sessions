package model;

public class Seance {
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private String matriculeEnseignant;
    private String nomEnseignant;
    private String contactEnseignant;
    private int seanceId;

    // Constructor pour la 1ere interface
    public Seance(String classe, String matiere, String jour, String heure, String matriculeEnseignant) {
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.matriculeEnseignant = matriculeEnseignant;
    }

    // Constructor pour la 2eme interface
    public Seance(int seanceId, String classe, String matiere, String jour, String heure, String matriculeEnseignant,
            String nomEnseignant, String contactEnseignant) {
        this.seanceId = seanceId;
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.matriculeEnseignant = matriculeEnseignant;
        this.nomEnseignant = nomEnseignant;
        this.contactEnseignant = contactEnseignant;
    }

    // Getters
    public int getId() {
        return seanceId;
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
