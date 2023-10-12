package SistemaLogin;

import Inventario.Vehiculo;
import Reservas.Reserva;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Cliente extends Persona {

    private int nivelDeAcceso = 0;
    private int idReserva = -1;
    private ArrayList<DatosClienteLicencia> datosClienteLicencia;
    private DatosClienteTarjeta datosClienteTarjeta;

    public Cliente(String nombres, String apellidos, String celular, DatosClienteLicencia datosClienteLicencia, DatosClienteTarjeta datosClienteTarjeta) {
        this.datosClienteLicencia = new ArrayList<DatosClienteLicencia>();
        this.datosClienteLicencia.add(datosClienteLicencia);
        this.datosClienteTarjeta = datosClienteTarjeta;
        super.nombres = nombres;
        super.apellidos = apellidos;
        super.celular = celular;
        super.nivelDeAcceso = nivelDeAcceso;
    }

    private void reservarVehiculo(String nombreCategoria, String sedeRecoger, String fechaRecoger, String horaRecoger, String sedeEntregar, String fechaEntregar, String horaRangoEntregar) {
        // TODO implement here
    }

    private void alquilarVehiculo(Boolean alterarReserva, Boolean otrosConductores) {
        // TODO implement here
    }

    private void alterarReserva(int idReserva, String nuevaSedeEntregar, String nuevaFechaEntregar, String nuevaHoraRangoEntregar) {
        // TODO implement here
    }

    private int calcularValorAlquiler(String fechaRecoger, String fechaEntregar, Vehiculo vehiculo) {
        // TODO implement here
        return 0;
    }

    private Reserva getReserva() {
        // TODO implement here
        return null;
    }

    private void setReserva(int id) {
        // TODO implement here
    }
}