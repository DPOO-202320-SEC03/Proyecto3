package Consola;
import java.io.*;
import java.util.*;

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
                System.out.println("Data cargada correctamente.");
            } else {
                this.catalogo = new Catalogo();
                this.hashUsuarios = new HashMap<String, Usuario>();
                this.hashSedes = new HashMap<String, Sede>();
                this.hashReservas = new HashMap<String, Reserva>();
                System.out.println("Data creada correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al tratar de cargar la data.");
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
            System.out.println("Data guardada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al tratar de guardar la data.");
            e.printStackTrace();
        }
    }

    private void iniciarApp() {
        cargarInformacion();
        mostrarMenuUsuario();
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
            this.hashUsuarios = (HashMap<String, Usuario>) ois.readObject();
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


    private void login(String username, String password) {
        // TODO implement here
    }

    private void cerrarApp() {
        // TODO implement here
    }


    }

