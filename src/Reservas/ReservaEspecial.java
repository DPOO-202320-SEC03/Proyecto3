package Reservas;

import Inventario.Catalogo;

public class ReservaEspecial extends Reserva {

    private String sedeOrigen;
    private String sedeDestino;
    private String fechaRecoger;
    private String horaRecoger;
    private String fechaEntrega;

    public ReservaEspecial(String sedeOrigen, String sedeDestino, String fechaRecoger, String horaRecoger, String fechaEntrega, String placa) {
        super.idReserva = ++Reserva.totalDeReservas;
        this.sedeOrigen = sedeOrigen;
        this.sedeDestino = sedeDestino;
        this.fechaRecoger = fechaRecoger;
        this.horaRecoger = horaRecoger;
        this.fechaEntrega = fechaEntrega;
        super.placa = placa;
    }
    @Override
    public String getResumen(Catalogo catalogo, TarifasGlobales tarifaGlobal) {
        return "ID Reserva: " + super.idReserva 
                +"\nSede origen: " + sedeOrigen
        		+"\nSede destino: " + sedeDestino
        		+"\nFecha recoger: " + fechaRecoger
        		+"\nHora recoger: " + horaRecoger
        		+"\nFecha entrega: " + fechaEntrega
                +"\nPlaca del vehículo: " + super.placa;
    }
    
}