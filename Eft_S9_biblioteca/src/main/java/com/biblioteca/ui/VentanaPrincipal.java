package com.biblioteca.ui;


import javax.swing.*;

import com.biblioteca.controlador.ControladorLibro;
import com.biblioteca.controlador.ControladorUsuario;
import com.biblioteca.model.Libro;

public class VentanaPrincipal {

    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnSimular;
    private JButton btnCerrarSesion;
    private JPanel panelPrincipal;
    private JTextArea txtActividad;
    private JButton btnVenta;


    private final ControladorUsuario controladorUsuario;
    private final ControladorLibro controladorLibro;
    private final ControladorPrestamo controladorPrestamo;

    /**
     * Constructor de la ventana principal.
     */
    public VentanaPrincipal( ControladorUsuario controladorUsuario) {

        this.controladorUsuario =  controladorUsuario;


        this.controladorLibro = new ControladorLibro();


        this.controladorPrestamo = new ControladorPrestamo();


        btnRegistrar.addActionListener( e -> abrirRegistroProducto());


        btnListar.addActionListener(e -> abrirListaProductos());


        btnCerrarSesion.addActionListener(e -> cerrarSesion());

        txtActividad.append( "Sistema iniciado. Esperando actividad...\n" );


        btnSimular.addActionListener(
                e -> simularActividad()
        );


        btnVenta.addActionListener(
                e -> abrirPrestamo()
        );
    }


    private void cerrarSesion() {

        JFrame ventanaActual =  (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal);

        ventanaActual.dispose();
        VentanaLogin login =new VentanaLogin(controladorUsuario);
        JFrame frame =new JFrame("Biblioteca Escolar - Inicio de sesión" );
        frame.setContentPane(login.getPanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void abrirRegistroLibro() {

        VentanaRegistroLibro ventana = new VentanaRegistroLibro(controladorLibro);
        ventana.setVisible(true);
    }


    private void abrirListaLibro() {

        VentanaListaLibros ventana = new VentanaListaLibros(controladorLibro);
        ventana.setVisible(true);
    }


    private void simularActividad() {

        // Verificar que existan productos
        if (controladorLibro.getLibros().isEmpty()) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Debe registrar al menos un libro.",
                    "Simulación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Utilizamos el primer producto registrado
        Libro libro = controladorLibro.getLibros().get(0);

        int stockInicial = libro.getStock();

        txtActividad.append("\n--- Nueva simulación ---\n" );

        txtActividad.append("Libro: " + libro.getTitulo() + "\n");

        txtActividad.append( "Stock inicial: " + stockInicial + "\n");

        txtActividad.append("\n------------------\n" );

        // Crear las tareas
        Vendedor vendedor = new Vendedor(producto, 10, txtActividad);
        Reponedor reponedor = new Reponedor(producto, 5, txtActividad);

        // Crear los hilos
        Thread hiloVendedor = new Thread(vendedor);

        Thread hiloReponedor = new Thread(reponedor);

        // Iniciar los hilos
        hiloVendedor.start();
        hiloReponedor.start();
    }

    /**
     * Abre la ventana para registrar
     * una nueva venta.
     */
    private void abrirVenta() {

        VentanaVenta ventana =
                new VentanaVenta(
                        controladorLibro,
                        controladorVentas
                );

        ventana.setVisible(true);
    }

    /**
     * Retorna el panel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}