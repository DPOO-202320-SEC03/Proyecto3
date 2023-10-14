package Inventario;

import java.io.Serializable;
import java.util.*;

public class Sede implements Serializable {

    private String nombreSede;
    private String ubicacion;
    private String horariosDeAtencion;

    public Sede(String nombreSede, String ubicacion, String horariosDeAtencion) {
        this.nombreSede = nombreSede;
        this.ubicacion = ubicacion;
        this.horariosDeAtencion = horariosDeAtencion;
    }
    
    public ArrayList<String> getInfoSede() {
        ArrayList<String> infoSede = new ArrayList<String>();
        infoSede.add(this.nombreSede);
        infoSede.add(this.ubicacion);
        infoSede.add(this.horariosDeAtencion);
        return infoSede;
    }
}