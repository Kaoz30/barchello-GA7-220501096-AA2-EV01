package com.barchello.util;

import com.barchello.modelo.UsuarioVO;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Utilidad para el manejo de sesiones y permisos (RBAC).
 * Proyecto: Barchello Bávaro Pub
 * Evidencia: GA7-220501096-AA3-EV01
 * 
 * @author Jhones
 */
public class SessionUtils {

    private SessionUtils() {}

    /**
     * Tabla de permisos por rol. El backend es la fuente de verdad.
     * El frontend (JSP) replica esta tabla para mostrar/ocultar botones.
     */
    private static final Map<String, List<String>> PERMISOS = new HashMap<>();
    static {
        PERMISOS.put("admin",     Arrays.asList(
            "usuarios", "menu", "inventario", "compras", "caja", "reportes", "cocina", "reservas"
        ));
        PERMISOS.put("mesero",    Arrays.asList("reservas", "menu"));
        PERMISOS.put("cocinero",  Arrays.asList("cocina", "menu"));
        PERMISOS.put("cajero",    Arrays.asList("caja", "ventas"));
        PERMISOS.put("bartender", Arrays.asList("cocina", "menu"));
    }

    public static boolean estaLogueado(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;
        UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuarioLogueado");
        return usuario != null;
    }

    public static UsuarioVO obtenerUsuarioLogueado(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return null;
        return (UsuarioVO) session.getAttribute("usuarioLogueado");
    }

    public static boolean tienePermiso(HttpServletRequest request, String modulo) {
        UsuarioVO usuario = obtenerUsuarioLogueado(request);
        if (usuario == null) return false;
        String rol = usuario.getNombreRol();
        List<String> modulosPermitidos = PERMISOS.get(rol);
        return modulosPermitidos != null && modulosPermitidos.contains(modulo);
    }

    public static void redirigirSegunSession(HttpServletRequest request,
                                              HttpServletResponse response)
            throws IOException {
        String contextPath = request.getContextPath();
        if (estaLogueado(request)) {
            response.sendRedirect(contextPath + "/dashboard.jsp");
        } else {
            response.sendRedirect(contextPath + "/index.jsp");
        }
    }
}