/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.venta;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaOrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.LineaOrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.LineaOrdenCompraBO;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaOrdenCompraBOImpl implements LineaOrdenCompraBO{
    private final LineaOrdenCompraDAO lineaOrdenCompraDAO;
    
    public LineaOrdenCompraBOImpl(){
        lineaOrdenCompraDAO = new LineaOrdenCompraDAOImpl();
    }
    
    @Override
    public List<LineaOrdenCompra> listar() {
        return this.lineaOrdenCompraDAO.leerTodos();
    }

    @Override
    public void insertar(LineaOrdenCompra modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Linea Orden Compra no puede ser nula");
        }        
        
        if(modelo.getOrdenCompra() == null){
            throw new IllegalArgumentException("El Orden Compra asociado no puede ser nulo");            
        }
        
        this.lineaOrdenCompraDAO.crear(modelo);
    }

    @Override
    public void actualizar(LineaOrdenCompra modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Linea Orden Compra no puede ser nula");
        }   
        
        if(modelo.getId()<=0){
            throw new IllegalArgumentException("Linea Orden Compra con ID invalido");
        }
        
        LineaOrdenCompra linea = this.lineaOrdenCompraDAO.leer(modelo.getId());
        
        if(linea == null){
            throw new RuntimeException("No se encontro el Linea Orden Compra con ID: "+ modelo.getId());
        }
        
        this.lineaOrdenCompraDAO.actualizar(modelo);
    }

    @Override
    public LineaOrdenCompra obtener(int id) {
        if(id<=0){
            throw new IllegalArgumentException("Linea Orden Compra con ID invalido");
        }
        
        LineaOrdenCompra linea = this.lineaOrdenCompraDAO.leer(id);
        
        if(linea == null){
            throw new RuntimeException("No se encontro el Linea Orden Compra con ID: "+ id);
        }      
        
        return linea;
    }

    @Override
    public void eliminar(int id) {
        if(id<=0){
            throw new IllegalArgumentException("Linea Orden Compra con ID invalido");
        }
        
        LineaOrdenCompra linea = this.lineaOrdenCompraDAO.leer(id);
        
        if(linea == null){
            throw new RuntimeException("No se encontro el Linea Orden Compra con ID: "+ id);
        }   
        
        this.lineaOrdenCompraDAO.eliminar(id);
    }
    
}
