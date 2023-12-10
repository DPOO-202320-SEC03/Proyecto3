package pruebasReserva;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import Inventario.Catalogo;
import Reservas.Reserva;
import Reservas.ReservaNormal;
import SistemaLogin.Cliente;


public class ReservaGeneral {
    @Test
	public void testReservarVehiculo() {
		// Crear las instancias necesarias para el test
		HashMap<String, Reserva> hashReservas = new HashMap<>();
		Catalogo catalogo = new Catalogo();
		String nombreCategoria = "categoriaTest";
		String sedeRecoger = "sedeTest";
		String fechaRecoger = "fechaTest";
		String horaRecoger = "horaTest";
		String sedeEntregar = "sedeTest";
		String fechaEntregar = "fechaTest";
		String horaRangoEntregar = "horaTest";
		int otrosConductores = 1;
		ArrayList<String> nombresSeguros = new ArrayList<>();
		boolean estaEnNuevaApp = true;

		// Crear una instancia de Cliente
		Cliente cliente = new Cliente("usernameTest", "passwordTest", "nombresTest", "apellidosTest", "celularTest", "correoTest", null, null);

		// Crear una instancia de ReservaNormal con los mismos parámetros que se usarán en reservarVehiculo
		ReservaNormal reservaEsperada = new ReservaNormal(catalogo, nombreCategoria, sedeRecoger, fechaRecoger, horaRecoger, sedeEntregar, fechaEntregar, horaRangoEntregar, cliente.getUsername(), otrosConductores, nombresSeguros, hashReservas);
		if (estaEnNuevaApp) {
			reservaEsperada.setDescuentoNuevaApp(0.9);
		}

		// Llamar al método a probar
		String resultado = cliente.reservarVehiculo(hashReservas, catalogo, nombreCategoria, sedeRecoger, fechaRecoger, horaRecoger, sedeEntregar, fechaEntregar, horaRangoEntregar, otrosConductores, nombresSeguros, estaEnNuevaApp);

		// Verificar el resultado
		assertEquals(reservaEsperada.getResumen(catalogo, catalogo.getTarifasGlobales()), resultado);
	}
}
