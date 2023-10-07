package Reservas;

import java.io.*;
import java.util.*;

public class ReservaEspecial extends Reserva {

    private String sedeOrigen;
    private String sedeDestino;
    private String fechaRecoger;
    private String horaRecoger;
    private String fechaEntrega;

    public ReservaEspecial(String sedeOrigen, String sedeDestino, String fechaRecoger, String horaRecoger, String fechaEntrega) {
        this.sedeOrigen = sedeOrigen;
        this.sedeDestino = sedeDestino;
        this.fechaRecoger = fechaRecoger;
        this.horaRecoger = horaRecoger;
        this.fechaEntrega = fechaEntrega;
    }
    
    private ArrayList<String> getDetallesReserva() {
        // TODO implement here
        return null;
    }
}