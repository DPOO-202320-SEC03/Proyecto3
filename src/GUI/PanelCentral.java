package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
            lbUsuario.add(usu);
            JTextField tfUsuario = new JTextField(8);
            tfUsuario.setFont(new Font("Dialog", Font.PLAIN, 24));
            lbUsuario.add(tfUsuario);
            add(lbUsuario);

            JLabel lbContrasena = new JLabel();
            lbContrasena.setLayout(new GridLayout(1,2));
            JLabel contra = new JLabel("Contraseña: ");
            contra.setFont(new Font("Dialog", Font.PLAIN, 24));
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
                            dialogError.setLocationRelativeTo(getTopLevelAncestor());
                            dialogError.setSize(300,30);
                            dialogError.setVisible(true);
                            System.out.println("Contraseña incorrecta!!!");
                        } 
                    } else {
                        tfUsuario.setText("");
                        tfContrasena.setText("");
                        JDialog dialogError = new JDialog((JFrame) getTopLevelAncestor(), "Usuario no encontrado");
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setSize(300,30);
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
                        dialogOK.setLocationRelativeTo(getTopLevelAncestor());
                        dialogOK.setSize(300,30);
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
                        dialogError.setLocationRelativeTo(getTopLevelAncestor());
                        dialogError.setSize(300,30);
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
                    dialogError.setLocationRelativeTo(getTopLevelAncestor());
                    dialogError.setSize(300,30);
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
        }
    }

    public JLabel generadorLabelInput(String texto) {
        JLabel label = new JLabel();
        label.setLayout(new GridLayout(1,2));
        JLabel labelTexto = new JLabel(texto);
        labelTexto.setFont(new Font("Dialog", Font.PLAIN, 16));
        label.add(labelTexto);
        JTextField textField = new JTextField(8);
        textField.setFont(new Font("Dialog", Font.PLAIN, 16));
        label.add(textField);
        return label;
    }
}
