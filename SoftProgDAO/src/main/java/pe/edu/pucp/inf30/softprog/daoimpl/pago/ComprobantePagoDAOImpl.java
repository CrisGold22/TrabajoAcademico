/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.daoimpl.pago;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.softprog.dao.pago.ComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.pago.ComprobantePago;
import java.util.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.pago.LineaComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.pago.LineaComprobantePago;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public class ComprobantePagoDAOImpl extends TransaccionalBaseDAO<ComprobantePago> implements ComprobantePagoDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, ComprobantePago modelo) throws SQLException {
        String sql = "{CALL insertarComprobantePago(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_idComprobante", Integer.toString(modelo.getId()));
        cmd.setDate("p_fechaEmision", (java.sql.Date) modelo.getFechaEmision());
        cmd.setInt("p_RUC", modelo.getRUC());
        cmd.setDouble("p_totalSinImpuestos", modelo.getTotalSinImpuestos());
        cmd.setString("p_Impuesto", Double.toString(modelo.getImpuestos()));
        cmd.setDouble("p_totalFinal", modelo.getTotalFinal());
        cmd.setString("p_metodoPago", modelo.getMetodoString());
        cmd.setDouble("p_subTotal", modelo.getTotalSinImpuestos());
        cmd.setInt("p_activo", modelo.getActivo());
        cmd.setInt("p_OrdenCompra_IdPedido", modelo.getIdOrdenCompra());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, ComprobantePago modelo) throws SQLException {
        String sql = "{CALL modificarComprobantePago(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_idComprobante", Integer.toString(modelo.getId()));
        cmd.setDate("p_fechaEmision", (java.sql.Date) modelo.getFechaEmision());
        cmd.setInt("p_RUC", modelo.getRUC());
        cmd.setDouble("p_totalSinImpuestos", modelo.getTotalSinImpuestos());
        cmd.setString("p_Impuesto", Double.toString(modelo.getImpuestos()));
        cmd.setDouble("p_totalFinal", modelo.getTotalFinal());
        cmd.setString("p_metodoPago", modelo.getMetodoString());
        cmd.setDouble("p_subTotal", modelo.getTotalSinImpuestos());
        cmd.setInt("p_activo", modelo.getActivo());
        cmd.setInt("p_OrdenCompra_IdPedido", modelo.getIdOrdenCompra());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarComprobantePago(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idComprobante", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarComprobantePagoPorId(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idComprobante", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarComprobantesPago()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected ComprobantePago mapearModelo(ResultSet rs) throws SQLException {
        ComprobantePago comprobante = new ComprobantePago();
        
        comprobante.setId(rs.getInt("idComprobante"));
        comprobante.setFechaEmision(rs.getTimestamp("fechaEmision"));
        comprobante.setRUC(rs.getInt("RUC"));
        comprobante.setTotalSinImpuestos(rs.getDouble("totalSinImpuestos"));
        comprobante.setImpuestos(rs.getDouble("Impuesto"));
        comprobante.setTotalFinal(rs.getDouble("totalFinal"));
        comprobante.setMetodoString(rs.getString("metodoPago"));
        comprobante.setActivoInt(rs.getInt("activo"));
        comprobante.setIdOrdenCompra(rs.getInt("OrdenCompra_IdPedido"));
        
        return comprobante;
    }

   
}
