package Inventario;

import java.io.Serializable;

public class Seguro implements Serializable {

    private int tarifaExtraDiaria;
    private String nombreSeguro;
    private String descripcionSeguro;

    /**
     * metodo constructor de un seguro
     * @param tarifaExtraDiaria tarifa por cada dia extra 
     * @param nombreSeguro nombre unico del seguro
     * @param descripcionSeguro descripcion del seguro 
     */
    public Seguro(int tarifaExtraDiaria, String nombreSeguro, String descripcionSeguro) {
        this.tarifaExtraDiaria = tarifaExtraDiaria;
        this.nombreSeguro = nombreSeguro;
        this.descripcionSeguro = descripcionSeguro;
    }

    /**
     * @return retorna la tarifa extra por dia
     */
    public int getTarifaExtra() {
        return this.tarifaExtraDiaria;
    }

    /**
     * @return retorna el nombre del seguro 
     */
    public String getNombreSeguro() {
        return this.nombreSeguro;
    }

    /**
     * @return retorna la descripcion del seguro
     */
    public String getDescripcionSeguro() {
        return this.descripcionSeguro;
    }

}