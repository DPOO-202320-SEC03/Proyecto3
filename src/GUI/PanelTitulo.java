package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTitulo extends JPanel{
    private VentanaPrincipal principal;

    public PanelTitulo(VentanaPrincipal principal, int pagina) {
        this.principal = principal;
        setLayout(new GridLayout(1, 3));
        JPanel panel = new JPanel(new GridLayout(1, 10));
        if(pagina == 1){
            // Agregar texto en negrilla a la primera fila
            titulo("Registro");
            volver_pagina_anterior(panel, 0);
        }
        else if (pagina == 2){
            // Agregar texto en negrilla a la primera fila
            titulo("Menú administrador");
            volver_pagina_anterior(panel, 0);
        }
        else if (pagina == 3){
            // Agregar texto en negrilla a la primera fila
            titulo("Crear o eliminar empleado");
            volver_pagina_anterior(panel, 0);
        }
        else if (pagina == 4){
            // Agregar texto en negrilla a la primera fila
            titulo("Menú empleado");
            volver_pagina_anterior(panel, 0);
        }
        else if (pagina == 5){
            // Agregar texto en negrilla a la primera fila
            titulo("Bienvenido a Rentas x que desea hacer hoy?");
            volver_pagina_anterior(panel, 0);
        }
        else if (pagina == 6){
            // Agregar texto en negrilla a la primera fila
            titulo("Crear administrador local o eliminar un usuario");
            volver_pagina_anterior(panel, 2);
        }
        else if (pagina == 7){
            // Agregar texto en negrilla a la primera fila
            titulo("Crear categoria o actualizar precios por temporada");
            volver_pagina_anterior(panel, 2);
        }
        else if (pagina == 8){
            // Agregar texto en negrilla a la primera fila
            titulo("Crear sede");
            volver_pagina_anterior(panel, 2);
        }
        else if (pagina == 9){
            // Agregar texto en negrilla a la primera fila
            titulo("Crear seguro");
            volver_pagina_anterior(panel, 2);
        }
        else if (pagina == 10){
            // Agregar texto en negrilla a la primera fila
            titulo("Crear o editar tarifas globales");
            volver_pagina_anterior(panel, 2);
        }
        else if (pagina == 11){
            // Agregar texto en negrilla a la primera fila
            titulo("Crear, eliminar o ver el estado de un vehiculo");
            volver_pagina_anterior(panel, 2);
        }
        else if (pagina == 12){
            // Agregar texto en negrilla a la primera fila
            titulo("Trasladar vehiculo a otra sede");
            volver_pagina_anterior(panel, 2);
        }
        else if (pagina == 13){
            // Agregar texto en negrilla a la primera fila
            titulo("Consultar reserva por ID");
            volver_pagina_anterior(panel, 2);
        }
        else if (pagina == 14){
            titulo("Alquilar vehiculo");
            volver_pagina_anterior(panel, 4);
        }
        else if (pagina == 15){
            titulo("Agregar licencia de otros conductores");
            volver_pagina_anterior(panel, 4);
        }
        else if (pagina == 16){
            titulo("Recibir vehículo");
            volver_pagina_anterior(panel, 4);
        }
        else if (pagina == 17){
            titulo("Listo para alquiler");
            volver_pagina_anterior(panel, 4);
        }
        else if (pagina == 18){
            titulo("Reservar vehículo");
            volver_pagina_anterior(panel, 5);
        }
        else if (pagina == 19){
            titulo("Editar reserva");
            volver_pagina_anterior(panel, 5);
        }
        else if (pagina == 20){
            titulo("Reserva actual");
            volver_pagina_anterior(panel, 5);
        }
        else if (pagina == 21){
            titulo("Gráfica de reservas a lo largo del año");
            //TODO: puse 0 que lo devuelve a la pagina inicial... pero realmente no tengo ni la mas
            // minima idea de donde va esta pagina 
            volver_pagina_anterior(panel, 0);
        }
        add(panel);
        
    }
    
    private void cambiar_pagina(int pagina){
        principal.cambiar_pagina(pagina);
    }

    private void titulo(String titulo){
        setLayout(new GridLayout(1, 3));
        // Agregar texto en negrilla a la primera fila
        JLabel Lb_registro = new JLabel("<html><b>" + titulo + "</b></html>");
        Lb_registro.setFont(new Font("Dialog", Font.PLAIN, 34));
        add(Lb_registro);
    }

    private void volver_pagina_anterior(JPanel panel, int pagina){
        for (int i = 0; i < 9; i++) {
            panel.add(new JLabel());
        }
        // Agregar boton X a la primera fila
        JButton btn_atras = new JButton("<==");
        btn_atras.setPreferredSize(new Dimension(30, 30));
        btn_atras.addActionListener(e -> {
            cambiar_pagina(pagina);
        });
        
        panel.add(btn_atras);
    }
    
}
