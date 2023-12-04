package GUI.AppCliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
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

import Inventario.Seguro;
import SistemaLogin.Cliente;
import SistemaLogin.DatosClienteLicencia;
import SistemaLogin.DatosClienteTarjeta;
import SistemaLogin.Usuario;

public class PanelCentralCliente extends JPanel {
    private VentanaPrincipalCliente vp;
    public PanelCentralCliente(VentanaPrincipalCliente ventanaPrincipal, int pagina) {
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
                            if (nivelDeAcceso == 0) {
                                Cliente cliente = (Cliente) usuario;
                                ventanaPrincipal.setUsuarioActual(cliente);
                                ventanaPrincipal.cambiarPagina(14);
                            } else {
                                tfUsuario.setText("");
                                tfContrasena.setText("");
                                JDialog errDialog = new JDialog((JFrame) getTopLevelAncestor(), "Usuario no válido");
                                errDialog.setSize(300,30);
                                errDialog.setLocationRelativeTo(getTopLevelAncestor());
                                errDialog.setVisible(true);
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
        } else if (pagina == 14) {
            setLayout(new GridLayout(7,2));

            JButton btnCrearReserva = new JButton("Crear reserva");
            btnCrearReserva.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnCrearReserva.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(141);
            });
            add(btnCrearReserva);

            JButton btnEditarReserva = new JButton("Consultar disponibilidad");
            btnEditarReserva.setFont(new Font("Dialog", Font.PLAIN, 16));
            btnEditarReserva.addActionListener(e -> {
                ventanaPrincipal.cambiarPagina(142);
            });
            add(btnEditarReserva);

            for (int i = 0; i < 12; i++) {
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

                textAreaDescripcion.setLineWrap(true);
                textAreaDescripcion.setWrapStyleWord(true); 
                textAreaDescripcion.setColumns(15);

                textAreaDescripcion.setFont(new Font("Dialog", Font.PLAIN, 14));
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
}
