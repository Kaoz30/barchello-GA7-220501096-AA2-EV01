<%-- 
    Página JSP que muestra la lista de usuarios del sistema.
    Proyecto: Barchello Bávaro Pub
    Evidencia: GA7-220501096-AA2-EV02
    Document   : listaUsuarios
    Created on : 3/10/2026, 5:56:04 p. m.
    Author     : Jhones
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.barchello.modelo.UsuarioVO"%>
<%@page import="java.util.List"%>
<%
    List<UsuarioVO> usuarios = (List<UsuarioVO>) request.getAttribute("usuarios");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios - Barchello Bávaro Pub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between mb-3">
            <h2>Lista de Usuarios</h2>
            <div>
                <a href="<%= request.getContextPath() %>/auth?action=logout" 
                   class="btn btn-danger">Cerrar sesión</a>
            </div>
        </div>

        <% if (usuarios != null && !usuarios.isEmpty()) { %>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>Rol ID</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (UsuarioVO u : usuarios) { %>
                        <tr>
                            <td><%= u.getId() %></td>
                            <td><%= u.getNombre() %></td>
                            <td><%= u.getEmail() %></td>
                            <td><%= u.getRol() %></td>
                            <td>
                                <a href="usuario?accion=editar&id=<%= u.getId() %>" 
                                   class="btn btn-info btn-sm">Modificar</a>
                                <form action="usuario?accion=eliminar" method="POST" 
                                      style="display:inline;"
                                      onsubmit="return confirm('¿Eliminar este usuario?');">
                                    <input type="hidden" name="id" value="<%= u.getId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No hay usuarios registrados.</p>
        <% } %>
        
        <a href="test.html" class="btn btn-success">Crear nuevo usuario</a>
    </div>
</body>
</html>
