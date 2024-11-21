import java.util.ArrayList;
public class Etudiant extends Utilisateur {
        //ATTRIBUTS
    private ArrayList<Formation> inscriptions;

        //METHODES
    //CONSTRUCTEUR
    public Etudiant(String nom, String email, String motDePasse){
        super(nom, email, motDePasse);
        inscriptions = new ArrayList<Formation>();
    }

    void sinscrireFormation(Formation formation){
        inscriptions.add(formation);
    }
}
