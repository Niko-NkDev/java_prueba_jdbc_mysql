package com.nikonkd.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionJDBC {
    static final String URL = "jdbc:mysql://localhost:3306/bd_prueba";
    static final String USUARIO = "admin";
    static  final String CONTRASENA = "admin123";

    // main es un metodo o funcion de ejecucion en JAVA
    public static void main(String[] args) {

        Connection conexion = null;

        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con la base de datos
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            if (conexion != null) {
                System.out.println("¡Conexión exitosa con la base de datos!");

                // Aquí puedes realizar operaciones con la base de datos
            } else {
                System.out.println("Error al establecer la conexión con la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Cerrar la conexión en el bloque finally para asegurar que se cierre incluso si ocurre una excepción
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
