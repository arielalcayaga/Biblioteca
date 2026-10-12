package com.biblioteca.app;

import com.biblioteca.controlador.ControladorUsuario;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Controlador de usuarios
            ControladorUsuario controlador =  new ControladorUsuario();
            // Vista Login
            com.biblioteca.ui.VentanaLogin login = new com.biblioteca.ui.VentanaLogin(controlador);

            // Ventana que contiene el formulario
            JFrame frame = new JFrame("Biblioteca Escolar");

            frame.setContentPane(login.getPanelPrincipal());

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Dimensiones
            frame.setSize(450, 300);

            // Centra la ventana en la pantalla.
            frame.setLocationRelativeTo(null);

            //Hace visible la venta
            frame.setVisible(true);
        });
    }
}