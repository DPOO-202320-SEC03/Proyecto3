package SistemaLogin;

import Inventario.CaracteristicasBasicas;
import Inventario.Catalogo;
import Inventario.Categoria;
import Inventario.Sede;
import Inventario.Seguro;
import Inventario.Vehiculo;
import Reservas.Reserva;
import Reservas.ReservaEspecial;

import java.util.*;

public class Administrador extends Usuario {

    private int nivelDeAcceso = 3;

    public Administrador(String username, String password, String nombres, String apellidos, String celular, String correo) {
        super.username = username;
        super.password = password;
        super.nombres = nombres;
        super.apellidos = apellidos;
        super.celular = celular;
        super.correo = correo;
        super.nivelDeAcceso = nivelDeAcceso;
    }

    // Funciones para vehiculos

    public void crearVehiculo(Catalogo catalogo, String placa, String marca, String modelo, String color, String tipoDeTransmision, String tipoDeDireccion, String tipoDeCombustible, String cantidadDePasajeros, String nombreSede, String nombreCategoria, String fechaDisponibilidad) {
        CaracteristicasBasicas caracteristicasBasicas = new CaracteristicasBasicas(placa, marca, modelo, color, tipoDeTransmision, tipoDeDireccion,tipoDeCombustible, cantidadDePasajeros);
        Vehiculo vehiculoNuevo = new Vehiculo(nombreCategoria, caracteristicasBasicas);
        vehiculoNuevo.getDetallesSede().setSedeUbicacion(nombreSede);
        vehiculoNuevo.getDetallesSede().setDisponibilidadParaAlquilar(true);
        vehiculoNuevo.getDetallesSede().setFechaDisponibilidad(fechaDisponibilidad);
        vehiculoNuevo.getHistorialVehiculo().addEvent(fechaDisponibilidad, "Vehiculo nuevo disponible para alquiler");
        catalogo.getHashCategorias().get(nombreCategoria).getHashVehiculos().put(placa, vehiculoNuevo);
    }

