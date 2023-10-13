package Inventario;

import java.io.*;
import java.util.*;
import java.io.Serializable;
import Reservas.TarifasGlobales;

public class Catalogo implements Serializable {

    private TarifasGlobales tarifasGlobales;
    private  HashMap<String, Categoria> hashCategorias;
    private HashMap<String,Seguro> hashSeguros;

    private void setTarifasGlobales(TarifasGlobales tarifasGlobales) {
        this.tarifasGlobales = tarifasGlobales;
    }

    private TarifasGlobales getTarifasGlobales() {
        // TODO implement here
        return null;
    }

    private void setHashCategorias(HashMap<String, Categoria> hashCategorias) {
        this.hashCategorias = hashCategorias;
    }

    private HashMap<String, Categoria> getHashCategorias() {
        // TODO implement here
        return null;
    }

    private void setHashSeguros(HashMap<String, Seguro> hashSeguros) {
        this.hashSeguros = hashSeguros;
    }

    private HashMap<String, Seguro> getHashSeguros() {
        // TODO implement here
        return null;
    }

}