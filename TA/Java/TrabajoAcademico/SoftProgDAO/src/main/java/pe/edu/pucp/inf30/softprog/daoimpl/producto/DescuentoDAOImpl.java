/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.daoimpl.producto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.softprog.dao.producto.DescuentoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.producto.Descuento;

/**
 *
 * @author Cristhian Horacio
 */
public class DescuentoDAOImpl extends BaseDAO<Descuento> implements DescuentoDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, Descuento modelo) throws SQLException {
        String sql = "{CALL insertarDescuento(?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idReglaPrecioVolumen", modelo.getId());
        cmd.setString("p_tipoDescuento", modelo.getTipoDescuentoString());
        cmd.setDouble("p_precioPorVolumen", modelo.getPrecioPorVolumen());
        cmd.setInt("p_cantidadMax", modelo.getCantidadMax());
        cmd.setInt("p_cantidadMin", modelo.getCantidadMin());
        cmd.setInt("p_Activo", modelo.getActivo());
        cmd.setInt("p_Producto_ID_Producto", modelo.getProducto().getId());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, Descuento modelo) throws SQLException {
        String sql = "{CALL modificarDescuento(?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idReglaPrecioVolumen", modelo.getId());
        cmd.setString("p_tipoDescuento", modelo.getTipoDescuentoString());
        cmd.setDouble("p_precioPorVolumen", modelo.getPrecioPorVolumen());
        cmd.setInt("p_cantidadMax", modelo.getCantidadMax());
        cmd.setInt("p_cantidadMin", modelo.getCantidadMin());
        cmd.setInt("p_Activo", modelo.getActivo());
        cmd.setInt("p_Producto_ID_Producto", modelo.getProducto().getId());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarDescuento(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idReglaPrecioVolumen", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarDescuentoPorId(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idReglaPrecioVolumen", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarDescuentos()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }

    @Override
    protected Descuento mapearModelo(ResultSet rs) throws SQLException {
        Descuento descuento = new Descuento();
        
        descuento.setId(rs.getInt("idReglaPrecioVolumen"));
        descuento.setTipoDescuentoString(rs.getString("tipoDescuento"));
        descuento.setPrecioPorVolumen(rs.getDouble("precioPorVolumen"));
        descuento.setCantidadMax(rs.getInt("cantidadMax"));
        descuento.setCantidadMin(rs.getInt("cantidadMin"));
        descuento.setActivoInt(rs.getInt("Activo"));
        descuento.setProducto(new ProductoDAOImpl().leer(rs.getInt("Producto_ID_Producto")));
        
        return descuento;
    }

    public Descuento obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizarPrecioDescuentoProducto(Integer id, double nuevo_precio) {
        
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoactualizarPrecioDescuentoProducto(conn,id,nuevo_precio)) {
                return cmd.executeUpdate()>0;
            }
        catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            throw new RuntimeException(e);
        }
        }); 
    }
    
    protected PreparedStatement comandoactualizarPrecioDescuentoProducto(Connection conn,
             Integer id,double nuevo_precio) throws SQLException {
         
        String sql = "{CALL actualizarPrecioDescuentoProducto(?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idProducto", id);
        cmd.setDouble("p_nuevo_precio_desc", nuevo_precio);
        
        return cmd; 
    }          
    
}
