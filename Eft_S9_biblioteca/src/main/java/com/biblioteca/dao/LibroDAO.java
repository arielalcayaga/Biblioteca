package com.biblioteca.dao;


import com.biblioteca.model.Libro;

import java.util.List;

public interface LibroDAO {
    
        void agregar(Libro libro);               // Agrega un nuevo libro
        void actualizar(Libro libro);             // Modifica un libro existente
        void eliminar(int idLibro);                  // Elimina un libro por su ID
        Libro buscarPorId(int idLibro);           // Busca un libro por ID
        List<Libro> listarTodos();                   // Lista todos los libros
        List<Libro> buscarPorNombre(String nombre);  // Busca libros que coincidan por nombre
    }