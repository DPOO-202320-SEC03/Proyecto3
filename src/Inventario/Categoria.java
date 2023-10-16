package Inventario;

import java.io.Serializable;
import java.util.*;

public class Categoria implements Serializable {

    private String nombreCategoria;
    private int rangoCategoria;
    private HashMap<String, Vehiculo> hashVehiculos;
    private HashMap<String, Integer> hashTarifaPorTemporada;

    public Categoria(String nombreCategoria, int rangoCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.rangoCategoria = rangoCategoria;
        this.hashVehiculos = new HashMap<String, Vehiculo>();
        this.hashTarifaPorTemporada = new HashMap<String, Integer>();
    }

    public String getNombreCategoria() {
        return this.nombreCategoria;
    }

    public int getRangoCategoria() {
        return this.rangoCategoria;
    }

    public int getVehiculosDisponibles(String sede) {
        // itera sobre el hash vehiculos y cuenta los que estan disponibles
        int disponibles = 0;
        for (Map.Entry<String, Vehiculo> vehiculo : this.hashVehiculos.entrySet()) {
            if (vehiculo.getValue().getDetallesSede().getSedeUbicacion().equals(sede)) {
                if (!vehiculo.getValue().getEnAlquiler() && vehiculo.getValue().getDetallesSede().getDisponibilidadParaAlquilar() && !vehiculo.getValue().getEnReserva()) {
                    disponibles++;
                }
            }
        }
        return disponibles;
    }

    public String getPlacaVehiculoParaReserva() {
        String placa = "na";
        for (Map.Entry<String, Vehiculo> vehiculo : this.hashVehiculos.entrySet()) {
            if (!vehiculo.getValue().getEnAlquiler() && vehiculo.getValue().getDetallesSede().getDisponibilidadParaAlquilar() && !vehiculo.getValue().getEnReserva()) {
                placa = vehiculo.getKey();
                vehiculo.getValue().setEnReserva(true);
            }
        }
        return placa;
    }

    public HashMap<String, Vehiculo> getHashVehiculos() {
        return this.hashVehiculos;
    }

    public HashMap<String, Integer> getHashTarifaPorTemporada() {
        return this.hashTarifaPorTemporada;
    }

}