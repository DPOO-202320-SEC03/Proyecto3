package Inventario;

import java.io.*;
import java.util.*;
import Inventario.CaracteristicasBasicas;
import Inventario.DetallesAlquiler;
import Inventario.DetallesSede;
import Inventario.HistorialVehiculo;

public class Vehiculo {

    private String categoria;
    private int rangoCategoria;
    private Boolean enAlquiler = false;
    private ArrayList<Integer> reservas;
    private CaracteristicasBasicas caracteristicasBasicas;
    private DetallesAlquiler detallesAlquiler;
    private DetallesSede detallesSede;
    private HistorialVehiculo historialVehiculo;

    public Vehiculo(String categoria, int rangoCategoria, CaracteristicasBasicas caracteristicasBasicas) {
        this.categoria = categoria;
        this.rangoCategoria = rangoCategoria;
        this.reservas = new ArrayList<Integer>();
        this.caracteristicasBasicas = caracteristicasBasicas;
        this.detallesAlquiler = new DetallesAlquiler();
        this.detallesSede = new DetallesSede();
        this.historialVehiculo = new HistorialVehiculo();
    }

    private Boolean getEnAlquiler() {
        // TODO implement here
        return null;
    }

    private void setEnAlquiler(Boolean alquiler) {
        // TODO implement here
    }

    private ArrayList<Integer> getReservas() {
        // TODO implement here
        return null;
    }

    private void addReserva(String rangoDeFechasEnAlquiler) {
        // TODO implement here
    }

    private CaracteristicasBasicas getCaracteristicasBasicas() {
        // TODO implement here
        return null;
    }

    private DetallesAlquiler getDetallesAlquiler() {
        // TODO implement here
        return null;
    }

    private void setDetallesAlquiler(DetallesAlquiler detallesAlquiler) {
        // TODO implement here
    }

    private DetallesSede getDetallesSede() {
        // TODO implement here
        return null;
    }

    private void setDetallesSede(DetallesSede detallesSede) {
        // TODO implement here
    }

    private HistorialVehiculo getHistorialVehiculo() {
        // TODO implement here
        return null;
    }

    private void setHistorialVehiculo(HistorialVehiculo historialVehiculo) {
        // TODO implement here
    }

}