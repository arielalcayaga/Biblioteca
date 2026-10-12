package com.biblioteca.controlador;

import com.biblioteca.conexion.DatabaseConnection;

import com.biblioteca.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorUsuario {

    public boolean validarUsuario(
            String correo,
            String contrasenia) {

        String sql = "SELECT * FROM usuarios " + "WHERE correo = ? AND contrasenia = ?";

        try (
                Connection conexion = DatabaseConnection.obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            // Asignar parámetros
            ps.setString(1, correo);
            ps.setString(2, contrasenia);

            // Ejecutar consulta
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println( "Error al validar usuario: "+ e.getMessage() );
            return false;
        }
    }
}
