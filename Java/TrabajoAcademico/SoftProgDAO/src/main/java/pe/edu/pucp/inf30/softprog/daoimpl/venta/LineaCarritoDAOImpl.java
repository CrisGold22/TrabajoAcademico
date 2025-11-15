/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.daoimpl.venta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaCarritoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.ProductoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarrito;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaCarritoDAOImpl extends TransaccionalBaseDAO<LineaCarrito> implements LineaCarritoDAO {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, LineaCarrito modelo) throws SQLException {
        String sql = "{CALL insertarLineaCarrito(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_cantidad", modelo.getCantidad());
        cmd.setDouble("p_precioVolumen", modelo.getPrecio());
        cmd.setDouble("p_subTotal", modelo.getSubTotal());
        cmd.setInt("p_CarritoDeCompras_Productos", modelo.getCarritoCompras().getId());
        cmd.setInt("p_activo", modelo.getActivo());
        cmd.setInt("p_Producto_ID_Producto", modelo.getProducto().getId());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, LineaCarrito modelo) throws SQLException {
        String sql = "{CALL modificarLineaCarrito(?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaCarrito", modelo.getId());
        cmd.setInt("p_cantidad", modelo.getCantidad());
        cmd.setDouble("p_precioVolumen", modelo.getPrecio());
        cmd.setDouble("p_subTotal", modelo.getSubTotal());
        cmd.setInt("p_CarritoDeCompras_Productos", modelo.getCarritoCompras().getId());
        cmd.setInt("p_activo", modelo.getActivo());
        cmd.setInt("p_Producto_ID_Producto", modelo.getProducto().getId());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarLineaCarrito(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaCarrito", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarLineaCarritoPorId(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaCarrito", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarLineasCarrito()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        
        return cmd;
    }

    @Override
    protected LineaCarrito mapearModelo(ResultSet rs) throws SQLException {
        LineaCarrito linea = new LineaCarrito();
        
        linea.setId(rs.getInt("idLineaCarrito"));
        linea.setCantidad(rs.getInt("cantidad"));
        linea.setPrecio(rs.getDouble("precio"));
        linea.setSubTotal(rs.getDouble("subTotal"));
        linea.setCarritoCompras(new CarritoComprasDAOImpl().leer(rs.getInt("carritoDeCompras_Productos")));
        linea.setActivoInt(rs.getInt("activo"));
        linea.setProducto(new ProductoDAOImpl().leer(rs.getInt("producto_ID_Producto")));
        
        return linea;
    }

    public LineaCarrito obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    protected PreparedStatement comandoListarPorIdCarrito(Connection conn, int id) throws SQLException{
        String sql = "{CALL listarLineasCarritoPorIdCarrito(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_Id_CarritoDeCompras", id);
        
        return cmd;
    }
    
    @Override
    public List<LineaCarrito> listarPorIdCarrito(int id) {
        return ejecutarComando(conn -> listarPorIdCarrito(id, conn));
    }

    @Override
    public List<LineaCarrito> listarPorIdCarrito(int id, Connection conn) {
        try(PreparedStatement cmd = this.comandoListarPorIdCarrito(conn, id)){
            ResultSet rs = cmd.executeQuery();
            
            List<LineaCarrito> modelos = new ArrayList<>();
            while(rs.next()){
                modelos.add(this.mapearModelo(rs));
            }
            
            return modelos;
        } catch (SQLException ex) {
            System.err.println("Error SQL: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
}
