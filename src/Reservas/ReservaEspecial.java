package Reservas;

public class ReservaEspecial extends Reserva {

    private String sedeOrigen;
    private String sedeDestino;
    private String fechaRecoger;
    private String horaRecoger;
    private String fechaEntrega;
    private String placa;

    public ReservaEspecial(String sedeOrigen, String sedeDestino, String fechaRecoger, String horaRecoger, String fechaEntrega, String placa) {
        super.idReserva = ++Reserva.totalDeReservas;
        this.sedeOrigen = sedeOrigen;
        this.sedeDestino = sedeDestino;
        this.fechaRecoger = fechaRecoger;
        this.horaRecoger = horaRecoger;
        this.fechaEntrega = fechaEntrega;
        this.placa = placa;
    }
    
    public String getResumen() {
    	
        return "Sede origen: " + sedeOrigen
        		+"\nSede destino: " + sedeDestino
        		+"\nFecha recoger: " + fechaRecoger
        		+"\nHora recoger: " + horaRecoger
        		+"\nFecha entrega: " + fechaEntrega
                +"\nPlaca del vehículo: " + placa;
    }
    
}