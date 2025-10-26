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
import pe.edu.pucp.inf30.softprog.dao.producto.ProductoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;

/**
 *
 * @author Cristhian Horacio
 */
public class ProductoDAOImpl extends BaseDAO<Producto> implements ProductoDAO {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, Producto modelo) throws SQLException {
        String sql = "{CALL insertarProducto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_ID_Producto", modelo.getId());
        cmd.setString("p_Nombre", modelo.getNombre());
        cmd.setString("p_SKU", modelo.getSKU());
        cmd.setString("p_Descripcion", modelo.getDescripcion());
        cmd.setDouble("p_precioRegular", modelo.getPrecioUnitario());
        cmd.setDouble("p_precioAlMayor", modelo.getPrecioAlMayor());
        cmd.setString("p_UnidadDeMedida", modelo.getMedidaAlMayorString());
        cmd.setInt("p_StockDisponible", modelo.getStockDisponible());
        cmd.setInt("p_StockMaximo", modelo.getStockMaximo());
        cmd.setInt("p_StockMinimo", modelo.getStockMinimo());
        cmd.setInt("p_Activo", modelo.getActivo());
        cmd.setInt("p_CategoriaProducto_idCategoriaProducto", modelo.getIdCategoria());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, Producto modelo) throws SQLException {
        String sql = "{CALL modificarProducto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_ID_Producto", modelo.getId());
        cmd.setString("p_Nombre", modelo.getNombre());
        cmd.setString("p_SKU", modelo.getSKU());
        cmd.setString("p_Descripcion", modelo.getDescripcion());
        cmd.setDouble("p_precioRegular", modelo.getPrecioUnitario());
        cmd.setDouble("p_precioAlMayor", modelo.getPrecioAlMayor());
        cmd.setString("p_UnidadDeMedida", modelo.getMedidaAlMayorString());
        cmd.setInt("p_StockDisponible", modelo.getStockDisponible());
        cmd.setInt("p_StockMaximo", modelo.getStockMaximo());
        cmd.setInt("p_StockMinimo", modelo.getStockMinimo());
        cmd.setInt("p_Activo", modelo.getActivo());
        cmd.setInt("p_CategoriaProducto_idCategoriaProducto", modelo.getIdCategoria());

        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarProducto(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_ID_Producto", id);

        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarProductoPorId(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_ID_Producto", id);

        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarProductos()}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        return cmd;
    }

    @Override
    protected Producto mapearModelo(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        
        producto.setId(rs.getInt("ID_Producto"));
        producto.setNombre(rs.getString("Nombre"));
        producto.setSKU(rs.getString("SKU"));
        producto.setDescripcion(rs.getString("Descripcion"));
        producto.setPrecioAlMayor(rs.getDouble("precioAlMayor"));
        producto.setPrecioUnitario(rs.getDouble("precioRegular"));
        producto.setMedidaAlMayorString(rs.getString("UnidadDeMedida"));
        producto.setStockDisponible(rs.getInt("StockDisponible"));
        producto.setStockMaximo(rs.getInt("StockMaximo"));
        producto.setStockMinimo(rs.getInt("StockMinimo"));
        producto.setActivoInt(rs.getInt("Activo"));
        producto.setIdCategoria(rs.getInt("CategoriaProducto_idCategoriaProducto"));
        
        return producto;
    }

    public Producto obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
