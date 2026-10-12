package com.biblioteca.ui;

import com.biblioteca.controlador.ControladorLibro;
import com.biblioteca.model.Libro;

import javax.swing.*;
import java.awt.*;


public class VentanaRegistroLibro extends JFrame {

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtIsbn;
    private JTextField txtEditorial;
    private JTextField txtStock;
    private JComboBox<String> cmbCategoria;

    private JButton btnGuardar;

    private final ControladorLibro controladorLibro;


    public VentanaRegistroLibro( ControladorLibro controladorLibro) {

        this.controladorLibro = controladorLibro;

        configurarVentana();
        crearComponentes();
    }


    private void configurarVentana() {

        setTitle("Biblioteca Escolar - Registrar libro");
        setSize(450, 350);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
    }


    private void crearComponentes() {

        JPanel panel =  new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25 ));


        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtIsbn = new JTextField();
        txtEditorial = new JTextField();
        txtStock = new JTextField();

        cmbCategoria = new JComboBox<>(
                new String[]{
                        "Ciencia Ficción",
                        "Historia",
                        "Filosofía",
                        "Literatura",
                        "Tecnología"
                }
        );

        btnGuardar =new JButton("Guardar producto");

        // Agregar componentes
        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);

        panel.add(new JLabel("Autor:"));
        panel.add(txtAutor);

        panel.add(new JLabel("ISBN:"));
        panel.add(txtIsbn);

        panel.add(new JLabel("Editorial:"));
        panel.add(txtEditorial);

        panel.add(new JLabel("Stock:"));
        panel.add(txtStock);

        panel.add(new JLabel("Categoría:"));
        panel.add(cmbCategoria);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        // Evento
        btnGuardar.addActionListener(
                e -> guardarLibro()
        );
    }


    private void guardarLibro() {

        if (txtTitulo.getText().trim().isEmpty()|| txtAutor.getText().trim().isEmpty()|| txtIsbn.getText().trim().isEmpty()|| txtEditorial.getText().trim().isEmpty())|| txtStock.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String titulo = txtTitulo.getText().trim();
        String autor = txtTitulo.getText().trim();
        String isbn = txtTitulo.getText().trim();
        String editorial = txtTitulo.getText().trim();
        int stock = Integer.parseInt(txtStock.getText().trim());
        String categoria =  cmbCategoria.getSelectedItem().toString();

        Libro libro =  new Libro(id, titulo, autor, isbn, editorial, stock, categoria);

        boolean registrado = controladorLibro.agregar(libro);

            if (registrado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Libro registrado correctamente."
                );

                limpiarCampos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible registrar el libro.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
    }
            private void limpiarCampos() {

            txtTitulo.setText("");
            txtAutor.setText("");
            txtIsbn.setText("");
            txtEditorial.setText("");
            txtStock.setText("");
            cmbCategoria.setSelectedIndex(0);
            txtTitulo.requestFocus();
        }
            }