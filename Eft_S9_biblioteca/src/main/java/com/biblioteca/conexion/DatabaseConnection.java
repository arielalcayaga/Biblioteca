package com.biblioteca.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    /**
     * Clase encargada de gestionar la conexión con la base de datos MySQL.
     */
    public class DatabaseConnection {

        // Cambia estos valores según tu configuración local
        private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
        private static final String USUARIO = "root";
        private static final String CONTRASENA = "12345"; // <-- AJUSTA ESTO SEGÚN TU CONFIGURACIÓN

        public static Connection obtenerConexion() throws SQLException {
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        }
    }