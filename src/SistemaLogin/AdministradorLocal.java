package SistemaLogin;

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

    public void crearEmpleado(HashMap<String, Usuario> hashUsuarios, String username, String password, String nombres, String apellidos, String celular, String correo) {
        String nombreSede = getSede();
        Empleado empleado = new Empleado(username, password, nombreSede, nombres, apellidos, celular, correo);
        hashUsuarios.put(username, empleado);
    }

    public void eliminarEmpleado(HashMap<String, Usuario> hashUsuarios, String usernameEmpleado) {
        hashUsuarios.remove(usernameEmpleado);
    }

    public String getSede() {
        return this.nombreSede;
    }

}
