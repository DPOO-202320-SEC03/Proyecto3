package src.SistemaLogin;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class DatosClienteMedioDePago {

    /**
     * Default constructor
     */
    public DatosClienteMedioDePago() {
    }

    /**
     * 
     */
    private int numeroTarjeta;

    /**
     * 
     */
    private String fechaVencimiento;

    /**
     * 
     */
    private String titular;

    /**
     * 
     */
    private String marcaInternacional;

    /**
     * 
     */
    private int cvv;

    /**
     * 
     */
    private Boolean tarjetaBloqueada = False;

    /**
     * @return
     */
    private Boolean getEstadoTarjeta() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private void bloquearTarjeta() {
        // TODO implement here
        return null;
    }

}