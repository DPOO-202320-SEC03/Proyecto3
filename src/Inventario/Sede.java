package Inventario;

import java.io.*;
import java.util.*;

public class Sede {

    private String nombreSede;
    private String ubicacion;
    private String horariosDeAtencion;

    public Sede(String nombreSede, String ubicacion, String horariosDeAtencion, Catalogo catalogo) {
        this.nombreSede = nombreSede;
        this.ubicacion = ubicacion;
        this.horariosDeAtencion = horariosDeAtencion;
    }
    
    private ArrayList<String> getInfoSede() {
        // TODO implement here
        return null;
    }

    private Catalogo getVehiculosPorSede() {
        // TODO implement here
        return null;
    }

}