package Inventario;

import java.util.*;
import java.io.Serializable;
import Reservas.TarifasGlobales;

public class Catalogo implements Serializable {

    private TarifasGlobales tarifasGlobales;
    private  HashMap<String, Categoria> hashCategorias;
    private HashMap<String,Seguro> hashSeguros;

    public Catalogo() {
        this.tarifasGlobales = new TarifasGlobales();
        this.hashCategorias = new HashMap<String, Categoria>();
        this.hashSeguros = new HashMap<String, Seguro>();
    }

    public TarifasGlobales getTarifasGlobales() {
        return this.tarifasGlobales;
    }

    public HashMap<String, Categoria> getHashCategorias() {
        return this.hashCategorias;
    }

    public HashMap<String, Seguro> getHashSeguros() {
        return this.hashSeguros;
    }

}