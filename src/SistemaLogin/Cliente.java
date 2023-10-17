package SistemaLogin;

import Inventario.Catalogo;
import Reservas.Reserva;
import Reservas.ReservaNormal;

import java.util.*;

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
        ReservaNormal reservaCliente = new ReservaNormal(catalogo, nombreCategoria, sedeRecoger, fechaRecoger, horaRecoger, sedeEntregar, fechaEntregar, horaRangoEntregar, super.username, otrosConductores, nombresSeguros);
        if (reservaCliente.getPlaca().equals("NA")) {
            return "No hay vehiculos disponibles en este momento para esta categoria";
        } else {
            catalogo.getHashCategorias().get(nombreCategoria).getHashVehiculos().get(reservaCliente.getPlaca()).setEnReserva(true);
            hashReservas.put(Integer.toString(reservaCliente.getIdReserva()), reservaCliente);
            this.idReserva = reservaCliente.getIdReserva();
            this.tieneReserva = true;
            return reservaCliente.getResumen(catalogo, catalogo.getTarifasGlobales());
        }
    }

    public String alterarReserva(HashMap<String, Reserva> hashReservas, int idReserva, String nuevaSedeEntregar, String nuevaFechaEntregar, String nuevaHoraRangoEntregar, int otrosConductores, Catalogo catalogo) {
        ((ReservaNormal) hashReservas.get(Integer.toString(idReserva))).editarReserva(nuevaSedeEntregar, nuevaFechaEntregar, nuevaHoraRangoEntregar, otrosConductores);
        return ((ReservaNormal)hashReservas.get(String.valueOf(idReserva))).getResumen(catalogo, catalogo.getTarifasGlobales());
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