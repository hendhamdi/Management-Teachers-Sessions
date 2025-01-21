package model;

public class Seance {

    private int id;
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private String nomEnseignant;
    private String contactEnseignant;

    // Constructeur
    public Seance(int id, String classe, String matiere, String jour, String heure, String nomEnseignant,
            String contactEnseignant) {
        this.id = id;
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.nomEnseignant = nomEnseignant;
        this.contactEnseignant = contactEnseignant;
    }

    // Getter et Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public String getContactEnseignant() {
        return contactEnseignant;
    }

    public void setContactEnseignant(String contactEnseignant) {
        this.contactEnseignant = contactEnseignant;
    }
}
