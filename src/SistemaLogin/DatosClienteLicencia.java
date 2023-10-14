package SistemaLogin;

import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class DatosClienteLicencia {

    private int numeroLicencia;
    private String paisExpedicion;
    private String fechaVencimiento;
    private BufferedImage imagenLicencia;

    public DatosClienteLicencia(int numeroLicencia, String paisExpedicion, String fechaVencimiento, BufferedImage imagenLicencia) {
        this.numeroLicencia = numeroLicencia;
        this.paisExpedicion = paisExpedicion;
        this.fechaVencimiento = fechaVencimiento;
        this.imagenLicencia = imagenLicencia;
    }

    public BufferedImage getImagenLicencia() {
        return this.imagenLicencia;
    }
}