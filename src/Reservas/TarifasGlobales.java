package Reservas;

public class TarifasGlobales {
    
    int tarifaConductorExtra;
    int tarifaEntregaOtraSede;

    public TarifasGlobales(int tarifaConductorExtra, int tarifaEntregaOtraSede) {
        this.tarifaConductorExtra = tarifaConductorExtra;
        this.tarifaEntregaOtraSede = tarifaEntregaOtraSede;
    }
    
    private int getTarifaExtra() {
        return tarifaConductorExtra;
    }

    private int getTarifaSede() {
        return tarifaEntregaOtraSede;
    }
}
