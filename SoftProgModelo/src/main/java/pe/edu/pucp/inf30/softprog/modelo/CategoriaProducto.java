/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo;

/**
 *
 * @author Cristhian Horacio
 */
public class CategoriaProducto {
    private int idCategoriaProd;
    private String nombreCategoria;
    private String descripcionCategoria;
    
    public CategoriaProducto(){
        
    }

    public CategoriaProducto(int idCategoriaProd, String nombreCategoria, String descripcionCategoria) {
        this.idCategoriaProd = idCategoriaProd;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
    }

    public int getIdCategoriaProd() {
        return idCategoriaProd;
    }

    public void setIdCategoriaProd(int idCategoriaProd) {
        this.idCategoriaProd = idCategoriaProd;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }
    
    
}
