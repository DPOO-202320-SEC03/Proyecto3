package SistemaLogin;

import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import Inventario.Categoria;
import Inventario.Catalogo;
import Inventario.Vehiculo;
import Inventario.DetallesAlquiler;

public class Empleado extends Usuario {

    private int nivelDeAcceso = 1;
    private String nombreSede;

    public Empleado(String username, String password, String nombreSede, String nombres, String apellidos, String celular, String correo) {
        super.username = username;
        super.password = password;
        this.nombreSede = nombreSede;
        super.nombres = nombres;
        super.apellidos = apellidos;
        super.celular = celular;
        super.correo = correo;
        super.nivelDeAcceso = nivelDeAcceso;
    }

    public void alquilarVehiculo(Catalogo catalogo, String placa, String usernameClienteAlquiler, String fechaDevolucion, String sedeDevolucion, String fechaDeAlquiler) {
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
           if (categoria.getValue().getHashVehiculos().containsKey(placa)){
                categoria.getValue().getHashVehiculos().get(placa).setEnAlquiler(true);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setUsuarioClienteAlquiler(usernameClienteAlquiler);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setFechaDevolucion(fechaDevolucion);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setSedeDevolucion(sedeDevolucion);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(fechaDeAlquiler, "Vehiculo alquilado");
           }
        }
    }

    public void otrosConductoresAgregarLicencia(String usernameClienteAlquiler, int numeroLicencia, String paisExpedicion, String fechaVencimiento, BufferedImage imagenLicencia) {
        // TODO implement here
    }

    public void recibirVehiculo(Catalogo catalogo, String placa, String usernameClienteAlquiler, Boolean necesitaMantenimiento) {
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
           if (categoria.getValue().getHashVehiculos().containsKey(placa)){
                String devolucion = categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().getFechaDevolucion();
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setUsuarioClienteAlquiler("na");
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setFechaDevolucion("na");
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setFechaDisponibilidad(devolucion);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(devolucion, "Vehiculo entregado por " + usernameClienteAlquiler + " en la sede");
                if (necesitaMantenimiento == false){
                    categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(devolucion, "Vehiculo disponible para alquiler");
                    categoria.getValue().getHashVehiculos().get(placa).setEnAlquiler(true);
                    System.out.println("Vehiculo recibido y listo para alquilar");
                }
           }
           else{System.out.println("No se encontro el vehiculo");}
        }
    }

    public void necesitaMantenimiento(Catalogo catalogo, String fechaEstimadaRegreso, String placa ,String descripcionMantenimiento) {
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
           if (categoria.getValue().getHashVehiculos().containsKey(placa)){
                String devolucion = categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().getFechaDisponibilidad();
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setFechaDisponibilidad(fechaEstimadaRegreso);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(fechaEstimadaRegreso, "El vehiculo se encuentra en mantenimiento debido: " + descripcionMantenimiento);
                System.out.println("Vehiculo puesto en mantenimiento exitosamente");
           }
        }
    }

    public void vehiculoListoParaAlquiler(Catalogo catalogo, String placa, String fechaDisponibilidad) {
        // TODO implement here
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
           if (categoria.getValue().getHashVehiculos().containsKey(placa)){
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setFechaDisponibilidad(fechaDisponibilidad);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(fechaDisponibilidad, "Vehiculo disponible para alquiler");
                categoria.getValue().getHashVehiculos().get(placa).setEnAlquiler(true);
                System.out.println("Vehiculo listo para alquilar");
           }
           else{System.out.println("No se pudo encontrar el vehiculo");}
        }
    }

    public String getSede() {
        return this.nombreSede;
    }

}

