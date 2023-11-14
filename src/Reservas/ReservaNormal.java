package Reservas;

import java.util.*;

import Inventario.Catalogo;
import Inventario.Categoria;
import Inventario.Seguro;
import SistemaLogin.Cliente;
import SistemaLogin.Usuario;

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
    private String rangoAlquiler;

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
        this.rangoAlquiler = fechaRecoger + "-" + fechaEntregar;
        String placa = catalogo.getHashCategorias().get(categoriaVehiculo).getPlacaVehiculoParaReserva(rangoAlquiler, sedeRecoger);
        while (placa.equals("na")) {
            int rangoCategoriaNueva = catalogo.getHashCategorias().get(categoriaVehiculo).getRangoCategoria() + 1;
            String categoriaNueva = "na";
            for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                if (categoria.getValue().getRangoCategoria() == rangoCategoriaNueva) {
                    categoriaNueva = categoria.getKey();
                }
            }
            if (!(categoriaNueva.equals("na"))) {
                placa = catalogo.getHashCategorias().get(categoriaNueva).getPlacaVehiculoParaReserva(rangoAlquiler, sedeRecoger);
            } else {
                placa = "NA";
            }
        } 
        super.placa = placa;
    }

    public String getNuevaPlacaParaReserva(Catalogo catalogo, HashMap<String, Usuario> hashUsuarios, String usernameClienteAlquiler, String sedeRecoger) {
        String placa = catalogo.getHashCategorias().get(categoriaVehiculo).getPlacaVehiculoParaReserva(rangoAlquiler, sedeRecoger);
        while (placa.equals("na")) {
            int rangoCategoriaNueva = catalogo.getHashCategorias().get(categoriaVehiculo).getRangoCategoria() + 1;
            String categoriaNueva = "na";
            for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                if (categoria.getValue().getRangoCategoria() == rangoCategoriaNueva) {
                    categoriaNueva = categoria.getKey();
                }
            }
            if (!(categoriaNueva.equals("na"))) {
                placa = catalogo.getHashCategorias().get(categoriaNueva).getPlacaVehiculoParaReserva(rangoAlquiler, sedeRecoger);
            } else {
                placa = "NA";
            }
        } 
        super.placa = placa;
        if (super.placa.equals("NA")) {
            ((Cliente) hashUsuarios.get(usernameClienteAlquiler)).setTieneReserva(false);
        }
        return super.placa;
    }

    public String editarReserva(Catalogo catalogo, String sedeEntregar, String fechaEntregar, String horaRangoEntregar, int otrosConductores, String sedeRecoger) {
        String rangoAlquilerNuevo = fechaRecoger + "-" + fechaEntregar;
        // encuentra y elimina el rango viejo del vehiculo
        catalogo.getHashCategorias().get(categoriaVehiculo).getHashVehiculos().get(super.placa).getReservas().remove(this);
        String placaNueva = catalogo.getHashCategorias().get(categoriaVehiculo).getPlacaVehiculoParaReserva(rangoAlquilerNuevo, sedeRecoger);

        if (!placaNueva.equals("na")) {
            this.sedeEntregar = sedeEntregar;
            this.fechaEntregar = fechaEntregar;
            this.horaRangoEntregar = horaRangoEntregar;
            this.otrosConductores = otrosConductores;
            this.rangoAlquiler = rangoAlquilerNuevo;
            super.placa = placaNueva;
            catalogo.getHashCategorias().get(categoriaVehiculo).getHashVehiculos().get(placaNueva).getReservas().add(this);
        } else {
            catalogo.getHashCategorias().get(categoriaVehiculo).getHashVehiculos().get(super.placa).getReservas().add(this);
        }
        return placaNueva;
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
        
        HashMap<String, Categoria>  hashCategorias = catalogo.getHashCategorias();
        HashMap<String, Seguro> hashSeguros = catalogo.getHashSeguros();

        String anioInicialReserva = fechaRecoger.split("/")[2];
        String rangoTempAlta = tarifaGlobal.getRangoTemporadaAlta();
        String fechaInicioTempAlta = rangoTempAlta.split("-")[0] + "/" + anioInicialReserva;
        String fechaFinalTempAlta = rangoTempAlta.split("-")[1] + "/" + anioInicialReserva;
        Boolean inicioEnTemporadaAlta = false;
        long diferenciaInicioTInicioR = rangoFecha(fechaInicioTempAlta+"-"+fechaRecoger);
        long diferenciaFinalTInicioR = rangoFecha(fechaFinalTempAlta+"-"+fechaRecoger);
        if ((diferenciaInicioTInicioR > 0) && (diferenciaFinalTInicioR < 0)) {
            inicioEnTemporadaAlta = true;
        }

        long diasAlquiler = rangoFecha(fechaRecoger + "-" + fechaEntregar);
        int tarifaBaja = hashCategorias.get(categoriaVehiculo).getHashTarifaPorTemporada().get("baja");
        int tarifaAlta = hashCategorias.get(categoriaVehiculo).getHashTarifaPorTemporada().get("alta");
        int valorAlquiler =0;


        if(inicioEnTemporadaAlta) {
            valorAlquiler += diasAlquiler * tarifaAlta;
        } else {
            valorAlquiler += diasAlquiler * tarifaBaja;
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
    		seguros += nomseguro + ", " ;
    	}

        seguros = seguros.substring(0, seguros.length() - 2);

        return "ID Reserva: " + String.valueOf(super.idReserva)
                +"\nCategoria Vehiculo: " + categoriaVehiculo
        		+"\nSede Recoger: " + sedeRecoger
        		+"\nFecha Recoger: " + fechaRecoger
        		+"\nHora Recoger: " + horaRecoger
        		+"\nSede Entregar: " + sedeEntregar
        		+"\nFecha Entregar: " + fechaEntregar
        		+"\nHora Rango Entregar: " + horaRangoEntregar
        		+"\nUsuario Conductor Principal: " + usuarioConductorPrincipal
           		+"\nNúmero de conductores extra: " + String.valueOf(otrosConductores)
        		+"\nSeguros: " + seguros
                +"\nDias en alquiler: " + String.valueOf(rangoFecha(fechaRecoger + "-" + fechaEntregar))
                +"\nValor proyectado del alquiler " + String.valueOf(getValorProyectadoAlquiler(catalogo, tarifaGlobal))
                +"\nValor total del alquiler " + String.valueOf(getValorAlquilerCompleto(catalogo, tarifaGlobal));
    }

    public static long rangoFecha(String rangoF)
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

    public String getSedeRecoger() {
        return this.sedeRecoger;
    }


    public String getSedeEntrega() {
        return this.sedeEntregar;
    }

    public String getFechaEntrega() {
        return this.fechaEntregar;
    }

    public String getFechaAlquiler() {
        return this.fechaRecoger;
    }

    public String getCategoriaVehiculo() {
        return this.categoriaVehiculo;
    }

    public String getUsuarioAlquiler() {
        return this.usuarioConductorPrincipal;
    }
    
    @Override
    public String getRangoAlquiler() {
        return this.rangoAlquiler;
    }
}