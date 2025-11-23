/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.producto;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.producto.DescuentoDAO;
import pe.edu.pucp.inf30.softprog.dao.producto.ProductoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.DescuentoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.ProductoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.producto.Descuento;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.DescuentoBO;

/**
 *
 * @author Cristhian Horacio
 */
public class DescuentoBOImpl implements DescuentoBO{    
    private final DescuentoDAO descuentoDAO;
    private final ProductoDAO productoDAO;

    public DescuentoBOImpl(){
        descuentoDAO = new DescuentoDAOImpl();
        productoDAO = new ProductoDAOImpl();
    }
    
    @Override
    public List<Descuento> listar() {
        return this.descuentoDAO.leerTodos();
    }

    @Override
    public void insertar(Descuento modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("El Descuento no puede ser nulo");
        }      
        
        if(modelo.getProducto() == null){
            throw new IllegalArgumentException("El Producto asociado no puede ser nulo");
        }
        
        this.descuentoDAO.crear(modelo);
    }

    @Override
    public void actualizar(Descuento modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("El Descuento no puede ser nulo");
        }      
        
        if(modelo.getProducto() == null){
            throw new IllegalArgumentException("El Producto asociado no puede ser nulo");
        }
        
        if(modelo.getId() <= 0){
            throw new IllegalArgumentException("Descuento con ID inválido");
        }
        
        Descuento descuento = this.descuentoDAO.leer(modelo.getId());
        
        if(descuento == null){
            throw new RuntimeException("No se encontro el Descuento con ID: "+ modelo.getId());
        }
        
        this.descuentoDAO.actualizar(modelo);
    }

    @Override
    public Descuento obtener(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Descuento con ID inválido");
        }
        
        Descuento descuento = this.descuentoDAO.leer(id);
        
        if(descuento == null){
            throw new RuntimeException("No se encontro el Descuento con ID: "+ id);
        }        
        
        return descuento;
    }

    @Override
    public void eliminar(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Descuento con ID inválido");
        }
        
        Descuento descuento = this.descuentoDAO.leer(id);
        
        if(descuento == null){
            throw new RuntimeException("No se encontro el Descuento con ID: "+ id);
        }  
        
        this.descuentoDAO.eliminar(id);
    }

    @Override
    public boolean actualizarDescuentoPorIdProducto(Integer id, Descuento descuento) {
        if(id <= 0){
            throw new IllegalArgumentException("Descuento con ID inválido");
        }
        
        if(descuento == null){
            throw new RuntimeException("No se encontro el Producto asociado con ID: "+ id);
        }
        
        return this.descuentoDAO.actualizarDescuentoPorIdProducto(id, descuento);
    }
    
}
