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
    private String usuarioConductorPrincipal;
    private int otrosConductores = 0;
    private ArrayList<String> nombresSeguros;

    public ReservaNormal(String categoriaVehiculo, String sedeRecoger, String fechaRecoger, String horaRecoger, String sedeEntregar, String fechaEntregar, String horaRangoEntregar, String usuarioConductorPrincipal, int otrosConductores, ArrayList<String> nombresSeguros) {
        super.idReserva = ++Reserva.totalDeReservas;
        this.categoriaVehiculo = categoriaVehiculo;
        this.sedeRecoger = sedeRecoger;
        this.fechaRecoger = fechaRecoger;
        this.horaRecoger = horaRecoger;
        this.sedeEntregar = sedeEntregar;
        this.fechaEntregar = fechaEntregar;
        this.horaRangoEntregar = horaRangoEntregar;
        this.usuarioConductorPrincipal = usuarioConductorPrincipal;
        this.otrosConductores = otrosConductores;
        this.nombresSeguros = nombresSeguros;
    }

    public void editarReserva(String sedeEntregar, String fechaEntregar, String horaRangoEntregar, int otrosConductores) {
        this.sedeEntregar = sedeEntregar;
        this.fechaEntregar = fechaEntregar;
        this.horaRangoEntregar = horaRangoEntregar;
        this.otrosConductores = otrosConductores;
    }

    public int getValorProyectadoAlquiler() {
        // TODO implement here
        return 0;
    }

    public int getValorAlquilerCompleto() {
        // TODO implement here
        return 0;
    }
    public String getResumen() {
    	
    	String seguros = "";
    	
    	for (String nomseguro : nombresSeguros) 
    	{
    		seguros += nomseguro + "/n" ;
    	}

        return "ID Reserva: " + super.idReserva 
                +"\nCategoria Vehiculo: " + categoriaVehiculo
        		+"/nSede Recoger: " + sedeRecoger
        		+"/nFecha Recoger: " + fechaRecoger
        		+"/nHora Recoger: " + horaRecoger
        		+"/nSede Entregar: " + sedeEntregar
        		+"/nFecha Entregar: " + fechaEntregar
        		+"/nHora Rango Entregar: " + horaRangoEntregar
        		+"/nUsuario Conductor Principal: " + usuarioConductorPrincipal
        		+"Seguros: " + seguros;
    }

}