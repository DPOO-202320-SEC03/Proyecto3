package SistemaLogin;

import java.io.Serializable;

public class Usuario implements Serializable {

    protected String username;
    protected String password;
    protected int nivelDeAcceso;
    protected String nombres;
    protected String apellidos;
    protected String celular;
    protected String correo;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNombre() {
        return this.nombres;
    }

    public int getNivelDeAcceso() {
        return this.nivelDeAcceso;
    }

}