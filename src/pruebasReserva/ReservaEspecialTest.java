package pruebasReserva;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Reservas.Reserva;
import Reservas.ReservaEspecial;
import java.util.HashMap;

class ReservaEspecialTest extends Reserva{

    @Test
    void testGetResumen() {
        HashMap<String, Reserva> hashReservas = new HashMap<>();
        ReservaEspecial reserva = new ReservaEspecial("sedeOrigenTest", "sedeDestinoTest", "fechaRecogerTest", "horaRecogerTest", "fechaEntregaTest", "placaTest", hashReservas);
        String expected = "ID Reserva: 1\nSede origen: sedeOrigenTest\nSede destino: sedeDestinoTest\nFecha recoger: fechaRecogerTest\nHora recoger: horaRecogerTest\nFecha entrega: fechaEntregaTest\nPlaca del vehículo: placaTest";
        assertEquals(expected, reserva.getResumen(null, null));
    }

    @Test
    void testGetRangoAlquiler() {
        HashMap<String, Reserva> hashReservas = new HashMap<>();
        ReservaEspecial reserva = new ReservaEspecial("sedeOrigenTest", "sedeDestinoTest", "fechaRecogerTest", "horaRecogerTest", "fechaEntregaTest", "placaTest", hashReservas);
        String expected = "fechaRecogerTest-fechaEntregaTest";
        assertEquals(expected, reserva.getRangoAlquiler());
    }
}