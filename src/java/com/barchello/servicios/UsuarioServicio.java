package com.barchello.servicios;

import com.barchello.dao.RolDAO;
import com.barchello.dao.UsuarioDAO;
import com.barchello.modelo.Rol;
import com.barchello.modelo.UsuarioVO;
import java.sql.SQLException;
import java.util.List;

/**
 * Capa de lógica de negocio para la gestión de usuarios.
 * Aplica validaciones antes de acceder a la base de datos.
 * Proyecto: Barchello Bávaro Pub
 * Evidencia: GA7-220501096-AA3-EV01
 * 
 * @author Jhones
 */
public class UsuarioServicio {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final RolDAO rolDAO = new RolDAO();

    /**
     * Valida y crea un nuevo usuario.
     */
    public boolean crearUsuario(UsuarioVO usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío.");
            return false;
        }
        if (usuario.getNombre().length() < 3) {
            System.err.println("El nombre debe tener al menos 3 caracteres.");
            return false;
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            System.err.println("Email inválido.");
            return false;
        }
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
     */
    public UsuarioVO buscarPorId(int id) {
        try {
            return usuarioDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     * Valida nombre, email y rol antes de llamar al DAO.
     */
    public boolean actualizarUsuario(UsuarioVO usuario) {
        if (usuario.getId() <= 0) {
            System.err.println("ID de usuario inválido.");
            return false;
        }
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío.");
            return false;
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            System.err.println("Email inválido.");
            return false;
        }
        try {
            return usuarioDAO.actualizarUsuario(usuario);
        } catch (SQLException e) {
            System.err.println("Error de BD al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un usuario por su ID.
     */
    public boolean eliminarUsuario(int id) {
        try {
            return usuarioDAO.eliminarUsuario(id);
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista todos los roles disponibles.
     */
    public List<Rol> listarRoles() {
        try {
            return rolDAO.listarRoles();
        } catch (SQLException e) {
            System.err.println("Error al listar roles: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método login: valida las credenciales del usuario.
     * Devuelve el UsuarioVO si las credenciales son correctas, null en caso contrario.
     * 
     * NOTA: Para el login se usa email + clave.
     * Se recorre la lista de usuarios y se busca coincidencia.
     * (En producción esto se optimizaría con una consulta SQL específica).
     */
    public UsuarioVO login(String email, String clave) {
        if (email == null || clave == null || email.trim().isEmpty()) {
            return null;
        }
        try {
            List<UsuarioVO> usuarios = usuarioDAO.listarUsuarios();
            if (usuarios == null) {
                return null;
            }
            for (UsuarioVO u : usuarios) {
                if (u.getEmail().equalsIgnoreCase(email.trim()) 
                    && u.getClave().equals(clave)) {
                    return u;
                }
            }
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return null;
    }
}
