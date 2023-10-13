package SistemaLogin;

import java.util.*;
import java.io.Serializable;

public class Persona implements Serializable {

    protected int nivelDeAcceso;
    protected String nombres;
    protected String apellidos;
    protected String celular;

    public int getNivelDeAcceso() {
        return this.nivelDeAcceso;
    }

    public ArrayList<String> getInfoUsuario() {
        ArrayList<String> info = new ArrayList<String>();
        info.add(nombres);
        info.add(apellidos);
        info.add(celular);
        return info;
    }

}