package Reservas;

import java.io.*;
import java.util.*;

public class ReservaNormal extends Reserva {

    private String categoriaVehiculo;
    private String sedeRecoger;
    private String fechaRecoger;
    private String horaRecoger;
    private String sedeEntregar;
    private String fechaEntregar;
    private String horaRangoEntregar;
    private Boolean otrosConductores;
    private ArrayList<String> nombresSeguros;

    public ReservaNormal(String categoriaVehiculo, String sedeRecoger, String fechaRecoger, String horaRecoger, String sedeEntregar, String fechaEntregar, String horaRangoEntregar, Boolean otrosConductores, ArrayList<String> nombresSeguros) {
        this.categoriaVehiculo = categoriaVehiculo;
        this.sedeRecoger = sedeRecoger;
        this.fechaRecoger = fechaRecoger;
        this.horaRecoger = horaRecoger;
        this.sedeEntregar = sedeEntregar;
        this.fechaEntregar = fechaEntregar;
        this.horaRangoEntregar = horaRangoEntregar;
        this.otrosConductores = otrosConductores;
        this.nombresSeguros = nombresSeguros;
    }

    private void editarReserva(String sedeEntregar, String fechaEntregar, String horaRangoEntregar, Boolean otrosConductores) {
        this.sedeEntregar = sedeEntregar;
        this.fechaEntregar = fechaEntregar;
        this.horaRangoEntregar = horaRangoEntregar;
        this.otrosConductores = otrosConductores;
    }

    private int getValorProyectadoAlquiler() {
        // TODO implement here
        return 0;
    }

    private int getValorAlquilerCompleto() {
        // TODO implement here
        return 0;
    }

}