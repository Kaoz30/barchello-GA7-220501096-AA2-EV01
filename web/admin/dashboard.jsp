<%-- 
    Document   : dashboard
    Created on : 15/10/2026, 7:44:40 a. m.
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
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Administrador - Barchello Bávaro Pub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <span class="navbar-brand">Barchello Bávaro Pub</span>
            <div class="text-white">
                Hola, <strong><%= usuario.getNombre() %></strong>
                <a href="<%= request.getContextPath() %>/auth?action=logout" 
                   class="btn btn-danger btn-sm ms-3">Cerrar sesión</a>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h2>Panel de Administración</h2>
        <p>Desde aquí puedes gestionar usuarios, pedidos, reservas y más.</p>

        <div class="row mt-4">
            <div class="col-md-4 mb-3">
                <a href="<%= request.getContextPath() %>/usuario?accion=listar" 
                   class="btn btn-primary w-100 py-3">Gestionar Usuarios</a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="#" class="btn btn-outline-secondary w-100 py-3">Gestionar Pedidos (Próx.)</a>
            </div>
            <div class="col-md-4 mb-3">
                <a href="#" class="btn btn-outline-secondary w-100 py-3">Gestionar Reservas (Próx.)</a>
            </div>
        </div>
    </div>
</body>
</html>

