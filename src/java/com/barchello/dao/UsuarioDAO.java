/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.barchello.dao;

import com.barchello.config.ConexionBD;
import com.barchello.modelo.UsuarioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para la tabla usuario.
 * Implementa las operaciones CRUD con JDBC.
 * Proyecto: Barchello Bávaro Pub
 * Evidencia: GA7-220501096-AA2-EV01
 * 
 * @author Jhones
 */
public class UsuarioDAO {

    // ================== CREATE ==================
    /**
     * Inserta un nuevo usuario en la base de datos.
     * @param usuario objeto con los datos del usuario
     * @return true si se insertó correctamente
     * @throws SQLException si hay error en la operación
     */
    public boolean crearUsuario(UsuarioVO usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nombre, email, password, estado, rol_id) VALUES (?, ?, ?, ?, ?)";

        // Paso 6: Crear PreparedStatement
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getClave());
            ps.setString(4, "activo");
            ps.setInt(5, usuario.getRol());

            // Paso 7: Ejecutar sentencia SQL
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error en crearUsuario: " + e.getMessage());
            throw e;
        }
    }

    // ================== READ (Listar todos) ==================
    /**
     * Obtiene todos los usuarios registrados.
     * @return lista de usuarios
     * @throws SQLException si hay error en la consulta
     */
    public List<UsuarioVO> listarUsuarios() throws SQLException {
        List<UsuarioVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Paso 8: Procesar ResultSet
            while (rs.next()) {
                UsuarioVO u = new UsuarioVO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getInt("rol_id")
                );
                lista.add(u);
            }
            return lista;
        } catch (SQLException e) {
            System.err.println("Error en listarUsuarios: " + e.getMessage());
            throw e;
        }
    }

    // ================== READ (Buscar por ID) ==================
    /**
     * Busca un usuario por su identificador.
     * @param id identificador del usuario
     * @return objeto UsuarioVO o null si no existe
     * @throws SQLException si hay error en la consulta
     */
    public UsuarioVO buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UsuarioVO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("rol_id")
                    );
                }
            }
            return null;
        }
    }

    // ================== UPDATE ==================
    /**
     * Actualiza los datos de un usuario existente.
     * @param usuario objeto con los datos actualizados
     * @return true si se actualizó correctamente
     * @throws SQLException si hay error en la operación
     */
    public boolean actualizarUsuario(UsuarioVO usuario) throws SQLException {
        String sql = "UPDATE usuario SET nombre = ?, email = ?, password = ?, rol_id = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getClave());
            ps.setInt(4, usuario.getRol());
            ps.setInt(5, usuario.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }

    // ================== DELETE ==================
    /**
     * Elimina un usuario por su identificador.
     * @param id identificador del usuario
     * @return true si se eliminó correctamente
     * @throws SQLException si hay error en la operación
     */
    public boolean eliminarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }
}
