package Consola;
import java.io.*;
import java.util.*;

import SistemaLogin.Administrador;
import SistemaLogin.AdministradorLocal;
import SistemaLogin.Cliente;
import SistemaLogin.DatosClienteLicencia;
import SistemaLogin.DatosClienteTarjeta;
import SistemaLogin.Usuario;
import Inventario.Seguro;
import Inventario.Vehiculo;
import Inventario.Catalogo;
import Inventario.Categoria;
import Inventario.Sede;
import Reservas.Reserva;
import Reservas.TarifasGlobales;

import java.awt.image.BufferedImage;

public class AppReservas {

    private Catalogo catalogo;
    private HashMap<String, Usuario> hashUsuarios;
    private HashMap<String, Sede> hashSedes;
    private HashMap<String, Reserva> hashReservas;

    public static void main(String[] args) {
        AppReservas app = new AppReservas();
        app.iniciarApp();
    }

    private void cargarInformacion() {
        try {
            String workingDir = System.getProperty("user.dir");
            String filePath = workingDir + File.separator + "data" + File.separator;
            File archivoCatalogo = new File(filePath+"catalogo");
            File archivoUsuarios = new File(filePath+"usuarios");
            File archivoSedes = new File(filePath+"sedes");
            File archivoReservas = new File(filePath+"reservas");
            if (archivoCatalogo.exists() && archivoUsuarios.exists() && archivoSedes.exists() && archivoReservas.exists()) {
                // Inicia el catalogo
                try {
                    ObjectInputStream oisC = new ObjectInputStream(new FileInputStream(archivoCatalogo));
                    this.catalogo = (Catalogo) oisC.readObject();
                    oisC.close();
                    ObjectInputStream oisU = new ObjectInputStream(new FileInputStream(archivoUsuarios));
                    this.hashUsuarios = (HashMap<String, Usuario>) oisU.readObject();
                    oisU.close();
                    ObjectInputStream oisS = new ObjectInputStream(new FileInputStream(archivoSedes));
                    this.hashSedes = (HashMap<String, Sede>) oisS.readObject();
                    oisS.close();
                    ObjectInputStream oisR = new ObjectInputStream(new FileInputStream(archivoReservas));
                    this.hashReservas = (HashMap<String, Reserva>) oisR.readObject();
                    oisR.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                this.catalogo = new Catalogo();
                this.hashUsuarios = new HashMap<String, Usuario>();
                this.hashSedes = new HashMap<String, Sede>();
                this.hashReservas = new HashMap<String, Reserva>();
                crearAdministrador();
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la información!!!");
            e.printStackTrace();
        }
    }

    private void guardarInformacion() {
        try {
            String workingDir = System.getProperty("user.dir");
            String filePath = workingDir + File.separator + "data" + File.separator;
            File archivoCatalogo = new File(filePath+"catalogo");
            File archivoUsuarios = new File(filePath+"usuarios");
            File archivoSedes = new File(filePath+"sedes");
            File archivoReservas = new File(filePath+"reservas");
            try {
                ObjectOutputStream oosC = new ObjectOutputStream(new FileOutputStream(archivoCatalogo));
                oosC.writeObject(this.catalogo);
                oosC.close();
                ObjectOutputStream oosU = new ObjectOutputStream(new FileOutputStream(archivoUsuarios));
                oosU.writeObject(this.hashUsuarios);
                oosU.close();
                ObjectOutputStream oosS = new ObjectOutputStream(new FileOutputStream(archivoSedes));
                oosS.writeObject(this.hashSedes);
                oosS.close();
                ObjectOutputStream oosR = new ObjectOutputStream(new FileOutputStream(archivoReservas));
                oosR.writeObject(this.hashReservas);
                oosR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error al guardar la información!!!");
            e.printStackTrace();
        }
    }

    private void crearAdministrador() {
        Administrador admin = new Administrador("ADMIN", "ADMIN", "Nombre", "Apellido", "1234567890","ADMIN@GOOGLE.COM");
        hashUsuarios.put(admin.getUsername(), admin);
    }

    public void mostrarMenuUsuario() {
        System.out.println("Bienvenido a la aplicación de reservas de vehículos");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
    }

    private void iniciarApp() {
        cargarInformacion();
        boolean continuar = true;
		while (continuar) {
			try {
				mostrarMenuUsuario();
                int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
                if (opcion_seleccionada == 1) {
                    iniciarSesion();
                } else if (opcion_seleccionada == 2) {
                    registrarse();
                } else if (opcion_seleccionada == 3) {
                    continuar = false;
                } else {
                    System.out.println("Debe seleccionar uno de los números de las opciones!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe seleccionar uno de los números de las opciones!!!");
            }
        }
        guardarInformacion();
    }

    private void registrarse() {
        System.out.println("\nPor favor ingrese sus datos personales:");
        String nombres = input("Por favor ingrese sus nombres");
        String apellidos = input("Por favor ingrese sus apellidos");
        String celular = input("Por favor ingrese su celular");
        String correo = input("Por favor ingrese su correo");
        System.out.println("\nPor favor ingrese los datos de su licencia:");
        Integer numeroDeLicencia = Integer.parseInt(input("Por favor ingrese el número de su licencia"));
        String paisDeExpedicion = input("Por favor ingrese el país de expedición de su licencia");
        String fechaDeVencimientoLicencia = input("Por favor ingrese la fecha de vencimiento de su licencia");
        BufferedImage imagenLicencia = null;
        try {
            String workingDir = System.getProperty("user.dir");
            String filePath = workingDir + File.separator + "data" + File.separator;
            File file = new File(filePath+"licencia.jpg");
            imagenLicencia = javax.imageio.ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Error al intentar leer la imagen de licencia, asegurarse que esta en la carpeta data!!!");
        }
        System.out.println("\nPor favor ingrese los datos de su tarjeta:");
        Integer numeroDeTarjeta = Integer.parseInt(input("Por favor ingrese el número de su tarjeta"));
        String fechaDeVencimientoTarjeta = input("Por favor ingrese la fecha de vencimiento de su tarjeta");
        String titular = input("Por favor ingrese el titular de la tarjeta");
        String marcaInternacional = input("Por favor ingrese la marca internacional de la tarjeta");
        Integer ccv = Integer.parseInt(input("Por favor ingrese el ccv de la tarjeta"));
        DatosClienteLicencia licencia = new DatosClienteLicencia(numeroDeLicencia, paisDeExpedicion, fechaDeVencimientoTarjeta, imagenLicencia);
        DatosClienteTarjeta tarjeta = new DatosClienteTarjeta(numeroDeTarjeta,fechaDeVencimientoTarjeta,titular,marcaInternacional,ccv);
        System.out.println("\nPor favor ingrese los datos para su usuario:");
        String username = input("Por favor ingrese su usuario");
        String password = input("Por favor ingrese su contraseña");
        Cliente cliente = new Cliente(username,password,nombres,apellidos,celular,correo,licencia,tarjeta);
        this.hashUsuarios.put(cliente.getUsername(), cliente);
    }
    
    private void iniciarSesion() {
        String username = input("Por favor ingrese su usuario");
        String password = input("Por favor ingrese su contraseña");
        int nivelDeAcceso;
        try {
            Usuario usuario = this.hashUsuarios.get(username);
            if (usuario.getPassword().equals(password)) {
                System.out.println("\nBienvenido " + usuario.getNombre() + "\n");
                nivelDeAcceso = usuario.getNivelDeAcceso();
                if (nivelDeAcceso == 3) {
                    Administrador admin = (Administrador) usuario;
                    ejecutarMenuAdministrador(admin);
                } else if (nivelDeAcceso == 2) {
                    // TODO ejecutarMenuAdministradorLocal(usuario);
                    AdministradorLocal adminLocal = (AdministradorLocal) usuario;
                    System.out.println("TEST ADMINLOCAL");
                } else if (nivelDeAcceso == 1) {
                    //TODO ejecutarMenuCliente(usuario);
                    Cliente cliente = (Cliente) usuario;
                    System.out.println("TEST CLIENTE");
                } else {
                    System.out.println("Nivel de acceso no válido!!!");
                }
            } else {
                System.out.println("Contraseña incorrecta!!!");
            }
        } catch (Exception e) {
            System.out.println("Usuario no encontrado!!!");
        }
    }

    private void ejecutarMenuAdministrador(Administrador admin) {
        Boolean continuar = true;
        while (continuar) {
            cargarInformacion();
            try {
                System.out.println("\nBienvenido al menú para administradores\n");
                System.out.println("Operaciones relacionadas con usuarios:");
                System.out.println("1. Crear administrador local");
                System.out.println("2. Eliminar usuario");
                System.out.println("Operaciones relacionadas con sedes:");
                System.out.println("3. Crear sede");
                System.out.println("Operaciones relacionadas con categorias:");
                System.out.println("4. Crear categoria");
                System.out.println("5. Crear o actualizar tarifas por temporada a categoria");
                System.out.println("Operaciones relacionadas con seguros:");
                System.out.println("6. Crear seguro");
                System.out.println("Operaciones relacionadas con tarifas globales:");
                System.out.println("7. Crear tarifas globales");
                System.out.println("8. Editar tarifas globales");
                System.out.println("Operaciones relacionadas con vehículos:");
                System.out.println("9. Crear vehículo");
                System.out.println("10. Eliminar vehículo");
                System.out.println("11. Obtener estado del vehículo");
                System.out.println("12. Trasladar vehículo");
                System.out.println("13. Salir");
                
                int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
                if (opcion_seleccionada == 1) {
                    String username = input("Ingrese un usuario para el administrador local");
                    String password = input("Ingrese una contraseña para el administrador local");
                    String nombreSede = input("Ingrese el nombre de la sede para el administrador local");
                    String nombres = input("Ingrese los nombres del administrador local");
                    String apellidos = input("Ingrese los apellidos del administrador local");
                    String celular = input("Ingrese el celular del administrador local");
                    String correo = input("Ingrese el correo del administrador local");
                    admin.crearAdministradorLocal(hashUsuarios, username, password, nombreSede, nombres, apellidos, celular, correo);
                    System.out.println("Administrador local creado y guardado exitosamente!!!");
                } else if (opcion_seleccionada == 2) {
                    String username = input("Ingrese un usuario que desea eliminar");
                    admin.eliminarUsuario(hashUsuarios, username);
                    System.out.println("Usuario" + username + "eliminado exitosamente!!!");
                } else if (opcion_seleccionada == 3) {
                    String nombreSede = input("Ingrese el nombre de la sede");
                    String ubicacion = input("Ingrese la ubicación de la sede");
                    String horariosDeAtencion = input("Ingrese los horarios de atención de la sede (En formato HH:MM - HH:MM)");
                    admin.crearSede(hashSedes, nombreSede, ubicacion, horariosDeAtencion);
                    System.out.println("Sede creada y guardada exitosamente!!!");
                } else if (opcion_seleccionada == 4) {
                    String nombreCategoria = input("Ingrese el nombre de la categoria");
                    int rangoCategoria = Integer.parseInt(input("Ingrese el rango de la categoria"));
                    admin.crearCategoria(catalogo, nombreCategoria, rangoCategoria);
                    System.out.println("Categoria creada y guardada exitosamente!!!");
                } else if (opcion_seleccionada == 5) {
                    String nombreCategoria = input("Ingrese el nombre de la categoria");
                    int tarifaTemporadaAlta = Integer.parseInt(input("Ingrese la tarifa para temporada alta"));
                    int tarifaTemporadaBaja = Integer.parseInt(input("Ingrese la tarifa para temporada baja"));
                    admin.crearTarifaPorTemporada(catalogo, nombreCategoria, tarifaTemporadaAlta, tarifaTemporadaBaja);
                    System.out.println("Tarifas por temporada creadas y guardadas exitosamente!!!");
                } else if (opcion_seleccionada == 6) {
                    int tarifaExtraDiaria = Integer.parseInt(input("Ingrese la tarifa extra diaria del seguro"));
                    String nombreSeguro = input("Ingrese el nombre del seguro");
                    String descripcionSeguro = input("Ingrese la descripción del seguro");
                    admin.crearSeguro(catalogo, tarifaExtraDiaria, nombreSeguro, descripcionSeguro);
                    System.out.println("Seguro creado y guardado exitosamente!!!");
                } else if (opcion_seleccionada == 7) {
                    int tarifaConductorExtra = Integer.parseInt(input("Ingrese la tarifa por un conductor extra"));
                    int tarifaEntregaOtraSede = Integer.parseInt(input("Ingrese la tarifa por entregar el vehículo en otra sede"));
                    String rangoTemporadaAlta = input("Ingrese el rango de la temporada alta (En formato DD/MM - DD/MM)");
                    admin.crearTarifasGlobales(catalogo, tarifaConductorExtra, tarifaEntregaOtraSede, rangoTemporadaAlta);
                    System.out.println("Tarifas globales creadas y guardadas exitosamente!!!");
                } else if (opcion_seleccionada == 8) {
                    System.out.println("A continuacion vera los valores previos de tarifas globales");
                    System.out.println("- Tarifa por conductor extra: " + this.catalogo.getTarifasGlobales().getTarifaExtra());
                    System.out.println("- Tarifa por entrega en otra sede: " + this.catalogo.getTarifasGlobales().getTarifaSede());
                    System.out.println("- Rango de temporada alta: " + this.catalogo.getTarifasGlobales().getRangoTemporadaAlta());
                    System.out.println("A continuacion ingrese los nuevos valores de tarifas globales");
                    int tarifaConductorExtra = Integer.parseInt(input("Ingrese la tarifa por un conductor extra"));
                    int tarifaEntregaOtraSede = Integer.parseInt(input("Ingrese la tarifa por entregar el vehículo en otra sede"));
                    String rangoTemporadaAlta = input("Ingrese el rango de la temporada alta (En formato DD/MM - DD/MM)");
                    admin.crearTarifasGlobales(catalogo, tarifaConductorExtra, tarifaEntregaOtraSede, rangoTemporadaAlta);
                    System.out.println("Tarifas globales actualizadas y guardadas exitosamente!!!");
                } else if (opcion_seleccionada == 9) {
                    String placa = input("Ingrese la placa del vehículo nuevo (En formato ABC-123)");
                    String marca = input("Ingrese la marca del vehículo nuevo");
                    String modelo = input("Ingrese el modelo del vehículo nuevo");
                    String color = input("Ingrese el color del vehículo nuevo");
                    String tipoDeTransmision = input("Ingrese el tipo de transmisión del vehículo nuevo");
                    String tipoDeDireccion = input("Ingrese el tipo de dirección del vehículo nuevo");
                    String tipoDeCombustible = input("Ingrese el tipo de combustible del vehículo nuevo");
                    String cantidadDePasajeros = input("Ingrese la cantidad de pasajeros que puede albergar el vehículo nuevo");
                    String nombreSede = input("Ingrese el nombre de la sede donde se encuentra el vehículo nuevo");
                    String categoria = input("Ingrese la categoria del vehículo nuevo");
                    while (!catalogo.getHashCategorias().containsKey(categoria)) {
                        System.out.println("La categoria ingresada no existe, por favor ingrese una categoria valida");
                        System.out.println("La categoria validas son:");
                        for (String key : catalogo.getHashCategorias().keySet()) {
                            System.out.println("- " + key);
                        }
                        categoria = input("Ingrese la categoria del vehículo nuevo");
                    }
                    Boolean disponibleParaAlquilar = Boolean.parseBoolean(input("Ingrese si el vehículo nuevo se encuentra disponible para alquiler (En formato true/false)"));
                    String fechaDisponibilidad = input("Ingrese la fecha de disponibilidad del vehículo nuevo (En formato DD/MM/YYYY)");
                    admin.crearVehiculo(catalogo, placa, marca, modelo, color, tipoDeTransmision, tipoDeDireccion, tipoDeCombustible, cantidadDePasajeros, nombreSede, categoria, disponibleParaAlquilar, fechaDisponibilidad);
                    System.out.println("Vehículo creado y guardado exitosamente!!!");
                } else if (opcion_seleccionada == 10) {
                    String placa = input("Ingrese la placa del vehículo que desea eliminar");
                    admin.eliminarVehiculo(catalogo, placa);
                    System.out.println("Vehículo eliminado exitosamente!!!");
                } else if (opcion_seleccionada == 11) {
                    String placa = input("Ingrese la placa del vehículo que desea consultar");
                    String resumenEstado = admin.estadoVehiculo(catalogo, placa);
                    System.out.println(resumenEstado);
                } else if (opcion_seleccionada == 12) {
                    String placa = input("Ingrese la placa del vehículo que desea trasladar");
                    String nombreSede = input("Ingrese el nombre de la sede a la cual desea trasladar el vehículo");
                    String fechaRecoger = input("Ingrese la fecha en la cual desea recoger el vehículo (En formato DD/MM/YYYY)");
                    String horaRecoger = input("Ingrese la hora en la cual desea recoger el vehículo (En formato HH:MM)");
                    String fechaEntregar = input("Ingrese la fecha en la cual desea entregar el vehículo (En formato DD/MM/YYYY)");
                    String detallesTraslado = admin.trasladarVehiculo(catalogo, hashReservas, placa, nombreSede, fechaRecoger, horaRecoger, fechaEntregar);
                    System.out.println(detallesTraslado);
                    System.out.println("Vehículo trasladado y reserva especial creada exitosamente!!!");
                } else if (opcion_seleccionada == 13) {
                    continuar = false;
                } else {
                    System.out.println("Debe seleccionar uno de los números de las opciones!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe seleccionar uno de los números de las opciones!!!");
            }
            guardarInformacion();
        }
    }

    private String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e) {
			System.out.println("Error leyendo de la consola.");
			e.printStackTrace();
		}
		return null;
	}

    }

