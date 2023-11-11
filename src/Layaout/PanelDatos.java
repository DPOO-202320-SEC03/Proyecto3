package Layaout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDatos extends JPanel{
    private VentanaPrincipal principal;

    public PanelDatos(VentanaPrincipal principal, int pagina) {
        this.principal = principal;
        // Pagina 1: Registro
        if(pagina == 1){
            // Agregar un grid de 1 fila y 2 columnas
            setLayout(new GridLayout(1, 3));
            // Agregar un panel de 2 filas y 1 columna a la primera columna
            JPanel panel = new JPanel(new GridLayout(2, 1));
            add(panel);
            // Agregar texto vacio a la primera fila
            panel.add(new JLabel());
            // Agregar imagen a la segunda fila
            String workingDir = System.getProperty("user.dir");
            String filePath = workingDir + File.separator + "src" + File.separator + "Layaout" +
            File.separator + "imagenes" + File.separator
                    + "fondo_registro.jpg";
            try {
                BufferedImage image = ImageIO.read(new File(filePath));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                panel.add(picLabel);
            } catch (IOException e) {
                System.out.println("Error al leer el archivo de imagen: " + e.getMessage());
                return;
            }

            // Agregar panel de 18 filas y 1 columna a la segunda columna
            JPanel panel2 = new JPanel(new GridLayout(1, 3));
            add(panel2);

            JPanel panel2_1 = new JPanel(new GridLayout(18, 1));
            panel2.add(panel2_1);

            // Agregar labels y cuadro de textos
            panel2_1.add(new JLabel("Datos personales:"));
            panel2_1.add(new JLabel("Nombre: "));

            JTextField txtField_nombre = new JTextField();
            panel2_1.add(txtField_nombre);

            panel2_1.add(new JLabel("Apellido: "));

            JTextField txtField_apellido = new JTextField();
            panel2_1.add(txtField_apellido);

            panel2_1.add(new JLabel("Celular: "));

            JTextField txtField_celular = new JTextField();
            panel2_1.add(txtField_celular);

            panel2_1.add(new JLabel("Correo: "));

            JTextField txtField_correo = new JTextField();
            panel2_1.add(txtField_correo);

            panel2_1.add(new JLabel("Datos licencia:"));
            panel2_1.add(new JLabel("Numero: "));

            JTextField txtField_numero_lcn = new JTextField();
            panel2_1.add(txtField_numero_lcn);

            panel2_1.add(new JLabel("Pais expedicion: "));

            JTextField txtField_pais_exp_lcn = new JTextField();
            panel2_1.add(txtField_pais_exp_lcn);

            panel2_1.add(new JLabel("Fecha de vencimiento: "));

            JTextField txtField_fecha_ven_lcn = new JTextField();
            panel2_1.add(txtField_fecha_ven_lcn);

            panel2_1.add(new JLabel("Foto de la licencia:"));
            //TODO: hacer que puedan poner una imagen
            panel2_1.add(new JLabel());

            JPanel panel2_2 = new JPanel(new GridLayout(18, 1));
            panel2.add(panel2_2);

            panel2_2.add(new JLabel("Datos tarjeta:"));
            panel2_2.add(new JLabel("Numero: "));

            JTextField txtField_num_trj = new JTextField();
            panel2_2.add(txtField_num_trj);

            panel2_2.add(new JLabel("Fecha de vencimiento: "));

            JTextField txtField_fch_ven_trj = new JTextField();
            panel2_2.add(txtField_fch_ven_trj);

            panel2_2.add(new JLabel("Titular: "));

            JTextField txtField_titular_trj = new JTextField();
            panel2_2.add(txtField_titular_trj);

            panel2_2.add(new JLabel("Marcar internacional: "));

            JTextField txtField_mrc_inter_trj = new JTextField();
            panel2_2.add(txtField_mrc_inter_trj);

            panel2_2.add(new JLabel("CCV: "));

            JTextField txtField_ccv = new JTextField();
            panel2_2.add(txtField_ccv);

            panel2_2.add(new JLabel("Datos usuario:"));
            panel2_2.add(new JLabel("Usuario: "));

            JTextField txtField_usuario = new JTextField();
            panel2_2.add(txtField_usuario);

            panel2_2.add(new JLabel("Contraseña: "));

            JTextField txtField_contraseña = new JTextField();
            panel2_2.add(txtField_contraseña);

            JPanel panel3 = new JPanel(new GridLayout(18, 1));
            add(panel3);

            agregarEtiquetas(panel3, 17);

            // boton de enviar
            // TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                JTextField[] campos = {
                    txtField_nombre, txtField_apellido, txtField_celular, txtField_correo, 
                    txtField_numero_lcn, txtField_pais_exp_lcn, txtField_fecha_ven_lcn, 
                    txtField_num_trj, txtField_fch_ven_trj, txtField_titular_trj, 
                    txtField_mrc_inter_trj, txtField_ccv, txtField_usuario, txtField_contraseña
                };
            
                List<String> valores = new ArrayList<>();
                for (JTextField campo : campos) {
                    valores.add(campo.getText());
                }
            
                JOptionPane.showMessageDialog(this, "Valores: " + valores);
            });
            // esto es otra forma de hacer lo de arriba pero valor por valor... lo dejo por si sirve si no sirve eliminenlo
            /*btn_enviar.addActionListener(e -> {
                List<String> valores = new ArrayList<>();
                valores.add(txtField_nombre.getText());
                valores.add(txtField_apellido.getText());
                valores.add(txtField_celular.getText());
                valores.add(txtField_correo.getText());
                valores.add(txtField_numero_lcn.getText());
                valores.add(txtField_pais_exp_lcn.getText());
                valores.add(txtField_fecha_ven_lcn.getText());
                valores.add(txtField_num_trj.getText());
                valores.add(txtField_fch_ven_trj.getText());
                valores.add(txtField_titular_trj.getText());
                valores.add(txtField_mrc_inter_trj.getText());
                valores.add(txtField_ccv.getText());
                valores.add(txtField_usuario.getText());
                valores.add(txtField_contraseña.getText());
                JOptionPane.showMessageDialog(this, "Valores: " + valores);
            }); */
            panel3.add(btn_enviar);
        }
        // Pagina 2: Administrador
        else if(pagina == 2){
            // Agregar un grid de 1 fila y 2 columnas
            JPanel panel = new JPanel(new GridLayout(1, 2));
            // Agregar un panel de 8 filas y 1 columna a la primera columna 
            JPanel panel1 = new JPanel(new GridLayout(8, 1));
            panel.add(panel1);
            panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

            // Agregar cada una  de las opciones, y un boton al lado que lleva a su respectiva pagina
            JPanel panel1_1 = new JPanel(new GridLayout(1, 2));
            panel1_1.add(new JLabel("Crear administrador local y eliminar usuario"));
            JButton boton6 = new JButton("");
            boton6.addActionListener(e -> {
                cambiar_pagina(6);
            });
            panel1_1.add(boton6);
            panel1_1.setPreferredSize(new Dimension(1000, 70));
            panel1.add(panel1_1);

            JPanel panel1_2 = new JPanel(new GridLayout(1, 2));
            panel1_2.add(new JLabel("Crear categoria o actualizar precios por temporada"));
            JButton boton7 = new JButton("");
            boton7.addActionListener(e -> {
                cambiar_pagina(7);
            });
            panel1_2.add(boton7);
            panel1_2.setPreferredSize(new Dimension(1000, 70));
            panel1.add(panel1_2);

            JPanel panel1_3 = new JPanel(new GridLayout(1, 2));
            panel1_3.add(new JLabel("Crear sede"));
            JButton boton8 = new JButton("");
            boton8.addActionListener(e -> {
                cambiar_pagina(8);
            });
            panel1_3.add(boton8);
            panel1_3.setPreferredSize(new Dimension(1000, 70));
            panel1.add(panel1_3);

            JPanel panel1_4 = new JPanel(new GridLayout(1, 2));
            panel1_4.add(new JLabel("Crear seguro"));
            JButton boton9 = new JButton("");
            boton9.addActionListener(e -> {
                cambiar_pagina(9);
            });
            panel1_4.add(boton9);
            panel1_4.setPreferredSize(new Dimension(1000, 70));
            panel1.add(panel1_4);

            JPanel panel1_5 = new JPanel(new GridLayout(1, 2));
            panel1_5.add(new JLabel("Crear o editar tarifas globales"));
            JButton boton10 = new JButton("");
            boton10.addActionListener(e -> {
                cambiar_pagina(10);
            });
            panel1_5.add(boton10);
            panel1_5.setPreferredSize(new Dimension(1000, 70));
            panel1.add(panel1_5);

            JPanel panel1_6 = new JPanel(new GridLayout(1, 2));
            panel1_6.add(new JLabel("Crear, eliminar o ver el estado de un vehiculo"));
            JButton boton11 = new JButton("");
            boton11.addActionListener(e -> {
                cambiar_pagina(11);
            });
            panel1_6.add(boton11);
            panel1_6.setPreferredSize(new Dimension(1000, 70));
            panel1.add(panel1_6);

            JPanel panel1_7 = new JPanel(new GridLayout(1, 2));
            panel1_7.add(new JLabel("Trasladar vehiculo a otra sede"));
            JButton boton12 = new JButton("");
            boton12.addActionListener(e -> {
                cambiar_pagina(12);
            });
            panel1_7.add(boton12);
            panel1_7.setPreferredSize(new Dimension(1000, 70));
            panel1.add(panel1_7);

            JPanel panel1_8 = new JPanel(new GridLayout(1, 2));
            panel1_8.add(new JLabel("Consultar reserva por ID"));
            JButton boton13 = new JButton("");
            boton13.addActionListener(e -> {
                cambiar_pagina(13);
            });
            panel1_8.add(boton13);
            panel1_8.setPreferredSize(new Dimension(1000, 70));
            panel1.add(panel1_8);

            add(panel);
            // esto es para cambiar el tamaño de las letras de los jlabel de la primera columna
            for (Component comp : panel1.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        // Pagina 3: admin local
        else if(pagina == 3){
            // Agregar un grid de 13 fila y 1 columnas
            JPanel panel = new JPanel(new GridLayout(13, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una  de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Ingrese un usuario"));
            JTextField textField_usuario = new JTextField();
            textField_usuario.setName("textField_usuario");
            panel.add(textField_usuario);

            panel.add(new JLabel("Ingrese una contraseña"));
            JTextField textField_contraseña = new JTextField();
            textField_contraseña.setName("textField_contraseña");
            panel.add(textField_contraseña);

            panel.add(new JLabel("Ingrese los nombres del empleado"));
            JTextField textField_nom_emp = new JTextField();
            textField_nom_emp.setName("textField_nom_emp");
            panel.add(textField_nom_emp);

            panel.add(new JLabel("Ingrese los apellidos del empleado"));
            JTextField textField_ape_emp = new JTextField();
            textField_ape_emp.setName("textField_ape_emp");
            panel.add(textField_ape_emp);

            panel.add(new JLabel("Ingrese el celular del empleado"));
            JTextField textField_cel_emp = new JTextField();
            textField_cel_emp.setName("textField_cel_emp");
            panel.add(textField_cel_emp);

            panel.add(new JLabel("Ingrese el correo del empleado"));
            JTextField textField_correo_emp = new JTextField();
            textField_correo_emp.setName("textField_correo_emp");
            panel.add(textField_correo_emp);

            // boton de enviar que agarra todo lo escrito en los campos de texto
            //TODO: hacer las restricciones respectivas y mensajes de error
            JPanel panel_1 = new JPanel(new GridLayout(1, 2));
            panel_1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> valores = new ArrayList<>();
                for (Component comp : panel.getComponents()) {
                    if (comp instanceof JTextField) {
                        JTextField textField = (JTextField) comp;
                        valores.add(textField.getText());
                    }
                }
                System.out.println(valores); 
            });
            panel_1.add(btn_enviar);

            // boton de eliminar que agarra el usuario escrito en el campo de texto de usuario
            // TODO: hacer los mensajes de error
            JButton btn_eliminar = new JButton("Eliminar");
            btn_eliminar.addActionListener(e -> {
                String usuario_eli = (textField_usuario.getText());
            });
            panel_1.add(btn_eliminar);

            panel.add(panel_1);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(700, 50));
            }
        }
        // Pagina 4: empleado
        else if(pagina == 4){
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        JPanel panel1_1 = new JPanel(new GridLayout(1, 2));
        panel1_1.add(new JLabel("Alquilar vehículo"));
        JButton btn_14 = new JButton();
        btn_14.addActionListener(e -> {
            cambiar_pagina(14);
        });
        panel1_1.add(btn_14);
        panel.add(panel1_1);

        JPanel panel1_2 = new JPanel(new GridLayout(1, 2));
        panel1_2.add(new JLabel("Agregar licencia de otros conductores"));
        JButton btn_15 = new JButton();
        btn_15.addActionListener(e -> {
            cambiar_pagina(15);
        });
        panel1_2.add(btn_15);
        panel.add(panel1_2);

        JPanel panel1_3 = new JPanel(new GridLayout(1, 2));
        panel1_3.add(new JLabel("Recibir vehículo"));
        JButton btn_16 = new JButton();
        btn_16.addActionListener(e -> {
            cambiar_pagina(16);
        });
        panel1_3.add(btn_16);
        panel.add(panel1_3);

        JPanel panel1_4 = new JPanel(new GridLayout(1, 2));
        panel1_4.add(new JLabel("Listo para alquiler"));
        JButton btn_17 = new JButton();
        btn_17.addActionListener(e -> {
            cambiar_pagina(17);
        });
        panel1_4.add(btn_17);
        panel.add(panel1_4);

        add(panel);
        for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(1000, 120));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        // Pagina 5: cliente
        else if(pagina == 5){
            // Agregar un grid de 3 fila y 1 columnas
            JPanel panel = new JPanel(new GridLayout(3, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una  de las opciones, y al lado un boton que lleva a su respectiva pagina
            JPanel panel1_1 = new JPanel(new GridLayout(1, 2));
            panel1_1.add(new JLabel("Reservar vehículo"));
            JButton btn_18 = new JButton();
            btn_18.addActionListener(e -> {
                cambiar_pagina(18);
            });
            panel1_1.add(btn_18);
            panel.add(panel1_1);

            JPanel panel1_2 = new JPanel(new GridLayout(1, 2));
            panel1_2.add(new JLabel("Editar reserva"));
            JButton btn_19 = new JButton();
            btn_19.addActionListener(e -> {
                cambiar_pagina(19);
            });
            panel1_2.add(btn_19);
            panel.add(panel1_2);

            JPanel panel1_3 = new JPanel(new GridLayout(1, 2));
            panel1_3.add(new JLabel("Reserva actual"));
            JButton btn_20 = new JButton();
            btn_20.addActionListener(e -> {
                cambiar_pagina(20);
            });
            panel1_3.add(btn_20);
            panel.add(panel1_3);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(1000, 120));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //pagina 6: crear administrador local y eliminar usuario
        else if(pagina == 6){
            // Agregar un grid de 15 fila y 1 columnas
            JPanel panel = new JPanel(new GridLayout(15, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una  de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Ingrese un usuario para el administrador local"));
            JTextField txtField_usuario = new JTextField();
            panel.add(txtField_usuario);

            panel.add(new JLabel("Ingrese una contraseña para el administrador local"));
            JTextField txtField_contraseña = new JTextField();
            panel.add(txtField_contraseña);

            panel.add(new JLabel("Ingrese una sede para el administrador local"));
            // TODO: carlos aqui lo puse como texto, pero puedes cambiarlo para que sea una lista desplegable
            // aunque sera mas dificil... si hay tiempo de sobre puedo hacerlo yo.
            JTextField txtField_sede = new JTextField();
            panel.add(txtField_sede);

            panel.add(new JLabel("Ingresar los nombres del administrador local"));
            JTextField txtField_nom = new JTextField();
            panel.add(txtField_nom);

            panel.add(new JLabel("Ingresar los apellidos del administrador local"));
            JTextField txtField_ape = new JTextField();
            panel.add(txtField_ape);

            panel.add(new JLabel("Ingresar el celular del administrador local"));
            JTextField txtField_cel = new JTextField();
            panel.add(txtField_cel);

            panel.add(new JLabel("Ingresar el correo del administrador local"));
            JTextField txtField_correo = new JTextField();
            panel.add(txtField_correo);

            //TODO. bueno los mismo de lo demas mensajes de errores y restricciones.
            JPanel panel2 = new JPanel(new GridLayout(1, 2));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_usuario.getText());
                lista.add(txtField_contraseña.getText());
                lista.add(txtField_sede.getText());
                lista.add(txtField_nom.getText());
                lista.add(txtField_ape.getText());
                lista.add(txtField_cel.getText());
                lista.add(txtField_correo.getText());
                System.out.println(lista);
            });

            panel2.add(btn_enviar);
             JButton btn_eliminar = new JButton("Eliminar");
            btn_eliminar.addActionListener(e -> {
                String usuario = txtField_usuario.getText();
                System.out.println(usuario);
            });
            panel2.add(btn_eliminar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(700, 40));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        // Pagina 7: crear categoria o actualizar precios por temporada
        else if(pagina == 7){
            // Agregar un grid de 9 fila y 1 columnas
            JPanel panel = new JPanel(new GridLayout(9, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una  de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Ingrese el nombre de la categoría"));
            JTextField txtField_nom = new JTextField();
            panel.add(txtField_nom);
            panel.add(new JLabel("Ingrese el rango (únicamente al crear la categoría)"));
            JTextField txtField_rango = new JTextField();
            panel.add(txtField_rango);
            panel.add(new JLabel("Ingrese tarifa por temporada alta"));
            JTextField txtField_tarifa_alta = new JTextField();
            panel.add(txtField_tarifa_alta);
            panel.add(new JLabel("Ingrese tarifa por temporada baja"));
            JTextField txtField_tarifa_baja = new JTextField();
            panel.add(txtField_tarifa_baja);

            //TODO. bueno los mismo de lo demas mensajes de errores y restricciones.
            JPanel panel2 = new JPanel(new GridLayout(1, 2));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_nom.getText());
                lista.add(txtField_rango.getText());
                lista.add(txtField_tarifa_alta.getText());
                lista.add(txtField_tarifa_baja.getText());
                System.out.println(lista);
            });
            //TODO: hacer las restricciones respectivas y mensajes de error 
            panel2.add(btn_enviar);
            JButton btn_actualizar = new JButton("Actualizar");
            btn_actualizar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_nom.getText());
                lista.add(txtField_tarifa_alta.getText());
                lista.add(txtField_tarifa_baja.getText());
                System.out.println(lista);
            });
            panel2.add(btn_actualizar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(1000, 60));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //Pagina 8: crear sede
        else if(pagina == 8){
            // Agregar un grid de 9 fila y 1 columnas
            JPanel panel = new JPanel(new GridLayout(9, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una  de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Ingrese el nombre de la sede"));
            JTextField txtField_nom = new JTextField();
            panel.add(txtField_nom);
            panel.add(new JLabel("Ingrese la ubicacion de la sede"));
            JTextField txtField_ubi = new JTextField();
            panel.add(txtField_ubi);
            panel.add(new JLabel("Ingrese los horarios de atencion (en formato HH:MM - HH:MM)"));
            JTextField txtField_horaio = new JTextField();
            panel.add(txtField_horaio);

            //TODO. bueno los mismo de lo demas mensajes de errores y restricciones.
            JPanel panel2 = new JPanel(new GridLayout(1, 2));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_nom.getText());
                lista.add(txtField_ubi.getText());
                lista.add(txtField_horaio.getText());
                System.out.println(lista);
            });
            panel2.add(btn_enviar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(1000, 80));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //Pagina 9: crear seguro
        else if(pagina == 9){
            // Agregar un grid de 7 filas y 1 columna
            JPanel panel = new JPanel(new GridLayout(7, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Ingrese la tarifa extra diaria del seguro"));
            JTextField txtField_tarifa_ext = new JTextField();
            panel.add(txtField_tarifa_ext);
            panel.add(new JLabel("Ingrese el nombre del seguro"));
            JTextField txtField_nom = new JTextField();
            panel.add(txtField_nom);
            panel.add(new JLabel("Ingrese la descripción del seguro"));
            JTextField txtField_descr = new JTextField();
            panel.add(txtField_descr);

            JPanel panel2 = new JPanel(new GridLayout(1, 1));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            //TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_tarifa_ext.getText());
                lista.add(txtField_nom.getText());
                lista.add(txtField_descr.getText());
                System.out.println(lista);
            });
            panel2.add(btn_enviar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(1000, 80));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //Pagina 10: crear o editar tarifas globales
        else if(pagina == 10){
            //Agregar un grid de 12 filas y 1 columna
            String cnd_extra = "x";
            String entregar_vec_sede_dis = "x";
            String rango_temp_alta = "x";
            JPanel panel = new JPanel(new GridLayout(12, 1));
            //Agregar un resumen de los valores actuales
            //TODO: cambiar los "X" por los valores reales.
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            panel.add(new JLabel("A continuación verá los valores actuales"));
            panel.add(new JLabel("Tarifa por conductor extra: " + cnd_extra));
            panel.add(new JLabel("Tarifa por entrega en otra sede: " + entregar_vec_sede_dis));
            panel.add(new JLabel("Rango de temporada alta: " + rango_temp_alta));
            panel.add(new JLabel("A continuación ingrese los nuevos valores de tarifas globales"));
            //Agregar cada una de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Ingrese tarifa por conductor extra"));
            JTextField txtField_tarifa_conduc_ext = new JTextField();
            panel.add(txtField_tarifa_conduc_ext);
            panel.add(new JLabel("Ingrese tarifa por entregar el vehículo en otra sede"));
            JTextField txtField_tarifa_entregar_vec_sede_dis = new JTextField();
            panel.add(txtField_tarifa_entregar_vec_sede_dis);
            panel.add(new JLabel("Ingrese el rango de la temporada alta (en formato MM/DD-MM/DD)"));
            JTextField txtField_rango_temp_alta = new JTextField();
            panel.add(txtField_rango_temp_alta);

            JPanel panel2 = new JPanel(new GridLayout(1, 1));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            //TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_tarifa_conduc_ext.getText());
                lista.add(txtField_tarifa_entregar_vec_sede_dis.getText());
                lista.add(txtField_rango_temp_alta.getText());
                System.out.println(lista);
            });
            panel2.add(btn_enviar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(800, 50));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //Pagina 11: crear, eliminar o ver el estado de un vehiculo
        else if(pagina == 11){
            JPanel panel = new JPanel(new GridLayout(13, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una de las opciones, y al lado su campo de texto
            panel.add(new JLabel("Ingrese la placa del vehiculo nuevo (en formato ABC-123)"));
            JTextField txtField_placa = new JTextField();
            panel.add(txtField_placa);
            panel.add(new JLabel("Ingrese la marca del vehiuclo nuevo"));
            JTextField txtField_marca = new JTextField();
            panel.add(txtField_marca);
            panel.add(new JLabel("Ingrese el modelo del vehiculo nuevo"));
            JTextField txtField_modelo = new JTextField();
            panel.add(txtField_modelo);
            panel.add(new JLabel("Ingrese el color del vehiculo nuevo"));
            JTextField txtField_color = new JTextField();
            panel.add(txtField_color);
            panel.add(new JLabel("Ingrese el tipo de transmision del vehiculo nuevo"));
            JTextField txtField_transmision = new JTextField();
            panel.add(txtField_transmision);
            panel.add(new JLabel("Ingrese el tipo de direccion del vehiculo nuevo"));
            JTextField txtField_tipo_direccion = new JTextField();
            panel.add(txtField_tipo_direccion);
            panel.add(new JLabel("Ingrese el tipo de combustible del vehiculo nuevo"));
            JTextField txtField_tipo_combustible = new JTextField();
            panel.add(txtField_tipo_combustible);
            panel.add(new JLabel("Ingrese la cantidad de pasajeros incluyendo el conductor que soporta el vehiculo"));
            JTextField txtField_cnt_pasajeros = new JTextField();
            panel.add(txtField_cnt_pasajeros);
            //TODO: por si desea volverlo lista
            panel.add(new JLabel("Ingrese el nombre de la sede donde esta el vehiculo nuevo"));
            JTextField txtField_sede = new JTextField();
            panel.add(txtField_sede);
            //TODO: por si desea volverlo lista
            panel.add(new JLabel("Ingrese la categoria del vehiculo nuevo"));
            JTextField txtField_categoria = new JTextField();
            panel.add(txtField_categoria);
            panel.add(new JLabel("Ingrese si el vehiculo nuevo esta disponible para alquilar (true/false)"));
            JTextField txtField_disponible = new JTextField();
            panel.add(txtField_disponible);
            panel.add(new JLabel("Ingrese la fecha de disponibilidad del vehiculo nuevo (MM/DD/AA)"));
            JTextField txtField_fch_disponibilidad = new JTextField();
            panel.add(txtField_fch_disponibilidad);

            JPanel panel2 = new JPanel(new GridLayout(1, 3));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            //TODO: alguien mas cree la ventana de estado del vehiculo... asignele como numero el 22
            JButton btn_estado = new JButton("Estado");
            panel2.add(btn_estado);

            //TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_eliminar = new JButton("Eliminar");
            btn_eliminar.addActionListener(e -> {
                String placa = txtField_placa.getText();
                System.out.println("Placa: " + placa);
            });
            panel2.add(btn_eliminar);
            //TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_crear = new JButton("Crear");
            btn_crear.addActionListener(e -> {
                JTextField[] campos = {
                    txtField_placa, txtField_marca, txtField_modelo, txtField_color, 
                    txtField_transmision, txtField_tipo_direccion, txtField_tipo_combustible, 
                    txtField_cnt_pasajeros, txtField_sede, txtField_categoria, 
                    txtField_disponible, txtField_fch_disponibilidad
                };
            
                List<String> lista = new ArrayList<>();
                for (JTextField campo : campos) {
                    lista.add(campo.getText());
                }
                System.out.println(lista);
            });
            panel2.add(btn_crear);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(550, 50));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //Pagina 12: trasladar vehiculo a otra sede
        else if(pagina == 12){
            //Agregar un grid de 11 filas y 1 columna
            JPanel panel = new JPanel(new GridLayout(11, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Ingrese la placa del vehiculo que desea transladar"));
            JTextField txtField_placa = new JTextField();
            panel.add(txtField_placa);
            //TODO: por si desea volverlo lista
            panel.add(new JLabel("Ingrese el nombre de la sede que desea transladar el vehiculo"));
            JTextField txtField_sede = new JTextField();
            panel.add(txtField_sede);
            panel.add(new JLabel("Ingrese la fecha la cual desea recoger el vehiculo (en formato MM/DD/AAAA)"));
            JTextField txtField_fch_recoger = new JTextField();
            panel.add(txtField_fch_recoger);
            panel.add(new JLabel("Ingrese la hora en la que desea recoger el vehiculo (en formato HH:MM)"));
            JTextField txtField_hora_recoger = new JTextField();
            panel.add(txtField_hora_recoger);
            panel.add(new JLabel("Ingrese la fecha en la cual desea entregar el vehiculo (en formato MM/DD/AAAA)"));
            JTextField txtField_fch_entregar = new JTextField();
            panel.add(txtField_fch_entregar);

            JPanel panel2 = new JPanel(new GridLayout(1, 1));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            //TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_placa.getText());
                lista.add(txtField_sede.getText());
                lista.add(txtField_fch_recoger.getText());
                lista.add(txtField_hora_recoger.getText());
                lista.add(txtField_fch_entregar.getText());
                System.out.println(lista);
            });
            panel2.add(btn_enviar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(900, 60));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }   
        //Pagina 13: consultar reserva por ID 
        else if(pagina == 13){
            //TODO: esto tambien se lo dejo a alguien mas 
        }
        //Pagina 14: alquilar vehiculo
        else if (pagina == 14){
            // Agregar un grid de 11 filas y 1 columna
            JPanel panel = new JPanel(new GridLayout(11, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Ingrese la placa del vehiculo"));
            JTextField txtField_placa = new JTextField();
            panel.add(txtField_placa);
            panel.add(new JLabel("Ingrese el usuario del cliente a alquilar"));
            JTextField txtField_usuario = new JTextField();
            panel.add(txtField_usuario);
            panel.add(new JLabel("Ingrese la fecha de devolucion del vehiculo (en formato MM/DD/AAAA)"));
            JTextField txtField_fch_devo = new JTextField();
            panel.add(txtField_fch_devo);
            panel.add(new JLabel("Ingrese la sede de devolucion del vehiculo"));
            JTextField txtField_sede_devo = new JTextField();
            panel.add(txtField_sede_devo);
            panel.add(new JLabel("Ingrese la fecha de alquiler del vehiculo (en formato MM/DD/AAAA)"));
            JTextField txtField_fch_alquiler = new JTextField();
            panel.add(txtField_fch_alquiler);

            JPanel panel2 = new JPanel(new GridLayout(1, 1));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            //TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_placa.getText());
                lista.add(txtField_usuario.getText());
                lista.add(txtField_fch_devo.getText());
                lista.add(txtField_sede_devo.getText());
                lista.add(txtField_fch_alquiler.getText());
                System.out.println(lista);
            });
            panel2.add(btn_enviar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(900, 60));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        // Pagina 15: agregar licencia de otros conductores
        else if(pagina == 15){
            // Agregar un grid de 5 filas y 1 columna
            JPanel panel = new JPanel(new GridLayout(9, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Por favor ingrese el usuario del cliente"));
            JTextField txtField_usuario = new JTextField();
            panel.add(txtField_usuario);
            panel.add(new JLabel("Por favor ingrese el numero de licencia del cliente"));
            JTextField txtField_num_licencia = new JTextField();
            panel.add(txtField_num_licencia);
            panel.add(new JLabel("Por favor ingrese el pais de expedicion de la licencia del cliente"));	
            JTextField txtField_pais_exp = new JTextField();
            panel.add(txtField_pais_exp);
            panel.add(new JLabel("Por favor ingrese la fecha de vencimiento de la licencia del cliente (en formato MM/DD/AAAA)"));
            JTextField txtField_fch_vencimiento = new JTextField();
            panel.add(txtField_fch_vencimiento);

            JPanel panel2 = new JPanel(new GridLayout(1, 1));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            //TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_usuario.getText());
                lista.add(txtField_num_licencia.getText());
                lista.add(txtField_pais_exp.getText());
                lista.add(txtField_fch_vencimiento.getText());
                System.out.println(lista);
            });
            panel2.add(btn_enviar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(1000, 65));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //Pagina 16: recibir vehiculo
        else if(pagina == 16){
            // Agregar un grid de 5 filas y 1 columna
            JPanel panel = new JPanel(new GridLayout(10, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Por favor ingrese el usuario del cliente"));
            JTextField txtField_usuario = new JTextField();
            panel.add(txtField_usuario);
            panel.add(new JLabel("El vehiculo necesita mantenimiento? (true/false)"));
            JTextField txtField_mantenimiento = new JTextField();
            panel.add(txtField_mantenimiento);
            panel.add(new JLabel("Solo ingrese si necesita mantenimiento"));
            panel.add(new JLabel("Ingrese la fecha estimada de regreso del vehiculo"));
            JTextField txtField_fch_regreso_vec = new JTextField();
            panel.add(txtField_fch_regreso_vec);
            panel.add(new JLabel("Ingrese la descripcion del mantenimiento"));
            JTextField txtField_desc_mantenimiento = new JTextField();
            panel.add(txtField_desc_mantenimiento);

            JPanel panel2 = new JPanel(new GridLayout(1, 1));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            //TODO: hacer las restricciones respectivas y mensajes de error
            // ademas deben de hacer que al decir que no c necesita mantenimiento no 
            // se muestren los campos de fecha y descripcion de mantenimiento
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_usuario.getText());
                lista.add(txtField_mantenimiento.getText());
                lista.add(txtField_fch_regreso_vec.getText());
                lista.add(txtField_desc_mantenimiento.getText());
                System.out.println(lista);
            });
            panel2.add(btn_enviar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(1000, 65));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //Pagina 17: listo para alquiler
        else if(pagina == 17){
            // Agregar un grid de 5 filas y 1 columna
            JPanel panel = new JPanel(new GridLayout(5, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una de las opciones, y abajo su campo de texto
            panel.add(new JLabel("Ingrese la placa del vehiculo (debe de estar en mantenimiento)"));
            JTextField txtField_placa = new JTextField();
            panel.add(txtField_placa);
            panel.add(new JLabel("Ingrese la fecha actual"));
            JTextField txtField_fch_actual = new JTextField();
            panel.add(txtField_fch_actual);

            JPanel panel2 = new JPanel(new GridLayout(1, 1));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            //TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_placa.getText());
                lista.add(txtField_fch_actual.getText());
                System.out.println(lista);
            });
            panel2.add(btn_enviar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(1000, 65));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //Pagina 18: reservar vehículo
        else if(pagina == 18){
            // Agregar un grid de 19 filas y 1 columna
            JPanel panel = new JPanel(new GridLayout(19, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una de las opciones y abajo su campo de texto
            //TODO: por si desea volverlo lista
            panel.add(new JLabel("Ingrese el nombre de la categoría del vehículo que quiere"));
            JTextField txtField_categoria = new JTextField();
            panel.add(txtField_categoria);
            //TODO: por si desea volverlo lista
            panel.add(new JLabel("Ingrese el nombre de la sede donde quiere recoger el vehículo"));
            JTextField txtField_sede_recoger = new JTextField();
            panel.add(txtField_sede_recoger);
            panel.add(new JLabel("Ingrese la fecha en la cual quiere recoger el vehículo (en formato MM/DD/AAAA)"));
            JTextField txtField_fch_recoger = new JTextField();
            panel.add(txtField_fch_recoger);
            panel.add(new JLabel("Ingrese la hora en la que quiere recoger el vehículo (en formato HH:MM)"));
            JTextField txtField_hora_recoger = new JTextField();
            panel.add(txtField_hora_recoger);
            //TODO: por si desea volverlo lista
            panel.add(new JLabel("Ingrese el nombre de la sede donde quiere entregar el vehículo"));
            JTextField txtField_sede_entregar = new JTextField();
            panel.add(txtField_sede_entregar);
            panel.add(new JLabel("Ingrese la fecha en la cual quiere entregar el vehículo (en formato MM/DD/AAAA, incluidos 0, ej. 01/01/2001. La fecha debe estar en el mismo año del alquiler)"));
            JTextField txtField_fch_entregar = new JTextField();
            panel.add(txtField_fch_entregar);
            panel.add(new JLabel("Ingrese la hora en la cual quiere entregar el vehículo (en formato HH:MM)"));
            JTextField txtField_hora_entregar = new JTextField();
            panel.add(txtField_hora_entregar);
            panel.add(new JLabel("Ingrese la cantidad de conductores extras"));
            JTextField txtField_conduc_ext = new JTextField();
            panel.add(txtField_conduc_ext);
            //TODO: por si desea volverlo lista
            panel.add(new JLabel("Ingrese los nombres de los seguros que quiere, separados por coma"));
            JTextField txtField_seguros = new JTextField();
            panel.add(txtField_seguros);

            JPanel panel2 = new JPanel(new GridLayout(1, 1));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            //TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_categoria.getText());
                lista.add(txtField_sede_recoger.getText());
                lista.add(txtField_fch_recoger.getText());
                lista.add(txtField_hora_recoger.getText());
                lista.add(txtField_sede_entregar.getText());
                lista.add(txtField_fch_entregar.getText());
                lista.add(txtField_hora_entregar.getText());
                lista.add(txtField_conduc_ext.getText());
                lista.add(txtField_seguros.getText());
                System.out.println(lista);
            });
            panel2.add(btn_enviar);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(900, 35));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //Pagina 19: editar reserva 
        else if (pagina == 19){
            // Agregar un grid de 9 filas y 1 columna
            JPanel panel = new JPanel(new GridLayout(9, 1));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            // Agregar cada una de las opciones y abajo su campo de texto
            //TODO: por si desea volverlo lista
            panel.add(new JLabel("Ingrese el nombre de la sede donde quiere entregar el vehículo"));
            JTextField txtField_sede_entregar = new JTextField();
            panel.add(txtField_sede_entregar);
            panel.add(new JLabel("Ingrese la fecha en la cual quiere entregar el vehículo (en formato MM/DD/AAAA, incluidos 0, ej. 01/01/2001. La fecha debe estar en el mismo año del alquiler)"));
            JTextField txtField_fch_entregar = new JTextField();
            panel.add(txtField_fch_entregar);
            panel.add(new JLabel("Ingrese la hora en la cual quiere entregar el vehículo (en formato HH:MM)"));
            JTextField txtField_hora_entregar = new JTextField();
            panel.add(txtField_hora_entregar);
            panel.add(new JLabel("Ingrese la cantidad de conductores extras"));
            JTextField txtField_conduc_ext = new JTextField();
            panel.add(txtField_conduc_ext);

            JPanel panel2 = new JPanel(new GridLayout(1, 2));
            panel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            //TODO: hacer las restricciones respectivas y mensajes de error
            JButton btn_enviar = new JButton("Enviar");
            btn_enviar.addActionListener(e -> {
                List<String> lista = new ArrayList<>();
                lista.add(txtField_sede_entregar.getText());
                lista.add(txtField_fch_entregar.getText());
                lista.add(txtField_hora_entregar.getText());
                lista.add(txtField_conduc_ext.getText());
                System.out.println(lista);
            });
            
            JButton btn_res_act = new JButton("Ver reserva actual");
            btn_res_act.addActionListener(e -> cambiar_pagina(20));
            panel2.add(btn_enviar);
            panel2.add(btn_res_act);
            panel.add(panel2);

            add(panel);
            for (Component comp : panel.getComponents()) {
                comp.setPreferredSize(new Dimension(900, 70));
                if (comp instanceof JPanel) {
                    JPanel panel_a = (JPanel) comp;
                    for (Component innerComp : panel_a.getComponents()) {
                        if (innerComp instanceof JLabel) {
                            JLabel label = (JLabel) innerComp;
                            label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
                        }
                    }
                }
            }
        }
        //Pagina 20: reserva actual
        else if (pagina == 20){
            //TODO: esto se lo dejo a alguien mas
        }
        //Pagina 21: gráfica de reservas a lo largo del año
        else if (pagina == 21){
            //TODO: esto se lo dejo a alguien mas
        }
    }
    private void agregarEtiquetas(JPanel panel, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            panel.add(new JLabel());
        }
    }
    private void cambiar_pagina(int pagina){
        principal.cambiar_pagina(pagina);

    }
}