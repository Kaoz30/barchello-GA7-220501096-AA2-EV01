<%-- 
    Document   : dashboard
    Created on : 15/10/2026, 10:46:59 a. m.
    Author     : Jhones
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.barchello.modelo.UsuarioVO"%>
<%@page import="com.barchello.util.SessionUtils"%>
<%
    UsuarioVO usuario = SessionUtils.obtenerUsuarioLogueado(request);
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    String rol = usuario.getNombreRol();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Barchello Bávaro Pub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #EFEBE9; }
        .navbar { background: #3E2723; }
        .btn-modulo {
            background: #8D6E63; color: white; border: none;
            border-radius: 12px; padding: 20px; text-align: center;
            text-decoration: none; display: block; transition: all 0.2s;
        }
        .btn-modulo:hover { background: #6d4c41; color: white; transform: translateY(-2px); }
        .btn-modulo .icono { font-size: 2rem; display: block; margin-bottom: 8px; }
    </style>
</head>
<body>
    <nav class="navbar navbar-dark">
        <div class="container-fluid">
            <span class="navbar-brand">🍺 Barchello Bávaro Pub</span>
            <div class="text-white">
                Hola, <strong><%= usuario.getNombre() %></strong>
                <span class="badge bg-light text-dark ms-2"><%= rol %></span>
                <a href="<%= request.getContextPath() %>/auth?action=logout"
                   class="btn btn-danger btn-sm ms-3">Cerrar sesión</a>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h2 class="text-cuero">Panel de Control</h2>
        <p class="text-muted">Bienvenido, <strong><%= usuario.getNombre() %></strong>. 
           Los módulos que ves a continuación dependen de tu rol activo.</p>

        <div class="row mt-4">
            <%-- Módulo Usuarios: solo admin --%>
            <% if (SessionUtils.tienePermiso(request, "usuarios")) { %>
                <div class="col-md-3 mb-3">
                    <a href="<%= request.getContextPath() %>/usuario?accion=listar"
                       class="btn-modulo">
                        <span class="icono">👥</span>Gestionar Usuarios
                    </a>
                </div>
            <% } %>

            <%-- Módulo Menú: admin, mesero, cocinero, bartender --%>
            <% if (SessionUtils.tienePermiso(request, "menu")) { %>
                <div class="col-md-3 mb-3">
                    <a href="#" class="btn-modulo">
                        <span class="icono">📖</span>Menú
                    </a>
                </div>
            <% } %>

            <%-- Módulo Reservas: admin, mesero --%>
            <% if (SessionUtils.tienePermiso(request, "reservas")) { %>
                <div class="col-md-3 mb-3">
                    <a href="#" class="btn-modulo">
                        <span class="icono">📅</span>Reservas
                    </a>
                </div>
            <% } %>

            <%-- Módulo Cocina: admin, cocinero, bartender --%>
            <% if (SessionUtils.tienePermiso(request, "cocina")) { %>
                <div class="col-md-3 mb-3">
                    <a href="#" class="btn-modulo">
                        <span class="icono">🔥</span>Cocina &amp; Bar
                    </a>
                </div>
            <% } %>

            <%-- Módulo Caja: admin, cajero --%>
            <% if (SessionUtils.tienePermiso(request, "caja")) { %>
                <div class="col-md-3 mb-3">
                    <a href="#" class="btn-modulo">
                        <span class="icono">💰</span>Caja
                    </a>
                </div>
            <% } %>

            <%-- Módulo Reportes: solo admin --%>
            <% if (SessionUtils.tienePermiso(request, "reportes")) { %>
                <div class="col-md-3 mb-3">
                    <a href="#" class="btn-modulo">
                        <span class="icono">📊</span>Reportes
                    </a>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>
