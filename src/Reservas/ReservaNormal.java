package Reservas;

import java.util.*;

import Inventario.Catalogo;
import Inventario.Categoria;

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

    public ReservaNormal(Catalogo catalogo, String categoriaVehiculo, String sedeRecoger, String fechaRecoger, String horaRecoger, String sedeEntregar, String fechaEntregar, String horaRangoEntregar, String usuarioConductorPrincipal, int otrosConductores, ArrayList<String> nombresSeguros) {
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
        String placa = catalogo.getHashCategorias().get(categoriaVehiculo).getPlacaVehiculoParaReserva();
        while (placa.equals("na")) {
            int rangoCategoriaNueva = catalogo.getHashCategorias().get(categoriaVehiculo).getRangoCategoria() + 1;
            String categoriaNueva = "na";
            for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                if (categoria.getValue().getRangoCategoria() == rangoCategoriaNueva) {
                    categoriaNueva = categoria.getKey();
                }
            }
            if (!categoriaNueva.equals("na")) {
                placa = catalogo.getHashCategorias().get(categoriaNueva).getPlacaVehiculoParaReserva();
            } else {
                placa = "No hay vehiculos disponibles en este momento";
            }
        } 
        super.placa = placa;
    }

    public void editarReserva(Catalogo catalogo, String sedeEntregar, String fechaEntregar, String horaRangoEntregar, int otrosConductores) {
        this.sedeEntregar = sedeEntregar;
        this.fechaEntregar = fechaEntregar;
        this.horaRangoEntregar = horaRangoEntregar;
        this.otrosConductores = otrosConductores;
    }

    public int getValorProyectadoAlquiler() {
        // TODO el 30% del valor alquiler completo
        return 0;
    }

    public int getValorAlquilerCompleto() {
        // TODO implement here
        // Aca se tiene que tener en cuenta tanto:
        // 1. el valor por temporada, se analzia cuantos dias esta en alquiler el vehiculo y se obtiene de la categoria el valor por temporada
        // 2. el valor por seguros adicionales, se analiza si el cliente quiere alguno de los seguros adicionales y se suma al valor total
        // 3. el valor por conductor adicional, se analiza si el cliente quiere un conductores adicionales y se suma al valor total
        // 4. el valor por entregar en otra sede, se analiza si el cliente quiere entregar el vehiculo en otra sede y se suma al valor total
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
        		+"\nSede Recoger: " + sedeRecoger
        		+"\nFecha Recoger: " + fechaRecoger
        		+"\nHora Recoger: " + horaRecoger
        		+"\nSede Entregar: " + sedeEntregar
        		+"\nFecha Entregar: " + fechaEntregar
        		+"\nHora Rango Entregar: " + horaRangoEntregar
        		+"\nUsuario Conductor Principal: " + usuarioConductorPrincipal
        		+"\nSeguros: " + seguros
                +"\nValor proyectado del alquiler " + String.valueOf(getValorProyectadoAlquiler())
                +"\nValor total del alquiler " + String.valueOf(getValorAlquilerCompleto());
    }

}