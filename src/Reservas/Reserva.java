package Reservas;

import java.io.*;
import java.util.*;

public class Reserva {

    public static int totalDeReservas = 0;
    private int idReserva;

    public Reserva() {
        this.idReserva = totalDeReservas++;
    }

    private int getIdReserva() {
       
        return idReserva;
    }

    private void setIdReserva(int id) {
       
    	this.idReserva = id;
    }

    private String getResumen() {
        
        return "ID Reserva: " + idReserva;
    }

}