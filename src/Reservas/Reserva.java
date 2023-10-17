package Reservas;

import java.io.Serializable;

import Inventario.Catalogo;

public class Reserva implements Serializable {

    public static int totalDeReservas = 0;
    protected int idReserva;
    protected String placa;

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int id) {
    	this.idReserva = id;
    }

    public String getResumen(Catalogo catalogo, TarifasGlobales tarifaGlobal) {
        return "";
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
    	this.placa = placa;
    }

}