package Inventario;

import java.io.*;
import java.util.*;

public class Sede {

    private String nombreSede;
    private String ubicacion;
    private String horariosDeAtencion;
    private Catalogo catalogo;

    public Sede(String nombreSede, String ubicacion, String horariosDeAtencion, Catalogo catalogo) {
        this.nombreSede = nombreSede;
        this.ubicacion = ubicacion;
        this.horariosDeAtencion = horariosDeAtencion;
        this.catalogo = catalogo;
    }
    
    private ArrayList<String> getInfoSede() {
        // TODO implement here
        return null;
    }

    private Catalogo getCatalogoSede() {
        // TODO implement here
        return null;
    }

}