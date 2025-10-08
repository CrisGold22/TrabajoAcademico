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
import pe.edu.pucp.inf30.softprog.dao.venta.DetalleEnvioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.venta.DetalleEnvioDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class DetalleEnvioDAOImpl extends BaseDAO<DetalleEnvioDTO> implements DetalleEnvioDAO {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, DetalleEnvioDTO modelo) throws SQLException {
        String sql = "{CALL insertarDetalleDeEnvio(?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_id_DetalleEnvio", modelo.getId());
        cmd.setString("p_descripcion", modelo.getDescripcion());
        cmd.setDate("p_fechaEntrega", (Date) modelo.getFechaEntrega());
        cmd.setDate("p_horarioEntrega", (Date) modelo.getHorarioEntrega());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, DetalleEnvioDTO modelo) throws SQLException {
        String sql = "{CALL actualizarDetalleDeEnvio(?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_id_DetalleEnvio", modelo.getId());
        cmd.setString("p_descripcion", modelo.getDescripcion());
        cmd.setDate("p_fechaEntrega", (Date) modelo.getFechaEntrega());
        cmd.setDate("p_horarioEntrega", (Date) modelo.getHorarioEntrega());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarDetalleDeEnvio(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_id_DetalleEnvio", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarDetalleDeEnvioPorId(?)}";

        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_id_DetalleEnvio", id);

        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarDetallesDeEnvio()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }

    @Override
    protected DetalleEnvioDTO mapearModelo(ResultSet rs) throws SQLException {
        DetalleEnvioDTO detalle = new DetalleEnvioDTO();
        
        detalle.setId(rs.getInt("id_DetalleEnvio"));
        detalle.setDescripcion(rs.getString("descripcion"));
        detalle.setFechaEntrega(rs.getDate("fechaEntrega"));
        detalle.setHorarioEntrega(rs.getDate("horarioEntrega"));
        detalle.setDireccion(rs.getString("Direccion"));
        detalle.setDistritoString(rs.getString("Distrito"));
        
        return detalle;
    }

    public DetalleEnvioDTO obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
