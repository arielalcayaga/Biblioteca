package com.biblioteca.dao;

import com.biblioteca.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

        Usuario autenticar(String correo, String contrasenia); // Verifica credenciales y retorna usuario válido
        void insertar(Usuario usuario);       // Agrega un nuevo usuario
        void actualizar(Usuario usuario);     // Modifica un usuario existente
        void eliminar(int idUsuario);         // Elimina un usuario (opcional: lógica)
        Usuario buscarPorId(int idUsuario);   // Busca usuario por ID
        List<Usuario> listarTodos(); // Lista todos los usuarios
    }
