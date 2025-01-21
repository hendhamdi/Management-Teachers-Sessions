package model;

public class Seance {
    private int id; // Ajoutez un attribut pour l'id
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private String matriculeEnseignant;
    private String nomEnseignant; // Ajoutez un attribut pour le nom de l'enseignant
    private String contactEnseignant; // Ajoutez un attribut pour le contact de l'enseignant

    // Constructeur avec tous les paramètres
    public Seance(int id, String classe, String matiere, String jour, String heure,
            String matriculeEnseignant, String nomEnseignant, String contactEnseignant) {
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
