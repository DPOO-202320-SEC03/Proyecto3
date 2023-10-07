package SistemaLogin;

public class Usuario {

    private String username;
    private String password;
    private Persona persona;

    public Usuario(String username, String password, Persona persona) {
        this.username = username;
        this.password = password;
        this.persona = persona;
    }

    private String getUsername() {
        // TODO implement here
        return "";
    }

    private String getPassword() {
        // TODO implement here
        return "";
    }
    private Persona getPersona() {
        // TODO implement here
        return null;
    }

}