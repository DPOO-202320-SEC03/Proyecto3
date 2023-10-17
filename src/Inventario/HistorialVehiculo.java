package Inventario;

import java.io.Serializable;
import java.util.*;

public class HistorialVehiculo implements Serializable {

    private HashMap<String, String> logEventos;

    /**
     *  metodo que crea un hasmap con el historial de eventos de un vehiculo
     */
    public HistorialVehiculo () {
        this.logEventos = new HashMap<String, String>();
    }

    /**
     * metodo usado para crear un nuevo evento para el log 
     * @param fecha fecha en la que se realizo el evento
     * @param evento el evento 
     */
    public void addEvent(String fecha, String evento) {
        this.logEventos.put(fecha, evento);
    }

    /**
     * @return retorna el log con todos los eventos
     */
    public HashMap<String, String> getLogEventos() {
        return this.logEventos;
    }
}