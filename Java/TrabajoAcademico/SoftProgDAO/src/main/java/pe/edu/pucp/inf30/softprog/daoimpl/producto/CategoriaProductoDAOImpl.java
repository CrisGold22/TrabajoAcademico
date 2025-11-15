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
import pe.edu.pucp.inf30.softprog.dao.producto.CategoriaProductoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.producto.CategoriaProducto;

/**
 *
 * @author Cristhian Horacio
 */
public class CategoriaProductoDAOImpl extends BaseDAO<CategoriaProducto> implements CategoriaProductoDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, CategoriaProducto modelo) throws SQLException {
        String sql = "{CALL insertarCategoriaProducto(?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_NombreCategoria", modelo.getNombre());
        cmd.setString("p_Descripcion", modelo.getDescripcion());
        if(modelo.getCategoriaPadre() == null){
            cmd.setNull("p_idCategoriaPadre", Types.INTEGER);
        }
        else{
            cmd.setInt("p_idCategoriaPadre", modelo.getCategoriaPadre().getId());
        }
        cmd.setInt("p_Activo", modelo.getActivo());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
        
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, CategoriaProducto modelo) throws SQLException {
        String sql = "{CALL modificarCategoriaProducto(?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCategoriaProducto", modelo.getId());
        cmd.setString("p_NombreCategoria", modelo.getNombre());
        cmd.setString("p_Descripcion", modelo.getDescripcion());
        if(modelo.getCategoriaPadre() == null){
            cmd.setNull("p_idCategoriaPadre", Types.INTEGER);
        }
        else{
            cmd.setInt("p_idCategoriaPadre", modelo.getCategoriaPadre().getId());
        }
        cmd.setInt("p_Activo", modelo.getActivo());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarCategoriaProducto(?)}";
        
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
    protected CategoriaProducto mapearModelo(ResultSet rs) throws SQLException {
        CategoriaProducto categoria = new CategoriaProducto();
        
        categoria.setId(rs.getInt("idCategoriaProducto"));
        categoria.setNombre(rs.getString("nombreCategoria"));
        categoria.setDescripcion(rs.getString("descripcion"));
        categoria.setActivoInt(rs.getInt("activo"));
       
        int idPadre = rs.getInt("idCategoriaPadre");
        if (rs.wasNull()) {
            categoria.setCategoriaPadre(null);
        } else {
            categoria.setCategoriaPadre(new CategoriaProductoDAOImpl().leer(idPadre));
        }
        
        return categoria;
    }
    
}
