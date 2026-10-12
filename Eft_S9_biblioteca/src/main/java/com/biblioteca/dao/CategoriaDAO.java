package com.biblioteca.dao;

import com.biblioteca.model.Categoria;

import java.util.List;

public interface CategoriaDAO {

        void insertar(Categoria categoria);
        void actualizar(Categoria categoria);
        void eliminar(int id);
        Categoria buscarPorId(int id);
        List<Categoria> listarTodas();
    }