package SistemaLogin;

import java.io.*;
import java.util.*;

public class AdministradorLocal extends Persona {

    private int nivelDeAcceso = 2;
    private String nombreSede;

    public AdministradorLocal(String nombreSede, String nombres, String apellidos, String celular) {
        this.nombreSede = nombreSede;
        super.nombres = nombres;
        super.apellidos = apellidos;
        super.celular = celular;
        super.nivelDeAcceso = nivelDeAcceso;
    }

    private void crearEmpleado(String username, String password, String nombres, String apellidos, String celular) {
        // TODO implement here
    }

    private void eliminarEmpleado(String usernameEmpleado) {
        // TODO implement here
    }

    private String getSede() {
        return nombreSede;
    }

}