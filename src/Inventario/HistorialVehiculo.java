package Inventario;

import java.io.*;
import java.util.*;
import Reservas.Reserva;

public class HistorialVehiculo {

    private HashMap<String, String> logEventos;
    private ArrayList<Reserva> logReservas;

    public HistorialVehiculo () {
        this.logEventos = new HashMap<String, String>();
        this.logReservas = new ArrayList<Reserva>();
    }

    private void addEvent(String fecha, String evento) {
        // TODO implement here
    }

    private HashMap<String, String> getLogEventos() {
        // TODO implement here
        return null;
    }

    private void addReserva(Reserva reservas) {
        // TODO implement here
    }

    private ArrayList<Reserva> getLogReservas() {
        // TODO implement here
        return null;
    }

}