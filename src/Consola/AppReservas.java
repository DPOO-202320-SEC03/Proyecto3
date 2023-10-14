package Consola;
import java.io.*;
import java.util.*;

import SistemaLogin.Administrador;
import SistemaLogin.Cliente;
import SistemaLogin.DatosClienteLicencia;
import SistemaLogin.DatosClienteTarjeta;
import SistemaLogin.Usuario;
import Inventario.Catalogo;
import Inventario.Sede;
import Reservas.Reserva;
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
    
    private void iniciarSesion() {
        String username = input("Por favor ingrese su usuario");
        String password = input("Por favor ingrese su contraseña");
        int nivelDeAcceso;
        try {
            Usuario usuario = this.hashUsuarios.get(username);
            if (usuario.getPassword().equals(password)) {
                System.out.println("\nBienvenido " + usuario.getNombre() + "\n");
                nivelDeAcceso = usuario.getNivelDeAcceso();
                iniciarAppUsuario(nivelDeAcceso, usuario);
            } else {
                System.out.println("Contraseña incorrecta!!!");
            }
        } catch (Exception e) {
            System.out.println("Usuario no encontrado!!!");
        }
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

    private void iniciarAppUsuario(int nivelDeAcceso, Usuario usuario) {
        // TODO implement here
        System.out.println("TEST");
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

