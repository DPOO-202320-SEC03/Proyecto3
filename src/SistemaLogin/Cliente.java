package SistemaLogin;

import java.util.ArrayList;
import java.util.HashMap;

import Inventario.Catalogo;
import Reservas.Reserva;
import Reservas.ReservaNormal;

public class Cliente extends Usuario {

    private int nivelDeAcceso = 0;
    private int idReserva = -1;
    private Boolean tieneReserva = false;
    private ArrayList<DatosClienteLicencia> datosClienteLicencia;
    private DatosClienteTarjeta datosClienteTarjeta;

    public Cliente(String username, String password, String nombres, String apellidos, String celular, String correo, DatosClienteLicencia datosClienteLicencia, DatosClienteTarjeta datosClienteTarjeta) {
        super.username = username;
        super.password = password;        
        this.datosClienteLicencia = new ArrayList<DatosClienteLicencia>();
        this.datosClienteLicencia.add(datosClienteLicencia);
        this.datosClienteTarjeta = datosClienteTarjeta;
        super.nombres = nombres;
        super.apellidos = apellidos;
        super.celular = celular;
        super.correo = correo;
        super.nivelDeAcceso = nivelDeAcceso;
    }

    public String reservarVehiculo(HashMap<String, Reserva> hashReservas, Catalogo catalogo, String nombreCategoria, String sedeRecoger, String fechaRecoger, String horaRecoger, String sedeEntregar, String fechaEntregar, String horaRangoEntregar, int otrosConductores, ArrayList<String> nombresSeguros) {
        ReservaNormal reservaCliente = new ReservaNormal(catalogo, nombreCategoria, sedeRecoger, fechaRecoger, horaRecoger, sedeEntregar, fechaEntregar, horaRangoEntregar, super.username, otrosConductores, nombresSeguros, hashReservas);
        if (reservaCliente.getPlaca().equals("NA")) {
            return "No hay vehiculos disponibles en este momento para esta categoria";
        } else {
            hashReservas.put(Integer.toString(reservaCliente.getIdReserva()), reservaCliente);
            catalogo.getHashCategorias().get(reservaCliente.getCategoriaVehiculo()).getHashVehiculos().get(reservaCliente.getPlaca()).addReserva(reservaCliente);
            this.idReserva = reservaCliente.getIdReserva();
            this.tieneReserva = true;
            return reservaCliente.getResumen(catalogo, catalogo.getTarifasGlobales());
        }
    }

    public String alterarReserva(HashMap<String, Reserva> hashReservas, int idReserva, String nuevaSedeEntregar, String nuevaFechaEntregar, String nuevaHoraRangoEntregar, int otrosConductores, Catalogo catalogo) {
        String placaNueva = ((ReservaNormal) hashReservas.get(Integer.toString(idReserva))).editarReserva(catalogo, nuevaSedeEntregar, nuevaFechaEntregar, nuevaHoraRangoEntregar, otrosConductores, ((ReservaNormal) hashReservas.get(Integer.toString(idReserva))).getSedeRecoger());
        if (placaNueva.equals("na")) {
            return "No hay vehiculos disponibles en este momento para esta categoria, no se pudo editar la reserva";
        } else {
            return ((ReservaNormal)hashReservas.get(String.valueOf(idReserva))).getResumen(catalogo, catalogo.getTarifasGlobales());
        }
    }

    public String getResumenReservaActual(HashMap<String, Reserva> hashReservas, Catalogo catalogo) {
        return ((ReservaNormal)(hashReservas.get(String.valueOf(this.idReserva)))).getResumen(catalogo, catalogo.getTarifasGlobales());
    }

    public ArrayList<DatosClienteLicencia> getDatosClienteLicencia() {
        return this.datosClienteLicencia;
    }

    public Boolean getTieneTarjetaBloqueada() {
        return this.datosClienteTarjeta.getEstadoTarjeta();
    }

    public void setTieneReserva(Boolean estado) {
        this.tieneReserva = estado;
    }

    public Boolean getTieneReserva() {
        return this.tieneReserva;
    }

    public Integer getIdReserva() {
        return this.idReserva;
    }
}