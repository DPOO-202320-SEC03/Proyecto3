package SistemaLogin;

import Inventario.Vehiculo;
import Reservas.Reserva;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Cliente extends Usuario {

    private int nivelDeAcceso = 0;
    private int idReserva = -1;
    private ArrayList<DatosClienteLicencia> datosClienteLicencia;
    private DatosClienteTarjeta datosClienteTarjeta;

    public Cliente(String username, String password, String nombres, String apellidos, String celular, String correo, DatosClienteLicencia datosClienteLicencia, DatosClienteTarjeta datosClienteTarjeta) {
        super.username = username;
        super.password = password;        
        this.datosClienteLicencia = new ArrayList<DatosClienteLicencia>();
        this.datosClienteLicencia.add(datosClienteLicencia);
        this.datosClienteTarjeta = datosClienteTarjeta;
        super.nombres = nombres;
        super.apellidos = apellidos;
        super.celular = celular;
        super.correo = correo;
        super.nivelDeAcceso = nivelDeAcceso;
    }

    public void reservarVehiculo(String nombreCategoria, String sedeRecoger, String fechaRecoger, String horaRecoger, String sedeEntregar, String fechaEntregar, String horaRangoEntregar) {
        // TODO implement here
    }

    public void alquilarVehiculo(Boolean alterarReserva, Boolean otrosConductores) {
        // TODO implement here
    }

    public void alterarReserva(int idReserva, String nuevaSedeEntregar, String nuevaFechaEntregar, String nuevaHoraRangoEntregar) {
        // TODO implement here
    }

    public int calcularValorAlquiler(String fechaRecoger, String fechaEntregar, Vehiculo vehiculo) {
        // TODO implement here
        return 0;
    }

    public Reserva getReserva() {
        // TODO implement here
        return null;
    }

    public void setReserva(int id) {
        // TODO implement here
    }
}