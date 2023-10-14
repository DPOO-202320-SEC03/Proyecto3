package SistemaLogin;

import Inventario.Categoria;
import Inventario.Sede;
import Inventario.Seguro;
import Inventario.Vehiculo;
import Reservas.TarifasGlobales;

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

    public Vehiculo crearVehiculo(String placa, String marca, String modelo, String color, String tipoDeTransmision, String tipoDeDireccion) {
        // TODO implement here
        return null;
    }

    public void agregarVehiculo(String placa, String marca, String modelo, String color, String tipoDeTransmision, String tipoDeDireccion, String nombreSede, Vehiculo vehiculo, String nombreCategoria, Boolean disponibleParaAlquilar, String fechaDisponibilidad) {
        // TODO implement here
    }

    public void eliminarVehiculo(String placa) {
        // TODO implement here
    }

    public String estadoVehiculo(String placa) {
        // TODO implement here
        return "";
    }

    public void trasladarVehiculo(String placa, String sedeOrigen, String sedeDestino, String fechaRecoger, String horaRecoger, String fechaEntrega) {
        // TODO implement here
    }

    // Funcion para sedes

    public Sede crearSede(String nombreSede, String ubicacion, String horariosDeAtencion) {
        Sede sede = new Sede(nombreSede, ubicacion, horariosDeAtencion);
        return sede;
    }

    // Funciones para usuarios

    public AdministradorLocal crearAdministradorLocal(String username, String password, String nombreSede, String nombres, String apellidos, String celular, String correo) {
        AdministradorLocal adminLocal = new AdministradorLocal(username, password, nombreSede, nombres, apellidos, celular, correo);
        return adminLocal;
    }

    public HashMap<String, Usuario> eliminarUsuario(String nombreUsuario, HashMap<String, Usuario> hashUsuarios) {
        hashUsuarios.remove(nombreUsuario);
        return hashUsuarios;
    }

    // Funciones para seguros

    public Seguro crearSeguro(int tarifaExtraDiaria, String nombreSeguro, String descripcionSeguro) {
        Seguro seguro = new Seguro(tarifaExtraDiaria, nombreSeguro, descripcionSeguro);
        return seguro;
    }

    // Funciones para categorias

    public Categoria crearCategoria(String nombreCategoria, int rangoCategoria) {
        return new Categoria(nombreCategoria, rangoCategoria);
    }

    public HashMap<String, Categoria> crearTarifasPorTemporadaCategoria(HashMap<String, Categoria> hashCategorias, String nombreCategoria, int tarifaTemporadaAlta, int tarifaTemporadaBaja) {
        Categoria categoria = hashCategorias.get(nombreCategoria);
        HashMap<String, Integer> hashTarifaPorTemporada = categoria.getHashTarifaPorTemporada();
        hashTarifaPorTemporada.put("alta", tarifaTemporadaAlta);
        hashTarifaPorTemporada.put("baja", tarifaTemporadaBaja);
        categoria.updateHashTarifaPorTemporada(hashTarifaPorTemporada);
        hashCategorias.put(categoria.getNombreCategoria(), categoria);
        return hashCategorias;
    }

    // Funciones para las tarifas

    public TarifasGlobales crearTarifasGlobales(int tarifaConductorExtra, int tarifaEntregaOtraSede, String rangoTemporadaAlta) {
        TarifasGlobales tarifasGlobales = new TarifasGlobales();
        tarifasGlobales.setTarifaConductorExtra(tarifaConductorExtra);
        tarifasGlobales.setTarifaEntregaOtraSede(tarifaEntregaOtraSede);
        tarifasGlobales.setRangoTemporadaAlta(rangoTemporadaAlta);
        return tarifasGlobales;
    }
}