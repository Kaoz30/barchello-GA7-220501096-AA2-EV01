/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.barchello.modelo;

/**
 * Representa un rol del sistema Barchello Bávaro Pub.
 * Mapea la tabla rol de la base de datos barchello_db.
 * 
 * @author Jhones
 */
public class Rol {

    private int id;
    private String nombre;
    private String descripcion;

    /** Constructor sin parámetros. */
    public Rol() {
    }

    /** Constructor con parámetros.
     * @param id identificador único del rol
     * @param nombre nombre del rol
     * @param descripcion descripción del rol
     */
    public Rol(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
