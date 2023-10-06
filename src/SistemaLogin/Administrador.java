package src.SistemaLogin;

import src.Inventario.Vehiculo;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Administrador extends Persona {

    /**
     * Default constructor
     */
    public Administrador() {
    }

    /**
     * 
     */
    private int nivelDeAcceso = 3;

    /**
     * 
     */
    private String nombreSede;

    /**
     * @param String placa 
     * @param String marca 
     * @param String modelo 
     * @param String color 
     * @param String tipoDeTransmision 
     * @param String tipoDeDireccion 
     * @return
     */
    private Vehiculo crearVehiculo(void String placa, void String marca, void String modelo, void String color, void String tipoDeTransmision, void String tipoDeDireccion) {
        // TODO implement here
        return null;
    }

    /**
     * @param String placa 
     * @param String marca 
     * @param String modelo 
     * @param String color 
     * @param String tipoDeTransmision 
     * @param String tipoDeDireccion 
     * @param String nombreSede 
     * @param Vehiculo vehiculo 
     * @param String nombreCategoria 
     * @param Boolean disponibleParaAlquilar 
     * @param String fechaDisponibilidad 
     * @return
     */
    private void agregarVehiculo(void String placa, void String marca, void String modelo, void String color, void String tipoDeTransmision, void String tipoDeDireccion, void String nombreSede, void Vehiculo vehiculo, void String nombreCategoria, void Boolean disponibleParaAlquilar, void String fechaDisponibilidad) {
        // TODO implement here
        return null;
    }

    /**
     * @param String placa 
     * @return
     */
    private void eliminarVehiculo(void String placa) {
        // TODO implement here
        return null;
    }

    /**
     * @param String placa 
     * @return
     */
    private String estadoVehiculo(void String placa) {
        // TODO implement here
        return "";
    }

    /**
     * @param String nombreSede 
     * @param String ubicacion 
     * @param String horariosDeAtencion 
     * @return
     */
    private void crearSede(void String nombreSede, void String ubicacion, void String horariosDeAtencion) {
        // TODO implement here
        return null;
    }

    /**
     * @param String username 
     * @param String password 
     * @param String nombres 
     * @param String apellidos 
     * @param String celular 
     * @param String nombreSede 
     * @return
     */
    private void crearAdministradorLocal(void String username, void String password, void String nombres, void String apellidos, void String celular, void String nombreSede) {
        // TODO implement here
        return null;
    }

    /**
     * @param String nombreSede 
     * @return
     */
    private void eliminarAdministradorLocal(void String nombreSede) {
        // TODO implement here
        return null;
    }

    /**
     * @param String nombreUsuario 
     * @return
     */
    private void eliminarUsuario(void String nombreUsuario) {
        // TODO implement here
        return null;
    }

    /**
     * @param int tarifaExtraDiaria 
     * @param String nombreSeguro 
     * @param String descripcionSeguro 
     * @return
     */
    private void crearSeguro(void int tarifaExtraDiaria, void String nombreSeguro, void String descripcionSeguro) {
        // TODO implement here
        return null;
    }

    /**
     * @param String placa 
     * @param String sedeOrigen 
     * @param String sedeDestino 
     * @param String fechaRecoger 
     * @param String horaRecoger 
     * @param String fechaEntrega 
     * @return
     */
    private void trasladarVehiculo(void String placa, void String sedeOrigen, void String sedeDestino, void String fechaRecoger, void String horaRecoger, void String fechaEntrega) {
        // TODO implement here
        return null;
    }

}