package Reservas;

import java.io.Serializable;

public class Reserva implements Serializable {

    public static int totalDeReservas = 0;
    protected int idReserva;

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int id) {
    	this.idReserva = id;
    }

    public String getResumen() {
        
        return "ID Reserva: " + idReserva;
    }

}