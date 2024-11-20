public class User{
         //ATTRIBUTES
    private String name;
    private String email;
    private String password;

        //METHODS
    //CONSTRUCTOR
    User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //GETTERS
    String getName(){
        return name;
    }

    String getEmail(){
        return email;
    }

    String getPassword(){
        return password;
    }

    //SETTERS
    void setName(String name){
        this.name = name;
    }

    void setEmail(String email){
        this.email = email;
    }

    void setPassword(String password){
        this.password = password;
    }
}