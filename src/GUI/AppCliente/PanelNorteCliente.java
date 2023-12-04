package GUI.AppCliente;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelNorteCliente extends JPanel {

    private VentanaPrincipalCliente ventanaPrincilal;
    
    public PanelNorteCliente(VentanaPrincipalCliente ventanaPrincipal, int pagina) {

        this.ventanaPrincilal = ventanaPrincipal;

        setLayout(new GridLayout(1, 2));
        if (pagina == 0) {
            setLayout(new GridLayout(1, 1));
            String workingDir = System.getProperty("user.dir");
            String filePath = workingDir + File.separator + "src" + File.separator + "GUI" + File.separator + "imagenes" + File.separator + "fondo_inicio.jpg";
            try {
                BufferedImage image = ImageIO.read(new File(filePath));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                add(picLabel);
            } catch (IOException e) {
                System.out.println("Error al leer el archivo de imagen: " + e.getMessage());
            }
        } else if (pagina == 1) {
            titulo("LOGIN");
            volver_pagina_anterior(0);
        } else if (pagina == 2) {
            titulo("REGISTRO");
            volver_pagina_anterior(0);
        } else if (pagina == 14) {
            titulo("MENU CLIENTE");
            volver_pagina_anterior(1);
        } else if (pagina == 141) {
            titulo("CREAR RESERVA");
            volver_pagina_anterior(14);
        } else if (pagina == 142) {
            titulo("EDITAR RESERVA");
            volver_pagina_anterior(14);
        } else if (pagina == 143) {
            titulo("RESUMEN RESERVA ACTUAL");
            volver_pagina_anterior(14);
        }
    }

    private void titulo(String titulo){
        // Agregar texto en negrilla a la primera fila
        JLabel Lb_registro = new JLabel("<html><b>" + titulo + "</b></html>");
        Lb_registro.setFont(new Font("Dialog", Font.PLAIN, 34));
        add(Lb_registro);
    }

    private void volver_pagina_anterior(int pagina){
        JLabel Lb_atras = new JLabel();
        Lb_atras.setLayout(new GridLayout(1, 5));
        for (int i = 0; i < 3; i++) {
            Lb_atras.add(new JLabel());
        }
        // Agregar boton ATRAS a la primera fila
        JButton btn_atras = new JButton("ATRAS");
        btn_atras.setMinimumSize(new Dimension(80, 30));
        btn_atras.addActionListener(e -> {
            ventanaPrincilal.cambiarPagina(pagina);
        });
        
        Lb_atras.add(btn_atras);
        add(Lb_atras);
    }
    
}
