package SistemaLogin;

import java.io.*;
import java.util.*;

public class DatosClienteTarjeta {

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

    public Boolean getEstadoTarjeta() {
        // TODO implement here
        return false;
    }

    public void bloquearTarjeta() {
        // TODO implement here
    }

}