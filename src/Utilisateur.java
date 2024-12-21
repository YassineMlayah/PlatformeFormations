public class Utilisateur {
        // ATTRIBUTS
    private String nom;
    private String email;
    private String motDePasse;

        // METHODES
    // CONSTRUCTEUR
    public Utilisateur(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // GETTERS
    String getnom() {
        return nom;
    }

    String getEmail() {
        return email;
    }

    String getmotDePasse() {
        return motDePasse;
    }

    // SETTERS
    void setnom(String nom) {
        this.nom = nom;
    }

    void setEmail(String email) {
        this.email = email;
    }

    void setmotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}