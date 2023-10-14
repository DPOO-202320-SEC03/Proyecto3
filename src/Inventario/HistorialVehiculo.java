package Inventario;

import java.util.*;

public class HistorialVehiculo {

    private HashMap<String, String> logEventos;

    public HistorialVehiculo () {
        this.logEventos = new HashMap<String, String>();
    }

    public void addEvent(String fecha, String evento) {
        this.logEventos.put(fecha, evento);
    }

    public HashMap<String, String> getLogEventos() {
        return this.logEventos;
    }
}