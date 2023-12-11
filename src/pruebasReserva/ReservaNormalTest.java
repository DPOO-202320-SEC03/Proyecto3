package pruebasReserva;

import org.junit.jupiter.api.Test;

import Inventario.Catalogo;
import Reservas.Reserva;
import Reservas.ReservaNormal;
import Reservas.TarifasGlobales;
import SistemaLogin.Administrador;
import SistemaLogin.AdministradorLocal;
import SistemaLogin.Cliente;
import SistemaLogin.DatosClienteLicencia;
import SistemaLogin.DatosClienteTarjeta;
import SistemaLogin.Usuario;
import Inventario.Seguro;
import Inventario.Categoria;
import Inventario.Sede;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
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
	public HashMap<String, Usuario> hashUsuarios;
    public HashMap<String, Sede> hashSedes;
    public HashMap<String, Reserva> hashReservas;

    @BeforeEach
    public void setUp() {
		this.catalogo = new Catalogo();
		this.hashUsuarios = new HashMap<String, Usuario>();
		this.hashSedes = new HashMap<String, Sede>();
		this.hashReservas = new HashMap<String, Reserva>();
		String username = "ADMIN";;
		String password = "ADMIN";
		String nombres = "ADMIN";
		String apellidos = "ADMIN";
		String celular = "1234567890";
		String correo = "ADMIN@GOOGLE.COM";
		Administrador admin = new Administrador(username, password, nombres, apellidos, celular, correo);
		admin.crearSede(hashSedes, "SEDE1", "N.A.", "N.A.");
        admin.crearSede(hashSedes, "SEDE2", "N.A.", "N.A.");
        admin.crearAdministradorLocal(hashUsuarios, "ADMIN1", "ADMIN1", "SEDE1", "ADMIN1", "ADMIN1", "1234567890", "ADMIN1@GOOGLE.COM");
        admin.crearAdministradorLocal(hashUsuarios, "ADMIN2", "ADMIN2", "SEDE2", "ADMIN2", "ADMIN2", "1234567890", "ADMIN2@GOOGLE.COM");
        admin.crearCategoria(catalogo, "CATEGORIA1", 1);
        admin.crearCategoria(catalogo, "CATEGORIA2", 2);
        admin.crearCategoria(catalogo, "CATEGORIA3", 3);
        admin.crearSeguro(catalogo, 50, "SEGURO1", "SEGURO1 es un seguro que esta hecho como ejemplo para guiar al usuario en como quedaria el seguro que el administrador desee crear para los clientes. Puede tener una longitud grande, pues se inserta con un scrollbar que le permite al usuario ver el resto del texto.");
        admin.crearSeguro(catalogo, 100, "SEGURO2", "SEGURO2 es un seguro que esta hecho como ejemplo para guiar al usuario en como quedaria el seguro que el administrador desee crear para los clientes. Puede tener una longitud grande, pues se inserta con un scrollbar que le permite al usuario ver el resto del texto.");
        admin.crearSeguro(catalogo, 150, "SEGURO3", "SEGURO3 es un seguro que esta hecho como ejemplo para guiar al usuario en como quedaria el seguro que el administrador desee crear para los clientes. Puede tener una longitud grande, pues se inserta con un scrollbar que le permite al usuario ver el resto del texto.");
        admin.crearTarifasGlobales(catalogo, 1000, 500, "01/01-01/31");
        admin.crearVehiculo(catalogo, "AAA-001", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "5", "SEDE1", "CATEGORIA1", "01/01/2020");
        admin.crearVehiculo(catalogo, "AAA-002", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "5", "SEDE1", "CATEGORIA2", "01/01/2020");
        admin.crearVehiculo(catalogo, "AAA-003", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "5", "SEDE1", "CATEGORIA3", "01/01/2020");
        admin.crearVehiculo(catalogo, "BBB-001", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "5", "SEDE2", "CATEGORIA1", "01/01/2020");
        admin.crearVehiculo(catalogo, "BBB-002", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "5", "SEDE2", "CATEGORIA2", "01/01/2020");
        admin.crearVehiculo(catalogo, "BBB-003", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "N.A.", "5", "SEDE2", "CATEGORIA3", "01/01/2020");

        // Operaciones Admin Local

        AdministradorLocal adminLocal1 = (AdministradorLocal) hashUsuarios.get("ADMIN1");
        AdministradorLocal adminLocal2 = (AdministradorLocal) hashUsuarios.get("ADMIN2");
        adminLocal1.crearEmpleado(hashUsuarios, "EMPLEADO1", "EMPLEADO1", "EMPLEADO1", "EMPLEADO1", "1234567890", "EMPLEADO1@GOOGLE.COM");
        adminLocal2.crearEmpleado(hashUsuarios, "EMPLEADO2", "EMPLEADO2", "EMPLEADO2", "EMPLEADO2", "1234567890", "EMPLEADO2@GOOGLE.COM");
        usuario = new Usuario();
		int conductores = 0;
		HashMap<String, Seguro> segurosCatalogo = catalogo.getHashSeguros();
		ArrayList<String> nombresSeguros = new ArrayList<>(segurosCatalogo.keySet());

		reservaNormal = new ReservaNormal(catalogo, "CATEGORIA1", "SEDE1", "01/01/2022", "10:00", "SEDE2", "01/02/2022", "10:00", "EMPLEADO1", conductores, nombresSeguros, hashReservas);
		TarifasGlobales tarifaGlobal = new TarifasGlobales();
		tarifaGlobal.setRangoTemporadaAlta("01/01-01/31");
		tarifaGlobal.setTarifaConductorExtra(500);
		tarifaGlobal.setTarifaEntregaOtraSede(1000);
    }

    @Test
	void testGetResumen() {
		// Crear instancias y establecer datos de prueba
		
		String resultado = reservaNormal.getResumen(catalogo, tarifaGlobal);

		String valorEsperado = "ID Reserva: 1\n" +
        "Categoria Vehiculo: CATEGORIA1\n" +
        "Sede Recoger: SEDE1\n" +
        "Fecha Recoger: 01/01/2022\n" +
        "Hora Recoger: 10:00\n" +
        "Sede Entregar: SEDE2\n" +
        "Fecha Entregar: 01/02/2022\n" +
        "Hora Rango Entregar: 10:00\n" +
        "Usuario Conductor Principal: EMPLEADO1\n" +
        "Número de conductores extra: 0\n" +
        "Seguros: \n" +
        "Dias en alquiler: 0\n" +
        "Valor proyectado del alquiler 0.0\n" +
        "Valor total del alquiler 0.0";
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
