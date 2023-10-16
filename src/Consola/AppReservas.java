package Consola;
import java.io.*;
import java.util.*;

import SistemaLogin.Administrador;
import SistemaLogin.AdministradorLocal;
import SistemaLogin.Cliente;
import SistemaLogin.DatosClienteLicencia;
import SistemaLogin.DatosClienteTarjeta;
import SistemaLogin.Usuario;
import Inventario.Catalogo;
import Inventario.Categoria;
import Inventario.Sede;
import Reservas.Reserva;

import java.awt.image.BufferedImage;

// USUARIO DEFAULT ADMINISTRADOR: ADMIN
// CONTRASEÑA DEFAULT ADMINISTRADOR: ADMIN

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
        String username;
        String password;
        String nombres;
        String apellidos;
        String celular;
        String correo;
        System.out.println("Dado que es la primera vez que abre la aplicacion necesitamos que cree un usuario administrador");
        System.out.println("Decida que tipo de usuario desea crear, sugerimos que cree un usuario personalizado para mayor seguridad");
        System.out.println("Un usuario personalizado (P) tiene un usuario y contraseña que usted elige, mientras que un usuario default (D) tiene usuario y contraseña ADMIN");
        String tipoDeAdmin = input("Por favor ingrese el tipo de administrador que desea crear (D/P)");
        if (tipoDeAdmin.equals("D")) {
            username = "ADMIN";
            password = "ADMIN";
            nombres = "ADMIN";
            apellidos = "ADMIN";
            celular = "1234567890";
            correo = "ADMIN@GOOGLE.COM";
            System.out.println("Su usuario y contraseña de administrador son: ADMIN/ADMIN");
        } else {
            username = input("Por favor ingrese su usuario para la cuenta de administrador");
            password = input("Por favor ingrese su contraseña para la cuenta de administrador");
            while (password.length() < 3) {
                System.out.println("La contraseña debe tener al menos 3 caracteres");
                password = input("Por favor ingrese su contraseña para la cuenta de administrador");
            }
            nombres = input("Por favor ingrese sus nombres");
            apellidos = input("Por favor ingrese sus apellidos");
            celular = input("Por favor ingrese su celular");
            correo = input("Por favor ingrese su correo");
        }
        Administrador admin = new Administrador(username, password, nombres, apellidos, celular, correo);
        hashUsuarios.put(admin.getUsername(), admin);
    }

    private void iniciarApp() {
        cargarInformacion();
        boolean continuar = true;
		while (continuar) {
			try {
				System.out.println("\nBienvenido a la aplicación de reservas de vehículos");
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrarse");
                System.out.println("3. Salir\n");
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
            System.out.println("Error al intentar leer la imagen de licencia, asegurarse que esta en la carpeta data nombrada como licencia con extensión .png!!!");
        }
        System.out.println("\nPor favor ingrese los datos de su tarjeta:");
        Integer numeroDeTarjeta = Integer.parseInt(input("Por favor ingrese el número de su tarjeta"));
        String fechaDeVencimientoTarjeta = input("Por favor ingrese la fecha de vencimiento de su tarjeta");
        String titular = input("Por favor ingrese el titular de la tarjeta");
        String marcaInternacional = input("Por favor ingrese la marca internacional de la tarjeta");
        Integer ccv = Integer.parseInt(input("Por favor ingrese el ccv de la tarjeta"));
        DatosClienteLicencia licencia = new DatosClienteLicencia(numeroDeLicencia, paisDeExpedicion, fechaDeVencimientoLicencia, imagenLicencia);
        DatosClienteTarjeta tarjeta = new DatosClienteTarjeta(numeroDeTarjeta,fechaDeVencimientoTarjeta,titular,marcaInternacional,ccv);
        System.out.println("\nPor favor ingrese los datos para su usuario:");
        String username = input("Por favor ingrese su usuario");
        while (hashUsuarios.containsKey(username)) {
            System.out.println("El usuario ingresado ya existe, por favor ingrese un usuario nuevo");
            username = input("Por favor ingrese su usuario");
        }
        String password = input("Por favor ingrese su contraseña");
        while (password.length() < 3) {
            System.out.println("La contraseña debe tener al menos 3 caracteres");
            password = input("Por favor ingrese su contraseña");
        }
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
                    AdministradorLocal adminLocal = (AdministradorLocal) usuario;
                    ejecutarMenuAdministradorLocal(adminLocal);
                } else if (nivelDeAcceso == 1) {
                    Cliente cliente = (Cliente) usuario;
                    ejecutarMenuCliente(cliente);
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
                System.out.println("12. Trasladar vehículo a otra sede");
                System.out.println("Operaciones relacionadas con reservas:");
                System.out.println("13. Consultar reserva por ID\n");
                System.out.println("14. Salir");
                
                int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
                if (opcion_seleccionada == 1) {
                    String username = input("Ingrese un usuario para el administrador local");
                    while (hashUsuarios.containsKey(username)) {
                        System.out.println("El usuario ingresado ya existe, por favor ingrese un usuario nuevo");
                        username = input("Ingrese un usuario para el administrador local");
                    }
                    String password = input("Ingrese una contraseña para el administrador local");
                    while (password.length() < 3) {
                        System.out.println("La contraseña debe tener al menos 3 caracteres");
                        password = input("Ingrese una contraseña para el administrador local");
                    }
                    String nombreSede = input("Ingrese el nombre de la sede para el administrador local");
                    String nombres = input("Ingrese los nombres del administrador local");
                    String apellidos = input("Ingrese los apellidos del administrador local");
                    String celular = input("Ingrese el celular del administrador local");
                    String correo = input("Ingrese el correo del administrador local");
                    admin.crearAdministradorLocal(hashUsuarios, username, password, nombreSede, nombres, apellidos, celular, correo);
                    System.out.println("Administrador local creado y guardado exitosamente!!!");
                } else if (opcion_seleccionada == 2) {
                    String username = input("Ingrese un usuario que desea eliminar");
                    if (!username.equals(admin.getUsername())) {
                        admin.eliminarUsuario(hashUsuarios, username);
                        System.out.println("Usuario" + username + "eliminado exitosamente!!!");
                    } else {
                        System.out.println("No se puede eliminar tu propio usuario!!!");
                    }
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
                    if (hashSedes.size()>0 && catalogo.getHashCategorias().size()>0) {
                        String placa = input("Ingrese la placa del vehículo nuevo (En formato ABC-123)");
                        String marca = input("Ingrese la marca del vehículo nuevo");
                        String modelo = input("Ingrese el modelo del vehículo nuevo");
                        String color = input("Ingrese el color del vehículo nuevo");
                        String tipoDeTransmision = input("Ingrese el tipo de transmisión del vehículo nuevo");
                        String tipoDeDireccion = input("Ingrese el tipo de dirección del vehículo nuevo");
                        String tipoDeCombustible = input("Ingrese el tipo de combustible del vehículo nuevo");
                        String cantidadDePasajeros = input("Ingrese la cantidad de pasajeros que puede albergar el vehículo nuevo incluyendo al conductor");

                        String nombreSede = input("Ingrese el nombre de la sede donde se encuentra el vehículo nuevo");
                        while (!hashSedes.containsKey(nombreSede)) {
                            System.out.println("La sede ingresada no existe, por favor ingrese una sede valida");
                            System.out.println("Las sedes validas son:");
                            for (String key : hashSedes.keySet()) {
                                System.out.println("- " + key);
                            }
                            nombreSede = input("Ingrese el nombre de la sede donde se encuentra el vehículo nuevo");
                        }

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
                    } else {
                        System.out.println("No hay sedes o categorias creadas, por favor cree una antes de crear un vehículo");
                    }   
                } else if (opcion_seleccionada == 10) {
                    String placa = input("Ingrese la placa del vehículo que desea eliminar");
                    Boolean esta = false;
                    for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                        if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                            esta = true;
                        }
                    }
                    if (esta) {
                        admin.eliminarVehiculo(catalogo, placa);
                        System.out.println("Vehículo eliminado exitosamente!!!");
                    } else {
                        System.out.println("El vehículo no existe!!!");
                    }
                } else if (opcion_seleccionada == 11) {
                    String placa = input("Ingrese la placa del vehículo que desea consultar");
                    Boolean esta = false;
                    for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                        if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                            esta = true;
                        }
                    }
                    if (esta) {
                        String resumenEstado = admin.estadoVehiculo(catalogo, placa);
                        System.out.println(resumenEstado);
                    } else {
                        System.out.println("El vehículo no existe!!!");
                    }
                } else if (opcion_seleccionada == 12) {
                    String placa = input("Ingrese la placa del vehículo que desea trasladar");
                    Boolean esta = false;
                    String sedeActual = "";
                    for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                        if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                            esta = true;
                            sedeActual = categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().getSedeUbicacion();
                        }
                    }
                    if (esta) {
                        String sedeDestino = input("Ingrese el nombre de la sede a la cual desea trasladar el vehículo");
                        while (!hashSedes.containsKey(sedeDestino) || sedeDestino.equals(sedeActual)) {
                            System.out.println("La sede destino ingresada no existe o es la actual, por favor ingrese una sede valida");
                            System.out.println("Las sedes validas son:");
                            for (String key : hashSedes.keySet()) {
                                if (!key.equals(sedeActual)) {
                                    System.out.println("- " + key);
                                }
                            }
                            sedeDestino = input("Ingrese el nombre de la sede a la cual desea trasladar el vehículo");
                        }
                        String fechaRecoger = input("Ingrese la fecha en la cual desea recoger el vehículo (En formato DD/MM/YYYY)");
                        String horaRecoger = input("Ingrese la hora en la cual desea recoger el vehículo (En formato HH:MM)");
                        String fechaEntregar = input("Ingrese la fecha en la cual desea entregar el vehículo (En formato DD/MM/YYYY)");
                        String detallesTraslado = admin.trasladarVehiculo(catalogo, hashReservas, placa, sedeDestino, fechaRecoger, horaRecoger, fechaEntregar);
                        System.out.println(detallesTraslado);
                        System.out.println("Vehículo trasladado y reserva especial creada exitosamente!!!");
                    } else {
                        System.out.println("El vehículo no existe!!!");
                    }
                } else if (opcion_seleccionada == 13) {
                    String idReserva = input("Ingrese el id de la reserva que desea consultar");
                    if (hashReservas.containsKey(idReserva)) {
                        admin.resumenReserva(hashReservas, idReserva);
                    } else {
                        System.out.println("La reserva no existe!!!");
                    }
                } else if (opcion_seleccionada == 14) {
                    continuar = false;
                } else {
                    System.out.println("Debe seleccionar uno de los números de las opciones!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe seleccionar uno de los números de las opciones!!!");
            }
            guardarInformacion();
            cargarInformacion();
        }
    }

    private void ejecutarMenuAdministradorLocal(AdministradorLocal adminLocal) {
        // TODO implementar
        Boolean continuar = true;
        while (continuar) {
            try {
                System.out.println("\nBienvenido al menú para administradores locales\n");
                // TODO implementar
                System.out.println("14. Salir");
                
                int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
                if (opcion_seleccionada == 1) {
                    // TODO implementar
                } else if (opcion_seleccionada == 14) {
                    continuar = false;
                } else {
                    System.out.println("Debe seleccionar uno de los números de las opciones!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe seleccionar uno de los números de las opciones!!!");
            }
            guardarInformacion();
            cargarInformacion();
        }
    }

    private void ejecutarMenuCliente(Cliente cliente) {
        // TODO implementar
        Boolean continuar = true;
        while (continuar) {
            try {
                System.out.println("\nBienvenido al menú para clientes\n");
                // TODO implementar
                System.out.println("14. Salir");
                
                int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
                if (opcion_seleccionada == 1) {
                    // TODO implementar
                } else if (opcion_seleccionada == 14) {
                    continuar = false;
                } else {
                    System.out.println("Debe seleccionar uno de los números de las opciones!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe seleccionar uno de los números de las opciones!!!");
            }
            guardarInformacion();
            cargarInformacion();
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

