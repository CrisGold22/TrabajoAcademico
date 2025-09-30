/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.producto;

import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.producto.utils.TipoCategoria;

/**
 *
 * @author Cristhian Horacio
 */
public class CategoriaProductoDTO extends RegistroDTO{
    private TipoCategoria nombre;
    private String descripcion;
    
    public CategoriaProductoDTO(){
        
    }

    public CategoriaProductoDTO(TipoCategoria nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public CategoriaProductoDTO(TipoCategoria nombre, String descripcion, int id, boolean activo) {
        super(id, activo);
        this.nombre = nombre;
        this.descripcion = descripcion;
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
    
    
}
