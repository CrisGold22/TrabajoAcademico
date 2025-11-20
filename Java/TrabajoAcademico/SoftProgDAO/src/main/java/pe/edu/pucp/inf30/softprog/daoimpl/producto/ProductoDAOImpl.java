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
        String sql = "{CALL insertarProducto(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_Nombre", modelo.getNombre());
        cmd.setString("p_SKU", modelo.getSKU());
        cmd.setString("p_Descripcion", modelo.getDescripcion());
        cmd.setString("p_ImagenProducto", modelo.getDescripcion());
        cmd.setDouble("p_precioRegular", modelo.getPrecioRegular());
        cmd.setDouble("p_precioAlMayor", modelo.getPrecioAlMayor());
        cmd.setString("p_UnidadDeMedida", modelo.getMedidaAlMayorString());
        cmd.setInt("p_StockDisponible", modelo.getStockDisponible());
        cmd.setInt("p_StockMaximo", modelo.getStockMaximo());
        cmd.setInt("p_Activo", modelo.getActivoInt());
        cmd.setInt("p_CategoriaProducto_idCategoriaProducto", modelo.getCategoria().getId());
        cmd.setString("p_MarcaProducto", modelo.getMarca());
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
        cmd.setDouble("p_precioRegular", modelo.getPrecioRegular());
        cmd.setDouble("p_precioAlMayor", modelo.getPrecioAlMayor());
        cmd.setString("p_UnidadDeMedida", modelo.getMedidaAlMayorString());
        cmd.setInt("p_StockDisponible", modelo.getStockDisponible());
        cmd.setInt("p_StockMaximo", modelo.getStockMaximo());
        cmd.setInt("p_Activo", modelo.getActivoInt());
        cmd.setInt("p_CategoriaProducto_idCategoriaProducto", modelo.getCategoria().getId());
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
        
        producto.setId(rs.getInt("id_Producto"));
        producto.setNombre(rs.getString("nombre"));
        producto.setSKU(rs.getString("sku"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setImagenProducto(rs.getString("imagenProducto"));
        producto.setPrecioAlMayor(rs.getDouble("precioAlMayor"));
        producto.setPrecioRegular(rs.getDouble("precioRegular"));
        producto.setMedidaAlMayorString(rs.getString("unidadDeMedida"));
        producto.setStockDisponible(rs.getInt("stockDisponible"));
        producto.setStockMaximo(rs.getInt("stockMaximo"));
        producto.setActivoInt(rs.getInt("activo"));
        producto.setCategoria(new CategoriaProductoDAOImpl().leer(rs.getInt("categoriaProducto_idCategoriaProducto")));
        producto.setMarca(rs.getString("marca"));
        
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
    
    
     protected PreparedStatement comandoFiltrarProductoPorPrecioRegular(Connection conn,
             Integer id,double RangoPrecio1,double RangoPrecio2) throws SQLException {
         
        String sql = "{CALL filtrarProductoPorPrecioRegular(?,?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idCategoriaProducto", id);
        cmd.setDouble("p_Filtro1", RangoPrecio1);
        cmd.setDouble("p_Filtro2", RangoPrecio2);
        
        return cmd; 
    }     
     
    @Override
    public List<Producto> filtrarProductoPorPrecioRegular(Integer id,double RangoPrecio1,double RangoPrecio2){
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoFiltrarProductoPorPrecioRegular(conn,id,RangoPrecio1,RangoPrecio2)) {
                ResultSet rs = cmd.executeQuery();

                List<Producto> modelos = new ArrayList<>();
                while (rs.next()) {
                    modelos.add(this.mapearModelo(rs));
                }

                return modelos;
            }
        });        
    }
    
    @Override
    public List<Producto> filtrarProductoPorPrecioAlMayor(Integer id,double RangoPrecio1,double RangoPrecio2){
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoFiltrarProductoPorPrecioAlMayor(conn,id,RangoPrecio1,RangoPrecio2)) {
                ResultSet rs = cmd.executeQuery();

                List<Producto> modelos = new ArrayList<>();
                while (rs.next()) {
                    modelos.add(this.mapearModelo(rs));
                }

                return modelos;
            }
        });        
    }  
    
     protected PreparedStatement comandoFiltrarProductoPorPrecioAlMayor(Connection conn,
             Integer id,double RangoPrecio1,double RangoPrecio2) throws SQLException {
         
        String sql = "{CALL filtrarProductoPorPrecioAlMayor(?,?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idCategoriaProducto", id);
        cmd.setDouble("p_Filtro1", RangoPrecio1);
        cmd.setDouble("p_Filtro2", RangoPrecio2);
        
        return cmd; 
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

    @Override
    public boolean verificarStockSuficientePorID(Integer id, Integer cantidadSolicitada) {
    
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoverificarStockSuficientePorID(conn,id,cantidadSolicitada)) {
                
                if(cmd.executeUpdate()== 0){
                    return false;
                    
                }
                
                int validar = 0;
                        
                if (cmd instanceof CallableStatement callableCmd) {
                     validar =  callableCmd.getInt("p_stock_suficiente");
                }
                
                if(validar==1) return true;
                
                return false;
                
            }
        }); 
    }
    
    
    protected PreparedStatement comandoverificarStockSuficientePorID(Connection conn,
             Integer id,Integer cantidadSolicitada) throws SQLException {
         
        String sql = "{CALL verificarStockSuficientePorID(?,?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_id_producto", id);
        cmd.setInt("p_cantidad_solicitada", cantidadSolicitada);
        cmd.registerOutParameter("p_stock_suficiente", Types.INTEGER);
        
        return cmd; 
    }  

    @Override
    public boolean verificarStockSuficientePorSKU(String sku, Integer cantidadSolicitada) {
        
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoverificarStockSuficientePorSKU(conn,sku,cantidadSolicitada)) {
                
                if(cmd.executeUpdate()== 0){
                    return false;
                    
                }
                
                int validar = 0;
                        
                if (cmd instanceof CallableStatement callableCmd) {
                     validar =  callableCmd.getInt("p_stock_suficiente");
                }
                
                if(validar==1) return true;
                
                return false;
                
            }
        });         
    }

    protected PreparedStatement comandoverificarStockSuficientePorSKU(Connection conn,
             String sku,Integer cantidadSolicitada) throws SQLException {
         
        String sql = "{CALL verificarStockSuficientePorSKU(?,?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setString("p_sku_producto", sku);
        cmd.setInt("p_cantidad_solicitada", cantidadSolicitada);
        cmd.registerOutParameter("p_stock_suficiente", Types.INTEGER);
        
        return cmd; 
    }      

    
    protected PreparedStatement comandoObtenerProductosPorCategoria(Connection conn,
            Integer idCategoria) throws SQLException {
         
        String sql = "{CALL obtenerProductosPorCategoria(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idCategoriaProducto", idCategoria);

        return cmd; 
    }         
    
    @Override
    public List<Producto> obtenerProductosPorCategoria(Integer idCategoria) {
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoObtenerProductosPorCategoria(conn,idCategoria)) {
                ResultSet rs = cmd.executeQuery();

                List<Producto> modelos = new ArrayList<>();
                while (rs.next()) {
                    modelos.add(this.mapearModelo(rs));
                }

                return modelos;
            }
        });           
    }
    
    public PreparedStatement comandolistaProductosPorCategoria(Connection conn,
            String nombre) throws SQLException {
        
        String sql = "{CALL listaProductosPorCategoria(?)}";
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setString("p_nombreCategoria", nombre);
        return cmd;
    }
    
    @Override
    public List<Producto> listaProductosPorCategoria(String nombre) {
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandolistaProductosPorCategoria(conn,nombre)) {
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

