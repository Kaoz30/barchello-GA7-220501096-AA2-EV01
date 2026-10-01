/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.barchello.controller;

import com.barchello.modelo.UsuarioVO;
import com.barchello.servicios.UsuarioServicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet controlador para la gestión de usuarios.
 * Maneja peticiones GET (listar) y POST (crear).
 * Proyecto: Barchello Bávaro Pub
 * Evidencia: GA7-220501096-AA2-EV01
 * 
 * @author Jhones
 */
@WebServlet(name = "ControllerUsuario", urlPatterns = {"/usuario"})
public class ControllerUsuario extends HttpServlet {

    private final UsuarioServicio usuarioServicio = new UsuarioServicio();

    /**
     * Maneja peticiones GET (listar usuarios).
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if ("listar".equals(accion)) {
            List<UsuarioVO> usuarios = usuarioServicio.listarUsuarios();

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Usuarios</title></head><body>");
            out.println("<h1>Lista de Usuarios</h1>");
            out.println("<ul>");
            if (usuarios != null) {
                for (UsuarioVO u : usuarios) {
                    out.println("<li>" + u.getId() + " - " + u.getNombre()
                            + " (" + u.getEmail() + ")</li>");
                }
            }
            out.println("</ul>");
            out.println("</body></html>");
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Controller</title></head><body>");
            out.println("<h1>ControllerUsuario funcionando</h1>");
            out.println("<p><a href='?accion=listar'>Listar usuarios</a></p>");
            out.println("</body></html>");
        }
    }

    /**
     * Maneja peticiones POST (crear usuario).
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");
        int rol = Integer.parseInt(request.getParameter("rol"));

        UsuarioVO usuario = new UsuarioVO(nombre, email, clave, rol);
        boolean exito = usuarioServicio.crearUsuario(usuario);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (exito) {
            out.println("<h1>Usuario creado exitosamente</h1>");
        } else {
            out.println("<h1>Error al crear usuario</h1>");
        }
    }
}
