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
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompraDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class OrdenCompraDAOImpl extends BaseDAO<OrdenCompraDTO> implements OrdenCompraDAO {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, OrdenCompraDTO modelo) throws SQLException {
        String sql = "{CALL insertarOrdenCompra(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_IdPedido", modelo.getId());
        cmd.setDate("p_FechaCreacion", (Date) modelo.getFechaCreacion());
        cmd.setDouble("p_total_parcial", modelo.getTotalParcial());
        cmd.setDouble("p_total_final", modelo.getTotalFinal());
        cmd.setDouble("p_descuentoTotal", modelo.getDescuentoTotal());
        cmd.setString("p_Estado", modelo.getEstadoString());
//        cmd.setString("p_DetalleDeOrdenCompra_id_Detalle", modelo.);
//        cmd.setString("p_DetalleDeEnvio_id_DetalleEnvio", modelo.);
//        cmd.setInt("p_LineaComprobantePago_idLineaComprobantePago", modelo.get);
        cmd.setInt("p_activo", modelo.getActivo());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, OrdenCompraDTO modelo) throws SQLException {
        String sql = "{CALL actualizarOrdenCompra(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_IdPedido", modelo.getId());
        cmd.setDate("p_FechaCreacion", (Date) modelo.getFechaCreacion());
        cmd.setDouble("p_total_parcial", modelo.getTotalParcial());
        cmd.setDouble("p_total_final", modelo.getTotalFinal());
        cmd.setDouble("p_descuentoTotal", modelo.getDescuentoTotal());
        cmd.setString("p_Estado", modelo.getEstadoString());
//        cmd.setString("p_DetalleDeOrdenCompra_id_Detalle", modelo.);
//        cmd.setString("p_DetalleDeEnvio_id_DetalleEnvio", modelo.);
//        cmd.setInt("p_LineaComprobantePago_idLineaComprobantePago", modelo.get);
        cmd.setInt("p_activo", modelo.getActivo());
        
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
    protected OrdenCompraDTO mapearModelo(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public OrdenCompraDTO obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
