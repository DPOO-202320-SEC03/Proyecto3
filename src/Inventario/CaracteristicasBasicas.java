package Inventario;

import java.util.*;

public class CaracteristicasBasicas {

    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String tipoDeTransmision;
    private String tipoDeDireccion;
    private String tipoDeCombustible;
    private String cantidadDePasajeros;

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

    public String getPlaca() {
        return this.placa;
    }

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