package SistemaLogin;

import java.util.*;
import java.awt.image.BufferedImage;
import Inventario.Categoria;
import Reservas.Reserva;
import Reservas.ReservaNormal;
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

    public String alquilarVehiculo(Catalogo catalogo, HashMap<String, Usuario> hashUsuarios, HashMap<String, Reserva> hashReservas, String usernameClienteAlquiler) {
        // encuentra el usuario que quiere va a alquilar en el hash de usuarios y obtiene los detalles de su reserva retorna la placa del vehiculo alquilado
        Integer id = ((Cliente) hashUsuarios.get(usernameClienteAlquiler)).getIdReserva();
        ReservaNormal reserva = (ReservaNormal) hashReservas.get(Integer.toString(id));
        String placa = reserva.getPlaca();
        String rangoAlquiler = reserva.getRangoAlquiler();
        if (placa.equals("NA")) {
            placa = catalogo.getHashCategorias().get(reserva.getCategoriaVehiculo()).getPlacaVehiculoParaReserva(rangoAlquiler);
            while (placa.equals("na")) {
                int rangoCategoriaNueva = catalogo.getHashCategorias().get(reserva.getCategoriaVehiculo()).getRangoCategoria() + 1;
                String categoriaNueva = "na";
                for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                    if (categoria.getValue().getRangoCategoria() == rangoCategoriaNueva) {
                        categoriaNueva = categoria.getKey();
                    }
                }
                if (!(categoriaNueva.equals("na"))) {
                    placa = catalogo.getHashCategorias().get(categoriaNueva).getPlacaVehiculoParaReserva(rangoAlquiler);
                } else {
                    placa = "NA";
                }
            } 
            reserva.setPlaca(placa);
        }
        if (placa.equals("NA")) {
            ((Cliente) hashUsuarios.get(usernameClienteAlquiler)).setTieneReserva(false);
            return "No hay vehiculos disponibles en este momento para esta categoria. Crear una nueva reserva, se elimino la reserva antigua del usuario";
        } else {
            String fechaDeAlquiler = reserva.getFechaAlquiler();
            String fechaDevolucion = reserva.getFechaEntrega();
            String sedeDevolucion = reserva.getSedeEntrega();

            for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
            if (categoria.getValue().getHashVehiculos().containsKey(placa)){
                    categoria.getValue().getHashVehiculos().get(placa).setEnAlquiler(true);
                    categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setDisponibilidadParaAlquilar(false);
                    categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setUsuarioClienteAlquiler(usernameClienteAlquiler);
                    categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setFechaDevolucion(fechaDevolucion);
                    categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setSedeDevolucion(sedeDevolucion);
                    categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(fechaDeAlquiler, "Vehiculo alquilado por " + usernameClienteAlquiler + " en la sede " + this.nombreSede);
            }
            }
            return placa;
        }
    }

    public void otrosConductoresAgregarLicencia(HashMap<String, Usuario> hashUsuarios, String usernameClienteAlquiler, int numeroLicencia, String paisExpedicion, String fechaVencimiento, BufferedImage imagenLicencia) {
        for (Map.Entry<String, Usuario> usuario : hashUsuarios.entrySet()) {
            if (usuario.getValue().getUsername().equals(usernameClienteAlquiler)) {
                ((Cliente) usuario.getValue()).getDatosClienteLicencia().add(new DatosClienteLicencia(numeroLicencia, paisExpedicion, fechaVencimiento, imagenLicencia));
            }
        }
    }

    public void recibirVehiculoSinMantenimiento(Catalogo catalogo, String placa, String usernameClienteAlquiler, HashMap<String, Usuario> hashUsuarios) {
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
           if (categoria.getValue().getHashVehiculos().containsKey(placa)){
                String devolucion = categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().getFechaDevolucion();
                categoria.getValue().getHashVehiculos().get(placa).setEnAlquiler(false);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setDisponibilidadParaAlquilar(true); 
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setUsuarioClienteAlquiler("na");
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setFechaDevolucion("na");
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setFechaDisponibilidad(devolucion);
                ((Cliente)hashUsuarios.get(usernameClienteAlquiler)).setTieneReserva(false);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(devolucion, "Vehiculo entregado por " + usernameClienteAlquiler + " en la sede " + this.nombreSede);
            }
        }
    }

    public String recibirVehiculoConMantenimiento(Catalogo catalogo, String placa, String usernameClienteAlquiler, String fechaEstimadaRegreso, String descripcionMantenimiento, HashMap<String, Usuario> hashUsuarios) {
        String rta = "";
        String placaNueva = "";
        for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
           if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                String devolucion = categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().getFechaDevolucion();
                categoria.getValue().getHashVehiculos().get(placa).setEnAlquiler(false);
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setDisponibilidadParaAlquilar(false); 
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setUsuarioClienteAlquiler("na");
                categoria.getValue().getHashVehiculos().get(placa).getDetallesAlquiler().setFechaDevolucion("na");
                categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().setFechaDisponibilidad(fechaEstimadaRegreso);
                ((Cliente)hashUsuarios.get(usernameClienteAlquiler)).setTieneReserva(false);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(devolucion, "Vehiculo entregado por " + usernameClienteAlquiler + " en la sede " + this.nombreSede);
                categoria.getValue().getHashVehiculos().get(placa).getHistorialVehiculo().addEvent(devolucion, "Necesita mantenimiento, descripción: " + descripcionMantenimiento);
                // recorre cada una de las reservas que tiene el vehiculo y si esta dentro del rango de mantenimiento, la actualiza la placa
                for (Reserva reserva : categoria.getValue().getHashVehiculos().get(placa).getReservas()) {
                    Integer indexReserva = categoria.getValue().getHashVehiculos().get(placa).getReservas().indexOf(reserva);
                    long diferenciaFinalRInicioM = ReservaNormal.rangoFecha(reserva.getRangoAlquiler().split("-")[1]+"-"+devolucion);
                    if (diferenciaFinalRInicioM < 0) {
                        long diferenciaFinalMInicioR = ReservaNormal.rangoFecha(reserva.getRangoAlquiler().split("-")[0]+"-"+devolucion);
                        long diferenciaInicioRFinalM = ReservaNormal.rangoFecha(reserva.getRangoAlquiler().split("-")[0]+"-"+fechaEstimadaRegreso);
                        if (diferenciaFinalMInicioR > 0) {
                            placaNueva = ((ReservaNormal) categoria.getValue().getHashVehiculos().get(placa).getReservas().get(indexReserva)).getNuevaPlacaParaReserva(catalogo, hashUsuarios, usernameClienteAlquiler);
                        } else if (diferenciaInicioRFinalM > 0) {
                            placaNueva = ((ReservaNormal) categoria.getValue().getHashVehiculos().get(placa).getReservas().get(indexReserva)).getNuevaPlacaParaReserva(catalogo, hashUsuarios, usernameClienteAlquiler);
                        }
                        if (placaNueva.equals("NA")) {
                            rta = "- Se elimino la reserva del usuario " + ((ReservaNormal) categoria.getValue().getHashVehiculos().get(placa).getReservas().get(indexReserva)).getUsuarioAlquiler() +" dado el cruze en fechas por mantenimiento y que no hay mas vehiculos disponibles.\n";
                        } else if (!placaNueva.equals("")){
                            rta = "- Se edito la reserva del usuario " + ((ReservaNormal) categoria.getValue().getHashVehiculos().get(placa).getReservas().get(indexReserva)).getUsuarioAlquiler() +" dado el cruze en fechas por mantenimiento y se le asigno el vehiculo" + placaNueva + ".\n";;
                        }
                    }
                }
            }
        }
        return rta;
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

