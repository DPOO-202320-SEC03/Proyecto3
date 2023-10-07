package Inventario;

import java.io.*;
import java.util.*;

public class Catalogo {

    private  HashMap<String, Categoria> hashCategorias;
    private HashMap<String,Seguro> hashSeguros;

    public Catalogo(HashMap<String, Categoria> hashCategorias, HashMap<String, Seguro> hashSeguros) {
        this.hashCategorias = hashCategorias;
        this.hashSeguros = hashSeguros;
    }

    private HashMap<String, Categoria> getHashCategorias() {
        // TODO implement here
        return null;
    }

    private HashMap<String, Seguro> getHashSeguros() {
        // TODO implement here
        return null;
    }

}