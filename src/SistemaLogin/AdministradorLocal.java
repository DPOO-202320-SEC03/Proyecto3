package SistemaLogin;

import java.io.*;
import java.util.*;

public class AdministradorLocal extends Usuario {

    private int nivelDeAcceso = 2;
    private String nombreSede;

    public AdministradorLocal(String username, String password, String nombreSede, String nombres, String apellidos, String celular, String correo) {
        super.username = username;
        super.password = password;
        this.nombreSede = nombreSede;
        super.nombres = nombres;
        super.apellidos = apellidos;
        super.celular = celular;
        super.correo = correo;
        super.nivelDeAcceso = nivelDeAcceso;
    }

    public void crearEmpleado(String username, String password, String nombres, String apellidos, String celular) {
        // TODO implement here
    }

    public void eliminarEmpleado(String usernameEmpleado) {
        // TODO implement here Primero se revisa que el usuario sea un empleado, despues se elimina del hashmap y se retorna el hashmap actualizado
    }

    public String getSede() {
        return nombreSede;
    }

}