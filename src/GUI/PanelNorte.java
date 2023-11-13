package GUI;

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

public class PanelNorte extends JPanel {

    VentanaPrincipal ventanaPrincilal;
    
    public PanelNorte(VentanaPrincipal ventanaPrincipal, int pagina) {

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
        } else if (pagina == 11) {
            titulo("MENU ADMIN");
            volver_pagina_anterior(1);
        } else if (pagina == 111) {
            titulo("CREAR SEDE");
            volver_pagina_anterior(11);
        } else if (pagina == 112) {
            titulo("CREAR CATEGORIA");
            volver_pagina_anterior(11);
        } else if (pagina == 113) {
            titulo("EDITAR TARIFAS POR TEMPORADA");
            volver_pagina_anterior(11);
        } else if (pagina == 114) {
            titulo("CREAR SEGURO");
            volver_pagina_anterior(11);
        } else if (pagina == 115) {
            titulo("CREAR TARIFAS GLOBALES");
            volver_pagina_anterior(11);
        } else if (pagina == 116) {
            titulo("EDITAR TARIFAS GLOBALES");
            volver_pagina_anterior(11);
        } else if (pagina == 117) {
            titulo("CREAR VEHICULO");
            volver_pagina_anterior(11);
        } else if (pagina == 118) {
            titulo("ELIMINAR VEHICULO");
            volver_pagina_anterior(11);
        } else if (pagina == 119) {
            titulo("ESTADO VEHICULO");
            volver_pagina_anterior(11);
        } else if (pagina == 1110) {
            titulo("TRASLADAR VEHICULO");
            volver_pagina_anterior(11);
        } else if (pagina == 1111) {
            titulo("CONSULTAR RESERVA POR ID");
            volver_pagina_anterior(11);
        } else if (pagina == 1112) {
            titulo("CREAR ADMINISTRADOR LOCAL");
            volver_pagina_anterior(11);
        } else if (pagina == 1113) {
            titulo("ELIMINAR USUARIO");
            volver_pagina_anterior(11);
        } else if (pagina == 1114) {
            titulo("GRAFICA DE RESERVAS");
            volver_pagina_anterior(11);
        } else if (pagina == 12) {
            titulo("MENU ADMIN LOCAL");
            volver_pagina_anterior(1);
        } else if (pagina == 121) {
            titulo("CREAR EMPLEADO LOCAL");
            volver_pagina_anterior(12);
        } else if (pagina == 122) {
            titulo("ELIMINAR EMPLEADO LOCAL");
            volver_pagina_anterior(12);
        } else if (pagina == 13) {
            titulo("MENU EMPLEADO");
            volver_pagina_anterior(1);
        } else if (pagina == 131) {
            titulo("ALQUILAR VEHICULO");
            volver_pagina_anterior(13);
        } else if (pagina == 132) {
            titulo("AGREGAR LICENCIA DE OTROS CONDUCTORES");
            volver_pagina_anterior(13);
        } else if (pagina == 133) {
            titulo("RECIBIR VEHICULO");
            volver_pagina_anterior(13);
        } else if (pagina == 134) {
            titulo("LISTAR VEHICULO PARA ALQUILER");
            volver_pagina_anterior(13);
        } else if (pagina == 14) {
            titulo("MENU CLIENTE");
            volver_pagina_anterior(1);
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
