package SistemaLogin;

import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class Empleado extends Persona {

    private int nivelDeAcceso = 1;
    private String nombreSede;

    public Empleado(String nombreSede, String nombres, String apellidos, String celular) {
        this.nombreSede = nombreSede;
        super.nombres = nombres;
        super.apellidos = apellidos;
        super.celular = celular;
        super.nivelDeAcceso = nivelDeAcceso;
    }

    private void alquilarVehiculo(String usernameClienteAlquiler, String fechaDevolucion, String sedeDevolucion) {
        // TODO implement here
    }

    private void otrosConductoresAgregarLicencia(String usernameClienteAlquiler, int numeroLicencia, String paisExpedicion, String fechaVencimiento, BufferedImage imagenLicencia) {
        // TODO implement here
    }

    private void recibirVehiculo(String usernameClienteAlquiler, Boolean necesitaMantenimiento) {
        // TODO implement here
    }

    private void necesitaMantenimiento(String fechaEstimadaRegreso, String fechaRecibido, String descripcionMantenimiento) {
        // TODO implement here
    }

    private void vehiculoListoParaAlquiler(String placa) {
        // TODO implement here
    }

    private String getSede() {
        // TODO implement here
        return "";
    }

}