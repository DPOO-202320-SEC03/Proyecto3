package Inventario;

import java.util.*;
import java.io.Serializable;
import Reservas.TarifasGlobales;

public class Catalogo implements Serializable {

    private TarifasGlobales tarifasGlobales;
    private  HashMap<String, Categoria> hashCategorias;
    private HashMap<String,Seguro> hashSeguros;

    public void setTarifasGlobales(TarifasGlobales tarifasGlobales) {
        this.tarifasGlobales = tarifasGlobales;
    }

    public TarifasGlobales getTarifasGlobales() {
        return this.tarifasGlobales;
    }

    public void setHashCategorias(HashMap<String, Categoria> hashCategorias) {
        this.hashCategorias = hashCategorias;
    }

    public HashMap<String, Categoria> getHashCategorias() {
        return this.hashCategorias;
    }

    public void setHashSeguros(HashMap<String, Seguro> hashSeguros) {
        this.hashSeguros = hashSeguros;
    }

    public HashMap<String, Seguro> getHashSeguros() {
        return this.hashSeguros;
    }

}