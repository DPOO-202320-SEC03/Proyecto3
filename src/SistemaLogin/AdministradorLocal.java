package SistemaLogin;

import java.util.*;


public class AdministradorLocal extends Usuario {

    private int nivelDeAcceso = 2;
    private String nombreSede;

    /**
     * metodo constructor de el administrador local
     * @param username un usuario unico para el administrador local
     * @param password una contraseña para el administrador local
     * @param nombreSede el nombre de la sede en la que se ubica el administrador local
     * @param nombres el o los nombres de el administrador local
     * @param apellidos los apellidos de el administrador local
     * @param celular el numero de celular de el administrador local
     * @param correo el correro de contacto de el administrador local
     */
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
    /**
     * metodo para crear un nuevo emplearo
     * @param hashUsuarios hashmap donde se encuentra la informacion de todos los usuarios del sistema
     * @param username un usuario unico para el empleado
     * @param password una contraseña para el empleado
     * @param nombreSede el nombre de la sede en la que se ubica el empleado
     * @param nombres el o los nombres de el empleado
     * @param apellidos los apellidos de el empleado
     * @param celular el numero de celular de el empleado
     * @param correo el correro de contacto de el empleado
     */
    public void crearEmpleado(HashMap<String, Usuario> hashUsuarios, String username, String password, String nombres, String apellidos, String celular, String correo) {
        String nombreSede = getSede();
        Empleado empleado = new Empleado(username, password, nombreSede, nombres, apellidos, celular, correo);
        hashUsuarios.put(username, empleado);
    }

    /**
     * metodo para eliminar un empleado
     * @param hashUsuarios hashmap donde se encuentra la informacion de todos los usuarios del sistema
     * @param usernameEmpleado usuario del empleado a eliminar
     */
    public void eliminarEmpleado(HashMap<String, Usuario> hashUsuarios, String usernameEmpleado) {
        // en la clase AppReservas se hace la verificacion que sea tanto que el usuario exista como que sea un empleado
        hashUsuarios.remove(usernameEmpleado);
    }

    public String getSede() {
        return this.nombreSede;
    }

}
