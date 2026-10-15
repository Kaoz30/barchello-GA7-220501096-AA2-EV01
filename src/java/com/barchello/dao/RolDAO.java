package com.barchello.dao;

import com.barchello.config.ConexionBD;
import com.barchello.modelo.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para la tabla rol.
 * Proyecto: Barchello Bávaro Pub
 * Evidencia: GA7-220501096-AA3-EV01
 * 
 * @author Jhones
 */
public class RolDAO {

    /**
     * Lista todos los roles registrados en la base de datos.
     * @return lista de roles
     * @throws SQLException si hay error en la consulta
     */
    public List<Rol> listarRoles() throws SQLException {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion FROM rol ORDER BY id";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Rol rol = new Rol(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
                lista.add(rol);
            }
        } catch (SQLException e) {
            System.err.println("Error en listarRoles: " + e.getMessage());
            throw e;
        }
        return lista;
    }

    /**
     * Obtiene el nombre del rol a partir de su ID.
     * @param idRol identificador del rol
     * @return nombre del rol o null si no existe
     * @throws SQLException si hay error en la consulta
     */
    public String obtenerNombreRol(int idRol) throws SQLException {
        String sql = "SELECT nombre FROM rol WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                }
            }
        }
        return null;
    }
} 

