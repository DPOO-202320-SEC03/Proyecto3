package Inventario;

import java.io.Serializable;
import java.util.*;

import Reservas.Reserva;
import Reservas.ReservaNormal;

public class Categoria implements Serializable {

    private String nombreCategoria;
    private int rangoCategoria;
    private HashMap<String, Vehiculo> hashVehiculos;
    private HashMap<String, Integer> hashTarifaPorTemporada;

    /**
     * constructor de una categoria con un nombre unico, con un rango, un hashmap de vehiculos y un hasmap de tarifas
     * @param nombreCategoria nombre unico de la categoria
     * @param rangoCategoria rango de la categoria
     */
    public Categoria(String nombreCategoria, int rangoCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.rangoCategoria = rangoCategoria;
        this.hashVehiculos = new HashMap<String, Vehiculo>();
        this.hashTarifaPorTemporada = new HashMap<String, Integer>();
    }

    /**
     * @return retorna el nombre de la categoria
     */
    public String getNombreCategoria() {
        return this.nombreCategoria;
    }

    /**
     * @return retorna el rango de la categoria
     */
    public int getRangoCategoria() {
        return this.rangoCategoria;
    }

    /**
     * metodo publico que itera sobre el hash vehiculos y cuenta los que estan disponibles
     * @param sede sede en la que se desea buscar los vehiculos disponibles
     * @return el numero de vehiculos disponibles
     */
    public int getVehiculosDisponibles(String sede) {
        int disponibles = 0;
        for (Map.Entry<String, Vehiculo> vehiculo : this.hashVehiculos.entrySet()) {
            if (vehiculo.getValue().getDetallesSede().getSedeUbicacion().equals(sede)) {
                if (!vehiculo.getValue().getEnAlquiler() && vehiculo.getValue().getDetallesSede().getDisponibilidadParaAlquilar()) {
                    disponibles++;
                }
            }
        }
        return disponibles;
    }

    /**
     * metodo publico usado para saber el numero de placa de un vehiculo
     * @return el numero de placa del vehiculo
     */
    public String getPlacaVehiculoParaReserva(String rangoAlquiler) {
        String placa = "na";
        Boolean encontrado = false;
        String inicioAlquiler = rangoAlquiler.split("-")[0];
        String finAlquiler = rangoAlquiler.split("-")[1];
        for (Map.Entry<String, Vehiculo> vehiculo : this.hashVehiculos.entrySet()) {
            if (!encontrado) {
                Boolean validoParaAlquiler = true;
                ArrayList<Reserva> listaDeReservas = vehiculo.getValue().getReservas();
                for (Reserva reserva : listaDeReservas) {
                    long diferenciaFinalRInicioA = ReservaNormal.rangoFecha(reserva.getRangoAlquiler().split("-")[1]+"-"+inicioAlquiler);
                    if (diferenciaFinalRInicioA < 0) {
                        long diferenciaInicioRInicioA = ReservaNormal.rangoFecha(reserva.getRangoAlquiler().split("-")[0]+"-"+inicioAlquiler);
                        long diferenciaInicioRFinalA = ReservaNormal.rangoFecha(reserva.getRangoAlquiler().split("-")[0]+"-"+finAlquiler);
                        if (diferenciaInicioRInicioA > 0) {
                            validoParaAlquiler = false;
                        } else if (diferenciaInicioRFinalA > 0) {
                            validoParaAlquiler = false;
                        }
                    }
                }
                if (validoParaAlquiler) {
                    placa = vehiculo.getKey();
                    encontrado = true;
                }
            }
        }
        return placa;
    }

    /**
     * @return retorna el hashmap de los vehiculos dentro de las categoria
     */
    public HashMap<String, Vehiculo> getHashVehiculos() {
        return this.hashVehiculos;
    }

    /**
     * @return retorna la tarifa por temporada de la categoria 
     */
    public HashMap<String, Integer> getHashTarifaPorTemporada() {
        return this.hashTarifaPorTemporada;
    }

}