package com.barchello.controller;

import com.barchello.modelo.UsuarioVO;
import com.barchello.servicios.UsuarioServicio;
import com.barchello.util.SessionUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet controlador de autenticación.
 * Maneja login (POST) y logout (GET).
 * Proyecto: Barchello Bávaro Pub
 * Evidencia: GA7-220501096-AA3-EV01
 * 
 * @author Jhones
 */
@WebServlet(name = "AuthController", urlPatterns = {"/auth"})
public class AuthController extends HttpServlet {

    private final UsuarioServicio usuarioServicio = new UsuarioServicio();

    /**
     * doGet: maneja el logout y la redirección por sesión.
     * - ?action=logout → destruye la sesión y redirige al login.
     * - Sin acción → redirige según el rol activo.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("logout".equalsIgnoreCase(action)) {
            // Destruir la sesión si existe
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            // Redirigir según el rol activo
            SessionUtils.redirigirSegunSession(request, response);
        }
    }

    /**
     * doPost: maneja el login.
     * Recibe email y password, valida con el servicio y, si son correctas,
     * crea la sesión y redirige al dashboard del rol correspondiente.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UsuarioVO usuario = usuarioServicio.login(email, password);

            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogueado", usuario);
                // Todos los roles van al mismo dashboard
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            } else {
                request.setAttribute("error", "Correo o contraseña incorrectos");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Error en el proceso de login", e);
        }
    }
}
