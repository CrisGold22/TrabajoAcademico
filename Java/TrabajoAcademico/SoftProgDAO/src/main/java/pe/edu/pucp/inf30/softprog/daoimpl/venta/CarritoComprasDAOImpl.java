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
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.CarritoComprasDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.ClienteDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoCompras;

/**     
 *
 * @author Cristhian Horacio
 */
public class CarritoComprasDAOImpl extends TransaccionalBaseDAO<CarritoCompras> implements CarritoComprasDAO  {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, CarritoCompras modelo) throws SQLException {
        String sql = "CALL insertarCarritoDeCompras(?, ?, ?, ?, ?, ?, ?)";
        
        CallableStatement cmd = conn.prepareCall(sql);
       
        cmd.setDouble("p_subtotal", modelo.getSubtotal());
        cmd.setString("p_Estado", modelo.getEstadoString());
        cmd.setDouble("p_descuento", modelo.getDescuento());
        cmd.setInt("p_cliente_idCliente", modelo.getCliente().getId());
        cmd.setDouble("p_monto_final", modelo.getMontoFinal());
        cmd.setInt("p_activo", modelo.getActivo());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, CarritoCompras modelo) throws SQLException {
        String sql = "CALL modificarCarritoDeCompras(?, ?, ?, ?, ?, ?, ?)";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_Id_CarritoDeCompras", modelo.getId());
        cmd.setDouble("p_subtotal", modelo.getSubtotal());
        cmd.setString("p_Estado", modelo.getEstadoString());
        cmd.setDouble("p_descuento", modelo.getDescuento());
        cmd.setInt("p_cliente_idCliente", modelo.getCliente().getId());
        cmd.setDouble("p_monto_final", modelo.getMontoFinal());
        cmd.setInt("p_activo", modelo.getActivo());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "CALL eliminarCarritoDeCompras(?)";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_Id_CarritoDeCompras", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "CALL buscarCarritoDeComprasPorId(?)";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_Id_CarritoDeCompras", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "CALL listarCarritosDeCompras()";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }

    @Override
    protected CarritoCompras mapearModelo(ResultSet rs) throws SQLException {
        CarritoCompras carrito = new CarritoCompras();
        
        carrito.setId(rs.getInt("id_CarritoDeCompras"));
        carrito.setSubtotal(rs.getDouble("subtotal"));
        carrito.setEstadoString(rs.getString("estado"));
        carrito.setDescuento(rs.getDouble("montoFinal"));
        carrito.setCliente(new ClienteDAOImpl().leer(rs.getInt("cliente_idCliente")));
        carrito.setActivoInt(rs.getInt("activo"));
        
        return carrito;
    }

    protected PreparedStatement comandoObtenerCarritoComprasPorIdCliente(Connection conn, int id) throws SQLException {
        String sql = "CALL obtenerCarritoPorIdCliente(?)";
        
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_Id_Cliente", id);
        
        return cmd;
    }
    
    @Override
    public CarritoCompras obtenerCarritoPorIdCliente(int id) {
        return ejecutarComando(conn -> {
            try(PreparedStatement cmd = this.comandoObtenerCarritoComprasPorIdCliente(conn, id)){
                ResultSet rs = cmd.executeQuery();

                if (!rs.next()) {
                    System.err.println("No se encontro el registro con "
                            + "id: " + id);
                    return null;
                }

                return this.mapearModelo(rs);
                
            }
            catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            throw new RuntimeException(e);
            }
        });
    }
    
}
