/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.venta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.inf30.softprog.dao.pago.LineaComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaOrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.dao.venta.OrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.LineaOrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.OrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.dbmanager.DBFactoryProvider;
import pe.edu.pucp.inf30.softprog.dbmanager.DBManager;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompraDTO;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompraDTO;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.OrdenCompraBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.pago.ComprobantePagoBOImpl;

/**
 *
 * @author Cristhian Horacio
 */
public class OrdenCompraBOImpl implements OrdenCompraBO{
    private final OrdenCompraDAO ordenCompraDAO;
    private final LineaOrdenCompraDAO lineaOrdenCompraDAO;
    
    public OrdenCompraBOImpl(){
        ordenCompraDAO = new OrdenCompraDAOImpl();
        lineaOrdenCompraDAO = new LineaOrdenCompraDAOImpl();
    }
    
    @Override
    public List<OrdenCompraDTO> listar() {
        return this.ordenCompraDAO.leerTodos();
    }

    @Override
    public void insertar(OrdenCompraDTO modelo) {
        DBManager dbManager = DBFactoryProvider.getManager();
        
        try(Connection conn = dbManager.getConnection()){
            conn.setAutoCommit(false);
            
            try{
                int idOrden = this.ordenCompraDAO.crear(modelo, conn);
                modelo.setId(idOrden);
                
                for(LineaOrdenCompraDTO linea : modelo.getLineasOrden()){
                    linea.setIdOrdenCompra(idOrden);
                    lineaOrdenCompraDAO.crear(linea, conn);
                }
                
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error guardando OrdenVenta", ex);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error de conexión al guardar OrdenVenta"
                    + "", e);
        }
    }

    @Override
    public void actualizar(OrdenCompraDTO modelo) {
        DBManager dbManager = DBFactoryProvider.getManager();
        
        try(Connection conn = dbManager.getConnection()){
            conn.setAutoCommit(false);
            
            try{
                ordenCompraDAO.actualizar(modelo, conn);
                for(LineaOrdenCompraDTO linea : modelo.getLineasOrden()){
                    if(linea.getId() == 0){
                        linea.setIdOrdenCompra(modelo.getId());
                        lineaOrdenCompraDAO.crear(linea, conn);
                    }
                    else{
                        lineaOrdenCompraDAO.actualizar(linea, conn);
                    }
                }
                
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error guardando OrdenVenta", ex);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error de conexión al guardar OrdenVenta"
                    + "", e);
        }
    }

    @Override
    public OrdenCompraDTO obtener(int id) {
        OrdenCompraDTO orden = ordenCompraDAO.leer(id);
        if(orden == null) return null;
        List<LineaOrdenCompraDTO> lineas = lineaOrdenCompraDAO.listarPorIdOrdenCompra(id);
        orden.setLineasOrden(lineas);
        return orden;
    }

    @Override
    public void eliminar(int id) {
        DBManager dbManager = DBFactoryProvider.getManager();
        
        try(Connection conn = dbManager.getConnection()){
            conn.setAutoCommit(false);
            
            try{
                List<LineaOrdenCompraDTO> lineas = lineaOrdenCompraDAO.listarPorIdOrdenCompra(conn, id);
                for(LineaOrdenCompraDTO linea : lineas){
                    if(linea.getIdOrdenCompra() == id){
                        lineaOrdenCompraDAO.eliminar(linea.getId(), conn);
                    }
                }
                
                if(!lineaOrdenCompraDAO.eliminar(id, conn)){
                    throw new RuntimeException("El comprobante: " + id + ", " + "no se pudo eliminar");
                }
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error eliminando comprobante " + "con id=" + id, ex);
            }
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrdenCompraBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
