package Inventario;

import java.io.*;
import java.util.*;

import SistemaLogin.AdministradorLocal;
import SistemaLogin.Empleado;

public class Sede {

    private String nombreSede;
    private String ubicacion;
    private String horariosDeAtencion;
    private AdministradorLocal administradorLocal;
    private ArrayList<Empleado> listaEmpleados;

    public Sede(String nombreSede, String ubicacion, String horariosDeAtencion, AdministradorLocal administradorLocal) {
        this.nombreSede = nombreSede;
        this.ubicacion = ubicacion;
        this.horariosDeAtencion = horariosDeAtencion;
        this.administradorLocal = administradorLocal;
        this.listaEmpleados = new ArrayList<Empleado>();
    }
    
    private ArrayList<String> getInfoSede() {
        ArrayList<String> infoSede = new ArrayList<String>();
        infoSede.add(this.nombreSede);
        infoSede.add(this.ubicacion);
        infoSede.add(this.horariosDeAtencion);
        return infoSede;
    }

    private Catalogo getVehiculosPorSede() {
        // TODO implement here
        return null;
    }

}