package SistemaLogin;

import java.io.Serializable;

public class DatosClienteTarjeta implements Serializable {

    private int numeroTarjeta;
    private String fechaVencimiento;
    private String titular;
    private String marcaInternacional;
    private int cvv;
    private Boolean tarjetaBloqueada = false;

    public DatosClienteTarjeta(int numeroTarjeta, String fechaVencimiento, String titular, String marcaInternacional, int cvv) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.titular = titular;
        this.marcaInternacional = marcaInternacional;
        this.cvv = cvv;
    }

    public String getResumenTarjeta() {
        String estadoTarjeta = "";
        estadoTarjeta += "Numero de tarjeta: " + this.numeroTarjeta + "\n";
        estadoTarjeta += "Fecha de vencimiento: " + this.fechaVencimiento + "\n";
        estadoTarjeta += "Titular: " + this.titular + "\n";
        estadoTarjeta += "Marca internacional: " + this.marcaInternacional + "\n";
        estadoTarjeta += "CVV: " + this.cvv + "\n";
        estadoTarjeta += "Estado de la tarjeta: " + this.tarjetaBloqueada + "\n";
        return estadoTarjeta;
    }

    public Boolean getEstadoTarjeta() {
        return this.tarjetaBloqueada;
    }

    public void setEstadoTarjeta(Boolean estado) {
        this.tarjetaBloqueada = estado;
    }

}