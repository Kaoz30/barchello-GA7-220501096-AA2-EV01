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
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Usuarios - Barchello Bávaro Pub</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #EFEBE9;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: white;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            padding: 30px;
        }
        h1 {
            color: #3E2723;
            border-bottom: 3px solid #8D6E63;
            padding-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th {
            background: #8D6E63;
            color: white;
            padding: 12px;
            text-align: left;
        }
        td {
            padding: 10px 12px;
            border-bottom: 1px solid #EFEBE9;
            color: #3E2723;
        }
        tr:hover {
            background: #FAFAFA;
        }
        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #8D6E63;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
        }
        .btn:hover {
            background: #6d4c41;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Lista de Usuarios</h1>
        
        <%-- Recuperar la lista enviada por el Servlet --%>
        <%
            List<UsuarioVO> usuarios = (List<UsuarioVO>) request.getAttribute("usuarios");
            
            if (usuarios != null && !usuarios.isEmpty()) {
        %>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>ID Rol</th>
                </tr>
            </thead>
            <tbody>
                <%-- Iterar sobre los usuarios con un scriptlet --%>
                <% for (UsuarioVO usuario : usuarios) { %>
                <tr>
                    <td><%= usuario.getId() %></td>
                    <td><%= usuario.getNombre() %></td>
                    <td><%= usuario.getEmail() %></td>
                    <td><%= usuario.getRol() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        
        <% } else { %>
        
        <p>No hay usuarios registrados en la base de datos.</p>
        
        <% } %>
        
        <a href="test.html" class="btn">Crear nuevo usuario</a>
    </div>
</body>
</html>
