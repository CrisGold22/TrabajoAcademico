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
import pe.edu.pucp.inf30.softprog.dao.venta.CarritoComprasDAO;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaCarritoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.CarritoComprasDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.LineaCarritoDAOImpl;
import pe.edu.pucp.inf30.softprog.dbmanager.DBFactoryProvider;
import pe.edu.pucp.inf30.softprog.dbmanager.DBManager;
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoCompras;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarrito;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.CarritoComprasBO;

/**
 *
 * @author Cristhian Horacio
 */
public class CarritoComprasBOImpl implements CarritoComprasBO{
    private final CarritoComprasDAO carritoCompras;
    private final LineaCarritoDAO lineaCarrito;
    
    public CarritoComprasBOImpl(){
        carritoCompras = new CarritoComprasDAOImpl();
        lineaCarrito = new LineaCarritoDAOImpl();
    }
    
    @Override
    public List<CarritoCompras> listar() {  
       return this.carritoCompras.leerTodos();
    }

    @Override
    public void insertar(CarritoCompras modelo) {
        DBManager dbManager = DBFactoryProvider.getManager();
        
        try(Connection conn = dbManager.getConnection()){
            conn.setAutoCommit(false);
            
            try{
                int idOrden = this.carritoCompras.crear(modelo, conn);
                modelo.setId(idOrden);
                
                for(LineaCarrito linea : modelo.getLineasCarritos()){
                    linea.setCarritoCompras(modelo);
                    lineaCarrito.crear(linea, conn);
                }
                
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error guardando LineaCarrito", ex);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error de conexión al guardar CarritoCompras"
                    + "", e);
        }
    }

    @Override
    public void actualizar(CarritoCompras modelo) {
        DBManager dbManager = DBFactoryProvider.getManager();
        
        try(Connection conn = dbManager.getConnection()){
            conn.setAutoCommit(false);
            
            try{
                carritoCompras.actualizar(modelo, conn);
                
                for(LineaCarrito linea : modelo.getLineasCarritos()){
                    if(linea.getId() == 0){
                        linea.setCarritoCompras(modelo);
                        lineaCarrito.crear(linea, conn);
                    }
                    else{
                        lineaCarrito.actualizar(linea, conn);
                    }
                }
                
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error guardando LineaCarrito", ex);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error de conexión al actualizar CarritoCompra"
                    + "", e);
        }
    }

    @Override
    public CarritoCompras obtener(int id) {
        CarritoCompras carrito = carritoCompras.leer(id);
        if(carrito == null) return null;
        List<LineaCarrito> lineas = lineaCarrito.listarPorIdCarrito(id);
        carrito.setLineasCarrito(lineas);
        return carrito;
    }

    @Override
    public void eliminar(int id) {
        DBManager dbManager = DBFactoryProvider.getManager();
        
        try(Connection conn = dbManager.getConnection()){
            conn.setAutoCommit(false);
            
            try{
                List<LineaCarrito> lineas = lineaCarrito.listarPorIdCarrito(id, conn);
                for(LineaCarrito linea : lineas){
                    if(linea.getCarritoCompras().getId() == id){
                        lineaCarrito.eliminar(linea.getId(), conn);
                    }
                }
                
                if(!lineaCarrito.eliminar(id, conn)){
                    throw new RuntimeException("El comprobante: " + id + ", " + "no se pudo eliminar");
                }
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error eliminando comprobante " + "con id=" + id, ex);
            }
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CarritoComprasBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<LineaCarrito> listarLineaCarritoPorIdCarrito(int idCarritoCompras) {
        CarritoCompras carrito = this.obtener(idCarritoCompras);
        List<LineaCarrito> lista = carrito.getLineasCarritos();
        return lista;
    }

    @Override
    public CarritoCompras obtenerCarritoComprasPorIdCliente(int idCliente) {
        return carritoCompras.obtenerCarritoPorIdCliente(idCliente);
    }
    
}
