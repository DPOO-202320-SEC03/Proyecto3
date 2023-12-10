package pruebasReserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Inventario.Catalogo;
import Reservas.Reserva;
import Reservas.TarifasGlobales;

public class ReservaTest {

    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        reserva = new Reserva();
    }

    @Test
    public void testIdReserva() {
        int idEsperado = 123;
        reserva.setIdReserva(idEsperado);
        assertEquals(idEsperado, reserva.getIdReserva());
    }

    @Test
    public void testPlaca() {
        String placaEsperada = "ABC123";
        reserva.setPlaca(placaEsperada);
        assertEquals(placaEsperada, reserva.getPlaca());
    }
}