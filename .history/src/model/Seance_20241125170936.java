package model;

public class Seance {
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private String matriculeEnseignant;
    private String nomEnseignant; // Optionnel
    private String contactEnseignant; // Optionnel
    private int seanceId; // Champ pour l'identifiant

    // Constructeur pour la première interface (sans nom et contact enseignant)
    public Seance(String classe, String matiere, String jour, String heure, String matriculeEnseignant) {
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.matriculeEnseignant = matriculeEnseignant;
    }

    // Constructeur pour la deuxième interface (avec id, nom et contact enseignant)
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

    // Getters pour toutes les propriétés
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
