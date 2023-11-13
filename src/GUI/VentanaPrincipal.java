package GUI;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Inventario.Catalogo;
import Inventario.Sede;
import Reservas.Reserva;
import SistemaLogin.Administrador;
import SistemaLogin.AdministradorLocal;
import SistemaLogin.Cliente;
import SistemaLogin.DatosClienteLicencia;
import SistemaLogin.DatosClienteTarjeta;
import SistemaLogin.Usuario;

public class VentanaPrincipal extends JFrame{

    private Usuario usuarioActual;

    public Catalogo catalogo;
    public HashMap<String, Usuario> hashUsuarios;
    public HashMap<String, Sede> hashSedes;
    public HashMap<String, Reserva> hashReservas;

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
                // Es primera vez que se crea abre este sistema
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
                hashUsuarios.put(admin.getUsername(), admin);
                System.out.println("Su usuario y contraseña de administrador son: ADMIN/ADMIN");

                // Carga una informacion de prueba, se puede quitar de comentario para probar de una forma mas rápida.
                cargarInformacionEjemplo(admin);
                System.out.println("Se ha cargado la información de prueba!!!");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la información!!!");
            e.printStackTrace();
        }
        System.out.println("Se ha cargado la información!!!");
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
        System.out.println("Se ha guardado la información!!!");
    }

    /** 
     * metodo privado para cargar informacion de ejemplo
     */
    private void cargarInformacionEjemplo(Administrador admin) {

        // Operaciones Admin

        admin.crearSede(hashSedes, "SEDE1", "N.A.", "N.A.");
        admin.crearSede(hashSedes, "SEDE2", "N.A.", "N.A.");
        admin.crearAdministradorLocal(hashUsuarios, "ADMIN1", "ADMIN1", "SEDE1", "ADMIN1", "ADMIN1", "1234567890", "ADMIN1@GOOGLE.COM");
        admin.crearAdministradorLocal(hashUsuarios, "ADMIN2", "ADMIN2", "SEDE2", "ADMIN2", "ADMIN2", "1234567890", "ADMIN2@GOOGLE.COM");
        admin.crearCategoria(catalogo, "CATEGORIA1", 1);
        admin.crearCategoria(catalogo, "CATEGORIA2", 2);
        admin.crearCategoria(catalogo, "CATEGORIA3", 3);
        admin.crearTarifaPorTemporada(catalogo, "CATEGORIA1", 100, 20);
        admin.crearTarifaPorTemporada(catalogo, "CATEGORIA2", 200, 40);
        admin.crearTarifaPorTemporada(catalogo, "CATEGORIA3", 300, 60);
        admin.crearSeguro(catalogo, 50, "SEGURO1", "SEGURO1");
        admin.crearSeguro(catalogo, 100, "SEGURO2", "SEGURO2");
        admin.crearSeguro(catalogo, 150, "SEGURO3", "SEGURO3");
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

        BufferedImage imagenLicencia = null;
        try {
            String workingDir = System.getProperty("user.dir");
            String filePath = workingDir + File.separator + "data" + File.separator;
            File file = new File(filePath+"alicencia.png");
            imagenLicencia = javax.imageio.ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Error al intentar leer la imagen de licencia, asegurarse que esta en la carpeta data nombrada como licencia con extensión .png!!!");
        }
        DatosClienteLicencia licencia = new DatosClienteLicencia(0, "N.A.", "N.A.", imagenLicencia);
        DatosClienteTarjeta tarjeta = new DatosClienteTarjeta(0,"N.A.","N.A.","N.A.",000);
        Cliente cliente1 = new Cliente("CLIENTE1","CLIENTE1","CLIENTE1","CLIENTE1","1234567890","CLIENTE1@GOOGLE.COM",licencia,tarjeta);
        hashUsuarios.put(cliente1.getUsername(), cliente1);
        Cliente cliente2 = new Cliente("CLIENTE2","CLIENTE2","CLIENTE2","CLIENTE2","1234567890","CLIENTE1@GOOGLE.COM",licencia,tarjeta);
        hashUsuarios.put(cliente2.getUsername(), cliente2);
    }

    private static PanelNorte panelNorte;
    private static PanelCentral panelCentral;

    private static int pagina = 0;
    
    public VentanaPrincipal() {
        setTitle("Rentas X"); 
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        cargarInformacion();
        cambiarPagina(pagina);
        setVisible(true);
    }

    public void cambiarPagina(int paginaNueva) {
        guardarInformacion();
        getContentPane().removeAll();
        pagina = paginaNueva;
        panelNorte = new PanelNorte(this, paginaNueva);
        add(panelNorte, BorderLayout.NORTH);
        panelCentral = new PanelCentral(this, paginaNueva);
        add(panelCentral, BorderLayout.CENTER);
        System.out.println("Se ha cambiado a la pagina: " + paginaNueva);
        revalidate();
        repaint();
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public static void main(String[] args) {
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
    }

}
