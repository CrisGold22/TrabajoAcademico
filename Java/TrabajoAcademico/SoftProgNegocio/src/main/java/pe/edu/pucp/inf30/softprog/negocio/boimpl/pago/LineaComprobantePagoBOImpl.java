/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.pago;

import java.util.List;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.LineaComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.pago.LineaComprobantePago;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.LineaComprobantePagoBO;
import pe.edu.pucp.inf30.softprog.dao.pago.LineaComprobantePagoDAO;
/**
 *
 * @author Cristhian Horacio
 */
public class LineaComprobantePagoBOImpl implements LineaComprobantePagoBO{
    private final LineaComprobantePagoDAO lineaComprobantePagoDAO;
    
    public LineaComprobantePagoBOImpl(){
        lineaComprobantePagoDAO = new LineaComprobantePagoDAOImpl();
    }

    @Override
    public List<LineaComprobantePago> listar() {
        return this.lineaComprobantePagoDAO.leerTodos();
    }

    @Override
    public void insertar(LineaComprobantePago modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Linea Comprobante Pago no puede ser nulo");
        }        
        
        if(modelo.getComprobantePago() == null){
            throw new IllegalArgumentException("El ComprobantePago asociado no puede ser nulo");
        }
        
        this.lineaComprobantePagoDAO.crear(modelo);
    }

    @Override
    public void actualizar(LineaComprobantePago modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Linea Comprobante Pago no puede ser nulo");
        }        
        
        if(modelo.getId() <= 0){
            throw new IllegalArgumentException("Linea Comprobante Pago con ID inválido");
        }
            
        LineaComprobantePago linea = this.lineaComprobantePagoDAO.leer(modelo.getId());
        
        if(linea == null){
            throw new RuntimeException("No se encontro la Linea Comprobante Pago con ID: "+ modelo.getId());
        }
        
        this.lineaComprobantePagoDAO.actualizar(modelo);
    }

    @Override
    public LineaComprobantePago obtener(int id) {
        if( id <= 0){
            throw new IllegalArgumentException("Linea Comprobante Pago con ID inválido");
        }
       
        LineaComprobantePago modelo =  this.lineaComprobantePagoDAO.leer(id);
        
        if(modelo == null){
            throw new RuntimeException("No se encontro la LineaComprobantePago con ID: "+id);
        }
        
        return modelo;
    }

    @Override
    public void eliminar(int id) {
        
        if( id <= 0){
            throw new IllegalArgumentException("Linea Comprobante Pago con ID inválido");
        }
       
        LineaComprobantePago modelo =  this.lineaComprobantePagoDAO.leer(id);
        
        if(modelo == null){
            throw new RuntimeException("No se encontro la LineaComprobantePago con ID: "+id);
        }
        
        this.lineaComprobantePagoDAO.eliminar(id);
    }
    
}
