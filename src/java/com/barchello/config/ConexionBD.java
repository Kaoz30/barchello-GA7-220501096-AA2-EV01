/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.barchello.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de configuración para la conexión a la base de datos MySQL.
 * Proyecto: Barchello Bávaro Pub
 * Evidencia: GA7-220501096-AA2-EV01
 *
 * @author Jhones
 */
public class ConexionBD {

    // Parámetros de conexión (ajusta la contraseña)
    private static final String URL = "jdbc:mysql://localhost:3306/barchello_ev01?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    /** Constructor privado para evitar instanciación. */
    private ConexionBD() {
    }

    /**
     * Establece y retorna una conexión con la base de datos.
     * @return objeto Connection o null si falla
     * @throws SQLException si hay error en la conexión
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Paso 4: Inicializar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Paso 5: Crear el objeto Connection con DriverManager
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC de MySQL.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método main para probar la conexión.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos barchello_db");
                conn.close();
            } else {
                System.out.println("Error al conectar");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
