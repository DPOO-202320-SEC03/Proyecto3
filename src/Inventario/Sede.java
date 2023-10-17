package Inventario;

import java.io.Serializable;
import java.util.*;

public class Sede implements Serializable {

    private String nombreSede;
    private String ubicacion;
    private String horariosDeAtencion;

    /**
     * constructor de una sede 
     * @param nombreSede nombre unico de la sede 
     * @param ubicacion ubicacion de la sede 
     * @param horariosDeAtencion horarios de atencion de la sede 
     */
    public Sede(String nombreSede, String ubicacion, String horariosDeAtencion) {
        this.nombreSede = nombreSede;
        this.ubicacion = ubicacion;
        this.horariosDeAtencion = horariosDeAtencion;
    }
    
    /**
     * metodo que sirve para saber la informacion de un sede 
     * @return la infromacino de la sede
     */
    public ArrayList<String> getInfoSede() {
        ArrayList<String> infoSede = new ArrayList<String>();
        infoSede.add(this.nombreSede);
        infoSede.add(this.ubicacion);
        infoSede.add(this.horariosDeAtencion);
        return infoSede;
    }
}