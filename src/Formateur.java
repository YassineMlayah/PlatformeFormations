import java.util.ArrayList;
public class Formateur extends Utilisateur {
        //ATTRIBUTS
    private ArrayList<Formation> formations;

        //METHODES
    //CONSTRUCTEUR
    public Formateur(String nom, String email, String motDePasse){
        super(nom, email, motDePasse);
        formations = new ArrayList<Formation>();
    }

    void ajouterFormation(Formation formation){
        formations.add(formation);
    }
}
