package Consola;
import java.io.*;
import java.util.*;

import SistemaLogin.Administrador;
import SistemaLogin.AdministradorLocal;
import SistemaLogin.Cliente;
import SistemaLogin.DatosClienteLicencia;
import SistemaLogin.DatosClienteTarjeta;
import SistemaLogin.Empleado;
import SistemaLogin.Usuario;
import Inventario.Catalogo;
import Inventario.Categoria;
import Inventario.Sede;
import Inventario.Seguro;
import Inventario.Vehiculo;
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

    /**
     * metodo privado usado para cargar toda la informacion del sistema
     */
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

    /**
     * metodo privado para guardar en los archivos todos los cambios 
     */
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

    /**
     * metodo privado para crear un nuevo administradno 
     */
    private void crearAdministrador() {
        // variables usado para la creacion de un administrador personalizado
        String username;
        String password;
        String nombres;
        String apellidos;
        String celular;
        String correo;
        // cuando se abre por primera vez el programa de la opcion de crear un administrados default o uno personalizado 
        System.out.println("Dado que es la primera vez que abre la aplicacion necesitamos que cree un usuario administrador");
        System.out.println("Decida que tipo de usuario desea crear, sugerimos que cree un usuario personalizado para mayor seguridad");
        System.out.println("Un usuario personalizado (P) tiene un usuario y contraseña que usted elige, mientras que un usuario default (D) tiene usuario y contraseña ADMIN");
        String tipoDeAdmin = input("Por favor ingrese el tipo de administrador que desea crear (D/P)");
        // si se elije la opcion de default se asignan unos datos preestablecidos
        if (tipoDeAdmin.equals("D")) {
            username = "ADMIN";
            password = "ADMIN";
            nombres = "ADMIN";
            apellidos = "ADMIN";
            celular = "1234567890";
            correo = "ADMIN@GOOGLE.COM";
            System.out.println("Su usuario y contraseña de administrador son: ADMIN/ADMIN");
        } 
        // se le pide todos los datos al cliente, en donde la contraseña debe tener como minimo 3 caracteres 
        else {
            username = input("Por favor ingrese su usuario para la cuenta de administrador");
            while (username.length() < 3) {
                System.out.println("El usuario debe tener al menos 3 caracteres");
                password = input("Por favor ingrese su usuario para la cuenta de administrador");
            }
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
        // se crea un nuevo administrador cno los datos 
        Administrador admin = new Administrador(username, password, nombres, apellidos, celular, correo);
        hashUsuarios.put(admin.getUsername(), admin);
    }
    
    /**
     * metodo privado para inicar la app
     */
    private void iniciarApp() {
        // se carga la informacion y se crea un variable usada para verificar cunado el programa debe seguir
        cargarInformacion();
        boolean continuar = true;
		while (continuar) {
			try {
				System.out.println("\nBienvenido a la aplicación de reservas de vehículos");
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrarse");
                System.out.println("3. Salir\n");
                int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
                // opcion para que un usuario inicie seccion, segun el nivel de acceso del usuario se lleva a menus diferentes
                if (opcion_seleccionada == 1) {
                    iniciarSesion();
                } 
                // opcion para que un nuevo cliente se registre
                else if (opcion_seleccionada == 2) {
                    registrarse();
                } 
                // opcion para terminar el programa 
                else if (opcion_seleccionada == 3) {
                    continuar = false;
                } 
                // mensaje de error por si no se selecciona una opcion valida
                else {
                    System.out.println("Debe seleccionar uno de los números de las opciones!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe seleccionar uno de los números de las opciones!!!");
            }
        }
        guardarInformacion();
    }

    /**
     * metodo privado usado para registrar un cliente nuevo 
     */
    private void registrarse() {
        // se piden todos los datos al cliente, nombre, apellido, celular, correo, datos de licencia, datos de tarjeta, usuario y contraseña
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
        // se crea el cliente y se agrega la hash de usuario
        Cliente cliente = new Cliente(username,password,nombres,apellidos,celular,correo,licencia,tarjeta);
        this.hashUsuarios.put(cliente.getUsername(), cliente);
    }
    
    /**
     * funcion privada usada para que un usuario ya creado inicie seccion 
     */
    private void iniciarSesion() {
        // se pide el usuario y contraseña
        String username = input("Por favor ingrese su usuario");
        String password = input("Por favor ingrese su contraseña");
        int nivelDeAcceso;
        try {
            // se busca el usuario en el hash de usuario y se comprueba su nivel de acceso para verificar a que menu accede se dividen en
            //  3. administrador, 2. administrador local, 1. empleado, 0. cliente
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
                    Empleado empleado = (Empleado) usuario;
                    ejecutarMenuEmpleado(empleado);
                } else if (nivelDeAcceso == 0) {
                    Cliente cliente = (Cliente) usuario;
                    ejecutarMenuCliente(cliente);
                } else {
                    System.out.println("Nivel de acceso no válido!!!");
                }
            } 
            // mensaje de error por si la contraseña no es valida
            else {
                System.out.println("Contraseña incorrecta!!!");
            }
        }
        // mensaje de error por si el usuario no existe
         catch (Exception e) {
            System.out.println("Usuario no encontrado!!!");
        }
    }

    /**
     * metodo privado que ejecuta el menu con todas las funciones del administrador
     * @param admin parametro para invoncar los metodos de la clase Administrador
     */
    private void ejecutarMenuAdministrador(Administrador admin) {
        // variable para verificar si el menu se debe seguir ejecutando
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
                // opcion uno que sirve para la creacion de un nuevo administrador local
                if (opcion_seleccionada == 1) {
                    if (hashSedes.size() > 0) {
                // se pide un nuevo usuario para el empleado este debe tener 3 caracteres como minimo y debe de ser unico
                        String username = input("Ingrese un usuario para el administrador local");
                        while (hashUsuarios.containsKey(username) || username.length() < 3) {
                            System.out.println("El usuario ingresado ya existe o tiene menos de 3 caracteres, por favor ingrese un usuario valido");
                            username = input("Ingrese un usuario para el administrador local");
                        }
                // se pide un contraseña para el usuario con 3 carateres como minimo
                        String password = input("Ingrese una contraseña para el administrador local");
                        while (password.length() < 3) {
                            System.out.println("La contraseña debe tener al menos 3 caracteres");
                            password = input("Ingrese una contraseña para el administrador local");
                        }
                // se pide la sede en donde se ubicara el administrador local, si no existe la sede abre un menu con todas las disponibles
                        String nombreSede = input("Ingrese el nombre de la sede para el administrador local");
                        while (!hashSedes.containsKey(nombreSede)) {
                            System.out.println("La sede ingresada no existe, por favor ingrese una sede valida");
                            System.out.println("Las sedes validas son:");
                            for (String key : hashSedes.keySet()) {
                                System.out.println("- " + key);
                            }
                            nombreSede = input("Ingrese el nombre de la sede para el administrador local");
                        }
                // se pide los nombres, apellidos celular y correo de el administrador local y se llama al metodo para crear el aministrador local 
                        String nombres = input("Ingrese los nombres del administrador local");
                        String apellidos = input("Ingrese los apellidos del administrador local");
                        String celular = input("Ingrese el celular del administrador local");
                        String correo = input("Ingrese el correo del administrador local");
                        admin.crearAdministradorLocal(hashUsuarios, username, password, nombreSede, nombres, apellidos, celular, correo);
                        System.out.println("Administrador local creado y guardado exitosamente!!!");
                    } 
                // mensaje de error por si no se a creadio ninguna sede
                    else {
                        System.out.println("No hay sedes creadas, por favor cree una antes de crear un administrador local");
                    }
                } 
                // opcion dos que sirve para eliminar un usuario
                else if (opcion_seleccionada == 2) {
                    String username = input("Ingrese un usuario que desea eliminar");
                    // se verifica que el usuario no sea el mismo administrador y se elimina si existe
                    if (!username.equals(admin.getUsername())) {
                        admin.eliminarUsuario(hashUsuarios, username);
                        System.out.println("Usuario " + username + " eliminado exitosamente!!!");
                    } 
                // mensaje de error por si no existe el usuario
                    else {
                        System.out.println("No se puede eliminar tu propio usuario!!!");
                    }
                } 
                // opcion 3 que sirve para crear una nueva sede 
                else if (opcion_seleccionada == 3) {
                // se pide el nombre, ubicacion y los horarios de la sede
                    String nombreSede = input("Ingrese el nombre de la sede");
                    String ubicacion = input("Ingrese la ubicación de la sede");
                    String horariosDeAtencion = input("Ingrese los horarios de atención de la sede (En formato HH:MM - HH:MM)");
                    admin.crearSede(hashSedes, nombreSede, ubicacion, horariosDeAtencion);
                    System.out.println("Sede creada y guardada exitosamente!!!");
                } 
                // opcion 4 que sirve para crear una nueva categoria para el auto movil
                else if (opcion_seleccionada == 4) {
                // se le pide el nombre de la categoria, el rando y se crea 
                    String nombreCategoria = input("Ingrese el nombre de la categoria");
                    int rangoCategoria = Integer.parseInt(input("Ingrese el rango de la categoria"));
                    admin.crearCategoria(catalogo, nombreCategoria, rangoCategoria);
                    System.out.println("Categoria creada y guardada exitosamente!!!");
                } 
                // opcion 5 que sirve para Crear o actualizar tarifas por temporada a categoria
                else if (opcion_seleccionada == 5) {
                // se pide la categoria y las tarifas en temporada alta y baja, para despues crear las tarifas
                    String nombreCategoria = input("Ingrese el nombre de la categoria");
                    int tarifaTemporadaAlta = Integer.parseInt(input("Ingrese la tarifa para temporada alta"));
                    int tarifaTemporadaBaja = Integer.parseInt(input("Ingrese la tarifa para temporada baja"));
                    admin.crearTarifaPorTemporada(catalogo, nombreCategoria, tarifaTemporadaAlta, tarifaTemporadaBaja);
                    System.out.println("Tarifas por temporada creadas y guardadas exitosamente!!!");
                } 
                // opcion 6 que sirve para crear un seguro
                else if (opcion_seleccionada == 6) {
                // se pide una tarifa por cada dia extra, un numero, una descripcion del seguro y se crea el seguro
                    int tarifaExtraDiaria = Integer.parseInt(input("Ingrese la tarifa extra diaria del seguro"));
                    String nombreSeguro = input("Ingrese el nombre del seguro");
                    String descripcionSeguro = input("Ingrese la descripción del seguro");
                    admin.crearSeguro(catalogo, tarifaExtraDiaria, nombreSeguro, descripcionSeguro);
                    System.out.println("Seguro creado y guardado exitosamente!!!");
                } 
                // opcion 7 que sirve para crear tarifas globales
                else if (opcion_seleccionada == 7) {
                // se pide una tarifa por un conductor extra, tarifa por entregar el vehiculo en una sede distinta, y el rango de tiempo de la temporada alta
                    int tarifaConductorExtra = Integer.parseInt(input("Ingrese la tarifa por un conductor extra"));
                    int tarifaEntregaOtraSede = Integer.parseInt(input("Ingrese la tarifa por entregar el vehículo en otra sede"));
                    String rangoTemporadaAlta = input("Ingrese el rango de la temporada alta (En formato MM/DD - MM/DD)");
                    admin.crearTarifasGlobales(catalogo, tarifaConductorExtra, tarifaEntregaOtraSede, rangoTemporadaAlta);
                    System.out.println("Tarifas globales creadas y guardadas exitosamente!!!");
                } 
                // opcion 8 que sirve para editar tarifas globales
                else if (opcion_seleccionada == 8) {
                // se imprimen las tarifas actuales
                    System.out.println("A continuacion vera los valores previos de tarifas globales");
                    System.out.println("- Tarifa por conductor extra: " + this.catalogo.getTarifasGlobales().getTarifaExtra());
                    System.out.println("- Tarifa por entrega en otra sede: " + this.catalogo.getTarifasGlobales().getTarifaSede());
                    System.out.println("- Rango de temporada alta: " + this.catalogo.getTarifasGlobales().getRangoTemporadaAlta());
                    System.out.println("A continuacion ingrese los nuevos valores de tarifas globales");
                // se piden todas las nuevas tarifas y se crea la nueva tarifa
                    int tarifaConductorExtra = Integer.parseInt(input("Ingrese la tarifa por un conductor extra"));
                    int tarifaEntregaOtraSede = Integer.parseInt(input("Ingrese la tarifa por entregar el vehículo en otra sede"));
                    String rangoTemporadaAlta = input("Ingrese el rango de la temporada alta (En formato MM/DD - MM/DD)");
                    admin.crearTarifasGlobales(catalogo, tarifaConductorExtra, tarifaEntregaOtraSede, rangoTemporadaAlta);
                    System.out.println("Tarifas globales actualizadas y guardadas exitosamente!!!");
                } 
                // opcion 9 que sirve para crear un nuevo vehiculo
                else if (opcion_seleccionada == 9) {
                    if (hashSedes.size()>0 && catalogo.getHashCategorias().size()>0) {
                // se pide la informacion del vehiculo como la placa, marca modelo, colo, transmision, direccion, combustible, pasajero..
                        String placa = input("Ingrese la placa del vehículo nuevo (En formato ABC-123)");
                        String marca = input("Ingrese la marca del vehículo nuevo");
                        String modelo = input("Ingrese el modelo del vehículo nuevo");
                        String color = input("Ingrese el color del vehículo nuevo");
                        String tipoDeTransmision = input("Ingrese el tipo de transmisión del vehículo nuevo");
                        String tipoDeDireccion = input("Ingrese el tipo de dirección del vehículo nuevo");
                        String tipoDeCombustible = input("Ingrese el tipo de combustible del vehículo nuevo");
                        String cantidadDePasajeros = input("Ingrese la cantidad de pasajeros que puede albergar el vehículo nuevo incluyendo al conductor");

                // se pide un nobre de sede, si esta no es valida muestra un menu con todos los menus disponibles
                        String nombreSede = input("Ingrese el nombre de la sede donde se encuentra el vehículo nuevo");
                        while (!hashSedes.containsKey(nombreSede)) {
                            System.out.println("La sede ingresada no existe, por favor ingrese una sede valida");
                            System.out.println("Las sedes validas son:");
                            for (String key : hashSedes.keySet()) {
                                System.out.println("- " + key);
                            }
                            nombreSede = input("Ingrese el nombre de la sede donde se encuentra el vehículo nuevo");
                        }

                // se pide una categoria, si esta no es valida muestra un menu con todas las categorias disponibles
                        String categoria = input("Ingrese la categoria del vehículo nuevo");
                        while (!catalogo.getHashCategorias().containsKey(categoria)) {
                            System.out.println("La categoria ingresada no existe, por favor ingrese una categoria valida");
                            System.out.println("La categoria validas son:");
                            for (String key : catalogo.getHashCategorias().keySet()) {
                                System.out.println("- " + key);
                            }
                            categoria = input("Ingrese la categoria del vehículo nuevo");
                        }

                // se pregunta si esta listo para alquilar de enseguida y en que fecha estara disponible y se crea
                        Boolean disponibleParaAlquilar = Boolean.parseBoolean(input("Ingrese si el vehículo nuevo se encuentra disponible para alquiler (En formato true/false)"));
                        String fechaDisponibilidad = input("Ingrese la fecha de disponibilidad del vehículo nuevo (En formato MM/DD/AAAA)");
                        admin.crearVehiculo(catalogo, placa, marca, modelo, color, tipoDeTransmision, tipoDeDireccion, tipoDeCombustible, cantidadDePasajeros, nombreSede, categoria, disponibleParaAlquilar, fechaDisponibilidad);
                        System.out.println("Vehículo creado y guardado exitosamente!!!");
                    } 
                // mensaje de error por si no existe ninguna sede o categoria
                    else {
                        System.out.println("No hay sedes o categorias creadas, por favor cree una antes de crear un vehículo");
                    }   
                } 
                // opcion 10 que sirve para eliminar un vehiculo
                else if (opcion_seleccionada == 10) {
                // se pide la placa del vehiculo
                    String placa = input("Ingrese la placa del vehículo que desea eliminar");
                    Boolean esta = false;
                // se busca en el hash de vehiculos si exite la placa
                    for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                        if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                            esta = true;
                        }
                    }
                // si existe se elimina
                    if (esta) {
                        admin.eliminarVehiculo(catalogo, placa);
                        System.out.println("Vehículo eliminado exitosamente!!!");
                    } 
                // mensaje de error por si no se encontro la placa
                    else {
                        System.out.println("El vehículo no existe!!!");
                    }
                } 
                // opcion 11 que sirve para consultar el estado de un vehiculo
                else if (opcion_seleccionada == 11) {
                // se pregunta la placa del vehiculo
                    String placa = input("Ingrese la placa del vehículo que desea consultar");
                    Boolean esta = false;
                // se busca si existe la placa
                    for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                        if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                            esta = true;
                        }
                    }
                // si existe se muestra todo el estado del vehiculo
                    if (esta) {
                        String resumenEstado = admin.estadoVehiculo(catalogo, placa);
                        System.out.println(resumenEstado);
                    } 
                // mensaje de error por si no se encontro la placa
                    else {
                        System.out.println("El vehículo no existe!!!");
                    }
                } 
                // opcion 12 por si se desea trasladar el vehiculo a otra sede
                else if (opcion_seleccionada == 12) {
                // se pide la placa del vehiculo
                    String placa = input("Ingrese la placa del vehículo que desea trasladar");
                    Boolean esta = false;
                    String sedeActual = "";
                // se busca si existe la placa y se guarda la sede actual
                    for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                        if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                            esta = true;
                            sedeActual = categoria.getValue().getHashVehiculos().get(placa).getDetallesSede().getSedeUbicacion();
                        }
                    }
                // si se encontro se pide la sede de destino y se compara de que sea diferente a la actual o si no existe, en ambos casos
                // se despliega un menu con todas las sedes disponibles
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
                // se establece una fecha y hora en la que se recoge el vehiculo y una fecha en la cual se entrga
                        String fechaRecoger = input("Ingrese la fecha en la cual desea recoger el vehículo (En formato MM/DD/AAAA)");
                        String horaRecoger = input("Ingrese la hora en la cual desea recoger el vehículo (En formato HH:MM)");
                        String fechaEntregar = input("Ingrese la fecha en la cual desea entregar el vehículo (En formato MM/DD/AAAA)");
                        String detallesTraslado = admin.trasladarVehiculo(catalogo, hashReservas, placa, sedeDestino, fechaRecoger, horaRecoger, fechaEntregar);
                        System.out.println(detallesTraslado);
                        System.out.println("Vehículo trasladado y reserva especial creada exitosamente!!!");
                    } 
                // mensaje de error por si no existe la placa 
                    else {
                        System.out.println("El vehículo no existe!!!");
                    }
                } 
                // opcion 13 que sirve para consulatar un reserva basada en su ID
                else if (opcion_seleccionada == 13) {
                // se pide la ID
                    String idReserva = input("Ingrese el id de la reserva que desea consultar");
                // se busca la ID y si existe se muestran los detalles
                    if (hashReservas.containsKey(idReserva)) {
                        admin.resumenReserva(hashReservas, idReserva, catalogo);
                    } 
                // mensaje de error por si no se encuentra la reserva
                    else {
                        System.out.println("La reserva no existe!!!");
                    }
                } 
                // opcion 14 que sirve para salir del menu
                else if (opcion_seleccionada == 14) {
                    continuar = false;
                } 
                // mensaje de error por si no se selecciona una opcion valida
                else {
                    System.out.println("Debe seleccionar uno de los números de las opciones!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe seleccionar uno de los números de las opciones!!!");
            }
            guardarInformacion();
            cargarInformacion();
        }
    }
