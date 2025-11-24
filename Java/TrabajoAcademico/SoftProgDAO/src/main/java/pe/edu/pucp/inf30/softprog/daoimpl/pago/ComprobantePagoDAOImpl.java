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
import pe.edu.pucp.inf30.softprog.daoimpl.venta.OrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.pago.LineaComprobantePago;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public class ComprobantePagoDAOImpl extends TransaccionalBaseDAO<ComprobantePago> implements ComprobantePagoDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, ComprobantePago modelo) throws SQLException {
        String sql = "{CALL insertarComprobantePago(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setDate(1, new java.sql.Date(modelo.getFechaEmision().getTime()));

        cmd.setString(2, String.valueOf(modelo.getruc())); 
        
        cmd.setDouble(3, modelo.getTotalSinImpuestos());
        
        cmd.setDouble(4, modelo.getImpuestos());
        
        cmd.setDouble(5, modelo.getTotalFinal());
        
        cmd.setString(6, modelo.getMetodoString());
        
        cmd.setInt(7, modelo.getActivoInt());
        
        cmd.setInt(8, modelo.getOrdenCompra().getId());
        
        cmd.registerOutParameter(9, Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, ComprobantePago modelo) throws SQLException {
        String sql = "{CALL modificarComprobantePago(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_idComprobante", Integer.toString(modelo.getId()));
        cmd.setDate("p_fechaEmision", (java.sql.Date) modelo.getFechaEmision());
        cmd.setString("p_RUC", modelo.getruc());
        cmd.setDouble("p_totalSinImpuestos", modelo.getTotalSinImpuestos());
        cmd.setString("p_Impuesto", Double.toString(modelo.getImpuestos()));
        cmd.setDouble("p_totalFinal", modelo.getTotalFinal());
        cmd.setString("p_metodoPago", modelo.getMetodoString());
        cmd.setDouble("p_subTotal", modelo.getTotalSinImpuestos());
        cmd.setInt("p_activo", modelo.getActivoInt());
        cmd.setInt("p_OrdenCompra_IdPedido", modelo.getOrdenCompra().getId());
        
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
        comprobante.setFechaEmision(rs.getDate("fechaEmision"));
        comprobante.setruc(rs.getString("RUC"));
        comprobante.setTotalSinImpuestos(rs.getDouble("totalSinImpuestos"));
        comprobante.setImpuestos(rs.getDouble("Impuesto"));
        comprobante.setTotalFinal(rs.getDouble("totalFinal"));
        comprobante.setMetodoString(rs.getString("metodoPago"));
        comprobante.setActivoInt(rs.getInt("activo"));
        comprobante.setOrdenCompra(new OrdenCompraDAOImpl().leer(rs.getInt("OrdenCompra_IdPedido")));
        
        return comprobante;
    }

   
}
