package SistemaLogin;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class DatosClienteLicencia implements Serializable {

    private int numeroLicencia;
    private String paisExpedicion;
    private String fechaVencimiento;
    private transient BufferedImage imagenLicencia;

    public DatosClienteLicencia(int numeroLicencia, String paisExpedicion, String fechaVencimiento, BufferedImage imagenLicencia) {
        this.numeroLicencia = numeroLicencia;
        this.paisExpedicion = paisExpedicion;
        this.fechaVencimiento = fechaVencimiento;
        this.imagenLicencia = imagenLicencia;
    }

    public BufferedImage getImagenLicencia() {
        return this.imagenLicencia;
    }

    public String getResumenLicencia() {
        String estadoLicencia = "";
        estadoLicencia += "Numero de licencia: " + this.numeroLicencia + "\n";
        estadoLicencia += "Pais de expedicion: " + this.paisExpedicion + "\n";
        estadoLicencia += "Fecha de vencimiento: " + this.fechaVencimiento + "\n";
        return estadoLicencia;
    }
}