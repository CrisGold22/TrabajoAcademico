/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.producto;

import pe.edu.pucp.inf30.softprog.modelo.base.Registro;

/**
 *
 * @author Cristhian Horacio
 */
public class CategoriaProducto extends Registro {

    private String nombre;
    private String descripcion;
    private CategoriaProducto categoriaPadre;

    public CategoriaProducto() {
        categoriaPadre = null;
    }

    public CategoriaProducto(String nombre, String descripcion, CategoriaProducto categoriaPadre) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaPadre = categoriaPadre;
    }

    public CategoriaProducto(String nombre, String descripcion, CategoriaProducto categoriaPadre, int id, boolean activo) {
        super(id, activo);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaPadre = categoriaPadre;
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

    public CategoriaProducto getCategoriaPadre() {
        return categoriaPadre;
    }

    public void setCategoriaPadre(CategoriaProducto categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }

    
    

}
