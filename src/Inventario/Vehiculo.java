package Inventario;

import java.io.Serializable;
import java.util.*;

public class Vehiculo implements Serializable {

    private String categoria;
    private Boolean enAlquiler = false;
    private ArrayList<Integer> reservas;
    private CaracteristicasBasicas caracteristicasBasicas;
    private DetallesAlquiler detallesAlquiler;
    private DetallesSede detallesSede;
    private HistorialVehiculo historialVehiculo;

    public Vehiculo(String categoria, CaracteristicasBasicas caracteristicasBasicas) {
        this.categoria = categoria;
        this.reservas = new ArrayList<Integer>();
        this.caracteristicasBasicas = caracteristicasBasicas;
        this.detallesAlquiler = new DetallesAlquiler();
        this.detallesSede = new DetallesSede();
        this.historialVehiculo = new HistorialVehiculo();
    }

    public String getCategoriaVehiculo() {
        return this.categoria;
    }

    public Boolean getEnAlquiler() {
        return this.enAlquiler;
    }

    public void setEnAlquiler(Boolean alquiler) {
        this.enAlquiler = alquiler;
    }

    public ArrayList<Integer> getReservas() {
        return this.reservas;
    }

    public void addReserva(int idReserva) {
        this.reservas.add(idReserva);
    }

    public CaracteristicasBasicas getCaracteristicasBasicas() {
        return this.caracteristicasBasicas;
    }

    public DetallesAlquiler getDetallesAlquiler() {
        return this.detallesAlquiler;
    }

    public DetallesSede getDetallesSede() {
        return this.detallesSede;
    }

    public HistorialVehiculo getHistorialVehiculo() {
        return this.historialVehiculo;
    }

}