package Layaout;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelInicial extends JPanel {
    private JTextField textField_usuario;
    private JTextField textField_contraseña;
    private List<String> listaParametros;
    private VentanaPrincipal principal;

    public PanelInicial(VentanaPrincipal principal) {
        this.principal = principal;
        setLayout(new GridLayout(2, 1));
        listaParametros = new ArrayList<String>();

        // Agregar imagen a la primera fila del grid
        String workingDir = System.getProperty("user.dir");
        String filePath = workingDir + File.separator + "src" + File.separator + "Layaout" +
        File.separator + "imagenes" + File.separator
                + "fondo_inicio.jpg";
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            JLabel picLabel = new JLabel(new ImageIcon(image));
            add(picLabel);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de imagen: " + e.getMessage());
            return;
        }

        // Agregar panel de 3 columnas a la segunda fila del grid
        JPanel panel = new JPanel(new GridLayout(1, 3));
        add(panel);

        // Agregar 6 filas a la primera columna
        JPanel panel1 = new JPanel(new GridLayout(6, 1));
        panel.add(panel1);
        // Agregar texto en negrilla a la primera fila
        JLabel Lb_iniciar = new JLabel("<html><b>Iniciar seccion   </b></html>");
        Lb_iniciar.setFont(new Font("Dialog", Font.PLAIN, 34));
        panel1.add(Lb_iniciar);
        // Agregar texto a la segunda fila
        JLabel Lb_usuario = new JLabel("Usuario:");
        Lb_usuario.setFont(new Font("Dialog", Font.PLAIN, 24));
        panel1.add(Lb_usuario);
        // Agregar campo de texto a la tercera fila
        textField_usuario = new JTextField();
        panel1.add(textField_usuario);
        // Agregar texto a la cuarta fila
        JLabel Lb_contraseña = new JLabel("Contraseña:");
        Lb_contraseña.setFont(new Font("Dialog", Font.PLAIN, 24));
        panel1.add(Lb_contraseña);
        // Agregar campo de texto a la quinta fila
        textField_contraseña = new JTextField();
        panel1.add(textField_contraseña);

        // Agregar boton de enviar a la sexta fila, este agrega los textos escritos en 
        //textField_usuario y textField_contraseña a la lista listaParametros
        // TODO: hacer las restricciones respectivas y mensajes de error
        JButton btn_enviar = new JButton("Enviar");
        btn_enviar.addActionListener(e -> {
            listaParametros.add(textField_usuario.getText());
            listaParametros.add(textField_contraseña.getText());
            //TODO: no aqui pero pos comprobar si es un admin poner pagina 2
            // si es admin local poner pagina 3, si es empleado poner pagina 4
            // si es cliente poner pagina 5.
        });
        panel1.add(btn_enviar);
        

        // Agregar 3 filas a la segunda columna
        JPanel panel2 = new JPanel(new GridLayout(3, 1));
        panel.add(panel2);
        panel2.add(new JLabel());

        // Agregar JLabel con estilo de enlace a la segunda fila
        JLabel Lb_registrarse = new JLabel("<html><u>o registrate aqui</u></html>");
        Lb_registrarse.setFont(new Font("Dialog", Font.PLAIN, 24));
        Lb_registrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Lb_registrarse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                principal.cambiar_pagina(1);
            }
        });
        panel2.add(Lb_registrarse);

        panel2.add(new JLabel());

        // Agregar tercera columna vacía
        JPanel panel3 = new JPanel();
        String workingDir_2 = System.getProperty("user.dir");
        String filePath_2 = workingDir_2 + File.separator + "src" + File.separator + "Layaout" +
        File.separator + "imagenes" + File.separator
                + "fondo_inicio_2.jpg";
        try {
            BufferedImage image_2 = ImageIO.read(new File(filePath_2));
            JLabel picLabel_2 = new JLabel(new ImageIcon(image_2));
            panel3.add(picLabel_2);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de imagen: " + e.getMessage());
            return;
        }
        panel.add(panel3);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public List<String> getListaParametros() {
        return listaParametros;
    }
}