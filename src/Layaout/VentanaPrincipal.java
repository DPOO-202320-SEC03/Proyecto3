package Layaout;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class VentanaPrincipal extends JFrame{
    private PanelDatos panelEste;
    private PanelTitulo panelNorte;
    private PanelInicial panelInicial;

    private JPanel panelimagen;
    private int pagina = 19;
    

    public VentanaPrincipal() {
        super("Rentas x"); 
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cambiarLayout();
        setVisible(true);
    } 

    public void cambiar_pagina(int pagina){
        this.pagina = pagina;
        cambiarLayout();
        revalidate();
        repaint();
    }

    private void cambiarLayout() {
        getContentPane().removeAll();
        if (pagina == 0){
            setLayout(new GridLayout(1, 1));
            panelInicial = new PanelInicial(this);
            if (panelInicial.getComponentCount() > 0) {
                add(panelInicial);
            } else {
                System.out.println("Error al crear el PanelInicial");
            }
        }
        else if (pagina != 0) {
            setLayout(new BorderLayout());


            panelNorte = new PanelTitulo(this, pagina);
            add(panelNorte, BorderLayout.NORTH);

            JPanel panelResto = new JPanel(new BorderLayout());
            PanelDatos panelDatos = new PanelDatos(this, pagina);
            panelResto.add(panelDatos, BorderLayout.CENTER);

            add(panelResto, BorderLayout.WEST);
        }
    }

    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
    }


}