public class Seance {
    private String classe;
    private String matiere;
    private String jour;
    private String heure;
    private String matriculeEnseignant;

    // Constructeur et getters
    public Seance(String classe, String matiere, String jour, String heure, String matriculeEnseignant) {
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.matriculeEnseignant = matriculeEnseignant;
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
}
