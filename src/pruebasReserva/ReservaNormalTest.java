package pruebasReserva;

import org.junit.jupiter.api.Test;

import Inventario.Catalogo;
import Reservas.Reserva;
import Reservas.ReservaNormal;
import Reservas.TarifasGlobales;
import SistemaLogin.Usuario;
import Inventario.Seguro;
import Inventario.Categoria;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;

class ReservaNormalTest {

	private ReservaNormal reservaNormal;
    private Catalogo catalogo;
    private Usuario usuario;
	private TarifasGlobales tarifaGlobal;
	private Seguro seguro;
	private Categoria categoria;

    @BeforeEach
    public void setUp() {
		seguro = new Seguro(10, "nombreSeguroTest", "descripcionSeguroTest");
		categoria = new Categoria("categoriaTest", 1);
		tarifaGlobal = new TarifasGlobales();
		tarifaGlobal.setTarifaConductorExtra(10);
		tarifaGlobal.setTarifaEntregaOtraSede(10);
		tarifaGlobal.setRangoTemporadaAlta("01/01/2023-01/15/2023");
        catalogo = new Catalogo();
        usuario = new Usuario();
		int conductores = 0;
        reservaNormal = new ReservaNormal(catalogo, "categoriaTest", "sedeTest", "fechaRecogerTest", "horaRecogerTest", "sedeEntregarTest", "fechaEntregarTest", "horaRangoEntregarTest", "testUser", conductores, new ArrayList<>(), new HashMap<>());

    }

    @Test
	void testGetResumen() {
		// Crear instancias y establecer datos de prueba
		TarifasGlobales tarifaGlobal = new TarifasGlobales();
		String resultado = reservaNormal.getResumen(catalogo, tarifaGlobal);

		String valorEsperado = "ID Reserva: 1\nCategoria Vehiculo: categoriaTest\nSede Recoger: sedeTest\nFecha Recoger: fechaRecogerTest\nHora Recoger: horaRecogerTest\nSede Entregar: sedeEntregarTest\nFecha Entregar: fechaEntregarTest\nHora Rango Entregar: horaRangoEntregarTest\nUsuario Conductor Principal: testUser\nNúmero de conductores extra: 0\nSeguros: \nDias en alquiler: 0\nValor proyectado del alquiler 0.0\nValor total del alquiler 0.0";
		assertEquals(valorEsperado, resultado);
	}

	@Test
    void testgetValorProyectadoAlquiler() {
		// Crear instancias y establecer datos de prueba
		TarifasGlobales tarifaGlobal = new TarifasGlobales();
		int resultado = reservaNormal.getValorProyectadoAlquiler(catalogo, tarifaGlobal);
		// Comparar el resultado con el valor esperado
		int valorEsperado = 0; 
		assertEquals(valorEsperado, resultado);
	}

    @Test
    void testGetValorAlquilerCompleto() {
		// Crear instancias y establecer datos de prueba
		TarifasGlobales tarifaGlobal = new TarifasGlobales();
		int resultado = reservaNormal.getValorAlquilerCompleto(catalogo, tarifaGlobal);
		// Comparar el resultado con el valor esperado
		int valorEsperado = 0;
		assertEquals(valorEsperado, resultado);
	}

    @Test
    void testGetRangoFecha() {
		// Crear instancias y establecer datos de prueba
		long resultado = ReservaNormal.rangoFecha("01/01/2023-01/15/2023");
		// Comparar el resultado con el valor esperado
		long valorEsperado = 15; 
		assertEquals(valorEsperado, resultado);
	}
}
