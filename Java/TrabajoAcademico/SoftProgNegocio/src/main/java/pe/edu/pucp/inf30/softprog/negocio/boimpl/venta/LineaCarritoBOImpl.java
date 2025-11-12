/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.venta;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaCarritoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.LineaCarritoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarrito;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.LineaCarritoBO;

/**
 *
 * @author 
 */
public class LineaCarritoBOImpl implements LineaCarritoBO {
    private final LineaCarritoDAO lineaCarritoDAO;
    
    public LineaCarritoBOImpl(){
        lineaCarritoDAO = new LineaCarritoDAOImpl();
    }
    @Override
    public List<LineaCarrito> listar() {
        return this.lineaCarritoDAO.leerTodos();
    }

    @Override
    public void insertar(LineaCarrito modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Linea Carrito no puede ser nula");
        }
        
        if(modelo.getProducto() == null){
            throw new IllegalArgumentException("El Producto no puede ser nulo");
        }
        
        this.lineaCarritoDAO.crear(modelo);
    }

    @Override
    public void actualizar(LineaCarrito modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Linea Carrito no puede ser nula");
        }
        
        if(modelo.getProducto() == null){
            throw new IllegalArgumentException("El Producto no puede ser nulo");
        }

        if(modelo.getId()<=0){
            throw new IllegalArgumentException("Linea Carrito con ID invalido");
        }
        
        LineaCarrito linea = this.lineaCarritoDAO.leer(modelo.getId());
        
        if(linea == null){
            throw new RuntimeException("No se encontro la Linea Carrito con ID: "+ modelo.getId());
        }
        
        this.lineaCarritoDAO.actualizar(modelo);
    }

    @Override
    public LineaCarrito obtener(int id) {
        if(id<=0){
            throw new IllegalArgumentException("Linea Carrito con ID invalido");
        }
        
        LineaCarrito linea = this.lineaCarritoDAO.leer(id);
        
        if(linea == null){
            throw new RuntimeException("No se encontro la Linea Carrito con ID: "+ id);
        }
        
        return linea;
    }

    @Override
    public void eliminar(int id) {
        if(id<=0){
            throw new IllegalArgumentException("Linea Carrito con ID invalido");
        }
        
        LineaCarrito linea = this.lineaCarritoDAO.leer(id);
        
        if(linea == null){
            throw new RuntimeException("No se encontro la Linea Carrito con ID: "+ id);
        }
        
        this.lineaCarritoDAO.eliminar(id);
    }
	
	@Override
    public List<LineaCarrito> listarPorIdCarrito(int id) {
        return this.lineaCarritoDAO.listarPorIdCarrito(id);
    }
    
}
