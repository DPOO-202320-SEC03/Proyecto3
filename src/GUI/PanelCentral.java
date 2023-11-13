package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Inventario.Categoria;
import Inventario.Seguro;
import Inventario.Vehiculo;
import Reservas.Reserva;
import Reservas.ReservaNormal;
import SistemaLogin.Administrador;
import SistemaLogin.AdministradorLocal;
import SistemaLogin.Cliente;
import SistemaLogin.DatosClienteLicencia;
import SistemaLogin.DatosClienteTarjeta;
import SistemaLogin.Empleado;
import SistemaLogin.Usuario;

public class PanelCentral extends JPanel {
    private VentanaPrincipal vp;
    public PanelCentral(VentanaPrincipal ventanaPrincipal, int pagina) {
        this.vp = ventanaPrincipal;
        if (pagina == 0) {
            setLayout(new GridLayout(1,2));
            JButton btnSesion = new JButton("Iniciar sesion");
            btnSesion.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnSesion.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(1);
            });
            add(btnSesion);
            JButton btnRegistro = new JButton("Registrarse");
            btnRegistro.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnRegistro.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(2);
            });
            add(btnRegistro);
        } else if (pagina == 1){
            setLayout(new GridLayout(3,1));
            JLabel lbUsuario = new JLabel();
            lbUsuario.setLayout(new GridLayout(1,2));
            JLabel usu = new JLabel("Usuario: ");
            usu.setFont(new Font("Dialog", Font.PLAIN, 24));
            usu.setHorizontalAlignment(JLabel.CENTER);
            lbUsuario.add(usu);
            JTextField tfUsuario = new JTextField(8);
            tfUsuario.setFont(new Font("Dialog", Font.PLAIN, 24));
            lbUsuario.add(tfUsuario);
            add(lbUsuario);

            JLabel lbContrasena = new JLabel();
            lbContrasena.setLayout(new GridLayout(1,2));
            JLabel contra = new JLabel("Contraseña: ");
            contra.setFont(new Font("Dialog", Font.PLAIN, 24));
            contra.setHorizontalAlignment(JLabel.CENTER);
            lbContrasena.add(contra);
            JTextField tfContrasena = new JTextField(8);
            tfContrasena.setFont(new Font("Dialog", Font.PLAIN, 24));
            lbContrasena.add(tfContrasena);
            add(lbContrasena);

            JButton btnIniciarSesion = new JButton("Iniciar sesion");
            btnIniciarSesion.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnIniciarSesion.addActionListener(e -> {
                String username = tfUsuario.getText();
                String password = tfContrasena.getText();
                int nivelDeAcceso;
                try {
                    if (ventanaPrincipal.hashUsuarios.containsKey(username)) {
                        Usuario usuario = ventanaPrincipal.hashUsuarios.get(username);
                        if (usuario.getPassword().equals(password)) {
                            System.out.println("\nBienvenido " + usuario.getNombre() + "\n");
                            nivelDeAcceso = usuario.getNivelDeAcceso();
                            if (nivelDeAcceso == 3) {
                                Administrador admin = (Administrador) usuario;
                                ventanaPrincipal.setUsuarioActual(admin);
                                ventanaPrincipal.cambiarPagina(11);
                            } else if (nivelDeAcceso == 2) {
                                AdministradorLocal adminLocal = (AdministradorLocal) usuario;
                                ventanaPrincipal.setUsuarioActual(adminLocal);
                                ventanaPrincipal.cambiarPagina(12);
                            } else if (nivelDeAcceso == 1) {
                                Empleado empleado = (Empleado) usuario;
                                ventanaPrincipal.setUsuarioActual(empleado);
                                ventanaPrincipal.cambiarPagina(13);
                            } else if (nivelDeAcceso == 0) {
                                Cliente cliente = (Cliente) usuario;
                                ventanaPrincipal.setUsuarioActual(cliente);
                                ventanaPrincipal.cambiarPagina(14);
                            } else {
                                System.out.println("Nivel de acceso no válido!!!");
                            }
                        } else {
                            tfUsuario.setText("");
                            tfContrasena.setText("");
                            JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Clave incorrecta");
                            dialogError.setSize(300,30);
                            dialogError.setLocationRelativeTo(getTopLevelAncestor());
                            dialogError.setVisible(true);
                            System.out.println("Contraseña incorrecta!!!");
                        } 
                    } else {
                        tfUsuario.setText("");
                        tfContrasena.setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Usuario no encontrado");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Usuario no encontrado!!!");
                    }
                } catch (Exception ex) {
                    System.out.println("Error al intentar LOGIN!!!");
                }
            });
            add(btnIniciarSesion);
        } else if (pagina == 2) {
            setLayout(new GridLayout(15,1));

            JLabel lbNombre = generadorLabelInput("Nombre: ");
            add(lbNombre);

            JLabel lbApellido = generadorLabelInput("Apellido: ");
            add(lbApellido);

            JLabel lbCelular = generadorLabelInput("Celular: ");
            add(lbCelular);

            JLabel lbCorreo = generadorLabelInput("Correo: ");
            add(lbCorreo);
            
            JLabel lbNumeroLicencia = generadorLabelInput("Numero de licencia: ");
            add(lbNumeroLicencia);

            JLabel lbPaisExpedicion = generadorLabelInput("Pais de expedicion: ");
            add(lbPaisExpedicion);

            JLabel lbFechaDeVencimiento = generadorLabelInput("Fecha de vencimiento: ");
            add(lbFechaDeVencimiento);

            JLabel lbNumeroDeTarjeta = generadorLabelInput("Numero de tarjeta: ");
            add(lbNumeroDeTarjeta);

            JLabel lbFechaDeVencimientoTarjeta = generadorLabelInput("Fecha de vencimiento: ");
            add(lbFechaDeVencimientoTarjeta);

            JLabel lbTitularTarjeta = generadorLabelInput("Titular: ");
            add(lbTitularTarjeta);

            JLabel lbMarcaInternacional = generadorLabelInput("Marca internacional: ");
            add(lbMarcaInternacional);

            JLabel lbCodigoSeguridad = generadorLabelInput("Codigo de seguridad: ");
            add(lbCodigoSeguridad);

            JLabel lbUsuario = generadorLabelInput("Usuario: ");
            add(lbUsuario);

            JLabel lbContrasena = generadorLabelInput("Contraseña: ");
            add(lbContrasena);

            JButton btnRegistrarse = new JButton("Registrarse");
            btnRegistrarse.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnRegistrarse.addActionListener(e -> {
                String nombre = ((JTextField) lbNombre.getComponent(1)).getText();
                String apellido = ((JTextField) lbApellido.getComponent(1)).getText();
                String celular = ((JTextField) lbCelular.getComponent(1)).getText();
                String correo = ((JTextField) lbCorreo.getComponent(1)).getText();
                String numeroLicenciaS = ((JTextField) lbNumeroLicencia.getComponent(1)).getText();
                Integer numeroLicencia = Integer.parseInt(numeroLicenciaS);
                String paisExpedicion = ((JTextField) lbPaisExpedicion.getComponent(1)).getText();
                String fechaDeVencimiento = ((JTextField) lbFechaDeVencimiento.getComponent(1)).getText();
                String numeroDeTarjetaS = ((JTextField) lbNumeroDeTarjeta.getComponent(1)).getText();
                Integer numeroDeTarjeta = Integer.parseInt(numeroDeTarjetaS);
                String fechaDeVencimientoTarjeta = ((JTextField) lbFechaDeVencimientoTarjeta.getComponent(1)).getText();
                String titularTarjeta = ((JTextField) lbTitularTarjeta.getComponent(1)).getText();
                String marcaInternacional = ((JTextField) lbMarcaInternacional.getComponent(1)).getText();
                String codigoSeguridadS = ((JTextField) lbCodigoSeguridad.getComponent(1)).getText();
                Integer codigoSeguridad = Integer.parseInt(codigoSeguridadS);
                String username = ((JTextField) lbUsuario.getComponent(1)).getText();
                String password = ((JTextField) lbContrasena.getComponent(1)).getText();
                BufferedImage imagenLicencia = null;
                try {
                    String workingDir = System.getProperty("user.dir");
                    String filePath = workingDir + File.separator + "data" + File.separator;
                    File file = new File(filePath+"alicencia.png");
                    imagenLicencia = javax.imageio.ImageIO.read(file);

                    DatosClienteLicencia licencia = new DatosClienteLicencia(numeroLicencia, paisExpedicion, fechaDeVencimiento, imagenLicencia);
                    DatosClienteTarjeta tarjeta = new DatosClienteTarjeta(numeroDeTarjeta,fechaDeVencimientoTarjeta,titularTarjeta,marcaInternacional,codigoSeguridad);

                    if (!(vp.hashUsuarios.containsKey(username)) && password.length() > 3) {
                        Cliente cliente = new Cliente(username,password,nombre,apellido,celular,correo,licencia,tarjeta);
                        vp.hashUsuarios.put(cliente.getUsername(), cliente);
                        ventanaPrincipal.cambiarPagina(0);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Registrado con exito");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Registrado con exito!!!");
                    } else {
                        ((JTextField) lbNombre.getComponent(1)).setText("");
                        ((JTextField) lbApellido.getComponent(1)).setText("");
                        ((JTextField) lbCelular.getComponent(1)).setText("");
                        ((JTextField) lbCorreo.getComponent(1)).setText("");
                        ((JTextField) lbNumeroLicencia.getComponent(1)).setText("");
                        ((JTextField) lbPaisExpedicion.getComponent(1)).setText("");
                        ((JTextField) lbFechaDeVencimiento.getComponent(1)).setText("");
                        ((JTextField) lbNumeroDeTarjeta.getComponent(1)).setText("");
                        ((JTextField) lbFechaDeVencimientoTarjeta.getComponent(1)).setText("");
                        ((JTextField) lbTitularTarjeta.getComponent(1)).setText("");
                        ((JTextField) lbMarcaInternacional.getComponent(1)).setText("");
                        ((JTextField) lbCodigoSeguridad.getComponent(1)).setText("");
                        ((JTextField) lbUsuario.getComponent(1)).setText("");
                        ((JTextField) lbContrasena.getComponent(1)).setText("");

                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al intentar registrarse");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Error al intentar REGISTRARSE!!!");
                    }
                } catch (Exception ex) {
                    ((JTextField) lbNombre.getComponent(1)).setText("");
                    ((JTextField) lbApellido.getComponent(1)).setText("");
                    ((JTextField) lbCelular.getComponent(1)).setText("");
                    ((JTextField) lbCorreo.getComponent(1)).setText("");
                    ((JTextField) lbNumeroLicencia.getComponent(1)).setText("");
                    ((JTextField) lbPaisExpedicion.getComponent(1)).setText("");
                    ((JTextField) lbFechaDeVencimiento.getComponent(1)).setText("");
                    ((JTextField) lbNumeroDeTarjeta.getComponent(1)).setText("");
                    ((JTextField) lbFechaDeVencimientoTarjeta.getComponent(1)).setText("");
                    ((JTextField) lbTitularTarjeta.getComponent(1)).setText("");
                    ((JTextField) lbMarcaInternacional.getComponent(1)).setText("");
                    ((JTextField) lbCodigoSeguridad.getComponent(1)).setText("");
                    ((JTextField) lbUsuario.getComponent(1)).setText("");
                    ((JTextField) lbContrasena.getComponent(1)).setText("");

                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al registrarse");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al REGISTRARSE!!!");
                }
            });
            add(btnRegistrarse);
        } else if (pagina == 11) {
            setLayout(new GridLayout(7,2));

            JButton btnCrearSede = new JButton("Crear sede");
            btnCrearSede.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnCrearSede.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(111);
            });
            add(btnCrearSede);

            JButton btnCrearCategoria = new JButton("Crear categoria");
            btnCrearCategoria.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnCrearCategoria.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(112);
            });
            add(btnCrearCategoria);

            JButton btnEditarCategoria = new JButton("Editar tarifas por temporada");
            btnEditarCategoria.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnEditarCategoria.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(113);
            });
            add(btnEditarCategoria);

            JButton btnCrearSeguro = new JButton("Crear seguro");
            btnCrearSeguro.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnCrearSeguro.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(114);
            });
            add(btnCrearSeguro);

            JButton btnCrearTarifasGlobales = new JButton("Crear tarifas globales");
            btnCrearTarifasGlobales.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnCrearTarifasGlobales.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(115);
            });
            add(btnCrearTarifasGlobales);

            JButton btnEditarTarifasGlobales = new JButton("Editar tarifas globales");
            btnEditarTarifasGlobales.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnEditarTarifasGlobales.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(116);
            });
            add(btnEditarTarifasGlobales);

            JButton btnCrearVehiculo = new JButton("Crear vehiculo");
            btnCrearVehiculo.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnCrearVehiculo.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(117);
            });
            add(btnCrearVehiculo);

            JButton btnEliminarVehiculo = new JButton("Eliminar vehiculo");
            btnEliminarVehiculo.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnEliminarVehiculo.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(118);
            });
            add(btnEliminarVehiculo);

            JButton btnEstadoVehiculo = new JButton("Estado vehiculo");
            btnEstadoVehiculo.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnEstadoVehiculo.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(119);
            });
            add(btnEstadoVehiculo);

            JButton btnTrasladarVehiculo = new JButton("Trasladar vehiculo");
            btnTrasladarVehiculo.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnTrasladarVehiculo.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(1110);
            });
            add(btnTrasladarVehiculo);

            JButton btnConsultarReservaPorID = new JButton("Consultar reserva por ID");
            btnConsultarReservaPorID.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnConsultarReservaPorID.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(1111);
            });
            add(btnConsultarReservaPorID);

            JButton btnCrearAdministradorLocal = new JButton("Crear administrador local");
            btnCrearAdministradorLocal.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnCrearAdministradorLocal.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(1112);
            });
            add(btnCrearAdministradorLocal);

            JButton btnEliminarUsuario = new JButton("Eliminar usuario");
            btnEliminarUsuario.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnEliminarUsuario.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(1113);
            });
            add(btnEliminarUsuario);

            JButton btnGraficaDeReservas = new JButton("Grafica de reservas");
            btnGraficaDeReservas.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnGraficaDeReservas.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(1114);
            });
            add(btnGraficaDeReservas);

        } else if (pagina == 111) {
            setLayout(new GridLayout(4,1));
            JLabel lbNombreSede = generadorLabelInput("Nombre de la sede: ");
            add(lbNombreSede);

            JLabel lbUbicacionSede = generadorLabelInput("Ubicacion: ");
            add(lbUbicacionSede);

            JLabel lbHorariosDeAtencion = generadorLabelInput("Horarios de atencion: ");
            add(lbHorariosDeAtencion);

            JButton btnCrearSede = new JButton("Crear sede");
            btnCrearSede.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnCrearSede.addActionListener(e -> {
                String nombreSede = ((JTextField) lbNombreSede.getComponent(1)).getText();
                String ubicacionSede = ((JTextField) lbUbicacionSede.getComponent(1)).getText();
                String horariosDeAtencion = ((JTextField) lbHorariosDeAtencion.getComponent(1)).getText();
                if (nombreSede.length() > 3 && ubicacionSede.length() > 3 && horariosDeAtencion.length() > 3) {
                    if (!(vp.hashSedes.containsKey(nombreSede))) {
                        ((Administrador) vp.usu).crearSede(vp.hashSedes, nombreSede, ubicacionSede, horariosDeAtencion);
                        ventanaPrincipal.cambiarPagina(11);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Sede creada con exito");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Sede creada con exito!!!");
                    } else {
                        ((JTextField) lbNombreSede.getComponent(1)).setText("");
                        ((JTextField) lbUbicacionSede.getComponent(1)).setText("");
                        ((JTextField) lbHorariosDeAtencion.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Sede ya existente");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Sede ya existente!!!");
                    }
                } else {
                    ((JTextField) lbNombreSede.getComponent(1)).setText("");
                    ((JTextField) lbUbicacionSede.getComponent(1)).setText("");
                    ((JTextField) lbHorariosDeAtencion.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al crear sede");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al crear sede!!!");
                }
            });
            add(btnCrearSede);
        } else if (pagina == 112) {
            setLayout(new GridLayout(3,1));
            JLabel lbNombreCategoria = generadorLabelInput("Nombre de la categoria: ");
            add(lbNombreCategoria);

            JLabel lbRangoCategoria = generadorLabelInput("Rango de la categoria (1-10): ");
            add(lbRangoCategoria);

            JButton btnCrearCategoria = new JButton("Crear categoria");
            btnCrearCategoria.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnCrearCategoria.addActionListener(e -> {
                String nombreCategoria = ((JTextField) lbNombreCategoria.getComponent(1)).getText();
                String rangoCategoriaS = ((JTextField) lbRangoCategoria.getComponent(1)).getText();
                Integer rangoCategoria = Integer.parseInt(rangoCategoriaS);
                if (nombreCategoria.length() > 3 && rangoCategoria > 0 && rangoCategoria < 11) {
                    if (!(vp.catalogo.getHashCategorias().containsKey(nombreCategoria))) {
                        ((Administrador) vp.usu).crearCategoria(vp.catalogo, nombreCategoria, rangoCategoria);
                        ventanaPrincipal.cambiarPagina(11);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Categoria creada con exito");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Categoria creada con exito!!!");
                    } else {
                        ((JTextField) lbNombreCategoria.getComponent(1)).setText("");
                        ((JTextField) lbRangoCategoria.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Categoria ya existente");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Categoria ya existente!!!");
                    }
                } else {
                    ((JTextField) lbNombreCategoria.getComponent(1)).setText("");
                    ((JTextField) lbRangoCategoria.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al crear categoria");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al crear categoria!!!");
                }
            });
            add(btnCrearCategoria);
            
        } else if (pagina == 113) {
            setLayout(new GridLayout(4,1));

            JLabel lbNombreCategoria = generadorLabelInput("Nombre de la categoria: ");
            add(lbNombreCategoria);

            JLabel lbTarifaTemporadaAlta = generadorLabelInput("Tarifa temporada alta: ");
            add(lbTarifaTemporadaAlta);

            JLabel lbTarifaTemporadaBaja = generadorLabelInput("Tarifa temporada baja: ");
            add(lbTarifaTemporadaBaja);

            JButton btnEditarTarifasPorTemporada = new JButton("Editar tarifas por temporada");
            btnEditarTarifasPorTemporada.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnEditarTarifasPorTemporada.addActionListener(e -> {
                String nombreCategoria = ((JTextField) lbNombreCategoria.getComponent(1)).getText();
                String tarifaTemporadaAltaS = ((JTextField) lbTarifaTemporadaAlta.getComponent(1)).getText();
                Integer tarifaTemporadaAlta = Integer.parseInt(tarifaTemporadaAltaS);
                String tarifaTemporadaBajaS = ((JTextField) lbTarifaTemporadaBaja.getComponent(1)).getText();
                Integer tarifaTemporadaBaja = Integer.parseInt(tarifaTemporadaBajaS);
                if (nombreCategoria.length() > 3 && tarifaTemporadaAlta > 3 && tarifaTemporadaBaja > 3) {
                    if (vp.catalogo.getHashCategorias().containsKey(nombreCategoria)) {
                        ((Administrador) vp.usu).crearTarifaPorTemporada(vp.catalogo, nombreCategoria, tarifaTemporadaAlta, tarifaTemporadaBaja);
                        ventanaPrincipal.cambiarPagina(11);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Tarifas por temporada editadas con exito");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Tarifas por temporada editadas con exito!!!");
                    } else {
                        ((JTextField) lbNombreCategoria.getComponent(1)).setText("");
                        ((JTextField) lbTarifaTemporadaAlta.getComponent(1)).setText("");
                        ((JTextField) lbTarifaTemporadaBaja.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Categoria no existente");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Categoria no existente!!!");
                    }
                } else {
                    ((JTextField) lbNombreCategoria.getComponent(1)).setText("");
                    ((JTextField) lbTarifaTemporadaAlta.getComponent(1)).setText("");
                    ((JTextField) lbTarifaTemporadaBaja.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al editar tarifas por temporada");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al editar tarifas por temporada!!!");
                }
            });
            add(btnEditarTarifasPorTemporada);

        } else if (pagina == 114) {
            setLayout(new GridLayout(4,1));

            JLabel lbNombreSeguro = generadorLabelInput("Nombre del seguro: ");
            add(lbNombreSeguro);

            JLabel lbDescripcionSeguro = generadorLabelInput("Descripcion del seguro: ");
            add(lbDescripcionSeguro);

            JLabel lbTarifaExtraDiaria = generadorLabelInput("Tarifa extra diaria: ");
            add(lbTarifaExtraDiaria);

            JButton btnCrearSeguro = new JButton("Crear seguro");
            btnCrearSeguro.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnCrearSeguro.addActionListener(e -> {
                String nombreSeguro = ((JTextField) lbNombreSeguro.getComponent(1)).getText();
                String descripcionSeguro = ((JTextField) lbDescripcionSeguro.getComponent(1)).getText();
                String tarifaExtraDiariaS = ((JTextField) lbTarifaExtraDiaria.getComponent(1)).getText();
                Integer tarifaExtraDiaria = Integer.parseInt(tarifaExtraDiariaS);
                if (nombreSeguro.length() > 3 && descripcionSeguro.length() > 3 && tarifaExtraDiaria > 3) {
                    if (!(vp.catalogo.getHashSeguros().containsKey(nombreSeguro))) {
                        ((Administrador) vp.usu).crearSeguro(vp.catalogo, tarifaExtraDiaria, nombreSeguro, descripcionSeguro);
                        ventanaPrincipal.cambiarPagina(11);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Seguro creado con exito");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Seguro creado con exito!!!");
                    } else {
                        ((JTextField) lbNombreSeguro.getComponent(1)).setText("");
                        ((JTextField) lbDescripcionSeguro.getComponent(1)).setText("");
                        ((JTextField) lbTarifaExtraDiaria.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Seguro ya existente");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Seguro ya existente!!!");
                    }
                } else {
                    ((JTextField) lbNombreSeguro.getComponent(1)).setText("");
                    ((JTextField) lbDescripcionSeguro.getComponent(1)).setText("");
                    ((JTextField) lbTarifaExtraDiaria.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al crear seguro");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al crear seguro!!!");
                }
            });
            add(btnCrearSeguro);

        } else if (pagina == 115) {
            setLayout(new GridLayout(4,1));

            JLabel lbTarifaConductorExtra = generadorLabelInput("Tarifa conductor extra: ");
            add(lbTarifaConductorExtra);

            JLabel lbTarifaEntregaEnOtraSede = generadorLabelInput("Tarifa entrega en otra sede: ");
            add(lbTarifaEntregaEnOtraSede);

            JLabel lbRangoTemporadaAlta = generadorLabelInput("Rango temporada alta (MM/DD-MM/DD): ");
            add(lbRangoTemporadaAlta);

            JButton btnCrearTarifasGlobales = new JButton("Crear tarifas globales");
            btnCrearTarifasGlobales.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnCrearTarifasGlobales.addActionListener(e -> {
                String tarifaConductorExtraS = ((JTextField) lbTarifaConductorExtra.getComponent(1)).getText();
                Integer tarifaConductorExtra = Integer.parseInt(tarifaConductorExtraS);
                String tarifaEntregaEnOtraSedeS = ((JTextField) lbTarifaEntregaEnOtraSede.getComponent(1)).getText();
                Integer tarifaEntregaEnOtraSede = Integer.parseInt(tarifaEntregaEnOtraSedeS);
                String rangoTemporadaAlta = ((JTextField) lbRangoTemporadaAlta.getComponent(1)).getText();
                if (tarifaConductorExtra > 3 && tarifaEntregaEnOtraSede > 3 && rangoTemporadaAlta.length() > 3) {
                    ((Administrador) vp.usu).crearTarifasGlobales(vp.catalogo, tarifaConductorExtra, tarifaEntregaEnOtraSede, rangoTemporadaAlta);
                    ventanaPrincipal.cambiarPagina(11);
                    JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Tarifas globales creadas con exito");
                    dialogOK.setSize(300,30);
                    dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                    dialogOK.setVisible(true);
                    System.out.println("Tarifas globales creadas con exito!!!");
                } else {
                    ((JTextField) lbTarifaConductorExtra.getComponent(1)).setText("");
                    ((JTextField) lbTarifaEntregaEnOtraSede.getComponent(1)).setText("");
                    ((JTextField) lbRangoTemporadaAlta.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al crear tarifas globales");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al crear tarifas globales!!!");
                }
            });
            add(btnCrearTarifasGlobales);
        } else if (pagina == 116) {
            JDialog dialogTarifasAntiguas = new JDialog((JFrame) getTopLevelAncestor(), "Tarifas antiguas");
            dialogTarifasAntiguas.setSize(1100,700);
            dialogTarifasAntiguas.setLocationRelativeTo(getTopLevelAncestor());
            dialogTarifasAntiguas.setLayout(new GridLayout(3,2));

            dialogTarifasAntiguas.add(generadorLabelTarifasAntiguas("Tarifa conductor extra: "));
            dialogTarifasAntiguas.add(generadorLabelTarifasAntiguas("" + vp.catalogo.getTarifasGlobales().getTarifaExtra()));

            dialogTarifasAntiguas.add(generadorLabelTarifasAntiguas("Tarifa entrega en otra sede: "));
            dialogTarifasAntiguas.add(generadorLabelTarifasAntiguas("" + vp.catalogo.getTarifasGlobales().getTarifaSede()));

            dialogTarifasAntiguas.add(generadorLabelTarifasAntiguas("Rango temporada alta (MM/DD-MM/DD): "));
            dialogTarifasAntiguas.add(generadorLabelTarifasAntiguas("" + vp.catalogo.getTarifasGlobales().getRangoTemporadaAlta()));

            dialogTarifasAntiguas.setVisible(true);

            setLayout(new GridLayout(4,1));

            JLabel lbTarifaConductorExtra = generadorLabelInput("Tarifa conductor extra: ");
            add(lbTarifaConductorExtra);

            JLabel lbTarifaEntregaEnOtraSede = generadorLabelInput("Tarifa entrega en otra sede: ");
            add(lbTarifaEntregaEnOtraSede);

            JLabel lbRangoTemporadaAlta = generadorLabelInput("Rango temporada alta (MM/DD-MM/DD): ");
            add(lbRangoTemporadaAlta);

            JButton btnCrearTarifasGlobales = new JButton("Editar tarifas globales");
            btnCrearTarifasGlobales.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnCrearTarifasGlobales.addActionListener(e -> {
                String tarifaConductorExtraS = ((JTextField) lbTarifaConductorExtra.getComponent(1)).getText();
                Integer tarifaConductorExtra = Integer.parseInt(tarifaConductorExtraS);
                String tarifaEntregaEnOtraSedeS = ((JTextField) lbTarifaEntregaEnOtraSede.getComponent(1)).getText();
                Integer tarifaEntregaEnOtraSede = Integer.parseInt(tarifaEntregaEnOtraSedeS);
                String rangoTemporadaAlta = ((JTextField) lbRangoTemporadaAlta.getComponent(1)).getText();
                if (tarifaConductorExtra > 3 && tarifaEntregaEnOtraSede > 3 && rangoTemporadaAlta.length() > 3) {
                    ((Administrador) vp.usu).crearTarifasGlobales(vp.catalogo, tarifaConductorExtra, tarifaEntregaEnOtraSede, rangoTemporadaAlta);
                    ventanaPrincipal.cambiarPagina(11);
                    JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Tarifas globales creadas con exito");
                    dialogOK.setSize(300,30);
                    dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                    dialogOK.setVisible(true);
                    System.out.println("Tarifas globales creadas con exito!!!");
                } else {
                    ((JTextField) lbTarifaConductorExtra.getComponent(1)).setText("");
                    ((JTextField) lbTarifaEntregaEnOtraSede.getComponent(1)).setText("");
                    ((JTextField) lbRangoTemporadaAlta.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al crear tarifas globales");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al crear tarifas globales!!!");
                }
            });
            add(btnCrearTarifasGlobales);

        } else if (pagina == 117) {
            setLayout(new GridLayout(12,1));

            JLabel lbPlaca = generadorLabelInput("Placa: ");
            add(lbPlaca);

            JLabel lbMarca = generadorLabelInput("Marca: ");
            add(lbMarca);

            JLabel lbModelo = generadorLabelInput("Modelo: ");
            add(lbModelo);

            JLabel lbColor = generadorLabelInput("Color: ");
            add(lbColor);

            JLabel lbTipoDeTransmision = generadorLabelInput("Tipo de transmision: ");
            add(lbTipoDeTransmision);

            JLabel lbTipoDeDireccion = generadorLabelInput("Tipo de direccion: ");
            add(lbTipoDeDireccion);

            JLabel lbTipoDeCombustible = generadorLabelInput("Tipo de combustible: ");
            add(lbTipoDeCombustible);

            JLabel lbNumeroDePasajeros = generadorLabelInput("Numero de pasajeros: ");
            add(lbNumeroDePasajeros);

            JLabel lbNombreSedeActual = generadorLabelInput("Nombre de la sede actual: ");
            add(lbNombreSedeActual);

            JLabel lbNombreCategoria = generadorLabelInput("Nombre de la categoria: ");
            add(lbNombreCategoria);

            JLabel lbFechaDisponibilidad = generadorLabelInput("Fecha de disponibilidad (MM/DD/AAAA): ");
            add(lbFechaDisponibilidad);

            JButton btnCrearVehiculo = new JButton("Crear vehiculo");
            btnCrearVehiculo.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnCrearVehiculo.addActionListener(e -> {
                String placa = ((JTextField) lbPlaca.getComponent(1)).getText();
                String marca = ((JTextField) lbMarca.getComponent(1)).getText();
                String modelo = ((JTextField) lbModelo.getComponent(1)).getText();
                String color = ((JTextField) lbColor.getComponent(1)).getText();
                String tipoDeTransmision = ((JTextField) lbTipoDeTransmision.getComponent(1)).getText();
                String tipoDeDireccion = ((JTextField) lbTipoDeDireccion.getComponent(1)).getText();
                String tipoDeCombustible = ((JTextField) lbTipoDeCombustible.getComponent(1)).getText();
                String numeroDePasajeros = ((JTextField) lbNumeroDePasajeros.getComponent(1)).getText();
                String nombreSedeActual = ((JTextField) lbNombreSedeActual.getComponent(1)).getText();
                String nombreCategoria = ((JTextField) lbNombreCategoria.getComponent(1)).getText();
                String fechaDisponibilidad = ((JTextField) lbFechaDisponibilidad.getComponent(1)).getText();
                if (placa.length() > 3 && marca.length() > 3 && modelo.length() > 3 && color.length() > 3 && tipoDeTransmision.length() > 3 && tipoDeDireccion.length() > 3 && tipoDeCombustible.length() > 3 && numeroDePasajeros.length() > 0 && nombreSedeActual.length() > 3 && nombreCategoria.length() > 3 && fechaDisponibilidad.length() > 3) {
                    if (vp.catalogo.getHashCategorias().containsKey(nombreCategoria) && vp.hashSedes.containsKey(nombreSedeActual)) {
                        ((Administrador) vp.usu).crearVehiculo(vp.catalogo, placa, marca, modelo, color, tipoDeTransmision, tipoDeDireccion, tipoDeCombustible, numeroDePasajeros, nombreSedeActual, nombreCategoria, fechaDisponibilidad);
                        ventanaPrincipal.cambiarPagina(11);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Vehiculo creado con exito");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Vehiculo creado con exito!!!");
                    } else {
                        ((JTextField) lbPlaca.getComponent(1)).setText("");
                        ((JTextField) lbMarca.getComponent(1)).setText("");
                        ((JTextField) lbModelo.getComponent(1)).setText("");
                        ((JTextField) lbColor.getComponent(1)).setText("");
                        ((JTextField) lbTipoDeTransmision.getComponent(1)).setText("");
                        ((JTextField) lbTipoDeDireccion.getComponent(1)).setText("");
                        ((JTextField) lbTipoDeCombustible.getComponent(1)).setText("");
                        ((JTextField) lbNumeroDePasajeros.getComponent(1)).setText("");
                        ((JTextField) lbNombreSedeActual.getComponent(1)).setText("");
                        ((JTextField) lbNombreCategoria.getComponent(1)).setText("");
                        ((JTextField) lbFechaDisponibilidad.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Categoria o sede no existente");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Categoria o sede no existente!!!");
                    }
                } else {
                    ((JTextField) lbPlaca.getComponent(1)).setText("");
                    ((JTextField) lbMarca.getComponent(1)).setText("");
                    ((JTextField) lbModelo.getComponent(1)).setText("");
                    ((JTextField) lbColor.getComponent(1)).setText("");
                    ((JTextField) lbTipoDeTransmision.getComponent(1)).setText("");
                    ((JTextField) lbTipoDeDireccion.getComponent(1)).setText("");
                    ((JTextField) lbTipoDeCombustible.getComponent(1)).setText("");
                    ((JTextField) lbNumeroDePasajeros.getComponent(1)).setText("");
                    ((JTextField) lbNombreSedeActual.getComponent(1)).setText("");
                    ((JTextField) lbNombreCategoria.getComponent(1)).setText("");
                    ((JTextField) lbFechaDisponibilidad.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al crear vehiculo");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al crear vehiculo!!!");
                }
            });
            add(btnCrearVehiculo);

        } else if (pagina == 118) {
            setLayout(new GridLayout(2,1));

            JLabel lbPlaca = generadorLabelInput("Placa: ");
            add(lbPlaca);

            JButton btnEliminarVehiculo = new JButton("Eliminar vehiculo");
            btnEliminarVehiculo.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnEliminarVehiculo.addActionListener(e -> {
                String placa = ((JTextField) lbPlaca.getComponent(1)).getText();
                if (placa.length() > 3) {
                    Boolean esta = false;
                    for (Map.Entry<String, Categoria> categoria : vp.catalogo.getHashCategorias().entrySet()) {
                        if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                            esta = true;
                        }
                    }
                    if (esta) {
                        ((Administrador) vp.usu).eliminarVehiculo(vp.catalogo, placa);
                        ventanaPrincipal.cambiarPagina(11);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Vehiculo eliminado con exito");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Vehiculo eliminado con exito!!!");
                    } else {
                        ((JTextField) lbPlaca.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Vehiculo no existente");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Vehiculo no existente!!!");
                    }
                } else {
                    ((JTextField) lbPlaca.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al eliminar vehiculo");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al eliminar vehiculo!!!");
                }
            });
            add(btnEliminarVehiculo);

        } else if (pagina == 119) {
            setLayout(new GridLayout(2,1));

            JLabel lbPlaca = generadorLabelInput("Placa: ");
            add(lbPlaca);

            JButton btnEstadoVehiculo = new JButton("Estado vehiculo");
            btnEstadoVehiculo.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnEstadoVehiculo.addActionListener(e -> {
                String placa = ((JTextField) lbPlaca.getComponent(1)).getText();
                if (placa.length() > 3) {
                    Boolean esta = false;
                    for (Map.Entry<String, Categoria> categoria : vp.catalogo.getHashCategorias().entrySet()) {
                        if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                            esta = true;
                        }
                    }
                    if (esta) {
                        JDialog dialogEstadoVehiculo = new JDialog((JFrame) getTopLevelAncestor(), "Estado vehiculo");
                        dialogEstadoVehiculo.setSize(750,700);
                        dialogEstadoVehiculo.setLocationRelativeTo(getTopLevelAncestor());
                        dialogEstadoVehiculo.setLayout(new GridLayout(1,1));
                        String resumenEstado = ((Administrador) vp.usu).estadoVehiculo(vp.catalogo, placa);
                        
                        JTextArea textArea = new JTextArea(resumenEstado);
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        dialogEstadoVehiculo.add(scrollPane);

                        dialogEstadoVehiculo.setVisible(true);
                        System.out.println("Estado del vehiculo reportado exitosamente!!!");
                        ((JTextField) lbPlaca.getComponent(1)).setText("");
                        ventanaPrincipal.cambiarPagina(11);

                    } else {
                        ((JTextField) lbPlaca.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Vehiculo no existente");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Vehiculo no existente!!!");
                    }
                } else {
                    ((JTextField) lbPlaca.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al consultar estado vehiculo");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al consultar estado vehiculo!!!");
                }
            });
            add(btnEstadoVehiculo);

        } else if (pagina == 1110) {
            setLayout(new GridLayout(6,1));

            JLabel lbPlaca = generadorLabelInput("Placa: ");
            add(lbPlaca);

            JLabel lbSedeDestino = generadorLabelInput("Sede destino: ");
            add(lbSedeDestino);

            JLabel lbFechaRecoger = generadorLabelInput("Fecha recoger (MM/DD/AAAA): ");
            add(lbFechaRecoger);

            JLabel lbHoraRecoger = generadorLabelInput("Hora recoger (HH:MM): ");
            add(lbHoraRecoger);

            JLabel lbFechaEntregar = generadorLabelInput("Fecha entregar (MM/DD/AAAA): ");
            add(lbFechaEntregar);

            JButton btnTrasladarVehiculo = new JButton("Trasladar vehiculo");
            btnTrasladarVehiculo.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnTrasladarVehiculo.addActionListener(e -> {
                String placa = ((JTextField) lbPlaca.getComponent(1)).getText();
                String sedeDestino = ((JTextField) lbSedeDestino.getComponent(1)).getText();
                String fechaRecoger = ((JTextField) lbFechaRecoger.getComponent(1)).getText();
                String horaRecoger = ((JTextField) lbHoraRecoger.getComponent(1)).getText();
                String fechaEntregar = ((JTextField) lbFechaEntregar.getComponent(1)).getText();
                
                Boolean esta = false;
                String categoriaNombre = "";

                if (placa.length() > 3 && sedeDestino.length() > 3 && fechaRecoger.length() > 3 && horaRecoger.length() > 3 && fechaEntregar.length() > 3) {
                    for (Map.Entry<String, Categoria> categoria : vp.catalogo.getHashCategorias().entrySet()) {
                            if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                                esta = true;
                                categoriaNombre = categoria.getKey();
                            }
                        }
                    if (esta && vp.hashSedes.containsKey(sedeDestino)) {
                        Boolean tieneReservas = false;
                        for (Map.Entry<String, Categoria> categoria : vp.catalogo.getHashCategorias().entrySet()) {
                            if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                                for (Reserva reserva : categoria.getValue().getHashVehiculos().get(placa).getReservas()) {
                                    if (ReservaNormal.rangoFecha(reserva.getRangoAlquiler().split("-")[1]+"-"+fechaRecoger) < 0) {
                                        tieneReservas = true;
                                    }
                                }
                            }
                        }
                        Boolean estadoAlquiler = vp.catalogo.getHashCategorias().get(categoriaNombre).getHashVehiculos().get(placa).getEnAlquiler();
                        Boolean disponibleAlquiler = vp.catalogo.getHashCategorias().get(categoriaNombre).getHashVehiculos().get(placa).getDetallesSede().getDisponibilidadParaAlquilar();
                        if (!tieneReservas && !estadoAlquiler && disponibleAlquiler) {
                            String detallesTraslado = ((Administrador) vp.usu).trasladarVehiculo(vp.catalogo, vp.hashReservas, placa, sedeDestino, fechaRecoger, horaRecoger, fechaEntregar);
                            JDialog dialogDetallesTraslado = new JDialog((JFrame) getTopLevelAncestor(), "Vehículo trasladado y reserva especial creada exitosamente");
                            dialogDetallesTraslado.setSize(750,700);
                            dialogDetallesTraslado.setLocationRelativeTo(getTopLevelAncestor());
                            dialogDetallesTraslado.setLayout(new GridLayout(1,1));
                            JTextArea textArea = new JTextArea(detallesTraslado + "\n\nNo olvidar pedir a un EMPLEADO de la sede destino cambiar el estado a listo para alquiler!!!");
                            textArea.setEditable(false);
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            dialogDetallesTraslado.add(scrollPane);
                            dialogDetallesTraslado.setVisible(true);

                            ((JTextField) lbPlaca.getComponent(1)).setText("");
                            ((JTextField) lbSedeDestino.getComponent(1)).setText("");
                            ((JTextField) lbFechaRecoger.getComponent(1)).setText("");
                            ((JTextField) lbHoraRecoger.getComponent(1)).setText("");
                            ((JTextField) lbFechaEntregar.getComponent(1)).setText("");

                            ventanaPrincipal.cambiarPagina(11);
                            System.out.println("Vehículo trasladado y reserva especial creada exitosamente!!!");
                        } else {
                            ((JTextField) lbPlaca.getComponent(1)).setText("");
                            ((JTextField) lbSedeDestino.getComponent(1)).setText("");
                            ((JTextField) lbFechaRecoger.getComponent(1)).setText("");
                            ((JTextField) lbHoraRecoger.getComponent(1)).setText("");
                            ((JTextField) lbFechaEntregar.getComponent(1)).setText("");
                            JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El vehículo tiene reservas activas (despues de la fecha a recoger) o no esta disponible para alquiler");
                            dialogError.setSize(800,30);
                            dialogError.setLocationRelativeTo(getTopLevelAncestor());
                            dialogError.setVisible(true);
                            System.out.println("El vehículo tiene reservas activas (despues de la fecha a recoger) o no esta disponible para alquiler, no se puede trasladar!!!");
                        }
                    } else {
                        ((JTextField) lbPlaca.getComponent(1)).setText("");
                        ((JTextField) lbSedeDestino.getComponent(1)).setText("");
                        ((JTextField) lbFechaRecoger.getComponent(1)).setText("");
                        ((JTextField) lbHoraRecoger.getComponent(1)).setText("");
                        ((JTextField) lbFechaEntregar.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Vehiculo o sede no existente");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Vehiculo o sede no existente!!!");
                    }
                } else {
                    ((JTextField) lbPlaca.getComponent(1)).setText("");
                        ((JTextField) lbSedeDestino.getComponent(1)).setText("");
                        ((JTextField) lbFechaRecoger.getComponent(1)).setText("");
                        ((JTextField) lbHoraRecoger.getComponent(1)).setText("");
                        ((JTextField) lbFechaEntregar.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al trasladar vehiculo");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al trasladar vehiculo!!!");
                }
            });
            add(btnTrasladarVehiculo);

        } else if (pagina == 1111) {
            setLayout(new GridLayout(2,1));

            JLabel lbIdReserva = generadorLabelInput("Id reserva: ");
            add(lbIdReserva);

            JButton btnConsultarReserva = new JButton("Consultar reserva");
            btnConsultarReserva.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnConsultarReserva.addActionListener(e -> {
                String idReserva = ((JTextField) lbIdReserva.getComponent(1)).getText();
                if (idReserva.length() > 0) {
                    if (vp.hashReservas.containsKey(idReserva)) {
                        String resumenResreva = ((Administrador) vp.usu).resumenReserva(vp.hashReservas, idReserva, vp.catalogo);
                        JDialog dialogResumenReserva = new JDialog((JFrame) getTopLevelAncestor(), "Resumen reserva");
                        dialogResumenReserva.setSize(750,700);
                        dialogResumenReserva.setLocationRelativeTo(getTopLevelAncestor());
                        dialogResumenReserva.setLayout(new GridLayout(1,1));
                        JTextArea textArea = new JTextArea(resumenResreva);
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        dialogResumenReserva.add(scrollPane);
                        dialogResumenReserva.setVisible(true);
                        System.out.println("Reserva consultada exitosamente!!!");
                        ((JTextField) lbIdReserva.getComponent(1)).setText("");
                        ventanaPrincipal.cambiarPagina(11);

                    } else {
                        ((JTextField) lbIdReserva.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Reserva no existente");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Reserva no existente!!!");
                    }
                } else {
                    ((JTextField) lbIdReserva.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al consultar reserva");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al consultar reserva!!!");
                }
            });
            add(btnConsultarReserva);

        } else if (pagina == 1112) {
            setLayout(new GridLayout(8,1));

            JLabel lbNombreSede = generadorLabelInput("Nombre sede: ");
            add(lbNombreSede);

            JLabel lbUsuario = generadorLabelInput("Usuario: ");
            add(lbUsuario);

            JLabel lbContrasena = generadorLabelInput("Contrasena: ");
            add(lbContrasena);

            JLabel lbNombre = generadorLabelInput("Nombre: ");
            add(lbNombre);

            JLabel lbApellido = generadorLabelInput("Apellido: ");
            add(lbApellido);

            JLabel lbCelular = generadorLabelInput("Celular: ");
            add(lbCelular);

            JLabel lbCorreo = generadorLabelInput("Correo: ");
            add(lbCorreo);

            JButton btnCrearAdministradorLocal = new JButton("Crear administrador local");
            btnCrearAdministradorLocal.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnCrearAdministradorLocal.addActionListener(e -> {
                String nombreSede = ((JTextField) lbNombreSede.getComponent(1)).getText();
                String usuario = ((JTextField) lbUsuario.getComponent(1)).getText();
                String contrasena = ((JTextField) lbContrasena.getComponent(1)).getText();
                String nombre = ((JTextField) lbNombre.getComponent(1)).getText();
                String apellido = ((JTextField) lbApellido.getComponent(1)).getText();
                String celular = ((JTextField) lbCelular.getComponent(1)).getText();
                String correo = ((JTextField) lbCorreo.getComponent(1)).getText();
                if (nombreSede.length() > 3 && usuario.length() > 3 && contrasena.length() > 3 && nombre.length() > 3 && apellido.length() > 3 && celular.length() > 3 && correo.length() > 3) {
                    if (vp.hashSedes.containsKey(nombreSede) && !(vp.hashUsuarios.containsKey(usuario))) {
                        ((Administrador) vp.usu).crearAdministradorLocal(vp.hashUsuarios, usuario, contrasena, nombreSede, nombre, apellido, celular, correo);                        
                        ventanaPrincipal.cambiarPagina(11);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Administrador local creado con exito");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Administrador local creado con exito!!!");
                    } else {
                        ((JTextField) lbNombreSede.getComponent(1)).setText("");
                        ((JTextField) lbUsuario.getComponent(1)).setText("");
                        ((JTextField) lbContrasena.getComponent(1)).setText("");
                        ((JTextField) lbNombre.getComponent(1)).setText("");
                        ((JTextField) lbApellido.getComponent(1)).setText("");
                        ((JTextField) lbCelular.getComponent(1)).setText("");
                        ((JTextField) lbCorreo.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "La sede no existente o el usuario ya existe");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("La sede no existente o usuario repetido!!!");
                    }
                } else {
                    ((JTextField) lbNombreSede.getComponent(1)).setText("");
                    ((JTextField) lbUsuario.getComponent(1)).setText("");
                    ((JTextField) lbContrasena.getComponent(1)).setText("");
                    ((JTextField) lbNombre.getComponent(1)).setText("");
                    ((JTextField) lbApellido.getComponent(1)).setText("");
                    ((JTextField) lbCelular.getComponent(1)).setText("");
                    ((JTextField) lbCorreo.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al crear administrador local");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al crear administrador local!!!");
                }
            });
            add(btnCrearAdministradorLocal);

        } else if (pagina == 1113) {
            setLayout(new GridLayout(2,1));

            JLabel lbUsuario = generadorLabelInput("Usuario: ");
            add(lbUsuario);

            JButton btnEliminarUsuario = new JButton("Eliminar usuario");
            btnEliminarUsuario.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnEliminarUsuario.addActionListener(e -> {
                String usuario = ((JTextField) lbUsuario.getComponent(1)).getText();
                if (usuario.length() > 3) {
                    if (vp.hashUsuarios.containsKey(usuario) && !(usuario.equals("ADMIN"))) {
                        ((Administrador) vp.usu).eliminarUsuario(vp.hashUsuarios, usuario);
                        ventanaPrincipal.cambiarPagina(11);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Usuario: " + usuario + " eliminado con exito");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Usuario eliminado con exito!!!");
                    } else {
                        ((JTextField) lbUsuario.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no existe");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("El usuario no existe!!!");
                    }
                } else {
                    ((JTextField) lbUsuario.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al eliminar usuario");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al eliminar usuario!!!");
                }
            });
            add(btnEliminarUsuario);

        } else if (pagina == 1114) {
            //TODO: PONTO  aca tienes que hacer lo que hace que muestre la grafica no olvides que en vp estan todos los hash es solo hacer vp.hash
        } else if (pagina == 12) {
            setLayout(new GridLayout(7,2));

            JButton btnCrearEmpleadoLocal = new JButton("Crear empleado local");
            btnCrearEmpleadoLocal.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnCrearEmpleadoLocal.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(121);
            });
            add(btnCrearEmpleadoLocal);

            JButton btnEliminarEmpleadoLocal = new JButton("Eliminar empleado local");
            btnEliminarEmpleadoLocal.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnEliminarEmpleadoLocal.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(122);
            });
            add(btnEliminarEmpleadoLocal);

            for (int i = 0; i < 12; i++) {
                add(new JLabel());
            }

        } else if (pagina == 121) {
            setLayout(new GridLayout(7,1));

            JLabel lbUsuario = generadorLabelInput("Usuario: ");
            add(lbUsuario);

            JLabel lbContrasena = generadorLabelInput("Contrasena: ");
            add(lbContrasena);

            JLabel lbNombre = generadorLabelInput("Nombre: ");
            add(lbNombre);

            JLabel lbApellido = generadorLabelInput("Apellido: ");
            add(lbApellido);

            JLabel lbCelular = generadorLabelInput("Celular: ");
            add(lbCelular);

            JLabel lbCorreo = generadorLabelInput("Correo: ");
            add(lbCorreo);

            JButton btnCrearEmpleadoLocal = new JButton("Crear empleado local");
            btnCrearEmpleadoLocal.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnCrearEmpleadoLocal.addActionListener(e -> {
                String usuario = ((JTextField) lbUsuario.getComponent(1)).getText();
                String contrasena = ((JTextField) lbContrasena.getComponent(1)).getText();
                String nombre = ((JTextField) lbNombre.getComponent(1)).getText();
                String apellido = ((JTextField) lbApellido.getComponent(1)).getText();
                String celular = ((JTextField) lbCelular.getComponent(1)).getText();
                String correo = ((JTextField) lbCorreo.getComponent(1)).getText();
                if (usuario.length() > 3 && contrasena.length() > 3 && nombre.length() > 3 && apellido.length() > 3 && celular.length() > 3 && correo.length() > 3) {
                    if (!(vp.hashUsuarios.containsKey(usuario))) {
                        ((AdministradorLocal) vp.usu).crearEmpleado(vp.hashUsuarios, usuario, contrasena, nombre, apellido, celular, correo);                     
                        ventanaPrincipal.cambiarPagina(12);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Empleado local creado con exito");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Empleado local creado con exito!!!");
                    } else {
                        ((JTextField) lbUsuario.getComponent(1)).setText("");
                        ((JTextField) lbContrasena.getComponent(1)).setText("");
                        ((JTextField) lbNombre.getComponent(1)).setText("");
                        ((JTextField) lbApellido.getComponent(1)).setText("");
                        ((JTextField) lbCelular.getComponent(1)).setText("");
                        ((JTextField) lbCorreo.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario ya existe");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("Usuario repetido!!!");
                    }
                } else {
                    ((JTextField) lbUsuario.getComponent(1)).setText("");
                    ((JTextField) lbContrasena.getComponent(1)).setText("");
                    ((JTextField) lbNombre.getComponent(1)).setText("");
                    ((JTextField) lbApellido.getComponent(1)).setText("");
                    ((JTextField) lbCelular.getComponent(1)).setText("");
                    ((JTextField) lbCorreo.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al crear empleado local");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al crear empleado local!!!");
                }
            });
            add(btnCrearEmpleadoLocal);

        } else if (pagina == 122) {
            setLayout(new GridLayout(2,1));

            JLabel lbUsuario = generadorLabelInput("Usuario: ");
            add(lbUsuario);

            JButton btnEliminarEmpleadoLocal = new JButton("Eliminar empleado local");
            btnEliminarEmpleadoLocal.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnEliminarEmpleadoLocal.addActionListener(e -> {
                String usuario = ((JTextField) lbUsuario.getComponent(1)).getText();
                if (usuario.length() > 3 && !(usuario.equals(vp.usu.getUsername()))) {
                    if (vp.hashUsuarios.containsKey(usuario)) {
                        boolean esta = false;
                        for (Usuario usuarioF : vp.hashUsuarios.values()) {
                            if (usuarioF.getUsername().equals(usuario) && usuarioF.getNivelDeAcceso() == 1) {
                                esta = true;
                            }
                        }
                        if (esta) {
                            ((AdministradorLocal) vp.usu).eliminarEmpleado(vp.hashUsuarios, usuario);
                            ventanaPrincipal.cambiarPagina(12);
                            JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Empleado local eliminado con exito");
                            dialogOK.setSize(300,30);
                            dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                            dialogOK.setVisible(true);
                            System.out.println("Empleado local eliminado con exito!!!");
                        } else {
                            ((JTextField) lbUsuario.getComponent(1)).setText("");
                            JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no es un empleado local");
                            dialogError.setSize(300,30);
                            dialogError.setLocationRelativeTo(getTopLevelAncestor());
                            dialogError.setVisible(true);
                            System.out.println("El usuario no es un empleado local!!!");
                        }
                    } else {
                        ((JTextField) lbUsuario.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no existe");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("El usuario no existe!!!");
                    }
                } else {
                    ((JTextField) lbUsuario.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al eliminar empleado local");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al eliminar empleado local!!!");
                }
            });
            add(btnEliminarEmpleadoLocal);

        } else if (pagina == 13) {
            setLayout(new GridLayout(7,2));

            JButton btnAlquilarVehiculo = new JButton("Alquilar vehiculo");
            btnAlquilarVehiculo.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnAlquilarVehiculo.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(131);
            });
            add(btnAlquilarVehiculo);

            JButton btnAgregarLicenciaConductores = new JButton("Agregar licencia de otros conductores");
            btnAgregarLicenciaConductores.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnAgregarLicenciaConductores.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(132);
            });
            add(btnAgregarLicenciaConductores);

            JButton btnRecibirVehiculo = new JButton("Recibir vehiculo");
            btnRecibirVehiculo.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnRecibirVehiculo.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(133);
            });
            add(btnRecibirVehiculo);

            JButton btnListarVehiculoParaAlquiler = new JButton("Listar vehiculo para alquiler");
            btnListarVehiculoParaAlquiler.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnListarVehiculoParaAlquiler.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(134);
            });
            add(btnListarVehiculoParaAlquiler);

            for (int i = 0; i < 8; i++) {
                add(new JLabel());
            }

        } else if (pagina == 131) {
            setLayout(new GridLayout(2,1));

            JLabel lbUsuario = generadorLabelInput("Usuario del cliente con reserva para alquiler: ");
            add(lbUsuario);

            JButton btnAlquilarVehiculo = new JButton("Alquilar vehiculo");
            btnAlquilarVehiculo.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnAlquilarVehiculo.addActionListener(e -> {
                String usuario = ((JTextField) lbUsuario.getComponent(1)).getText();
                if (usuario.length() > 3) {
                    if (vp.hashUsuarios.containsKey(usuario)) {
                        boolean esta = false;
                        for (Usuario usuarioF : vp.hashUsuarios.values()) {
                            if (usuarioF.getUsername().equals(usuario) && usuarioF.getNivelDeAcceso() == 0) {
                                esta = true;
                            }
                        }
                        if (esta) {
                            if (((Cliente) vp.hashUsuarios.get(usuario)).getTieneReserva()) {
                                String placa = ((Empleado) vp.usu).alquilarVehiculo(vp.catalogo, vp.hashUsuarios, vp.hashReservas, usuario);
                                if (placa.equals("No hay vehiculos disponibles en este momento para esta categoria. Crear una nueva reserva, se elimino la reserva antigua del usuario")) {
                                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), placa);
                                    dialogError.setSize(800,30);
                                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                                    dialogError.setVisible(true);
                                    System.out.println(placa);
                                } else {
                                    ventanaPrincipal.cambiarPagina(13);
                                    JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Vehiculo: " + placa + " alquilado con exito");
                                    dialogOK.setSize(750,700);
                                    dialogOK.setLocationRelativeTo(getTopLevelAncestor());

                                    String resumenAlquiler = ((Cliente) vp.hashUsuarios.get(usuario)).getResumenReservaActual(vp.hashReservas, vp.catalogo);
                                    dialogOK.setLayout(new GridLayout(1,1));
                                    JTextArea textArea = new JTextArea(resumenAlquiler);
                                    textArea.setEditable(false);
                                    JScrollPane scrollPane = new JScrollPane(textArea);
                                    dialogOK.add(scrollPane);

                                    dialogOK.setVisible(true);
                                    System.out.println("Vehiculo alquilado con exito!!!");
                                }
                            } else {
                                ((JTextField) lbUsuario.getComponent(1)).setText("");
                                JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no tiene una reserva para alquiler");
                                dialogError.setSize(400,30);
                                dialogError.setLocationRelativeTo(getTopLevelAncestor());
                                dialogError.setVisible(true);
                                System.out.println("El usuario no tiene una reserva para alquiler!!!");
                            }
                        } else {
                            ((JTextField) lbUsuario.getComponent(1)).setText("");
                            JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no es un cliente");
                            dialogError.setSize(300,30);
                            dialogError.setLocationRelativeTo(getTopLevelAncestor());
                            dialogError.setVisible(true);
                            System.out.println("El usuario no es un cliente!!!");
                        }
                    } else {
                        ((JTextField) lbUsuario.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no existe");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("El usuario no existe!!!");
                    }
                } else {
                    ((JTextField) lbUsuario.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al alquilar vehiculo");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al alquilar vehiculo!!!");
                }
            });
            add(btnAlquilarVehiculo);

        } else if (pagina == 132) {
            setLayout(new GridLayout(5,1));

            JLabel lbUsuario = generadorLabelInput("Usuario del cliente: ");
            add(lbUsuario);

            JLabel lbNumeroDeLicencia = generadorLabelInput("Numero de licencia: ");
            add(lbNumeroDeLicencia);

            JLabel lbPaisDeExpedicion = generadorLabelInput("Pais de expedicion: ");
            add(lbPaisDeExpedicion);

            JLabel lbFechaDeVencimiento = generadorLabelInput("Fecha de vencimiento (MM/DD/AAAA): ");
            add(lbFechaDeVencimiento);

            JButton btnAgregarLicenciaConductores = new JButton("Agregar licencia de otros conductores");
            btnAgregarLicenciaConductores.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnAgregarLicenciaConductores.addActionListener(e -> {
                String usuario = ((JTextField) lbUsuario.getComponent(1)).getText();
                String numeroDeLicenciaS = ((JTextField) lbNumeroDeLicencia.getComponent(1)).getText();
                Integer numeroDeLicencia = Integer.parseInt(numeroDeLicenciaS);
                String paisDeExpedicion = ((JTextField) lbPaisDeExpedicion.getComponent(1)).getText();
                String fechaDeVencimiento = ((JTextField) lbFechaDeVencimiento.getComponent(1)).getText();

                if (usuario.length() > 3) {
                    if (vp.hashUsuarios.containsKey(usuario)) {
                        boolean esta = false;
                        for (Usuario usuarioF : vp.hashUsuarios.values()) {
                            if (usuarioF.getUsername().equals(usuario) && usuarioF.getNivelDeAcceso() == 0) {
                                esta = true;
                            }
                        }
                        if (esta) {
                            BufferedImage imagenLicencia = null;
                            try {
                                String workingDir = System.getProperty("user.dir");
                                String filePath = workingDir + File.separator + "data" + File.separator;
                                File file = new File(filePath+"alicencia.jpg");
                                imagenLicencia = javax.imageio.ImageIO.read(file);
                            } catch (IOException ex) {
                                System.out.println("Error al intentar leer la imagen de licencia, asegurarse que esta en la carpeta data nombrada como 'alicencia' con extensión .png!!!");
                            }
                            ((Empleado) vp.usu).otrosConductoresAgregarLicencia(vp.hashUsuarios, usuario, numeroDeLicencia, paisDeExpedicion, fechaDeVencimiento, imagenLicencia);
                            ventanaPrincipal.cambiarPagina(13);
                            JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Licencia agregada con exito");
                            dialogOK.setSize(300,30);
                            dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                            dialogOK.setVisible(true);
                            System.out.println("Licencia agregada con exito!!!");

                        } else {
                            ((JTextField) lbUsuario.getComponent(1)).setText("");
                            ((JTextField) lbNumeroDeLicencia.getComponent(1)).setText("");
                            ((JTextField) lbPaisDeExpedicion.getComponent(1)).setText("");
                            ((JTextField) lbFechaDeVencimiento.getComponent(1)).setText("");
                            JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no es un cliente");
                            dialogError.setSize(300,30);
                            dialogError.setLocationRelativeTo(getTopLevelAncestor());
                            dialogError.setVisible(true);
                            System.out.println("El usuario no es un cliente!!!");
                        }
                    } else {
                        ((JTextField) lbUsuario.getComponent(1)).setText("");
                        ((JTextField) lbNumeroDeLicencia.getComponent(1)).setText("");
                        ((JTextField) lbPaisDeExpedicion.getComponent(1)).setText("");
                        ((JTextField) lbFechaDeVencimiento.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no existe");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("El usuario no existe!!!");
                    }
                } else {
                    ((JTextField) lbUsuario.getComponent(1)).setText("");
                    ((JTextField) lbNumeroDeLicencia.getComponent(1)).setText("");
                    ((JTextField) lbPaisDeExpedicion.getComponent(1)).setText("");
                    ((JTextField) lbFechaDeVencimiento.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al agregar licencia de otros conductores");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al agregar licencia de otros conductores!!!");
                }
            });
            add(btnAgregarLicenciaConductores);

        } else if (pagina == 133) {
            setLayout(new GridLayout(5,1));
            
            JLabel lbUsuario = generadorLabelInput("Usuario del cliente: ");
            add(lbUsuario);

            JLabel lbMantenimiento = generadorLabelInput("Necesita mantenimiento (TRUE/FALSE): ");
            add(lbMantenimiento);

            JLabel lbFechaRegreso = generadorLabelInput("Si necesita, fecha estimada de regreso ((MM/DD/AAAA)/NA): ");
            add(lbFechaRegreso);

            JLabel lbDescripcionMantenimiento = generadorLabelInput("Si necesita, descripcion del mantenimiento (.../NA): ");
            add(lbDescripcionMantenimiento);

            JButton btnRecibirVehiculo = new JButton("Recibir vehiculo");
            btnRecibirVehiculo.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnRecibirVehiculo.addActionListener(e -> {
                String usuario = ((JTextField) lbUsuario.getComponent(1)).getText();
                String mantenimientoS = ((JTextField) lbMantenimiento.getComponent(1)).getText();
                Boolean mantenimiento = Boolean.parseBoolean(mantenimientoS.toLowerCase());
                String fechaRegreso = ((JTextField) lbFechaRegreso.getComponent(1)).getText();
                String descripcionMantenimiento = ((JTextField) lbDescripcionMantenimiento.getComponent(1)).getText();
                if (usuario.length() > 3 && mantenimientoS.length() > 3) {
                    if (vp.hashUsuarios.containsKey(usuario)) {
                        boolean esta = false;
                        String placaEnAlquiler = "";
                        for (Usuario usuarioF : vp.hashUsuarios.values()) {
                            if (usuarioF.getUsername().equals(usuario) && usuarioF.getNivelDeAcceso() == 0) {
                                esta = true;
                                Integer id = ((Cliente) usuarioF).getIdReserva();
                                for (Map.Entry<String, Reserva> reserva : vp.hashReservas.entrySet()) {
                                    if (reserva.getValue().getIdReserva() == id) {
                                        placaEnAlquiler = reserva.getValue().getPlaca();
                                    }
                                }
                            }
                        }
                        if (esta && !placaEnAlquiler.equals("")) {
                            if (mantenimiento) {
                                if (fechaRegreso.length() > 3 && descripcionMantenimiento.length() > 3) {
                                    String rta = ((Empleado) vp.usu).recibirVehiculoConMantenimiento(vp.catalogo, placaEnAlquiler, usuario, fechaRegreso, descripcionMantenimiento, vp.hashUsuarios);
                                    if (rta.equals("")) {
                                        ventanaPrincipal.cambiarPagina(13);
                                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "El vehículo " + placaEnAlquiler + " alquilado por " + usuario + " recibido exitosamente!!!");
                                        dialogOK.setSize(500,30);
                                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                                        dialogOK.setVisible(true);
                                        System.out.println("El vehículo " + placaEnAlquiler + " alquilado por " + usuario + " recibido exitosamente!!!");
                                    } else {
                                        ventanaPrincipal.cambiarPagina(13);
                                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El vehículo " + placaEnAlquiler + " alquilado por " + usuario + " recibido exitosamente!!!");
                                        dialogError.setSize(750,700);
                                        dialogError.setLocationRelativeTo(getTopLevelAncestor());

                                        dialogError.setLayout(new GridLayout(1,1));
                                        JTextArea textArea = new JTextArea("Las siguientes reservas fueron editadas a causa del mantenimiento:\n\n"+rta);
                                        textArea.setEditable(false);
                                        JScrollPane scrollPane = new JScrollPane(textArea);
                                        dialogError.add(scrollPane);

                                        dialogError.setVisible(true);
                                        System.out.println(rta);
                                    }
                                } else {
                                    ((JTextField) lbUsuario.getComponent(1)).setText("");
                                    ((JTextField) lbMantenimiento.getComponent(1)).setText("");
                                    ((JTextField) lbFechaRegreso.getComponent(1)).setText("");
                                    ((JTextField) lbDescripcionMantenimiento.getComponent(1)).setText("");
                                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al recibir vehiculo");
                                    dialogError.setSize(300,30);
                                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                                    dialogError.setVisible(true);
                                    System.out.println("Error al recibir vehiculo!!!");
                                }
                            } else {
                                ((Empleado) vp.usu).recibirVehiculoSinMantenimiento(vp.catalogo, placaEnAlquiler, usuario, vp.hashUsuarios);
                                ventanaPrincipal.cambiarPagina(13);
                                JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "El vehículo " + placaEnAlquiler + " alquilado por " + usuario + " recibido exitosamente!!!");
                                dialogOK.setSize(500,30);
                                dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                                dialogOK.setVisible(true);
                                System.out.println("El vehículo " + placaEnAlquiler + " alquilado por " + usuario + " recibido exitosamente!!!");
                            }
                        } else {
                            ((JTextField) lbUsuario.getComponent(1)).setText("");
                            ((JTextField) lbMantenimiento.getComponent(1)).setText("");
                            ((JTextField) lbFechaRegreso.getComponent(1)).setText("");
                            ((JTextField) lbDescripcionMantenimiento.getComponent(1)).setText("");
                            JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no es un cliente");
                            dialogError.setSize(300,30);
                            dialogError.setLocationRelativeTo(getTopLevelAncestor());
                            dialogError.setVisible(true);
                            System.out.println("El usuario no es un cliente!!!");
                        }
                    } else {
                        ((JTextField) lbUsuario.getComponent(1)).setText("");
                        ((JTextField) lbMantenimiento.getComponent(1)).setText("");
                        ((JTextField) lbFechaRegreso.getComponent(1)).setText("");
                        ((JTextField) lbDescripcionMantenimiento.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no existe");
                        dialogError.setSize(300,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("El usuario no existe!!!");
                    }
                } else {
                    ((JTextField) lbUsuario.getComponent(1)).setText("");
                    ((JTextField) lbMantenimiento.getComponent(1)).setText("");
                    ((JTextField) lbFechaRegreso.getComponent(1)).setText("");
                    ((JTextField) lbDescripcionMantenimiento.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al recibir vehiculo");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al recibir vehiculo!!!");
                }
            });
            add(btnRecibirVehiculo);


        } else if (pagina == 134) {
            setLayout(new GridLayout(3,1));

            JLabel lbPlaca = generadorLabelInput("Placa: ");
            add(lbPlaca);

            JLabel lbFechaActual = generadorLabelInput("Fecha actual (MM/DD/AAAA): ");
            add(lbFechaActual);

            JButton btnListarVehiculoParaAlquiler = new JButton("Listar vehiculo para alquiler");
            btnListarVehiculoParaAlquiler.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnListarVehiculoParaAlquiler.addActionListener(e -> {
                String placa = ((JTextField) lbPlaca.getComponent(1)).getText();
                String fechaActual = ((JTextField) lbFechaActual.getComponent(1)).getText();
                if (placa.length() > 3 && fechaActual.length() > 3) {
                    Boolean esta = false;
                    for (Map.Entry<String, Categoria> categoria : vp.catalogo.getHashCategorias().entrySet()) {
                        if (categoria.getValue().getHashVehiculos().containsKey(placa)) {
                            Vehiculo vehiculo = categoria.getValue().getHashVehiculos().get(placa);
                            if (!vehiculo.getEnAlquiler() && !vehiculo.getDetallesSede().getDisponibilidadParaAlquilar()) {
                                esta = true;
                            }
                        }
                    }
                    if (esta) {
                        ((Empleado) vp.usu).vehiculoListoParaAlquiler(vp.catalogo, placa, fechaActual);
                        ventanaPrincipal.cambiarPagina(13);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Vehiculo " + placa + " listo para alquiler");
                        dialogOK.setSize(300,30);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setVisible(true);
                        System.out.println("Vehiculo " + placa + " listo para alquiler!!!");
                    } else {
                        ((JTextField) lbPlaca.getComponent(1)).setText("");
                        ((JTextField) lbFechaActual.getComponent(1)).setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El vehículo no existe o no esta en alquiler o mantenimiento");
                        dialogError.setSize(500,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println("El vehículo no existe o no esta en alquiler o mantenimiento!!!");
                    }
                } else {
                    ((JTextField) lbPlaca.getComponent(1)).setText("");
                    ((JTextField) lbFechaActual.getComponent(1)).setText("");
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al listar vehiculo para alquiler");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al listar vehiculo para alquiler!!!");
                }
            });
            add(btnListarVehiculoParaAlquiler);

        } else if (pagina == 14) {
            setLayout(new GridLayout(7,2));

            JButton btnCrearReserva = new JButton("Crear reserva");
            btnCrearReserva.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnCrearReserva.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(141);
            });
            add(btnCrearReserva);

            JButton btnEditarReserva = new JButton("Editar reserva");
            btnEditarReserva.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnEditarReserva.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(142);
            });
            add(btnEditarReserva);

            JButton btnObtenerResumenReservaActual = new JButton("Obtener resumen de reserva actual");
            btnObtenerResumenReservaActual.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnObtenerResumenReservaActual.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(143);
            });
            add(btnObtenerResumenReservaActual);

            for (int i = 0; i < 11; i++) {
                add(new JLabel());
            }
        } else if (pagina == 141) {
            setLayout(new BorderLayout());
            
            // El panel este de seguros:
            JPanel panelEste = new JPanel();
            ArrayList<String> segurosSeleccionados = new ArrayList<String>();
            segurosSeleccionados.add(" ");
            ArrayList<String> listaSegurosNombre = new ArrayList<String>();
            ArrayList<String> listaSegurosDescripcion = new ArrayList<String>();
            ArrayList<String> listaSegurosPrecio = new ArrayList<String>();
            for (Map.Entry<String, Seguro> seguro : vp.catalogo.getHashSeguros().entrySet()) {
                listaSegurosNombre.add(seguro.getValue().getNombreSeguro());
                listaSegurosDescripcion.add(seguro.getValue().getDescripcionSeguro());
                listaSegurosPrecio.add(String.valueOf(seguro.getValue().getTarifaExtra()));
            }

            Integer numeroDeSeguros = listaSegurosNombre.size();

            panelEste.setLayout(new GridLayout(numeroDeSeguros+1,1));

            JLabel lbTituloSeguros = new JLabel("Seleccionar seguros");
            lbTituloSeguros.setFont(new Font("Dialog", Font.PLAIN, 22));
            lbTituloSeguros.setHorizontalAlignment(JLabel.CENTER);
            lbTituloSeguros.setOpaque(true);
            lbTituloSeguros.setBackground(Color.LIGHT_GRAY);

            panelEste.add(lbTituloSeguros);

            JPanel panelTituloTabla = new JPanel();
            panelTituloTabla.setLayout(new GridLayout(1,3));

            JLabel lbTituloNombre = new JLabel("Nombre");
            lbTituloNombre.setFont(new Font("Dialog", Font.PLAIN, 20));
            lbTituloNombre.setHorizontalAlignment(JLabel.CENTER);
            lbTituloNombre.setOpaque(true);
            lbTituloNombre.setBackground(Color.WHITE);
            lbTituloNombre.setBorder(new LineBorder(Color.BLACK));
            panelTituloTabla.add(lbTituloNombre);

            JLabel lbTituloDescripcion = new JLabel("Descripcion");
            lbTituloDescripcion.setFont(new Font("Dialog", Font.PLAIN, 20));
            lbTituloDescripcion.setHorizontalAlignment(JLabel.CENTER);
            lbTituloDescripcion.setOpaque(true);
            lbTituloDescripcion.setBackground(Color.WHITE);
            lbTituloDescripcion.setBorder(new LineBorder(Color.BLACK));
            panelTituloTabla.add(lbTituloDescripcion);

            JLabel lbTituloPrecio = new JLabel("Precio/Dia");
            lbTituloPrecio.setFont(new Font("Dialog", Font.PLAIN, 20));
            lbTituloPrecio.setHorizontalAlignment(JLabel.CENTER);
            lbTituloPrecio.setOpaque(true);
            lbTituloPrecio.setBackground(Color.WHITE);
            lbTituloPrecio.setBorder(new LineBorder(Color.BLACK));
            panelTituloTabla.add(lbTituloPrecio);

            panelEste.add(panelTituloTabla);

            for (int i = 1; i < numeroDeSeguros; i++) {

                JPanel panelSeguro = new JPanel();
                panelSeguro.setLayout(new GridLayout(1,3));

                JPanel panelNombre = new JPanel();
                JCheckBox checkBoxNombre = new JCheckBox(listaSegurosNombre.get(i));
                checkBoxNombre.setFont(new Font("Dialog", Font.PLAIN, 18));
                checkBoxNombre.addActionListener(e -> {
                    JCheckBox source = (JCheckBox) e.getSource();
                    String item = source.getText();

                    if (source.isSelected()) {
                        segurosSeleccionados.add(item);
                    } else {
                        segurosSeleccionados.remove(item);
                    }

                    System.out.println(segurosSeleccionados);
                });
                panelNombre.add(checkBoxNombre);
                panelNombre.setOpaque(true);
                panelNombre.setBackground(Color.WHITE);
                panelNombre.setBorder(new LineBorder(Color.BLACK));
                panelSeguro.add(panelNombre);

                JTextArea textAreaDescripcion = new JTextArea(listaSegurosDescripcion.get(i));
                textAreaDescripcion.setFont(new Font("Dialog", Font.PLAIN, 18));
                textAreaDescripcion.setEditable(false);
                textAreaDescripcion.setBackground(Color.WHITE);
                textAreaDescripcion.setBorder(new LineBorder(Color.BLACK));
                JScrollPane scrollPaneDescripcion = new JScrollPane(textAreaDescripcion);
                panelSeguro.add(scrollPaneDescripcion);

                JLabel lbPrecio = new JLabel(listaSegurosPrecio.get(i));
                lbPrecio.setFont(new Font("Dialog", Font.PLAIN, 18));
                lbPrecio.setHorizontalAlignment(JLabel.CENTER);
                lbPrecio.setVerticalAlignment(JLabel.TOP);
                lbPrecio.setOpaque(true);
                lbPrecio.setBackground(Color.WHITE);
                lbPrecio.setBorder(new LineBorder(Color.BLACK));
                panelSeguro.add(lbPrecio);

                panelEste.add(panelSeguro);
            }

            add(panelEste, BorderLayout.EAST);

            // El panel central demas cosas

            JPanel panelCentral = new JPanel();
            panelCentral.setLayout(new GridLayout(9,1));

            JLabel lbCategoria = new JLabel();
            lbCategoria.setLayout(new GridLayout(1,2));
            JLabel labelTextoCategoria = new JLabel("Categoria: ");
            labelTextoCategoria.setFont(new Font("Dialog", Font.PLAIN, 20));
            labelTextoCategoria.setHorizontalAlignment(JLabel.CENTER);
            lbCategoria.add(labelTextoCategoria);

            JComboBox<String> comboBoxCategoria = new JComboBox<>();
            comboBoxCategoria.setFont(new Font("Dialog", Font.PLAIN, 20));
            for (String key : vp.catalogo.getHashCategorias().keySet()) {
                comboBoxCategoria.addItem(key);
            }
            comboBoxCategoria.setSelectedItem(null);
            comboBoxCategoria.addActionListener(e -> {
                String categoriaSeleccionada = (String) comboBoxCategoria.getSelectedItem();
                System.out.println(categoriaSeleccionada);
            });
            lbCategoria.add(comboBoxCategoria);
            panelCentral.add(lbCategoria);

            JLabel lbSedeRecoger = new JLabel();
            lbSedeRecoger.setLayout(new GridLayout(1,2));
            JLabel lbTextoSedeRecoger = new JLabel("Sede a recoger: ");
            lbTextoSedeRecoger.setFont(new Font("Dialog", Font.PLAIN, 20));
            lbTextoSedeRecoger.setHorizontalAlignment(JLabel.CENTER);
            lbSedeRecoger.add(lbTextoSedeRecoger);

            JComboBox<String> comboBoxSedeRecoger = new JComboBox<>();
            comboBoxSedeRecoger.setFont(new Font("Dialog", Font.PLAIN, 20));
            for (String key : vp.hashSedes.keySet()) {
                comboBoxSedeRecoger.addItem(key);
            }
            comboBoxSedeRecoger.setSelectedItem(null);
            comboBoxSedeRecoger.addActionListener(e -> {
                String sedeRecogerSelecionada = (String) comboBoxSedeRecoger.getSelectedItem();
                System.out.println("Sede a recoger: " + sedeRecogerSelecionada);
            });

            lbSedeRecoger.add(comboBoxSedeRecoger);
            panelCentral.add(lbSedeRecoger);

            JLabel lbSedeEntregar = new JLabel();
            lbSedeEntregar.setLayout(new GridLayout(1,2));
            JLabel lbTextoSedeEntregar = new JLabel("Sede a entregar: ");
            lbTextoSedeEntregar.setFont(new Font("Dialog", Font.PLAIN, 20));
            lbTextoSedeEntregar.setHorizontalAlignment(JLabel.CENTER);
            lbSedeEntregar.add(lbTextoSedeEntregar);

            JComboBox<String> comboBoxSedeEntregar = new JComboBox<>();
            comboBoxSedeEntregar.setFont(new Font("Dialog", Font.PLAIN, 20));
            for (String key : vp.hashSedes.keySet()) {
                comboBoxSedeEntregar.addItem(key);
            }
            comboBoxSedeEntregar.setSelectedItem(null);
            comboBoxSedeEntregar.addActionListener(e -> {
                String sedeEntregarSelecionada = (String) comboBoxSedeEntregar.getSelectedItem();
                System.out.println("Sede de entrega: " + sedeEntregarSelecionada);
            });

            lbSedeEntregar.add(comboBoxSedeEntregar);
            panelCentral.add(lbSedeEntregar);

            JLabel lbFechaRecoger = generadorLabelInput("Fecha a recoger (MM/DD/AAAA): ");
            panelCentral.add(lbFechaRecoger);

            JLabel lbHoraRecoger = generadorLabelInput("Hora a recoger (HH:MM): ");
            panelCentral.add(lbHoraRecoger);

            JLabel lbFechaEntregar = generadorLabelInput("Fecha a entregar (MM/DD/AAAA): ");
            panelCentral.add(lbFechaEntregar);

            JLabel lbHoraEntregar = generadorLabelInput("Hora a entregar (HH:MM): ");
            panelCentral.add(lbHoraEntregar);

            JLabel lbOtrosConductores = generadorLabelInput("Conductores extra (0-3): ");
            panelCentral.add(lbOtrosConductores);

            JButton btnCrearReserva = new JButton("Crear reserva");
            btnCrearReserva.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnCrearReserva.addActionListener(e -> {
                String categoria = (String) comboBoxCategoria.getSelectedItem();
                String sedeRecoger = (String) comboBoxSedeRecoger.getSelectedItem();
                String sedeEntregar = (String) comboBoxSedeEntregar.getSelectedItem();
                String fechaRecoger = ((JTextField) lbFechaRecoger.getComponent(1)).getText();
                String horaRecoger = ((JTextField) lbHoraRecoger.getComponent(1)).getText();
                String fechaEntregar = ((JTextField) lbFechaEntregar.getComponent(1)).getText();
                String horaEntregar = ((JTextField) lbHoraEntregar.getComponent(1)).getText();
                String otrosConductoresS = ((JTextField) lbOtrosConductores.getComponent(1)).getText();
                int otrosConductores = Integer.parseInt(otrosConductoresS);
                if (categoria == null || sedeRecoger == null || sedeEntregar == null || fechaRecoger.length() < 3 || horaRecoger.length() < 3 || fechaEntregar.length() < 3 || horaEntregar.length() < 3 || otrosConductores > 3) {
                    comboBoxCategoria.setSelectedItem(null);
                    comboBoxSedeRecoger.setSelectedItem(null);
                    comboBoxSedeEntregar.setSelectedItem(null);
                    ((JTextField) lbFechaRecoger.getComponent(1)).setText("");
                    ((JTextField) lbHoraRecoger.getComponent(1)).setText("");
                    ((JTextField) lbFechaEntregar.getComponent(1)).setText("");
                    ((JTextField) lbHoraEntregar.getComponent(1)).setText("");
                    ((JTextField) lbOtrosConductores.getComponent(1)).setText("");

                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al crear reserva");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al crear reserva!!!");
                } else if (((Cliente) vp.usu).getTieneReserva()) {
                    ventanaPrincipal.cambiarPagina(14);
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario ya tiene una reserva");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("El usuario ya tiene una reserva!!!");
                } else if (((Cliente) vp.usu).getTieneTarjetaBloqueada()) {
                    comboBoxCategoria.setSelectedItem(null);
                    comboBoxSedeRecoger.setSelectedItem(null);
                    comboBoxSedeEntregar.setSelectedItem(null);
                    ((JTextField) lbFechaRecoger.getComponent(1)).setText("");
                    ((JTextField) lbHoraRecoger.getComponent(1)).setText("");
                    ((JTextField) lbFechaEntregar.getComponent(1)).setText("");
                    ((JTextField) lbHoraEntregar.getComponent(1)).setText("");
                    ((JTextField) lbOtrosConductores.getComponent(1)).setText("");

                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario tiene la tarjeta bloqueada");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("El usuario tiene la tarjeta bloqueada!!!");
                } else {
                    String resultado = ((Cliente) vp.usu).reservarVehiculo(vp.hashReservas, vp.catalogo, categoria, sedeRecoger, fechaRecoger, horaRecoger, sedeEntregar, fechaEntregar, horaEntregar, otrosConductores, segurosSeleccionados);
                    if (resultado.equals("No hay vehiculos disponibles en este momento para esta categoria") || resultado == null || resultado.equals("")) {
                        comboBoxCategoria.setSelectedItem(null);
                        comboBoxSedeRecoger.setSelectedItem(null);
                        comboBoxSedeEntregar.setSelectedItem(null);
                        ((JTextField) lbFechaRecoger.getComponent(1)).setText("");
                        ((JTextField) lbHoraRecoger.getComponent(1)).setText("");
                        ((JTextField) lbFechaEntregar.getComponent(1)).setText("");
                        ((JTextField) lbHoraEntregar.getComponent(1)).setText("");
                        ((JTextField) lbOtrosConductores.getComponent(1)).setText("");

                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), resultado);
                        dialogError.setSize(700,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println(resultado);
                    } else {
                        ventanaPrincipal.cambiarPagina(14);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Reserva creada con exito");
                        dialogOK.setSize(750,700);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());

                        dialogOK.setLayout(new GridLayout(1,1));
                        JTextArea textArea = new JTextArea("\nDetalles de la reserva a continuación: \n\n"+resultado);
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        dialogOK.add(scrollPane);

                        dialogOK.setVisible(true);
                        System.out.println(resultado);
                    }
                }
            });
            panelCentral.add(btnCrearReserva);

            add(panelCentral, BorderLayout.CENTER);
        } else if (pagina == 142) {
            setLayout(new GridLayout(5,1));

            JLabel lbSedeEntregar = new JLabel();
            lbSedeEntregar.setLayout(new GridLayout(1,2));
            JLabel lbTextoSedeEntregar = new JLabel("Sede a entregar: ");
            lbTextoSedeEntregar.setFont(new Font("Dialog", Font.PLAIN, 20));
            lbTextoSedeEntregar.setHorizontalAlignment(JLabel.CENTER);
            lbSedeEntregar.add(lbTextoSedeEntregar);

            JComboBox<String> comboBoxSedeEntregar = new JComboBox<>();
            comboBoxSedeEntregar.setFont(new Font("Dialog", Font.PLAIN, 20));
            for (String key : vp.hashSedes.keySet()) {
                comboBoxSedeEntregar.addItem(key);
            }
            comboBoxSedeEntregar.setSelectedItem(null);
            comboBoxSedeEntregar.addActionListener(e -> {
                String sedeEntregarSelecionada = (String) comboBoxSedeEntregar.getSelectedItem();
                System.out.println("Sede de entrega: " + sedeEntregarSelecionada);
            });

            lbSedeEntregar.add(comboBoxSedeEntregar);
            add(lbSedeEntregar);

            JLabel lbFechaEntregar = generadorLabelInput("Fecha a entregar (MM/DD/AAAA): ");
            add(lbFechaEntregar);

            JLabel lbHoraEntregar = generadorLabelInput("Hora a entregar (HH:MM): ");
            add(lbHoraEntregar);

            JLabel lbOtrosConductores = generadorLabelInput("Conductores extra (0-3): ");
            add(lbOtrosConductores);

            JButton btnEditarReserva = new JButton("Editar reserva");
            btnEditarReserva.setFont(new Font("Dialog", Font.PLAIN, 24));
            btnEditarReserva.addActionListener(e -> {
                String sedeEntregar = (String) comboBoxSedeEntregar.getSelectedItem();
                String fechaEntregar = ((JTextField) lbFechaEntregar.getComponent(1)).getText();
                String horaEntregar = ((JTextField) lbHoraEntregar.getComponent(1)).getText();
                String otrosConductoresS = ((JTextField) lbOtrosConductores.getComponent(1)).getText();
                int otrosConductores = Integer.parseInt(otrosConductoresS);

                if (sedeEntregar == null || fechaEntregar.length() < 3 || horaEntregar.length() < 3 || otrosConductores > 3) {
                    comboBoxSedeEntregar.setSelectedItem(null);
                    ((JTextField) lbFechaEntregar.getComponent(1)).setText("");
                    ((JTextField) lbHoraEntregar.getComponent(1)).setText("");
                    ((JTextField) lbOtrosConductores.getComponent(1)).setText("");

                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Error al editar reserva");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("Error al editar reserva!!!");
                } else if (!((Cliente) vp.usu).getTieneReserva()) {
                    ventanaPrincipal.cambiarPagina(14);
                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario no tiene una reserva");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("El usuario no tiene una reserva!!!");
                } else if (((Cliente) vp.usu).getTieneTarjetaBloqueada()) {
                    comboBoxSedeEntregar.setSelectedItem(null);
                    ((JTextField) lbFechaEntregar.getComponent(1)).setText("");
                    ((JTextField) lbHoraEntregar.getComponent(1)).setText("");
                    ((JTextField) lbOtrosConductores.getComponent(1)).setText("");

                    JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "El usuario tiene la tarjeta bloqueada");
                    dialogError.setSize(300,30);
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setVisible(true);
                    System.out.println("El usuario tiene la tarjeta bloqueada!!!");
                } else {
                    String resultado = ((Cliente) vp.usu).alterarReserva(vp.hashReservas, ((Cliente) vp.usu).getIdReserva(), sedeEntregar, fechaEntregar, horaEntregar, otrosConductores, vp.catalogo);
                    if (resultado.equals("No hay vehiculos disponibles en este momento para esta categoria") || resultado == null || resultado.equals("")) {
                        comboBoxSedeEntregar.setSelectedItem(null);
                        ((JTextField) lbFechaEntregar.getComponent(1)).setText("");
                        ((JTextField) lbHoraEntregar.getComponent(1)).setText("");
                        ((JTextField) lbOtrosConductores.getComponent(1)).setText("");

                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), resultado);
                        dialogError.setSize(700,30);
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setVisible(true);
                        System.out.println(resultado);
                    } else {
                        ventanaPrincipal.cambiarPagina(14);
                        JDialog dialogOK = new JDialog((JFrame) getTopLevelAncestor(), "Reserva editada con exito");
                        dialogOK.setSize(750,700);
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());

                        dialogOK.setLayout(new GridLayout(1,1));
                        JTextArea textArea = new JTextArea("\nDetalles de la reserva a continuación: \n\n"+resultado);
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        dialogOK.add(scrollPane);

                        dialogOK.setVisible(true);
                        System.out.println(resultado);
                    }
                }
            });
            add(btnEditarReserva);


        } else if (pagina == 143) {
            setLayout(new GridLayout(1,1));

            String reservaActual = ((Cliente) vp.usu).getResumenReservaActual(vp.hashReservas, vp.catalogo);

            JTextArea resumenReservaActual = new JTextArea(reservaActual);
            resumenReservaActual.setFont(new Font("Dialog", Font.PLAIN, 20));
            resumenReservaActual.setEditable(false);
            resumenReservaActual.setBackground(Color.WHITE);
            resumenReservaActual.setBorder(new LineBorder(Color.BLACK));
            
            JScrollPane scrollPane = new JScrollPane(resumenReservaActual);
            add(scrollPane);

        }
    }

    private JLabel generadorLabelInput(String texto) {
        JLabel label = new JLabel();
        label.setLayout(new GridLayout(1,2));
        JLabel labelTexto = new JLabel(texto);
        labelTexto.setFont(new Font("Dialog", Font.PLAIN, 20));
        labelTexto.setHorizontalAlignment(JLabel.CENTER);
        label.add(labelTexto);
        JTextField textField = new JTextField(8);
        textField.setFont(new Font("Dialog", Font.PLAIN, 20));
        label.add(textField);
        return label;
    }

    private JLabel generadorLabelTarifasAntiguas(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Dialog", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
}
