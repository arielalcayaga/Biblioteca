package com.biblioteca.ui;

import com.biblioteca.controlador.ControladorUsuario;
import com.biblioteca.model.Usuario;

import javax.swing.*;


public class VentanaLogin {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
    private JPanel panelPrincipal;

    private final ControladorUsuario controlador;

    public VentanaLogin(ControladorUsuario controlador) {

        this.controlador = controlador;

        // Evento del botón Ingresar
        btnIngresar.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {

        String username = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());

        // Validación de campos vacíos
        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Debe ingresar correo y contraseña.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validar credenciales
        if (controlador.validarUsuario(correo, contrasenia)) {

            JOptionPane.showMessageDialog( panelPrincipal,"Bienvenido a la Biblioteca Escolar" );

            // Obtiene y cierra la ventana Login
            JFrame ventanaLogin = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal);
            ventanaLogin.dispose();

            // Crea la ventana principal
            VentanaPrincipal principal = new VentanaPrincipal(controlador);

            JFrame frame = new JFrame("Biblioteca Escolar - Gestión de libros");

            frame.setContentPane(principal.getPanelPrincipal());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Correo o contraseña incorrectos.",
                    "Error de acceso",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /*
    * retorna el panel principal
    * */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }


}
