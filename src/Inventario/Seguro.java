package Inventario;

public class Seguro {

    private int tarifaExtraDiaria;
    private String nombreSeguro;
    private String descripcionSeguro;

    public Seguro(int tarifaExtraDiaria, String nombreSeguro, String descripcionSeguro) {
        this.tarifaExtraDiaria = tarifaExtraDiaria;
        this.nombreSeguro = nombreSeguro;
        this.descripcionSeguro = descripcionSeguro;
    }

    public int getTarifaExtra() {
        return this.tarifaExtraDiaria;
    }

    public String getNombreSeguro() {
        return this.nombreSeguro;
    }

    public String getDescripcionSeguro() {
        return this.descripcionSeguro;
    }

}