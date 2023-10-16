package SistemaLogin;

import java.util.*;
import java.awt.image.BufferedImage;
import Inventario.Categoria;
import Inventario.Catalogo;

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
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setDisponibilidadParaAlquilar(false); 
                categoria.getValue().getHashVehiculos().get(placa).setEnReserva(false);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setUsuarioClienteAlquiler(usernameClienteAlquiler);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setFechaDevolucion(fechaDevolucion);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setSedeDevolucion(sedeDevolucion);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(fechaDeAlquiler, "Vehiculo alquilado por " + usernameClienteAlquiler + " en la sede " + this.nombreSede);
           }
        }
    }

    public void otrosConductoresAgregarLicencia(HashMap<String, Usuario> hashUsuarios, String usernameClienteAlquiler, int numeroLicencia, String paisExpedicion, String fechaVencimiento, BufferedImage imagenLicencia) {
        for (Map.Entry<String, Usuario> usuario : hashUsuarios.entrySet()) {
            if (usuario.getValue().getUsername().equals(usernameClienteAlquiler)) {
                ((Cliente) usuario.getValue()).getDatosClienteLicencia().add(new DatosClienteLicencia(numeroLicencia, paisExpedicion, fechaVencimiento, imagenLicencia));
            }
        }
    }

    public void recibirVehiculoSinMantenimiento(Catalogo catalogo, String placa, String usernameClienteAlquiler) {
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
           if (categoria.getValue().getHashVehiculos().containsKey(placa)){
                String devolucion = categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().getFechaDevolucion();
                categoria.getValue().getHashVehiculos().get(placa).setEnAlquiler(false);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setDisponibilidadParaAlquilar(true); 
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setUsuarioClienteAlquiler("na");
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setFechaDevolucion("na");
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setFechaDisponibilidad(devolucion);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(devolucion, "Vehiculo entregado por " + usernameClienteAlquiler + " en la sede " + this.nombreSede);
            }
        }
    }

    public void recibirVehiculoConMantenimiento(Catalogo catalogo, String placa, String usernameClienteAlquiler, String fechaEstimadaRegreso, String descripcionMantenimiento) {
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
           if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                String devolucion = categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().getFechaDevolucion();
                categoria.getValue().getHashVehiculos().get(placa).setEnAlquiler(false);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setDisponibilidadParaAlquilar(false); 
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setUsuarioClienteAlquiler("na");
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setFechaDevolucion("na");
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setFechaDisponibilidad(fechaEstimadaRegreso);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(devolucion, "Vehiculo entregado por " + usernameClienteAlquiler + " en la sede " + this.nombreSede);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(devolucion, "Necesita mantenimiento, descripción: " + descripcionMantenimiento);
            }
        }
    }

    public void vehiculoListoParaAlquiler(Catalogo catalogo, String placa, String fechaDisponibilidad) {
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
           if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setDisponibilidadParaAlquilar(true); 
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setFechaDisponibilidad(fechaDisponibilidad);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(fechaDisponibilidad, "Vehiculo disponible para alquiler");
           }
        }
    }

    public String getSede() {
        return this.nombreSede;
    }

}

