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
import pe.edu.pucp.inf30.softprog.dao.producto.CategoriaProductoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.producto.CategoriaProductoDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class CategoriaProductoDAOImpl extends BaseDAO<CategoriaProductoDTO> implements CategoriaProductoDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, CategoriaProductoDTO modelo) throws SQLException {
        String sql = "{CALL insertarCategoriaProducto(?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCategoriaProducto", modelo.getId());
        cmd.setString("p_NombreCategoria", modelo.getNombreString());
        cmd.setString("p_Descripcion", modelo.getDescripcion());
        cmd.setInt("p_Catalogo_idCatalogo", modelo.getIdCategoriaPadre());
        cmd.setInt("p_Activo", modelo.getActivo());
        
        return cmd;
        
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, CategoriaProductoDTO modelo) throws SQLException {
        String sql = "{CALL modificarCategoriaProducto(?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCategoriaProducto", modelo.getId());
        cmd.setString("p_NombreCategoria", modelo.getNombreString());
        cmd.setString("p_Descripcion", modelo.getDescripcion());
        cmd.setInt("p_Catalogo_idCatalogo", modelo.getIdCategoriaPadre());
        cmd.setInt("p_Activo", modelo.getActivo());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliiminarCategoriaProducto(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCategoriaProducto", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarCategoriaProductoPorId(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCategoriaProducto", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarCategoriaProductos()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }

    @Override
    protected CategoriaProductoDTO mapearModelo(ResultSet rs) throws SQLException {
        CategoriaProductoDTO categoria = new CategoriaProductoDTO();
        
        categoria.setId(rs.getInt("idCategoriaProducto"));
        categoria.setNombreString(rs.getString("NombreCategoria"));
        categoria.setDescripcion(rs.getString("Descripcion"));
        categoria.setActivoInt(rs.getInt("Activo"));
        categoria.setIdCategoriaPadre(rs.getInt("Catalogo_idCatalogo"));
        
        return categoria;
    }
    
}
