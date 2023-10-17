package Inventario;

import java.util.*;
import java.io.Serializable;
import Reservas.TarifasGlobales;

public class Catalogo implements Serializable {

    private TarifasGlobales tarifasGlobales;
    private  HashMap<String, Categoria> hashCategorias;
    private HashMap<String,Seguro> hashSeguros;

    /**
     * metodo que guarda informacion relacionada a los tipos de autos y precios, que son las tarifas globales, hashmap con la categoria de los autos
     * y un hash map con los distintos seguro
     */
    public Catalogo() {
        this.tarifasGlobales = new TarifasGlobales();
        this.hashCategorias = new HashMap<String, Categoria>();
        this.hashSeguros = new HashMap<String, Seguro>();
    }

    /**
     * @return retorna la tarifa global actual
     */
    public TarifasGlobales getTarifasGlobales() {
        return this.tarifasGlobales;
    }

    /**
     * @return el hashmap con todas las categorias
     */
    public HashMap<String, Categoria> getHashCategorias() {
        return this.hashCategorias;
    }

    /**
     * @return el hashmap con todos los seguros
     */
    public HashMap<String, Seguro> getHashSeguros() {
        return this.hashSeguros;
    }

}