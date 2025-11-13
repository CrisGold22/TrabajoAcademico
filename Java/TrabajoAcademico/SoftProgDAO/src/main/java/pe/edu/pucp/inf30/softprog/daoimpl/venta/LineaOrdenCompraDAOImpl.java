/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.daoimpl.venta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaOrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.ProductoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaOrdenCompraDAOImpl extends TransaccionalBaseDAO<LineaOrdenCompra> implements LineaOrdenCompraDAO {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, LineaOrdenCompra modelo) throws SQLException {
        String sql = "{CALL insertarLineaOrdenCompra(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaOrdenCompra", modelo.getId());
        cmd.setInt("p_cantidad", modelo.getCantidad());
        cmd.setDouble("p_precioUnitario", modelo.getPrecioUnitario());
        cmd.setDouble("p_subtotal", modelo.getSubTotal());
        cmd.setInt("p_Producto_ID_Producto", modelo.getProducto().getId());
        cmd.setInt("p_OrdenCompra_IdPedido", modelo.getOrdenCompra().getId());
        cmd.setInt("p_CarritoDeCompras_Id", modelo.getCarritoCompras().getId());
        cmd.setInt("p_activo", modelo.getActivo());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, LineaOrdenCompra modelo) throws SQLException {
        String sql = "{CALL modificarLineaOrdenCompra(?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaOrdenCompra", modelo.getId());
        cmd.setInt("p_cantidad", modelo.getCantidad());
        cmd.setDouble("p_precioUnitario", modelo.getPrecioUnitario());
        cmd.setDouble("p_subtotal", modelo.getSubTotal());
        cmd.setInt("p_Producto_ID_Producto", modelo.getProducto().getId());
        cmd.setInt("p_OrdenCompra_IdPedido", modelo.getOrdenCompra().getId());
        cmd.setInt("p_CarritoDeCompras_Id", modelo.getCarritoCompras().getId());
        cmd.setInt("p_activo", modelo.getActivo());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarLineaOrdenCompra(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaOrdenCompra", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarLineaOrdenCompraPorId(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaOrdenCompra", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarLineasOrdenCompra()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }

    @Override
    protected LineaOrdenCompra mapearModelo(ResultSet rs) throws SQLException {
        LineaOrdenCompra linea = new LineaOrdenCompra();
        
        linea.setId(rs.getInt("idLineaOrdenCompra"));
        linea.setCantidad(rs.getInt("cantidad"));
        linea.setPrecioUnitario(rs.getDouble("precioUnitario"));
        linea.setSubTotal(rs.getDouble("subtotal"));
        linea.setProducto(new ProductoDAOImpl().leer(rs.getInt("Producto_ID_Producto")));
        linea.setOrdenCompra(new OrdenCompraDAOImpl().leer(rs.getInt("OrdenCompra_IdPedido")));
        linea.setCarritoCompras(new CarritoComprasDAOImpl().leer(rs.getInt("CarritoDeCompras_Id")));
        linea.setActivoInt(rs.getInt("activo"));
        
        return linea;
    }
    
    private PreparedStatement comandoListarPorIdOrdenCompra(Connection conn, int id) throws SQLException {
        String sql = "{CALL LISTAR_LINEAS_ORDEN_COMPRA_POR_ID_ORDEN(?)}";
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt(1, id); 

        return cmd;
    }

    @Override
    public List<LineaOrdenCompra> listarPorIdOrdenCompra(int id) {
        return ejecutarComando(conn -> listarPorIdOrdenCompra(conn, id));
    }
    

    @Override
    public List<LineaOrdenCompra> listarPorIdOrdenCompra(Connection conn, int id) {
        try(PreparedStatement cmd = this.comandoListarPorIdOrdenCompra(conn, id)){
            ResultSet rs = cmd.executeQuery();
            
            List<LineaOrdenCompra> modelos = new ArrayList<>();
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
