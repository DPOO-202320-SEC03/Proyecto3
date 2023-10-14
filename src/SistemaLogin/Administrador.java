package SistemaLogin;

import Inventario.Vehiculo;

import java.io.*;
import java.util.*;

public class Administrador extends Usuario {

    private int nivelDeAcceso = 3;

    public Administrador(String username, String password, String nombres, String apellidos, String celular, String correo) {
        super.username = username;
        super.password = password;
        super.nombres = nombres;
        super.apellidos = apellidos;
        super.celular = celular;
        super.correo = correo;
        super.nivelDeAcceso = nivelDeAcceso;
    }

    private Vehiculo crearVehiculo(String placa, String marca, String modelo, String color, String tipoDeTransmision, String tipoDeDireccion) {
        // TODO implement here
        return null;
    }

    private void agregarVehiculo(String placa, String marca, String modelo, String color, String tipoDeTransmision, String tipoDeDireccion, String nombreSede, Vehiculo vehiculo, String nombreCategoria, Boolean disponibleParaAlquilar, String fechaDisponibilidad) {
        // TODO implement here
    }

    private void eliminarVehiculo(String placa) {
        // TODO implement here
    }

    private String estadoVehiculo(String placa) {
        // TODO implement here
        return "";
    }

    private void crearSede(String nombreSede, String ubicacion, String horariosDeAtencion) {
        // TODO implement here
    }

    private void crearAdministradorLocal(String username, String password, String nombres, String apellidos, String celular, String nombreSede) {
        // TODO implement here
    }

    private void eliminarAdministradorLocal(String nombreSede) {
        // TODO implement here
    }

    private void eliminarUsuario(String nombreUsuario) {
        // TODO implement here
    }

    private void crearSeguro(int tarifaExtraDiaria, String nombreSeguro, String descripcionSeguro) {
        // TODO implement here
    }

    private void trasladarVehiculo(String placa, String sedeOrigen, String sedeDestino, String fechaRecoger, String horaRecoger, String fechaEntrega) {
        // TODO implement here
    }
}