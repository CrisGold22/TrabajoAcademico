/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.daoimpl.venta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.inf30.softprog.dao.venta.OrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public class OrdenCompraDAOImpl extends TransaccionalBaseDAO<OrdenCompra> implements OrdenCompraDAO {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, OrdenCompra modelo) throws SQLException {
        String sql = "{CALL insertarOrdenCompra(?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_IdPedido", modelo.getId());
        cmd.setDate("p_FechaCreacion", (Date) modelo.getFechaCreacion());
        cmd.setDouble("p_total_parcial", modelo.getTotalParcial());
        cmd.setDouble("p_total_final", modelo.getTotalFinal());
        cmd.setDouble("p_descuentoTotal", modelo.getDescuentoTotal());
        cmd.setString("p_Estado", modelo.getEstadoString());
        cmd.setInt("p_DetalleDeEnvio_id_DetalleEnvio", modelo.getIdDetalleEnvio());
        cmd.setInt("p_Activo", modelo.getActivo());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, OrdenCompra modelo) throws SQLException {
        String sql = "{CALL modificarOrdenCompra(?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_IdPedido", modelo.getId());
        cmd.setDate("p_FechaCreacion", (Date) modelo.getFechaCreacion());
        cmd.setDouble("p_total_parcial", modelo.getTotalParcial());
        cmd.setDouble("p_total_final", modelo.getTotalFinal());
        cmd.setDouble("p_descuentoTotal", modelo.getDescuentoTotal());
        cmd.setString("p_Estado", modelo.getEstadoString());
        cmd.setInt("p_DetalleDeEnvio_id_DetalleEnvio", modelo.getIdDetalleEnvio());
        cmd.setInt("p_Activo", modelo.getActivo());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarOrdenCompra(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_IdPedido", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarOrdenCompraPorId(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_IdPedido", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarOrdenesCompra()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }

    @Override
    protected OrdenCompra mapearModelo(ResultSet rs) throws SQLException {
        OrdenCompra orden = new OrdenCompra();
        
        orden.setId(rs.getInt("IdPedido"));
        orden.setFechaCreacion(rs.getDate("FechaCreacion"));
        orden.setTotalParcial(rs.getDouble("total_parcial"));
        orden.setTotalFinal(rs.getDouble("total_final"));
        orden.setDescuentoTotal(rs.getDouble("descuentoTotal"));
        orden.setEstadoString(rs.getString("Estado"));
        orden.setIdDetalleEnvio(rs.getInt("DetalleDeEnvio_id_DetalleEnvio"));
        orden.setActivoInt(rs.getInt("Activo"));
        
        return orden;
    }

}
