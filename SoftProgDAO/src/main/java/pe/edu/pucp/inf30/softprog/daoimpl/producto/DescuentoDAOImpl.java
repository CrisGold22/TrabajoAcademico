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
import pe.edu.pucp.inf30.softprog.dao.producto.DescuentoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.producto.DescuentoDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class DescuentoDAOImpl extends BaseDAO<DescuentoDTO> implements DescuentoDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, DescuentoDTO modelo) throws SQLException {
        String sql = "{CALL insertarDescuento(?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idReglaPrecioVolumen", modelo.getId());
        cmd.setDouble("p_precioPorVolumen", modelo.getPrecioPorVolumen());
        cmd.setInt("p_cantidadMax", modelo.getCantidadMax());
        cmd.setInt("p_cantidadMin", modelo.getCantidadMin());
        cmd.setInt("p_Activo", modelo.getActivo());
        cmd.setInt("p_Producto_ID_Producto", modelo.getIdProducto());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, DescuentoDTO modelo) throws SQLException {
        String sql = "{CALL actualizarDescuento(?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idReglaPrecioVolumen", modelo.getId());
        cmd.setDouble("p_precioPorVolumen", modelo.getPrecioPorVolumen());
        cmd.setInt("p_cantidadMax", modelo.getCantidadMax());
        cmd.setInt("p_cantidadMin", modelo.getCantidadMin());
        cmd.setInt("p_Activo", modelo.getActivo());
        cmd.setInt("p_Producto_ID_Producto", modelo.getIdProducto());
        
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
    protected DescuentoDTO mapearModelo(ResultSet rs) throws SQLException {
        DescuentoDTO descuento = new DescuentoDTO();
        
        descuento.setId(rs.getInt("idReglaPrecioVolumen"));
        descuento.setPrecioPorVolumen(rs.getDouble("precioPorVolumen"));
        descuento.setCantidadMax(rs.getInt("cantidadMax"));
        descuento.setCantidadMin(rs.getInt("cantidadMin"));
        descuento.setActivoInt(rs.getInt("Activo"));
        descuento.setIdProducto(rs.getInt("Producto_ID_Producto"));
        
        return descuento;
    }

    public DescuentoDTO obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
