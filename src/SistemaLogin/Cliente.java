package src.SistemaLogin;

import src.Reservas&Alquiler.Reserva;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Cliente extends Persona {

    /**
     * Default constructor
     */
    public Cliente() {
    }

    /**
     * 
     */
    private int nivelDeAcceso = 0;

    /**
     * 
     */
    private int idReserva = -1;

    /**
     * @param String username 
     * @param String password 
     * @param String nombres 
     * @param String apellidos 
     * @param String celular 
     * @param int numeroLicencia 
     * @param String paisExpedicion 
     * @param String fechaVencimiento 
     * @param BufferedImage imagenLicencia 
     * @param int numeroTarjeta 
     * @param String fechaVencimiento 
     * @param String titular 
     * @param String marcaInternacional 
     * @param int ccv 
     * @return
     */
    private void registrarCliente(void String username, void String password, void String nombres, void String apellidos, void String celular, void int numeroLicencia, void String paisExpedicion, void String fechaVencimiento, void BufferedImage imagenLicencia, void int numeroTarjeta, void String fechaVencimiento, void String titular, void String marcaInternacional, void int ccv) {
        // TODO implement here
        return null;
    }

    /**
     * @param String nombreCategoria 
     * @param String sedeRecoger 
     * @param String fechaRecoger 
     * @param String horaRecoger 
     * @param String sedeEntregar 
     * @param String fechaEntregar 
     * @param String horaRangoEntregar 
     * @return
     */
    private void reservarVehiculo(void String nombreCategoria, void String sedeRecoger, void String fechaRecoger, void String horaRecoger, void String sedeEntregar, void String fechaEntregar, void String horaRangoEntregar) {
        // TODO implement here
        return null;
    }

    /**
     * @param Boolean alterarReserva 
     * @param Boolean otrosConductores 
     * @return
     */
    private void alquilarVehiculo(void Boolean alterarReserva, void Boolean otrosConductores) {
        // TODO implement here
        return null;
    }

    /**
     * @param int idReserva 
     * @param String nuevaSedeEntregar 
     * @param String nuevaFechaEntregar 
     * @param String nuevaHoraRangoEntregar 
     * @return
     */
    private void alterarReserva(void int idReserva, void String nuevaSedeEntregar, void String nuevaFechaEntregar, void String nuevaHoraRangoEntregar) {
        // TODO implement here
        return null;
    }

    /**
     * @param String fechaRecoger 
     * @param String fechaEntregar 
     * @param Vehiculo vehiculo 
     * @return
     */
    private int calcularValorAlquiler(void String fechaRecoger, void String fechaEntregar, void Vehiculo vehiculo) {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    private Reserva getReserva() {
        // TODO implement here
        return null;
    }

    /**
     * @param int id 
     * @return
     */
    private void setReserva(void int id) {
        // TODO implement here
        return null;
    }

}