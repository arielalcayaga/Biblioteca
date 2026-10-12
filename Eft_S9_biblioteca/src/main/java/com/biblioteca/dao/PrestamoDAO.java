package com.biblioteca.dao;

import com.biblioteca.model.Prestamo;

import java.util.List;

public interface PrestamoDAO {
    
        int registrarPrestamo(Prestamo venta); // retorna ID generado
        List<Prestamo> listarTodas();
        Prestamo buscarPorId(int idPrestamo);
    }