package Consola;
import java.io.*;
import java.util.*;

import SistemaLogin.Administrador;
import SistemaLogin.Persona;
import SistemaLogin.Usuario;
import Inventario.Catalogo;
import Inventario.Sede;
import Reservas.Reserva;

public class AppReservas {

    private Catalogo catalogo;
    private HashMap<String, Usuario> hashUsuarios;
    private HashMap<String, Sede> hashSedes;
    private HashMap<String, Reserva> hashReservas;

    public static void main(String[] args) {
        AppReservas app = new AppReservas();
        app.iniciarApp();
    }

    public void mostrarMenuUsuario() {
        System.out.println("Bienvenido a la aplicación de reservas de vehículos");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
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
                iniciarCatalogo(archivoCatalogo);
                iniciarUsuarios(archivoUsuarios);
                iniciarSedes(archivoSedes);
                iniciarReservas(archivoReservas);
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
            guardarCatalogo(archivoCatalogo);
            guardarUsuarios(archivoUsuarios);
            guardarSedes(archivoReservas);
            guardarReservas(archivoSedes);
        } catch (Exception e) {
            System.out.println("Error al guardar la información!!!");
            e.printStackTrace();
        }
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
                    regustrarse();
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

    private void iniciarCatalogo(File fileCatalogo) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileCatalogo));
            this.catalogo = (Catalogo) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void iniciarUsuarios(File fileUsuarios) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileUsuarios));
            HashMap<String, Usuario> hashUsuarios = (HashMap<String, Usuario>) ois.readObject();
            this.hashUsuarios = hashUsuarios;
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void iniciarSedes(File fileSedes) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileSedes));
            this.hashSedes = (HashMap<String, Sede>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void iniciarReservas(File fileReservas) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileReservas));
            this.hashReservas = (HashMap<String, Reserva>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void guardarCatalogo(File fileCatalogo) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileCatalogo));
            oos.writeObject(this.catalogo);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarUsuarios(File fileUsuarios) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileUsuarios));
            oos.writeObject(this.hashUsuarios);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarSedes(File fileSedes) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSedes));
            oos.writeObject(this.hashSedes);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarReservas(File fileReservas) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileReservas));
            oos.writeObject(this.hashReservas);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crearAdministrador() {
        Administrador admin = new Administrador("Nombre", "Apellido", "1234567890");
        Usuario adminPersona = new Usuario("ADMIN", "ADMIN", admin);
        hashUsuarios.put(adminPersona.getUsername(), adminPersona);
    }

    private void iniciarSesion() {
        String username = input("Por favor ingrese su usuario");
        String password = input("Por favor ingrese su contraseña");
        int nivelDeAcceso;
        try {
            Usuario usuario = this.hashUsuarios.get(username);
            if (usuario.getPassword().equals(password)) {
                Persona persona = usuario.getPersona();
                System.out.println("\nBienvenido " + persona.getInfoUsuario().get(0) + "\n");
                nivelDeAcceso = persona.getNivelDeAcceso();
                iniciarAppUsuario(nivelDeAcceso, persona);
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } catch (Exception e) {
            System.out.println("Usuario no encontrado.");
        }
    }

    private void regustrarse() {
        // TODO implement here
    }

    private void iniciarAppUsuario(int nivelDeAcceso, Persona persona) {
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

