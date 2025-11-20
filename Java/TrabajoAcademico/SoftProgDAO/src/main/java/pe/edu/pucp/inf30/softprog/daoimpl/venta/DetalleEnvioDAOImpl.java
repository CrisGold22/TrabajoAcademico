/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.daoimpl.venta;

import com.mysql.cj.MysqlType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.softprog.dao.venta.DetalleEnvioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.venta.DetalleEnvio;

/**
 *
 * @author Cristhian Horacio
 */
public class DetalleEnvioDAOImpl extends BaseDAO<DetalleEnvio> implements DetalleEnvioDAO {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, DetalleEnvio modelo) throws SQLException {
        String sql = "{CALL insertarDetalleDeEnvio(?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_descripcion", modelo.getDescripcion());
        cmd.setString("p_Direccion", modelo.getDireccion());
        cmd.setString("p_Distrito", modelo.getDistritoString());
        cmd.setDate("p_fechaEntrega", java.sql.Date.valueOf(modelo.getFechaEntrega()));
        cmd.setDate("p_horarioEntrega", java.sql.Date.valueOf(modelo.getHorarioEntrega()));
        cmd.setInt("p_ordenCompra_IdPedido", modelo.getOrdenCompra().getId());
        cmd.setInt("p_activo", modelo.getActivoInt());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, DetalleEnvio modelo) throws SQLException {
        String sql = "{CALL modificarDetalleDeEnvio(?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_id_DetalleEnvio", modelo.getId());
        cmd.setString("p_descripcion", modelo.getDescripcion());
        cmd.setString("p_Direccion", modelo.getDireccion());
        cmd.setString("p_Distrito", modelo.getDistritoString());
        cmd.setDate("p_fechaEntrega", java.sql.Date.valueOf(modelo.getFechaEntrega()));
        cmd.setDate("p_horarioEntrega", java.sql.Date.valueOf(modelo.getHorarioEntrega()));
        cmd.setInt("p_ordenCompra_IdPedido", modelo.getOrdenCompra().getId());
        cmd.setInt("p_activo", modelo.getActivoInt());
        
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
    protected DetalleEnvio mapearModelo(ResultSet rs) throws SQLException {
        DetalleEnvio detalle = new DetalleEnvio();
        
        detalle.setId(rs.getInt("id_DetalleEnvio"));
        detalle.setDescripcion(rs.getString("descripcion"));
        detalle.setDireccion(rs.getString("direccion"));
        detalle.setDistritoString(rs.getString("distrito"));
        detalle.setFechaEntrega(rs.getDate("fechaEntrega").toLocalDate());
        detalle.setHorarioEntrega(rs.getDate("horarioEntrega").toLocalDate());
        detalle.setOrdenCompra(new OrdenCompraDAOImpl().leer(rs.getInt("ordenCompra_IdPedido")));
        detalle.setActivoInt(rs.getInt("activo"));
        
        return detalle;
    }

    public DetalleEnvio obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