    public void eliminarVehiculo(Catalogo catalogo, String placa) {
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
            if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                categoria.getValue().getHashVehiculos().remove(placa);
            }
        }
    }

    public String estadoVehiculo(Catalogo catalogo, String placa) {
        String estadoVehiculo = "";
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
            if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                estadoVehiculo += "Placa: " + categoria.getValue().getHashVehiculos().get(placa).getCaracteristicasBasicas().getPlaca() + "\n";
                estadoVehiculo += "Marca: " + categoria.getValue().getHashVehiculos().get(placa).getCaracteristicasBasicas().getAllInfo().get(1) + "\n";
                estadoVehiculo += "Modelo: " + categoria.getValue().getHashVehiculos().get(placa).getCaracteristicasBasicas().getAllInfo().get(2) + "\n";
                estadoVehiculo += "Color: " + categoria.getValue().getHashVehiculos().get(placa).getCaracteristicasBasicas().getAllInfo().get(3) + "\n";
                estadoVehiculo += "Tipo de transmision: " + categoria.getValue().getHashVehiculos().get(placa).getCaracteristicasBasicas().getAllInfo().get(4) + "\n";
                estadoVehiculo += "Tipo de direccion: " + categoria.getValue().getHashVehiculos().get(placa).getCaracteristicasBasicas().getAllInfo().get(5) + "\n";
                estadoVehiculo += "Tipo de combustible: " + categoria.getValue().getHashVehiculos().get(placa).getCaracteristicasBasicas().getAllInfo().get(6) + "\n";
                estadoVehiculo += "Cantidad de pasajeros: " + categoria.getValue().getHashVehiculos().get(placa).getCaracteristicasBasicas().getAllInfo().get(7) + "\n";
                estadoVehiculo += "Categoria: " + categoria.getValue().getHashVehiculos().get(placa).getCategoriaVehiculo() + "\n";
                estadoVehiculo += "En alquiler: " + categoria.getValue().getHashVehiculos().get(placa).getEnAlquiler() + "\n";
                ArrayList<Integer> idReservas = new ArrayList<Integer>();
                ArrayList<String> rangoReservas = new ArrayList<String>();
                for (Reserva reserva : categoria.getValue().getHashVehiculos().get(placa).getReservas()) {
                    idReservas.add(reserva.getIdReserva());
                    rangoReservas.add(reserva.getRangoAlquiler());
                }
                estadoVehiculo += "Historial de ID de reservas: " + idReservas + "\n";
                estadoVehiculo += "Rango reservas activas: " + rangoReservas + "\n";
                estadoVehiculo += "Sede: " + categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().getSedeUbicacion() + "\n";
                estadoVehiculo += "Disponible para alquilar: " + categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().getDisponibilidadParaAlquilar() + "\n";
                estadoVehiculo += "Fecha de disponibilidad: " + categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().getFechaDisponibilidad() + "\n";
                estadoVehiculo += "Usuario alquilando: " + categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().getUsuarioClienteAlquiler() + "\n";
                estadoVehiculo += "Fecha de devolucion: " + categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().getFechaDevolucion() + "\n";
                estadoVehiculo += "Sede de devolucion: " + categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().getSedeDevolucion() + "\n";
                estadoVehiculo += "Log de eventos: \n";
                estadoVehiculo += String.format("%-20s%-80s\n", "_".repeat(20), "_".repeat(80));
                HashMap<String, String> logEventos = categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().getLogEventos();
                for (Map.Entry<String, String> evento : logEventos.entrySet()) {
                    estadoVehiculo += String.format("%-20s%-80s\n", "Fecha: " + evento.getKey(), "| Evento: " + evento.getValue());
                }
            }
        }
        return estadoVehiculo;
    }

    public String trasladarVehiculo(Catalogo catalogo, HashMap<String, Reserva> hashReservas, String placa, String sedeDestino, String fechaRecoger, String horaRecoger, String fechaEntrega) {
        String detallesTraslado = "";
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
            if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                String sedeOrigen = categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().getSedeUbicacion();
                ReservaEspecial reservaTraslado = new ReservaEspecial(sedeOrigen, sedeDestino, fechaRecoger, horaRecoger, fechaEntrega, placa);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setSedeUbicacion(sedeDestino);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(fechaRecoger, "Vehiculo trasladado a " + sedeDestino);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setDisponibilidadParaAlquilar(false);
                hashReservas.put(Integer.toString(reservaTraslado.getIdReserva()), reservaTraslado);
                detallesTraslado = reservaTraslado.getResumen(catalogo, catalogo.getTarifasGlobales());
            }
        }
        return detallesTraslado;
    }

    // Funcion para sedes

    public void crearSede(HashMap<String, Sede> hashSedes, String nombreSede, String ubicacion, String horariosDeAtencion) {
        Sede sede = new Sede(nombreSede, ubicacion, horariosDeAtencion);
        hashSedes.put(nombreSede, sede);
    }

    // Funciones para usuarios

    public void crearAdministradorLocal(HashMap<String, Usuario> hashUsuarios, String username, String password, String nombreSede, String nombres, String apellidos, String celular, String correo) {
        AdministradorLocal adminLocal = new AdministradorLocal(username, password, nombreSede, nombres, apellidos, celular, correo);
        hashUsuarios.put(username, adminLocal);
    }

    public void eliminarUsuario(HashMap<String, Usuario> hashUsuarios, String nombreUsuario) {
        hashUsuarios.remove(nombreUsuario);
    }

    // Funciones para seguros

    public void crearSeguro(Catalogo catalogo, int tarifaExtraDiaria, String nombreSeguro, String descripcionSeguro) {
        Seguro seguro = new Seguro(tarifaExtraDiaria, nombreSeguro, descripcionSeguro);
        catalogo.getHashSeguros().put(nombreSeguro, seguro);
    }

    // Funciones para categorias

    public void crearCategoria(Catalogo catalogo, String nombreCategoria, int rangoCategoria) {
        Categoria categoria = new Categoria(nombreCategoria, rangoCategoria);
        catalogo.getHashCategorias().put(nombreCategoria, categoria);
    }

    public void crearTarifaPorTemporada(Catalogo catalogo, String nombreCategoria, int tarifaTemporadaAlta, int tarifaTemporadaBaja) {
        catalogo.getHashCategorias().get(nombreCategoria).getHashTarifaPorTemporada().put("alta", tarifaTemporadaAlta);
        catalogo.getHashCategorias().get(nombreCategoria).getHashTarifaPorTemporada().put("baja", tarifaTemporadaBaja);
    }

    // Funciones para las tarifas

    public void crearTarifasGlobales(Catalogo catalogo, int tarifaConductorExtra, int tarifaEntregaOtraSede, String rangoTemporadaAlta) {
        catalogo.getTarifasGlobales().setTarifaConductorExtra(tarifaConductorExtra);
        catalogo.getTarifasGlobales().setTarifaEntregaOtraSede(tarifaEntregaOtraSede);
        catalogo.getTarifasGlobales().setRangoTemporadaAlta(rangoTemporadaAlta);
    }

    // Funciones para las reservas

    public String resumenReserva(HashMap<String, Reserva> hashReservas, String idReserva, Catalogo catalogo) {
        return(hashReservas.get(idReserva).getResumen(catalogo, catalogo.getTarifasGlobales()));
    }
}