/**
     * metodo para ejecutar el menu del administrador local
     * @param adminLocal parametro para invocar los metodos del administrador local
     */
    private void ejecutarMenuAdministradorLocal(AdministradorLocal adminLocal) {
        // variable para verificar cuando debe de parar el programa
        Boolean continuar = true;
        while (continuar) {
            try {
                System.out.println("\nBienvenido al menú para administradores locales\n");
                System.out.println("1. Crear un nuevo empleado para la sede");
                System.out.println("2. Eliminar un empleado");
                System.out.println("14. Salir");
                int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
                // primera opcion que sirve para crear un nuevo empleado
                // se pide un nuevo usuario para el empleado este debe tener 3 caracteres como minimo y debe de ser unico
                if (opcion_seleccionada == 1) {
                    String username = input("Ingrese un usuario para el empleado");
                    while (hashUsuarios.containsKey(username) || username.length() < 3) {
                        System.out.println("El usuario ingresado ya existe o tiene menos de 3 caracteres, por favor ingrese un usuario valido");
                        username = input("Ingrese un usuario para el empleado");
                    }
                // se pide un contraseña para el usuario con 3 carateres como minimo
                    String password = input("Ingrese una contraseña para el empleado");
                    while (password.length() < 3) {
                        System.out.println("La contraseña debe tener al menos 3 caracteres");
                        password = input("Ingrese una contraseña para el empleado");
                    }
                // se pide los nombres, apellidos celular y correo de empleaddo y se llama al metodo para crear el empleado
                    String nombres = input("Ingrese los nombres del empleado");
                    String apellidos = input("Ingrese los apellidos del empleado");
                    String celular = input("Ingrese el celular del empleado");
                    String correo = input("Ingrese el correo del empleado");
                    adminLocal.crearEmpleado(hashUsuarios, username, password, nombres, apellidos, celular, correo);
                    System.out.println("Empleado creado y guardado exitosamente!!!");
                }
                // opcion 2 para eliminar un empleado
                else if(opcion_seleccionada == 2){
                // se pide el usuario que se desea eliminar 
                    String username = input("Ingrese un usuario que desea eliminar");
                // se verifica el usuario que se desea eliminar no sea el mismo con el que se esta ejecutano el programa, si es el mismo manda un mensaje de error
                    if (!username.equals(adminLocal.getUsername())) {
                        boolean esta = false;
                // se busca en el hasmap de usuario si existe el usuario que se busca y si tiene un nivel de acceso 1 que significa que es empleado
                        for (Usuario usuario : hashUsuarios.values()) {
                            if (usuario.getUsername().equals(username) && usuario.getNivelDeAcceso() == 1) {
                                esta = true;
                            }
                        }
                // si ambas condiciones se cumplen se crea el usuario y el programa dice que se creo con exito
                        if (esta) {
                            adminLocal.eliminarEmpleado(hashUsuarios, username);
                            System.out.println("Usuario " + username + " eliminado exitosamente!!!");
                        } 
                // si no cumple una de las condiciones o ninguna, notifica que no se pudo crear 
                        else {System.out.println("El usuario no existe o no es un empleado!!!");}
                    } else {
                        System.out.println("No se puede eliminar tu propio usuario!!!");
                    }
                }
                // opcion 14 para salir del programa se usa 14 porque la idea que todos los salir sean el mismo numero
                 else if (opcion_seleccionada == 14) {
                    continuar = false;
                } 
                // mensaje de error por si no selecciona ninguna de la opciones disponible
                else {
                    System.out.println("Debe seleccionar uno de los números de las opciones!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe seleccionar uno de los números de las opciones!!!");
            }
            guardarInformacion();
            cargarInformacion();
        }
    }

    /**
     * metodo privado que ejecuta el menu de empleado
     * @param empleado parametro usado par acceder a los metodos de los empleados
     */
    private void ejecutarMenuEmpleado(Empleado empleado) {
        Boolean continuar = true;
        while (continuar) {
            try {
                System.out.println("\nBienvenido al menú para empleados\n");
                System.out.println("1. Alquilar vehiculo");
                System.out.println("2. Agregar licencia de otros conductores");
                System.out.println("3. Entrega de vehiculo");
                System.out.println("4. Listo para alquiler");
                System.out.println("5. Salir");
                
                int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
            // opcion 1 que sirve para alquilar un vehiculo
                if (opcion_seleccionada == 1) {
            // se pide una placa, el cliente que alguila, una fecha y sede de devolucion, y es que fecha se alquilo 
                    String placa = input("Ingrese la placa del vehiculo");
                    String username = input("Ingrese el usuario del cliente a alquilar");
                    String fechaDevolucion = input("Ingrese la fecha de devolucion del vehiculo");
                    String sedeDevolucion = input("Ingrese la sede de devolucion del vehiculo");
                    String fechaDeAlquiler = input("Ingrese la fecha de alquiler del vehiculo");
                    empleado.alquilarVehiculo(catalogo, placa, username, fechaDevolucion, sedeDevolucion, fechaDeAlquiler);
                    System.out.println("Vehiculo alquilado exitosamente!!!");
                } 
            // opcion 2 que sirve para agregar la licencia de otro conductor 
                else if (opcion_seleccionada == 2) {
            // se pide el usuario del cliente y se busca si existe
                    String username = input("Por favor ingrese el usuario del cliente");
                    boolean esta = false;
                    for (Usuario usuario : hashUsuarios.values()) {
                        if (usuario.getUsername().equals(username) && usuario.getNivelDeAcceso() == 0) {
                            esta = true;
                        }
                    }
            // si existe se pide un numero de licencia, el pais de expedicion y la fecha de vencimiento
                    if (esta) {
                        Integer numeroDeLicencia = Integer.parseInt(input("Por favor ingrese el número de la licencia del conductor extra"));
                        String paisDeExpedicion = input("Por favor ingrese el país de expedición de la licencia del conductor extra");
                        String fechaDeVencimientoLicencia = input("Por favor ingrese la fecha de vencimiento de la licencia del conductor extra");
                        BufferedImage imagenLicencia = null;
                        try {
                            String workingDir = System.getProperty("user.dir");
                            String filePath = workingDir + File.separator + "data" + File.separator;
                            File file = new File(filePath+"licencia.jpg");
                            imagenLicencia = javax.imageio.ImageIO.read(file);
                        } catch (IOException e) {
                            System.out.println("Error al intentar leer la imagen de licencia, asegurarse que esta en la carpeta data nombrada como licencia con extensión .png!!!");
                        }
                        empleado.otrosConductoresAgregarLicencia(hashUsuarios, username, numeroDeLicencia, paisDeExpedicion, fechaDeVencimientoLicencia, imagenLicencia);
                        System.out.println("Licencia agregada exitosamente!!!");
                    } else {
                        System.out.println("El usuario ingresado no existe o no es un cliente!!!");
                    }
                } 
                // opcion 3 que sirve para administrar la entrega de un vehiculo
                else if (opcion_seleccionada == 3) {
                // se pide el usuaio que alquilo el vehiculo y se verifica que exista y que su nivel de acceso sea de 0 que significa que es un cliente
                    String username = input("Ingrese el usuario del cliente que lo alquiló: ");
                    String placaEnAlquiler = "";
                    boolean esta = false;
                // bucle que sirve para la verificacion
                    for (Usuario usuario : hashUsuarios.values()) {
                        if (usuario.getUsername().equals(username) && usuario.getNivelDeAcceso() == 0) {
                            esta = true;
                            Integer id = ((Cliente) usuario).getIdReserva();
                            for (Map.Entry<String, Reserva> reserva : hashReservas.entrySet()) {
                                if (reserva.getValue().getIdReserva() == id) {
                                    placaEnAlquiler = reserva.getValue().getPlaca();
                                }
                            }
                        }
                    }
                // si existe se pregunta si el vehiculo necesita mantenimiento
                    if (esta && !placaEnAlquiler.equals("")) {
                        boolean mantenimiento = Boolean.parseBoolean(input("El vehiculo necesita mantenimiento? (Necesita mecanico o lavado) (true/false): "));
                // si necesita mantenimiento se ingrsa un fecha estimada de regreso del vehiculo y la descripcion del vehiculo
                        if (mantenimiento) {
                            String fechaRegreso = input("Ingrese la fecha estimada de regreso del vehiculo: ");
                            String descriMantenimiento = input("Ingrese la descripcion del mantenimiento: ");
                            empleado.recibirVehiculoConMantenimiento(catalogo, placaEnAlquiler, username, fechaRegreso, descriMantenimiento, hashUsuarios);
                        } 
                // si no necesita mantenimiento se pone de enseguida en alquiler
                        else {
                            empleado.recibirVehiculoSinMantenimiento(catalogo, placaEnAlquiler, username, hashUsuarios);
                        }
                        System.out.println("El vehículo " + placaEnAlquiler + " alquilado por " + username + " recibido exitosamente!!!");
                    } else {
                        System.out.println("El usuario ingresado no existe o no es un cliente o no tiene un vehículo alquilado!!!");
                    }
                } 
                // opcion 4 que sirve para poner un vehiculo en disponibilidad de alquiler
                else if (opcion_seleccionada == 4) {
                //  se pide la placa y la fecha actual
                    String placa = input("Ingrese la placa del vehiculo: ");
                    String fechaDispo = input("Ingrese la fecha actual: ");
                // verifica que la placa exista y que el vehiculo este en mantenimiento
                    Boolean esta = false;
                    for (Map.Entry<String, Categoria> categoria : catalogo.getHashCategorias().entrySet()) {
                        if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                            Vehiculo vehiculo = categoria.getValue().getHashVehiculos().get(placa);
                            if (!vehiculo.getEnAlquiler() && !vehiculo.getEnReserva() && !vehiculo.getDetallesSede().getDisponibilidadParaAlquilar()) {
                                esta = true;
                            }
                        }
                    }
                // si esta disponible para alquilar cambiamos le estado para que este listo
                    if (esta) {
                        empleado.vehiculoListoParaAlquiler(catalogo, placa, fechaDispo);
                        System.out.println("Vehiculo listo para alquilar!!!");
                    } 
                // mensaje de error por si el vehiculo no existe o no esta en mantenimineto
                    else {
                        System.out.println("El vehículo no existe o no esta en mantenimiento!!!");
                    }
                } 
                // opcion 5 que sirve para salir de la aplicacion 
                else if (opcion_seleccionada == 5) {
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
                System.out.println("1. Reservar un vehiculo");
                System.out.println("2. Editar reserva");
                System.out.println("3. Obtener resumen de reserva actual"); 
                System.out.println("4. Salir");
                // TODO no olvidar usar el meteodo de categoria getVehiculosDisponibles para saber cuantos vehiculos disponibles hay para una sede determinada
                int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
                if (opcion_seleccionada == 1) {
                    if (!cliente.getTieneReserva()) {
                        String nombreCategoria = input("Ingrese el nombre de la categoria del vehículo que quiere");
                        while (!catalogo.getHashCategorias().containsKey(nombreCategoria)) {
                            System.out.println("La categoria ingresada no existe, por favor ingrese una categoria valida");
                            System.out.println("Las categoria validas son:");
                            for (String key : catalogo.getHashCategorias().keySet()) {
                                System.out.println("- " + key);
                            }
                            nombreCategoria = input("Ingrese el nombre de la categoria del vehículo que quiere");
                        }
                        String sedeRecoger = input("Ingrese el nombre de la sede donde quiere recoger el vehículo");
                        while (!hashSedes.containsKey(sedeRecoger)) {
                            System.out.println("La sede ingresada no existe, por favor ingrese una sede valida");
                            System.out.println("Las sedes validas son:");
                            for (String key : hashSedes.keySet()) {
                                System.out.println("- " + key);
                            }
                            sedeRecoger = input("Ingrese el nombre de la sede donde quiere recoger el vehículo");
                        }
                        String fechaRecoger = input("Ingrese la fecha en la cual quiere recoger el vehículo (En formato MM/DD/AAAA)");
                        String horaRecoger = input("Ingrese la hora en la cual quiere recoger el vehículo (En formato HH:MM)");
                        String sedeEntregar = input("Ingrese el nombre de la sede donde quiere entregar el vehículo");
                        while (!hashSedes.containsKey(sedeEntregar)) {
                            System.out.println("La sede ingresada no existe, por favor ingrese una sede valida");
                            System.out.println("Las sedes validas son:");
                            for (String key : hashSedes.keySet()) {
                                System.out.println("- " + key);
                            }
                            sedeEntregar = input("Ingrese el nombre de la sede donde quiere entregar el vehículo");
                        }
                        String fechaEntregar = input("Ingrese la fecha en la cual quiere entregar el vehículo \nRecuerde que esta fecha no puede superar un año de alquiler!!!\n Fecha (En formato MM/DD/AAAA)");
                        String horaEntregar = input("Ingrese la hora en la cual quiere entregar el vehículo (En formato HH:MM)");
                        int otrosCunductores = Integer.parseInt(input("Ingrese la cantidad de conductores extra que quiere"));
                        // lista los seguros disponibles actuales y su descripcion y su precio extra diario
                        ArrayList<String> listaSegurosDisponibles = new ArrayList<String>();
                        System.out.println("Los seguros disponibles son:");
                        for (Map.Entry<String, Seguro> seguro : catalogo.getHashSeguros().entrySet()) {
                            listaSegurosDisponibles.add(seguro.getValue().getNombreSeguro());
                            System.out.println("- " + seguro.getValue().getNombreSeguro() + " - " + seguro.getValue().getDescripcionSeguro() + " - " + String.valueOf(seguro.getValue().getTarifaExtra()));
                        }
                        ArrayList<String> listaSeguros = new ArrayList<String>();
                        Boolean listaSegurosValida = false;
                        while (listaSegurosValida) {
                            String seguros = input("Ingrese los nombre de los seguros que quiere, separados por coma");
                            String[] segurosArray = seguros.split(",");
                            for (String seguro : segurosArray) {
                                listaSeguros.add(seguro);
                            }
                            boolean todosSegurosValidos = true;
                            for (String seguro : listaSeguros) {
                                if (!listaSegurosDisponibles.contains(seguro)) {
                                    todosSegurosValidos = false;
                                }
                            }
                            if (todosSegurosValidos) {
                                listaSegurosValida = true;
                            } else {
                                System.out.println("Uno o mas seguros ingresados no son validos, por favor ingrese seguros validos");
                                System.out.println("Los seguros disponibles son:");
                                for (Map.Entry<String, Seguro> seguro : catalogo.getHashSeguros().entrySet()) {
                                    listaSegurosDisponibles.add(seguro.getValue().getNombreSeguro());
                                    System.out.println("- " + seguro.getValue().getNombreSeguro() + " - " + seguro.getValue().getDescripcionSeguro() + " - " + String.valueOf(seguro.getValue().getTarifaExtra()));
                                }
                            }
                        }  
                        cliente.reservarVehiculo(hashReservas, catalogo, nombreCategoria, sedeRecoger, fechaRecoger, horaRecoger, sedeEntregar, fechaEntregar, horaEntregar, otrosCunductores, listaSeguros);
                        System.out.println("\n Detalles de la reserva a continuación:");
                        System.out.println(cliente.getResumenReservaActual(hashReservas, catalogo));
                    } else if (cliente.getTieneTarjetaBloqueada()) {
                        System.out.println("Su tarjeta esta bloqueada, no puede hacer reservas!!!");
                    } else {
                        System.out.println("Ya tiene una reserva activa!!!");
                    }
                } else if (opcion_seleccionada == 2) {
                    if (cliente.getTieneReserva()) {
                        System.out.println("A continuacion tiene los detalles de su reserva actual");
                        System.out.println(cliente.getResumenReservaActual(hashReservas, catalogo));
                        String sedeEntregar = input("Ingrese el nombre de la sede donde quiere entregar el vehículo");
                        while (!hashSedes.containsKey(sedeEntregar)) {
                            System.out.println("La sede ingresada no existe, por favor ingrese una sede valida");
                            System.out.println("Las sedes validas son:");
                            for (String key : hashSedes.keySet()) {
                                System.out.println("- " + key);
                            }
                            sedeEntregar = input("Ingrese el nombre de la sede donde quiere entregar el vehículo");
                        }
                        String fechaEntregar = input("Ingrese la fecha en la cual quiere entregar el vehículo \nRecuerde que esta fecha no puede superar un año de alquiler!!!\n Fecha (En formato MM/DD/AAAA)");
                        String horaEntregar = input("Ingrese la hora en la cual quiere entregar el vehículo (En formato HH:MM)");
                        int otrosCunductores = Integer.parseInt(input("Ingrese la cantidad de conductores extra que quiere"));
                        cliente.alterarReserva(hashReservas, cliente.getIdReserva(), sedeEntregar, fechaEntregar, horaEntregar, otrosCunductores, catalogo);
                        System.out.println("Reserva editada exitosamente!!!");
                        System.out.println("\n Detalles de la reserva a continuación:");
                        System.out.println(cliente.getResumenReservaActual(hashReservas, catalogo));
                    } else {
                        System.out.println("No tiene una reserva activa!!!");
                    }
                } else if (opcion_seleccionada == 3) {
                    if (cliente.getTieneReserva()) {
                        System.out.println("\nA continuacion tiene los detalles de su reserva actual: ");
                        System.out.println(cliente.getResumenReservaActual(hashReservas, catalogo));
                    } else {
                        System.out.println("No tiene una reserva activa!!!");
                    }
                } else if (opcion_seleccionada == 4) {
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

