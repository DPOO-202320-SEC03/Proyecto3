package Inventario;

import java.io.*;
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

    private String getPlaca() {
        // TODO implement here
        return "";
    }

    private ArrayList<String> getAllInfo() {
        // TODO implement here
        return null;
    }
}