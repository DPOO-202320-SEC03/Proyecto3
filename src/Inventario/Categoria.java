package Inventario;

import java.util.*;

public class Categoria {

    private String nombreCategoria;
    private int rangoCategoria;
    private int vehiculosDisponibles;
    private HashMap<String, Vehiculo> hashVehiculos;
    private HashMap<String, Integer> hashTarifaPorTemporada;

    public Categoria(String nombreCategoria, int rangoCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.rangoCategoria = rangoCategoria;
        this.vehiculosDisponibles = 0;
        this.hashVehiculos = new HashMap<String, Vehiculo>();
        this.hashTarifaPorTemporada = new HashMap<String, Integer>();
    }

    public String getNombreCategoria() {
        return this.nombreCategoria;
    }

    public int getRangoCategoria() {
        return this.rangoCategoria;
    }

    public int getVehiculosDisponibles() {
        return this.vehiculosDisponibles;
    }

    public HashMap<String, Vehiculo> getHashVehiculos() {
        return this.hashVehiculos;
    }

    public HashMap<String, Integer> getHashTarifaPorTemporada() {
        return this.hashTarifaPorTemporada;
    }

}