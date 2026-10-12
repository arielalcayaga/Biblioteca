package com.biblioteca.controlador;

import com.biblioteca.dao.LibroDAO;
import com.biblioteca.model.Libro;

import java.util.ArrayList;
import java.util.List;

public class ControladorLibro {

    private final List<Libro> libros = new ArrayList<>();

    private final LibroDAO libroDAO = new LibroDAO() {
        @Override
        public void agregar(Libro libro) {

        }

        @Override
        public void actualizar(Libro libro) {

        }

        @Override
        public void eliminar(int idLibro) {

        }

        @Override
        public Libro buscarPorId(int idLibro) {
            return null;
        }

        @Override
        public List<Libro> listarTodos() {
            return List.of();
        }

        @Override
        public List<Libro> buscarPorNombre(String nombre) {
            return List.of();
        }
    };

    public boolean agregar(Libro libro) {

        boolean registrado = libroDAO.agregar(libro);
        if (registrado) {
            libros.add(libro);
        }
        return registrado;
    }


    public List<Libro> getLibros() {
        return libroDAO.listar();
    }


    /**
     * Actualizar los productos registrados.
     */
    public boolean actualizarLibro(Libro libro) {

        return libroDAO.actualizar(libro);
    }

    /**
     * Eliminar los libros registrados.
     */
    public boolean eliminarLibro(int id) {

        return libroDAO.eliminar(id);
    }

    /**
     * Verifica si un ID ya está registrado.
     *
     * @param id identificador que se desea buscar.
     * @return true si el ID existe, false en caso contrario.
     */
    public boolean existeId(int id) {

        for (Libro libro : libros) {

            if (Libro.getId() == id) {
                return true;
            }
        }

        return false;
    }
}