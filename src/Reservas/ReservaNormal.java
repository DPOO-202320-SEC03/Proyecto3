package Reservas;

import java.util.*;

import Inventario.Catalogo;
import Inventario.Categoria;
import Inventario.Seguro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


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
            if (!(categoriaNueva.equals("na"))) {
                placa = catalogo.getHashCategorias().get(categoriaNueva).getPlacaVehiculoParaReserva();
            } else {
                placa = "NA";
            }
        } 
        super.placa = placa;
    }

    public void editarReserva(String sedeEntregar, String fechaEntregar, String horaRangoEntregar, int otrosConductores) {
        this.sedeEntregar = sedeEntregar;
        this.fechaEntregar = fechaEntregar;
        this.horaRangoEntregar = horaRangoEntregar;
        this.otrosConductores = otrosConductores;
    }

    public int getValorProyectadoAlquiler(Catalogo catalogo, TarifasGlobales tarifaGlobal) {
        

        return (30 * getValorAlquilerCompleto(catalogo, tarifaGlobal))/100;
    }

    public int getValorAlquilerCompleto(Catalogo catalogo, TarifasGlobales tarifaGlobal) {
        // Aca se tiene que tener en cuenta tanto:
        // 1. el valor por temporada, se analzia cuantos dias esta en alquiler el vehiculo y se obtiene de la categoria el valor por temporada
        // 2. el valor por seguros adicionales, se analiza si el cliente quiere alguno de los seguros adicionales y se suma al valor total
        // 3. el valor por conductor adicional, se analiza si el cliente quiere un conductores adicionales y se suma al valor total
        // 4. el valor por entregar en otra sede, se analiza si el cliente quiere entregar el vehiculo en otra sede y se suma al valor total
        
        HashMap<String, Categoria>  hashCategorias = new HashMap<>();
        HashMap<String, Seguro> hashSeguros = new HashMap<>();
        hashCategorias = catalogo.getHashCategorias();
        hashSeguros = catalogo.getHashSeguros();

        String anioReserva = fechaRecoger.split("/")[2];
        String rangoTempAlta = tarifaGlobal.getRangoTemporadaAlta();
        String rangoTempAltaIncio = rangoTempAlta.split("-")[0] + "/" + anioReserva;
        String rangoTempAltaFinal = rangoTempAlta.split("-")[1] + "/" + anioReserva;
        String fechaInicioTempAlta = rangoTempAlta.split("-")[0] + "/" + anioReserva;
        String fechaFinalTempAlta = rangoTempAlta.split("-")[1] + "/" + anioReserva;
        
        long queTempInicio= rangoFecha(fechaRecoger + "-" + fechaInicioTempAlta);
        long queTempTermino = rangoFecha(fechaEntregar + "-" + fechaFinalTempAlta);
        long diasAlquiler = rangoFecha(fechaRecoger + "-" + fechaEntregar);
        long diasTempAlta = rangoFecha(rangoTempAltaIncio+"-"+rangoTempAltaFinal);
        int tarifaBaja = hashCategorias.get(categoriaVehiculo).getHashTarifaPorTemporada().get("baja");
        int tarifaAlta = hashCategorias.get(categoriaVehiculo).getHashTarifaPorTemporada().get("alta");
        int valorAlquiler =0;
         
        //valor por dia de alquiler
        if(diasAlquiler < 365)
        {
            if(queTempInicio>0)
            {
                valorAlquiler += (tarifaBaja * queTempInicio);
                
                if(queTempTermino>0)
                {
                    valorAlquiler += (tarifaAlta * rangoFecha(fechaInicioTempAlta + "-" + fechaEntregar));
                }
                else
                {
                    if(diasAlquiler > diasTempAlta)
                    {
                        valorAlquiler += (diasTempAlta * tarifaAlta) + (tarifaBaja * rangoFecha(fechaFinalTempAlta + "-" + fechaEntregar));                     
                    }
                    else
                    {
                        valorAlquiler += tarifaBaja * rangoFecha(fechaInicioTempAlta + "-" + fechaEntregar);
                    }
                }
            }
            if(queTempInicio<0)
            {
                valorAlquiler += (tarifaAlta * queTempInicio);
                
                if(queTempTermino>0)
                {
                    valorAlquiler += (tarifaAlta * rangoFecha(fechaInicioTempAlta + "-" + fechaEntregar));
                }
                else
                {
                    valorAlquiler += tarifaBaja * rangoFecha(fechaInicioTempAlta + "-" + fechaEntregar);
                }
            
            }
        }
        
        //valor por seguros adicionales
        int tarifaSeguroDiaria = 0;
        for(String seguro : nombresSeguros)
        {
            tarifaSeguroDiaria += hashSeguros.get(seguro).getTarifaExtra();
        }
        valorAlquiler += diasAlquiler * tarifaSeguroDiaria;

        //valor por otros conductores
        valorAlquiler += otrosConductores* tarifaGlobal.getTarifaExtra();

        //valor por entrega en otra sede
        if(sedeEntregar.equals(sedeRecoger) == false)
        {
            valorAlquiler += tarifaGlobal.getTarifaSede();
        }
        return valorAlquiler;
    }


    @Override
    public String getResumen(Catalogo catalogo, TarifasGlobales tarifaGlobal) {
    	String seguros = "";
    	
    	for (String nomseguro : nombresSeguros) 
    	{
    		seguros += nomseguro + "/n" ;
    	}
        return "ID Reserva: " + String.valueOf(super.idReserva)
                +"\nCategoria Vehiculo: " + categoriaVehiculo
        		+"\nSede Recoger: " + sedeRecoger
        		+"\nFecha Recoger: " + fechaRecoger
        		+"\nHora Recoger: " + horaRecoger
        		+"\nSede Entregar: " + sedeEntregar
        		+"\nFecha Entregar: " + fechaEntregar
        		+"\nHora Rango Entregar: " + horaRangoEntregar
        		+"\nUsuario Conductor Principal: " + usuarioConductorPrincipal
        		+"\nSeguros: " + seguros
                +"\nValor proyectado del alquiler " + String.valueOf(getValorProyectadoAlquiler(catalogo, tarifaGlobal))
                +"\nValor total del alquiler " + String.valueOf(getValorAlquilerCompleto(catalogo, tarifaGlobal));
    }

    public long rangoFecha(String rangoF)
    {
        // el formato de rangoF es un string de este tipo "MM/dd/aaaa" "01/01/2023-01/15/2023"
        long resultado= 0;

        // Specifica el formato de la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MM/dd/yyyy");

        try {
            // Extraer las fechas de recogida y entrega del vehicula del string.
            String[] fechas = rangoF.split("-");
            Date fechaInicio = formatoFecha.parse(fechas[0]);
            Date fechaFinal = formatoFecha.parse(fechas[1]);

            // Calcula la diferencia en millisegundos y luego dias
            long difMili = fechaFinal.getTime() - fechaInicio.getTime();
            long difDias = TimeUnit.DAYS.convert(difMili, TimeUnit.MILLISECONDS);
            resultado = difDias;
            

        } catch (ParseException e) {
            e.printStackTrace();
        }
    
    return resultado;
    }
}