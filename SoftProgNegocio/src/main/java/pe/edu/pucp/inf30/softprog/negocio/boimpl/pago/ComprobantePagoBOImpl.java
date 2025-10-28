/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.pago;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.inf30.softprog.dao.pago.ComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.dao.pago.LineaComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.ComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.LineaComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.dbmanager.DBFactoryProvider;
import pe.edu.pucp.inf30.softprog.dbmanager.DBManager;
import pe.edu.pucp.inf30.softprog.modelo.pago.ComprobantePago;
import pe.edu.pucp.inf30.softprog.modelo.pago.LineaComprobantePago;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.ComprobantePagoBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.LineaComprobantePagoBO;

/**
 *
 * @author Cristhian Horacio
 */
public class ComprobantePagoBOImpl implements ComprobantePagoBO{

    private final ComprobantePagoDAO comprobantePagoDAO;
    private final LineaComprobantePagoDAO lineaComprobantePagoDAO;
    
    public ComprobantePagoBOImpl(){
        comprobantePagoDAO = new ComprobantePagoDAOImpl();
        lineaComprobantePagoDAO = new LineaComprobantePagoDAOImpl();
    }
    
    @Override
    public List<ComprobantePago> listar() {
        return this.comprobantePagoDAO.leerTodos();
    }

    @Override
    public void insertar(ComprobantePago modelo) {
        DBManager dbManager = DBFactoryProvider.getManager();
        try (Connection conn = dbManager.getConnection()) {
            conn.setAutoCommit(false);

            try {
                int idOrden = this.comprobantePagoDAO.crear(modelo, conn);
                modelo.setId(idOrden);
                for (LineaComprobantePago linea : modelo.getLineasComprobantes()) {
                    linea.setIdComprobantePago(idOrden);
                    lineaComprobantePagoDAO.crear(linea, conn);
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
    public void actualizar(ComprobantePago modelo) {
       DBManager dbManager = DBFactoryProvider.getManager();
        try (Connection conn = dbManager.getConnection()) {
            conn.setAutoCommit(false);

            try {
                comprobantePagoDAO.actualizar(modelo, conn);
                for (LineaComprobantePago linea : modelo.getLineasComprobantes()) {
                    if(linea.getId() == 0){
                        linea.setIdComprobantePago(modelo.getId());
                        lineaComprobantePagoDAO.crear(linea, conn);
                    }
                    else{
                        lineaComprobantePagoDAO.actualizar(linea, conn);
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
    public ComprobantePago obtener(int id) {
        ComprobantePago comprobante = comprobantePagoDAO.leer(id);
        if(comprobante == null) return null;
        List<LineaComprobantePago> lineas = lineaComprobantePagoDAO.listarPorIdComprobante(id);
        comprobante.setLineasComprobantes(lineas);
        return comprobante; 
    }

    @Override
    public void eliminar(int id) {
        DBManager dbManager = DBFactoryProvider.getManager();
        
        try(Connection conn = dbManager.getConnection()){
            conn.setAutoCommit(false);
            
            try{
                List<LineaComprobantePago> lineas = lineaComprobantePagoDAO.listarPorIdComprobante(conn, id);
                for(LineaComprobantePago linea : lineas){
                    if(linea.getIdComprobantePago() == id){
                        lineaComprobantePagoDAO.eliminar(linea.getId(), conn);
                    }
                }
                
                if(!comprobantePagoDAO.eliminar(id, conn)){
                    throw new RuntimeException("El comprobante: " + id + ", " + "no se pudo eliminar");
                }
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error eliminando comprobante " + "con id=" + id, ex);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ComprobantePagoBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
