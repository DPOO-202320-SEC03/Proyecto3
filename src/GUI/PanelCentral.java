package GUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Inventario.Categoria;
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
    VentanaPrincipal vp;
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
                if (nombreCategoria.length() > 3 && rangoCategoria > 3 && rangoCategoria < 11) {
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
                if (placa.length() > 3 && marca.length() > 3 && modelo.length() > 3 && color.length() > 3 && tipoDeTransmision.length() > 3 && tipoDeDireccion.length() > 3 && tipoDeCombustible.length() > 3 && numeroDePasajeros.length() > 3 && nombreSedeActual.length() > 3 && nombreCategoria.length() > 3 && fechaDisponibilidad.length() > 3) {
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
            //TODO: PONTO aca tienes que hacer lo que hace que muestre la grafica no olvides que en vp estan todos los hash es solo hacer vp.hash
        } else if (pagina == 12) {
            //
        } else if (pagina == 13) {
            //
        } else if (pagina == 14) {
            //
        }
    }

    public JLabel generadorLabelInput(String texto) {
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

    public JLabel generadorLabelTarifasAntiguas(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Dialog", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
}
