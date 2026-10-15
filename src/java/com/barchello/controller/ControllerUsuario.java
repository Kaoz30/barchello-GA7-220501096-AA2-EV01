package com.barchello.controller;

import com.barchello.modelo.Rol;
import com.barchello.modelo.UsuarioVO;
import com.barchello.servicios.UsuarioServicio;
import com.barchello.util.SessionUtils;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet controlador para la gestión de usuarios.
 * Maneja peticiones GET (listar, editar) y POST (crear, actualizar, eliminar).
 * Proyecto: Barchello Bávaro Pub
 * Evidencia: GA7-220501096-AA3-EV01
 * 
 * @author Jhones
 */
@WebServlet(name = "ControllerUsuario", urlPatterns = {"/usuario"})
public class ControllerUsuario extends HttpServlet {

    private final UsuarioServicio usuarioServicio = new UsuarioServicio();

    /**
     * doGet: maneja listar y editar.
     * Verifica sesión activa y permiso para el módulo "usuarios".
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Verificar sesión activa
        if (!SessionUtils.estaLogueado(request)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        // 2. Verificar permiso para el módulo "usuarios"
        if (!SessionUtils.tienePermiso(request, "usuarios")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                "No tienes permiso para acceder a este módulo.");
            return;
        }

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                listarUsuarios(request, response);
                break;
            case "editar":
                editarUsuario(request, response);
                break;
            default:
                listarUsuarios(request, response);
        }
    }

    /**
     * doPost: maneja crear, actualizar y eliminar.
     * Verifica sesión activa y permiso para el módulo "usuarios".
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Verificar sesión activa
        if (!SessionUtils.estaLogueado(request)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        // 2. Verificar permiso para el módulo "usuarios"
        if (!SessionUtils.tienePermiso(request, "usuarios")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                "No tienes permiso para acceder a este módulo.");
            return;
        }

        String accion = request.getParameter("accion");
        if (accion == null) accion = "crear";

        switch (accion) {
            case "crear":
                crearUsuario(request, response);
                break;
            case "actualizar":
                actualizarUsuario(request, response);
                break;
            case "eliminar":
                eliminarUsuario(request, response);
                break;
            default:
                response.sendRedirect("usuario?accion=listar");
        }
    }

    // ============ LISTAR ============
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UsuarioVO> usuarios = usuarioServicio.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/listaUsuarios.jsp").forward(request, response);
    }

    // ============ EDITAR (formulario) ============
    private void editarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UsuarioVO usuario = usuarioServicio.buscarPorId(id);
        List<Rol> roles = usuarioServicio.listarRoles();
        request.setAttribute("usuarioEditar", usuario);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/editarUser.jsp").forward(request, response);
    }

    // ============ CREAR ============
    private void crearUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");
        int rol = Integer.parseInt(request.getParameter("rol"));

        UsuarioVO usuario = new UsuarioVO(nombre, email, clave, rol);
        usuarioServicio.crearUsuario(usuario);
        response.sendRedirect("usuario?accion=listar");
    }

    // ============ ACTUALIZAR ============
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        int rol = Integer.parseInt(request.getParameter("rol"));

        // 1. Recuperar el usuario actual desde la BD (trae su contraseña original)
        UsuarioVO usuario = usuarioServicio.buscarPorId(id);
        if (usuario == null) {
            response.sendRedirect("usuario?accion=listar");
            return;
        }

        // 2. Modificar SOLO los campos que el formulario envía
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setRol(rol);
        // NO se toca usuario.setClave(...) → la contraseña se mantiene intacta

        // 3. Guardar los cambios
        usuarioServicio.actualizarUsuario(usuario);
        response.sendRedirect("usuario?accion=listar");
    }

    // ============ ELIMINAR ============
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioServicio.eliminarUsuario(id);
        response.sendRedirect("usuario?accion=listar");
    }
}