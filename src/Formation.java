public class Formation{
        //ATTRIBUTS
    private String titre;
    private String description;
    private Formateur formateur;
    private double prix;

        //METHODES
    //GETTERS
    String getTitre(){
        return titre;
    }

    String getDescription(){
        return description;
    }

    Formateur getFormateur(){
        return formateur;
    }

    //SETTERS
    void setTitre(String titre){
        this.titre = titre;
    }

    void setDescription(String description){
        this.description = description;
    }

    void setFormateur(Formateur formateur){
        this.formateur = formateur;
    }
}
