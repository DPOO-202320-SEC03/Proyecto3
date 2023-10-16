package SistemaLogin;

import Inventario.Catalogo;
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

    public String reservarVehiculo(HashMap<String, Reserva> hashReservas, Catalogo catalogo, String nombreCategoria, String sedeRecoger, String fechaRecoger, String horaRecoger, String sedeEntregar, String fechaEntregar, String horaRangoEntregar) {
        // TODO implement here
        // En esta parte debe cambiar el estado de un vehiculo a reservado, tambien debe crearse una reserva nueva y guardarla en el hash de reservas, adicionalmente se debe cambiar el id de reserva del empleado
        // al final debe mostrarle al usuario el resumen de la reserva
        return "";
    }

    public String alterarReserva(HashMap<String, Reserva> hashReservas, Catalogo catalogo, int idReserva, String nuevaSedeEntregar, String nuevaFechaEntregar, String nuevaHoraRangoEntregar, int otrosConductores) {
        // TODO implement here
        // En esta parte se tiene que alterar la reserva anteriormente creada
        // al final debe mostrarle al usuario el resumen de la reserva editada
        return "";
    }

    public ArrayList<DatosClienteLicencia> getDatosClienteLicencia() {
        return this.datosClienteLicencia;
    }

    public Integer getIdReserva() {
        return this.idReserva;
    }

    public void setIdReserva(int id) {
        this.idReserva = id;
    }
}