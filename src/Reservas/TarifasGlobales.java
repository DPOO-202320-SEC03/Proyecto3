package Reservas;

public class TarifasGlobales {
    
    int tarifaConductorExtra;
    int tarifaEntregaOtraSede;
    
    private int getTarifaExtra() {
        return tarifaConductorExtra;
    }

    private int getTarifaSede() {
        return tarifaEntregaOtraSede;
    }

    private void setTarifaExtra(int tarifaConductorExtra) {
        this.tarifaConductorExtra = tarifaConductorExtra;
    }

    private void setTarifaEntregaOtraSede(int tarifaEntregaOtraSede) {
        this.tarifaEntregaOtraSede = tarifaEntregaOtraSede;
    }
}
