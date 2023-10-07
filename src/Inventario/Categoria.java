package Inventario;

import java.io.*;
import java.util.*;

public class Categoria {

    private String nombreCategoria;
    private int rangoCategoria;
    private int vehiculosDisponibles;
    private HashMap<String, Vehiculo> hashVehiculos;
    private HashMap<String, Integer> hashTarifaPorTemporada;

    public Categoria(String nombreCategoria, int rangoCategoria, int vehiculosDisponibles) {
        this.nombreCategoria = nombreCategoria;
        this.rangoCategoria = rangoCategoria;
        this.vehiculosDisponibles = vehiculosDisponibles;
        this.hashVehiculos = new HashMap<String, Vehiculo>();
        this.hashTarifaPorTemporada = new HashMap<String, Integer>();
    }

    private String getNombreCategoria() {
        // TODO implement here
        return "";
    }

    private int getRangoCategoria() {
        // TODO implement here
        return 0;
    }

    private int getVehiculosDisponibles() {
        // TODO implement here
        return 0;
    }

    private HashMap<String, Vehiculo> getHashVehiculos() {
        // TODO implement here
        return null;
    }

    private void updateHashVehiculos(HashMap<String, Vehiculo> hashVehiculos) {
        // TODO implement here
    }

    private HashMap<String, Integer> getHashTarifaPorTemporada() {
        // TODO implement here
        return null;
    }

    private void updateHashTarifaPorTemporada(HashMap<String, Integer> hashTarifaPorTemporada) {
        // TODO implement here
    }

}