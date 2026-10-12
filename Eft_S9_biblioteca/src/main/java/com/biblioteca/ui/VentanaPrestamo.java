package com.biblioteca.ui;

import com.biblioteca.controlador.ControladorLibro;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestamo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaPrestamo extends JFrame {
    
    private JComboBox<Libro> cmbLibro;
    private JTextField txtCantidad;
    private JButton btnAgregar;
    private JButton btnQuitar;
    private JButton btnGuardar;
    private JButton btnModificar;

    private JTable tablaDetalle;
    private DefaultTableModel modeloTabla;

    private JLabel lblTotal;
    
    private final ControladorLibro controladorLibro;
    private final ControladorPrestamo controladorPrestamo;
    
    private Prestamo prestamoActual;
    
    public VentanaPrestamo(
            ControladorLibro controladorLibro,
            ControladorPrestamo controladorPrestamo) {

        this.controladorLibro = controladorLibro;
        this.controladorPrestamo = controladorPrestamo;

        // Crear nueva venta
        prestamoActual = new Prestamo();

        configurarVentana();
        crearComponentes();
        cargarLibro();
    }

    private void configurarVentana() {

        setTitle("Biblioteca Escolar - Registrar Préstamo");
        setSize(750, 500);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);
    }
    
    private void crearComponentes() {

        setLayout(new BorderLayout(10, 10));
        

        JPanel panelSuperior =
                new JPanel(new FlowLayout());

        cmbLibro = new JComboBox<>();

        txtCantidad = new JTextField(5);

        btnAgregar = new JButton("Agregar libro");


        panelSuperior.add(
                new JLabel("Libro:")
        );

        panelSuperior.add(cmbLibro);

        panelSuperior.add(
                new JLabel("Cantidad:")
        );

        panelSuperior.add(txtCantidad);

        panelSuperior.add(btnAgregar);
        
        String[] columnas = {
                "ID",
                "ID Estudiante",
                "ID Libro",
                "Fecha Préstamo",
                "Fecha Devolución",
                "Devuelto"
        };

        modeloTabla =
                new DefaultTableModel(columnas, 0);
//
//        tablaDetalle =
                new JTable(modeloTabla);

        JScrollPane scroll =
                new JScrollPane(tablaDetalle);
        
        JPanel panelInferior =
                new JPanel(new FlowLayout());

        btnQuitar =
                new JButton("Quitar libro");

        btnModificar = new JButton("Modificar cantidad");

        btnGuardar =
                new JButton("Guardar venta");

        lblTotal =
                new JLabel("TOTAL: $0");

        panelInferior.add(btnQuitar);
        panelInferior.add(btnGuardar);
        panelInferior.add(btnModificar);
        panelInferior.add(lblTotal);

        add(
                panelSuperior,
                BorderLayout.NORTH
        );

        add(
                scroll,
                BorderLayout.CENTER
        );

        add(
                panelInferior,
                BorderLayout.SOUTH
        );

        btnAgregar.addActionListener(
                e -> agregarLibro()
        );

        btnQuitar.addActionListener(
                e -> quitarLibro()
        );

        btnGuardar.addActionListener(
                e -> guardarPrestamo()
        );

        btnModificar.addActionListener(
                e -> modificarCantidad()
        );
    }

    private void cargarLibro() {

        cmbLibro.removeAllItems();

        for (Libro libro :
                controladorLibro.getLibro()) {

            cmbLibro.addItem(libro);
        }
    }

    private void agregarLibro() {

        Libro libro =  (Libro) cmbLibro.getSelectedItem();

        if (libro == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No existen libros disponibles."
            );

            return;
        }

        try {

            int cantidad =
                    Integer.parseInt(
                            txtCantidad.getText().trim()
                    );

            if (cantidad <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "La cantidad debe ser mayor que cero."
                );

                return;
            }

            // Validar stock
            if (cantidad > libro.getStock()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Stock insuficiente."
                );

                return;
            }

            // Crear detalle
            DetallePrestamo detalle =
                    new DetallePrestamo(
                            libro.getId()
                    );


            prestamoActual.agregarDetalle(detalle);

            Object[] fila = {
                    detalle.getIdLibro(),
                    detalle.getNombreLibro(),

            };

            modeloTabla.addRow(fila);

            // Mostrar total actualizado
            actualizarTotal();

            txtCantidad.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La cantidad debe ser un número."
            );
        }
    }

    private void quitarLibro() {

        int fila = tablaDetalle.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un libro."
            );

            return;
        }

        // Eliminar del modelo Venta
        prestamoActualActual.getDetalles().remove(fila);

        // Recalcular total
        prestamoActualActual.calcularTotal();

        // Eliminar visualmente del JTable
        modeloTabla.removeRow(fila);

        actualizarTotal();
    }


    private void guardarPrestamo() {

        // No permitir ventas vacías
        if (prestamoActual.getDetalles().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe agregar al menos un libro."
            );

            return;
        }

        boolean registrada = controladorPrestamo.registrarPrestamo(prestamoActual);

        if (registrada) {

            JOptionPane.showMessageDialog(
                    this,
                    "Préstamo registrad correctamente.\n" +
                            "Préstamo N° " +
                            prestamoActual.getIdPrestamo()
            );

            limpiarPrestamo();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible registrar el prestamo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpiarVenta() {

        prestamoActual = new Prestamo();
        modeloTabla.setRowCount(0);
        txtCantidad.setText("");
    }


    private void modificarCantidad() {

        int fila = tablaDetalle.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un libro."
            );

            return;
        }

        try {

            int nuevaCantidad =
                    Integer.parseInt(
                            txtCantidad.getText().trim()
                    );

            if (nuevaCantidad <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "La cantidad debe ser mayor que cero."
                );

                return;
            }

            DetallePrestamo detalle =
                    prestamoActual
                            .getDetalles()
                            .get(fila);

            Libro libro = null;

            for (Libro p :
                    controladorLibro.getLibro()) {

                if (p.getId() == detalle.getIdLibro()) {

                    libro = p;
                    break;
                }
            }

            if (libro != null && nuevaCantidad > libro.getStock()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Stock insuficiente."
                );

                return;
            }

            txtCantidad.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Cantidad modificada correctamente."
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una cantidad válida."
            );
        }
    }

}