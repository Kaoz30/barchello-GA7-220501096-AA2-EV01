/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.barchello.modelo;

/**
 * Value Object que representa un usuario del sistema Barchello Bávaro Pub.
 * Mapea la tabla usuario de la base de datos barchello_db.
 * 
 * @author Jhones
 */
public class UsuarioVO {

    private int id;
    private String nombre;
    private String email;
    private String clave;
    private int rol;

    /** Constructor sin parámetros (para el método actualizar) */
    public UsuarioVO() {
    }

    /**
     * Constructor para INSERT (el ID lo genera MySQL).
     * @param nombre nombre completo del usuario
     * @param email correo electrónico
     * @param clave contraseña (simplificación académica)
     * @param rol ID del rol asociado
     */
    public UsuarioVO(String nombre, String email, String clave, int rol) {
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.rol = rol;
    }

    /**
     * Constructor completo (para SELECT,para el método buscarPorId).
     * @param id identificador único
     * @param nombre nombre completo
     * @param email correo electrónico
     * @param clave contraseña
     * @param rol ID del rol
     */
    public UsuarioVO(int id, String nombre, String email, String clave, int rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.rol = rol;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
    
    /**
     * Devuelve el nombre del rol según su ID.
     * Se puede obtener con un switch o con un RolDAO
     * Los IDs corresponden a la tabla rol de la base de datos barchello_ev01:
     * 1 = admin, 2 = mesero, 3 = cocinero, 4 = cajero, 5 = bartender
     * 
     * @return el nombre del rol en minúsculas, o "default" si el ID no coincide
     */
    public String getNombreRol() {
        switch (this.rol) {
            case 1: return "admin";
            case 2: return "mesero";
            case 3: return "cocinero";
            case 4: return "cajero";
            case 5: return "bartender";
            default: return "default";
        }
    }
}
