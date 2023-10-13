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

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    public Persona getPersona() {
        return this.persona;
    }

}