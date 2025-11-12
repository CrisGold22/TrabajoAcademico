/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.venta;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.DetalleEnvioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.DetalleEnvioDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.DetalleEnvio;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.DetalleEnvioBO;

/**
 *
 * @author Cristhian Horacio
 */
public class DetalleEnvioBOImpl implements DetalleEnvioBO{
    private final DetalleEnvioDAO detalleEnvioDAO;
    
    public DetalleEnvioBOImpl(){
        detalleEnvioDAO = new DetalleEnvioDAOImpl();
    }
    
    @Override
    public List<DetalleEnvio> listar() {
        return this.detalleEnvioDAO.leerTodos();
    }

    @Override
    public void insertar(DetalleEnvio modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("El Detalle Envio no puede ser nulo");
        }
        
        this.detalleEnvioDAO.crear(modelo);
    }

    @Override
    public void actualizar(DetalleEnvio modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("El Detalle Envio no puede ser nulo");
        }
        
        if(modelo.getId()<=0){
            throw new IllegalArgumentException("Detalle Envio con ID invalido");            
        }
        
        DetalleEnvio detalle = this.detalleEnvioDAO.leer(modelo.getId());
        
        if(detalle == null){
            throw new RuntimeException("No se encontro el Detalle Envio con ID: "+ modelo.getId());
        }
        
        this.detalleEnvioDAO.actualizar(modelo);
    }

    @Override
    public DetalleEnvio obtener(int id) {
        if(id<=0){
            throw new IllegalArgumentException("Detalle Envio con ID invalido");            
        }
        
        DetalleEnvio detalle = this.detalleEnvioDAO.leer(id);
        
        if(detalle == null){
            throw new RuntimeException("No se encontro el Detalel Envio con ID: "+ id);
        }
        
        return detalle;
    }

    @Override
    public void eliminar(int id) {
        if(id<=0){
            throw new IllegalArgumentException("Detalle Envio con ID invalido");            
        }
        
        DetalleEnvio detalle = this.detalleEnvioDAO.leer(id);
        
        if(detalle == null){
            throw new RuntimeException("No se encontro el Detalel Envio con ID: "+ id);
        }
        
        this.detalleEnvioDAO.eliminar(id);
    }
    
}
