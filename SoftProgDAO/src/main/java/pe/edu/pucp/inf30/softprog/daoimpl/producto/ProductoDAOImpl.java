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
        String sql = "{CALL insertarProducto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
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
        cmd.setString("p_MarcaProducto", modelo.getMarca());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, Producto modelo) throws SQLException {
        String sql = "{CALL modificarProducto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
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
        cmd.setString("p_MarcaProducto", modelo.getMarca());
        
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
        producto.setMarca(rs.getString("Marca"));
        
        return producto;
    }
    

     protected PreparedStatement comandoBuscarPorSKU(Connection conn,String sku) throws SQLException {
        String sql = "{CALL buscarProductoPorSKU(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setString("p_SKU",sku);
        
        return cmd;
    }    
    
    @Override
    public Producto obtenerPorSku(String sku){
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoBuscarPorSKU(conn, sku)){
                ResultSet rs = cmd.executeQuery();
                
                if (!rs.next()) {
                    System.err.println("No se encontro el registro con "
                            + "sku: " + sku);
                    return null;
                }
                
                return this.mapearModelo(rs);
            }           
        });        
    }
    
    
     protected PreparedStatement comandoFiltrarProductoPorPrecio(Connection conn,
             Integer id,double RangoPrecio1,double RangoPrecio2) throws SQLException {
         
        String sql = "{CALL filtrarProductoPorPrecio(?,?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idCategoriaProducto", id);
        cmd.setDouble("p_Filtro1", RangoPrecio1);
        cmd.setDouble("p_Filtro2", RangoPrecio2);
        
        return cmd; 
    }     
     
    @Override
    public List<Producto> filtrarProductoPorPrecio(Integer id,double RangoPrecio1,double RangoPrecio2){
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoFiltrarProductoPorPrecio(conn,id,RangoPrecio1,RangoPrecio2)) {
                ResultSet rs = cmd.executeQuery();

                List<Producto> modelos = new ArrayList<>();
                while (rs.next()) {
                    modelos.add(this.mapearModelo(rs));
                }

                return modelos;
            }
        });        
    }
    
    protected PreparedStatement comandoFiltrarProductoPorMarca(Connection conn,
             Integer id,String marca) throws SQLException {
         
        String sql = "{CALL filtrarProductoPorMarca(?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idCategoriaProducto", id);
        cmd.setString("p_MarcaProducto", marca);
        
        return cmd; 
    }   
    
    @Override
    public List<Producto> filtrarProductoPorMarca(Integer id,String marca){
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoFiltrarProductoPorMarca(conn,id,marca)) {
                ResultSet rs = cmd.executeQuery();

                List<Producto> modelos = new ArrayList<>();
                while (rs.next()) {
                    modelos.add(this.mapearModelo(rs));
                }

                return modelos;
            }
        });        
    }

    
    protected PreparedStatement comandoFiltrarProductoPorDescuento(Connection conn,
             Integer id, String nombre) throws SQLException {
         
        String sql = "{CALL filtrarProductoPorDescuento(?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idCategoriaProducto", id);
        cmd.setString("p_NombreCategoria", nombre);
        
        return cmd; 
    }       
    
    @Override
    public List<Producto> filtrarProductoPorDescuento(Integer id, String nombre) {
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoFiltrarProductoPorDescuento(conn,id,nombre)) {
                ResultSet rs = cmd.executeQuery();

                List<Producto> modelos = new ArrayList<>();
                while (rs.next()) {
                    modelos.add(this.mapearModelo(rs));
                }

                return modelos;
            }
        });       
    }


    
}
