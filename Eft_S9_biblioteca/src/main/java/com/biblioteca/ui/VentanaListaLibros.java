package com.biblioteca.ui;
import com.biblioteca.controlador.ControladorLibro;
import com.biblioteca.model.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaLibros extends JFrame {

    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;
    private JButton btnEditar;
    private JButton btnEliminar;

    private final ControladorLibro controladorLibro;

    public VentanaListaLibros( ControladorLibro controladorLibro) {

        this.controladorLibro = controladorLibro;

        configurarVentana();
        crearComponentes();
        cargarLibros();
    }

    /**
     * Configura las propiedades del JFrame.
     */
    private void configurarVentana() {

        setTitle("Biblioteca Escolar - Libros");
        setSize(650, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );

        setLocationRelativeTo(null);
    }

    /**
     * Construye los componentes de la ventana.
     */
    private void crearComponentes() {

        setLayout(new BorderLayout(10, 10));

        // Columnas de la tabla
        String[] columnas = {
                "Título",
                "Autor",
                "ISBN",
                "Editorial",
                "Stock",
                "ID Categoría"
        };

        modeloTabla = new DefaultTableModel(columnas, 0);

        tablaLibros =new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tablaLibros);

        btnActualizar =new JButton("Recargar"); // antes era Actualizar
        //nuevo
        btnEditar = new JButton("Editar libro");
        btnEliminar = new JButton("Eliminar libro");

        JPanel panelBoton = new JPanel();

        panelBoton.add(btnActualizar);
        panelBoton.add(btnEditar);
        panelBoton.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        // Evento
        btnActualizar.addActionListener(e -> cargarLibros());
        btnEditar.addActionListener(e -> editarLibros());
        btnEliminar.addActionListener(e -> eliminarLibros());
    }

    /**
     * Carga los productos del controlador
     * dentro del JTable.
     */
    private void cargarLibros() {

        // Limpiar filas existentes
        modeloTabla.setRowCount(0);

        for (Libro libro :  controladorLibro.getLibros()) {

            Object[] fila = {
                    libro.getId(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getIsbn(),
                    libro.getEditorial(),
                    libro.getStock(),
                    libro.getId_categoria()
            };

            modeloTabla.addRow(fila);
        }
    }

    private void editarLibros() {

        int fila = tablaLibros.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un libro."
            );

            return;
        }

        int id =   (int) modeloTabla.getValueAt(fila, 0);

        String tituloActual =    modeloTabla.getValueAt(fila, 1).toString();
        String autorActual =   modeloTabla.getValueAt(fila, 2).toString();
        String isbnActual =    modeloTabla.getValueAt(fila, 3).toString();
        String editorialActual =    modeloTabla.getValueAt(fila, 3).toString();
        int stockActual =   (int) modeloTabla.getValueAt(fila, 5);
        int id_categoriaActual =   (int) modeloTabla.getValueAt(fila, 6);
        JTextField txtTitulo =  new JTextField(tituloActual);
        JTextField txtAutor =   new JTextField(autorActual);
        JTextField txtIsbn =    new JTextField(String.valueOf(isbnActual));
        JTextField txtEditorial =    new JTextField(String.valueOf(editorialActual));
        JTextField txtStock =  new JTextField(String.valueOf(stockActual));
        JTextField txtId_categoria =    new JTextField(String.valueOf(id_categoriaActual));

        Object[] campos = {
                "Título:", txtTitulo,
                "Autor:", txtAutor,
                "ISBN:", txtIsbn,
                "Editorial:", txtEditorial,
                "Stock:", txtStock,
                "ID Categoría:", txtId_categoria
        };

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        campos,
                        "Editar libro ID " + id,
                        JOptionPane.OK_CANCEL_OPTION
                );

        if (opcion == JOptionPane.OK_OPTION) {

            try {

                Libro libro =
                        new Libro(
                                id,
                                txtTitulo.getText().trim(),
                                txtAutor.getText().trim(),
                                txtIsbn.getText().trim(),
                                txtEditorial.getText().trim(),
                                Integer.parseInt(
                                        txtStock.getText().trim()
                                ),
                                Integer.parseInt(
                                        txtId_categoria.getText().trim()
                                )
                        );

                boolean actualizado =  controladorLibro.actualizarLibro(libro);

                if (actualizado) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Libro actualizado correctamente."
                    );

                    cargarLibros();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No fue posible actualizar el libro."
                    );
                }

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Stock e ID Categoría deben ser números."
                );
            }
        }
    }

    private void eliminarLibros() {

        int fila =  tablaLibros.getSelectedRow();

        // Verificar selección
        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un libro.",
                    "Eliminar producto",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Obtener datos desde la fila seleccionada
        int id = (int) modeloTabla.getValueAt(fila, 0);

        String titulo =  modeloTabla.getValueAt(fila, 1).toString();

        // Solicitar confirmación
        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Desea eliminar el libro "
                                + titulo
                                + " (ID " + id + ")?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        // Solicitar eliminación al controlador
        boolean eliminado =  controladorLibro.eliminarProducto(id);

        if (eliminado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Producto eliminado correctamente."
            );

            // Volver a consultar MySQL
            cargarLibros();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible eliminar el Libro.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}