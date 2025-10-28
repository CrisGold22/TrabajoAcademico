/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.producto;

import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.producto.utils.TipoCategoria;

/**
 *
 * @author Cristhian Horacio
 */
public class CategoriaProducto extends Registro {

    private TipoCategoria nombre;
    private String descripcion;
    private int idCategoriaPadre;

    public CategoriaProducto() {

    }

    public CategoriaProducto(TipoCategoria nombre, String descripcion, int idCategoriaPadre) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoriaPadre = idCategoriaPadre;
    }

    public CategoriaProducto(TipoCategoria nombre, String descripcion, int idCategoriaPadre, int id, boolean activo) {
        super(id, activo);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoriaPadre = idCategoriaPadre;
    }
    
    public TipoCategoria getNombre() {
        return nombre;
    }

    public void setNombre(TipoCategoria nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreString() {
        String cadena = "";

        switch (this.nombre) {
            case ABARROTES ->
                cadena = "ABARROTES";
            case BEBIDAS ->
                cadena = "BEBIDAS";
            case CUIDADO_PERSONAL ->
                cadena = "CUIDADO_PERSONA";
            case ELECTRONICA ->
                cadena = "ELECTRONICA";
            case HOGAR ->
                cadena = "HOGAR";
            case LIMPIEZA ->
                cadena = "LIMPIEZA";
            case ROPA ->
                cadena = "ROPA";
        }

        return cadena;
    }

    public void setNombreString(String nombre) {
        switch (nombre) {
            case "ABARROTES" ->
                this.nombre = TipoCategoria.ABARROTES;
            case "BEBIDAS" ->
                this.nombre = TipoCategoria.BEBIDAS;
            case "CUIDADO_PERSONAL" ->
                this.nombre = TipoCategoria.CUIDADO_PERSONAL;
            case "ELECTRONICA" ->
                this.nombre = TipoCategoria.ELECTRONICA;
            case "HOGAR" ->
                this.nombre = TipoCategoria.HOGAR;
            case "LIMPIEZA" ->
                this.nombre = TipoCategoria.LIMPIEZA;
            case "ROPA" ->
                this.nombre = TipoCategoria.ROPA;
        }
    }

    public int getIdCategoriaPadre() {
        return idCategoriaPadre;
    }

    public void setIdCategoriaPadre(int idCategoriaPadre) {
        this.idCategoriaPadre = idCategoriaPadre;
    }
    
    

}
