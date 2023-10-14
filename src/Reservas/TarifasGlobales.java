package Reservas;

import java.io.Serializable;

public class TarifasGlobales implements Serializable {
    
    int tarifaConductorExtra;
    int tarifaEntregaOtraSede;
    String rangoTemporadaAlta;
    
    public int getTarifaExtra() {
        return tarifaConductorExtra;
    }

    public int getTarifaSede() {
        return tarifaEntregaOtraSede;
    }

    public void setTarifaConductorExtra(int tarifaConductorExtra) {
        this.tarifaConductorExtra = tarifaConductorExtra;
    }

    public void setTarifaEntregaOtraSede(int tarifaEntregaOtraSede) {
        this.tarifaEntregaOtraSede = tarifaEntregaOtraSede;
    }

    public String getRangoTemporadaAlta() {
        return rangoTemporadaAlta;
    }

    public void setRangoTemporadaAlta(String rangoTemporadaAlta) {
        this.rangoTemporadaAlta = rangoTemporadaAlta;
    }
}
