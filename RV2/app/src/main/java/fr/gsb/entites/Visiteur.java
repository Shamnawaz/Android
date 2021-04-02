package fr.gsb.entites;

public class Visiteur {

    private String matricule;
    private String mdp;
    private String nom;
    private String prenom;

    public Visiteur(String matricule, String mdp, String nom, String prenom){

        this.setMatricule(matricule);
        this.setMdp(mdp);
        this.setNom(nom);
        this.setPrenom(prenom);

    }

    public Visiteur(String matricule, String mdp){

        this.setMatricule(matricule);
        this.setMdp(mdp);
    }

    public Visiteur(){}

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Visiteur{" +
                "matricule='" + matricule + '\'' +
                ", mdp='" + mdp + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
