package fr.gsb.technique;
import fr.gsb.entites.Visiteur;

public class Session {

    private static Session session = null;
    private Visiteur leVisiteur;


    private Session(Visiteur unVisiteur){

        this.leVisiteur = leVisiteur;
    }

    public static Session getSession(){

        return session;
    }

    public static void ouvrir(Visiteur leVisiteur){

        session = new Session(leVisiteur);
    }

    public static void fermer(){

        session = null;
    }

    public Visiteur getVisiteur(){

        return leVisiteur;
    }
}
