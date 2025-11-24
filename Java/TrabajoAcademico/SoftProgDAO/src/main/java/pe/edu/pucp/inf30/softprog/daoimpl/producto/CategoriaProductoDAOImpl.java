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
import java.util.ArrayList;
import java.util.List;
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
        cmd.setInt("p_Activo", modelo.getActivoInt());
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
        cmd.setInt("p_Activo", modelo.getActivoInt());
        
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
    
    @Override
    public List<String> obtenerMarcasPorCategoria(Integer idCategoria){
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoObtenerMarcasPorCategorias(conn, idCategoria)) {
                ResultSet rs = cmd.executeQuery();

                List<String> modelos = new ArrayList<String>();
                while (rs.next()) {
                    modelos.add(rs.getString("marca"));
                }

                return modelos;
            }
        });           
    }
    
    protected PreparedStatement comandoObtenerMarcasPorCategorias(Connection conn,
            Integer idCategoria) throws SQLException{
   
        String sql = "{CALL obtenerMarcasPorCategoria(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idCategoriaProducto", idCategoria);
        
        return cmd;
    }
    
    protected PreparedStatement comandoObtenerCategoriaPorNombre(Connection conn, 
            String nombreCategoria)throws SQLException{
        
        String sql = "{CALL obtenerCategoriaPorNombre(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_categoria", nombreCategoria);
        
        return cmd;
    }
    
    @Override
    public CategoriaProducto obtenerCategoriaPorNombre(String nombreCategoria) {
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoObtenerCategoriaPorNombre(conn, nombreCategoria)){
                ResultSet rs = cmd.executeQuery();
                
                if (!rs.next()) {
                    System.err.println("No se encontro el registro con "
                            + "sku: " + nombreCategoria);
                    return null;
                }
                
                return this.mapearModelo(rs);
            }           
        });            
    }
    
}
