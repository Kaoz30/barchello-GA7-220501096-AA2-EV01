/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.barchello.servicios;

import com.barchello.dao.UsuarioDAO;
import com.barchello.modelo.UsuarioVO;
import java.sql.SQLException;
import java.util.List;

/**
 * Capa de lógica de negocio para la gestión de usuarios.
 * Aplica validaciones antes de acceder a la base de datos.
 * Proyecto: Barchello Bávaro Pub
 * Evidencia: GA7-220501096-AA2-EV01
 * 
 * @author Jhones
 */
public class UsuarioServicio {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Valida y crea un nuevo usuario.
     * @param usuario objeto con los datos del usuario
     * @return true si se creó correctamente
     */
    public boolean crearUsuario(UsuarioVO usuario) {
        // Validación: nombre no vacío
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío.");
            return false;
        }
        // Validación: nombre mínimo 3 caracteres
        if (usuario.getNombre().length() < 3) {
            System.err.println("El nombre debe tener al menos 3 caracteres.");
            return false;
        }
        // Validación: email debe contener @
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            System.err.println("Email inválido.");
            return false;
        }
        // Validación: contraseña mínimo 4 caracteres
        if (usuario.getClave() == null || usuario.getClave().length() < 4) {
            System.err.println("La contraseña debe tener al menos 4 caracteres.");
            return false;
        }

        try {
            return usuarioDAO.crearUsuario(usuario);
        } catch (SQLException e) {
            System.err.println("Error de BD al crear usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos los usuarios registrados.
     * @return lista de usuarios o null si hay error
     */
    public List<UsuarioVO> listarUsuarios() {
        try {
            return usuarioDAO.listarUsuarios();
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca un usuario por su ID.
     * @param id identificador del usuario
     * @return objeto UsuarioVO o null
     */
    public UsuarioVO buscarPorId(int id) {
        try {
            return usuarioDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
            return null;
        }
    }
}
