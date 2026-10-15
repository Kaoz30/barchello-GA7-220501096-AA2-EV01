<%-- 
    Document   : editarUser
    Created on : 15/10/2026, 8:04:43 a. m.
    Author     : Jhones
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.barchello.modelo.UsuarioVO"%>
<%@page import="com.barchello.modelo.Rol"%>
<%@page import="java.util.List"%>
<%
    UsuarioVO userEditar = (UsuarioVO) request.getAttribute("usuarioEditar");
    List<Rol> roles = (List<Rol>) request.getAttribute("roles");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario - Barchello Bávaro Pub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Modificar Usuario</h2>
        <form action="usuario?accion=actualizar" method="POST">
            <input type="hidden" name="id" value="<%= userEditar.getId() %>">
            
            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input type="text" class="form-control" name="nombre" 
                       value="<%= userEditar.getNombre() %>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" class="form-control" name="email" 
                       value="<%= userEditar.getEmail() %>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Rol</label>
                <select class="form-select" name="rol" required>
                    <% for (Rol r : roles) { %>
                        <option value="<%= r.getId() %>" 
                            <%= r.getId() == userEditar.getRol() ? "selected" : "" %>>
                            <%= r.getNombre() %>
                        </option>
                    <% } %>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Actualizar</button>
            <a href="usuario?accion=listar" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>

