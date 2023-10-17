package Inventario;

import java.io.Serializable;
import java.util.*;

public class CaracteristicasBasicas implements Serializable {

    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String tipoDeTransmision;
    private String tipoDeDireccion;
    private String tipoDeCombustible;
    private String cantidadDePasajeros;

    /**
     * metodo publico usado para generar las caracteristicas mas basicas de un vehicuo
     * @param placa placa del vehiculo con formato ABC-123
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param color colo del vehiculo
     * @param tipoDeTransmision tipo de transmision del vehiculo
     * @param tipoDeDireccion tipo de direccion del vehiculo
     * @param tipoDeCombustible tipo de combustible del vehiculo
     * @param cantidadDePasajeros la cantidad maxima que puede llevar el vehiculo
     */
    public CaracteristicasBasicas(String placa, String marca, String modelo, String color, String tipoDeTransmision, String tipoDeDireccion, String tipoDeCombustible, String cantidadDePasajeros) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipoDeTransmision = tipoDeTransmision;
        this.tipoDeDireccion = tipoDeDireccion;
        this.tipoDeCombustible = tipoDeCombustible;
        this.cantidadDePasajeros = cantidadDePasajeros;
    }

    /**
     * metodo publico que retorna la placa del vehiculo
     * @return un string que representa la placa del vehiculo
     */
    public String getPlaca() {
        return this.placa;
    }

    /**
     * un arraylist con toda la informacion basica del vehiculo
     * @return el arraylist
     */
    public ArrayList<String> getAllInfo() {
        ArrayList<String> info = new ArrayList<String>();
        info.add(this.placa);
        info.add(this.marca);
        info.add(this.modelo);
        info.add(this.color);
        info.add(this.tipoDeTransmision);
        info.add(this.tipoDeDireccion);
        info.add(this.tipoDeCombustible);
        info.add(this.cantidadDePasajeros);
        return info;
    }
}