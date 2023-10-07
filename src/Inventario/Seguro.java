package Inventario;

import java.io.*;
import java.util.*;

public class Seguro {

    private int tarifaExtraDiaria;
    private String nombreSeguro;
    private String descripcionSeguro;

    public Seguro(int tarifaExtraDiaria, String nombreSeguro, String descripcionSeguro) {
        this.tarifaExtraDiaria = tarifaExtraDiaria;
        this.nombreSeguro = nombreSeguro;
        this.descripcionSeguro = descripcionSeguro;
    }

    private int getTarifaExtra() {
        // TODO implement here
        return 0;
    }

    private String getNombreSeguro() {
        // TODO implement here
        return "";
    }

    private String getDescripcionSeguro() {
        // TODO implement here
        return "";
    }

}