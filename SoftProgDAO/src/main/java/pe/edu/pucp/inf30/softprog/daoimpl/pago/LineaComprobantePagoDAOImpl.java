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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.inf30.softprog.dao.pago.LineaComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.pago.LineaComprobantePagoDTO;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompraDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaComprobantePagoDAOImpl extends TransaccionalBaseDAO<LineaComprobantePagoDTO> implements LineaComprobantePagoDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, LineaComprobantePagoDTO modelo) throws SQLException {
        String sql = "{CALL insertarLineaComprobantePago(?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaComprobantePago", modelo.getId());
        cmd.setDouble("p_montoPagado", modelo.getMontoPagado());
        cmd.setInt("p_activo", modelo.getActivo());
        cmd.setInt("p_ComprobantePago_idComprobante", modelo.getIdComprobantePago());
        cmd.setInt("p_codigo", modelo.getCodigo());
        cmd.setInt("p_cantidad", modelo.getCantidad());
        cmd.setDouble("p_subtotal", modelo.getSubTotal());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, LineaComprobantePagoDTO modelo) throws SQLException {
        String sql = "{CALL modificarLineaComprobantePago(?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaComprobantePago", modelo.getId());
        cmd.setDouble("p_montoPagado", modelo.getMontoPagado());
        cmd.setInt("p_activo", modelo.getActivo());
        cmd.setInt("p_ComprobantePago_idComprobante", modelo.getIdComprobantePago());
        cmd.setInt("p_codigo", modelo.getCodigo());
        cmd.setInt("p_cantidad", modelo.getCantidad());
        cmd.setDouble("p_subtotal", modelo.getSubTotal());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarLineaComprobantePago(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaComprobantePago", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarLineaComprobantePago(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idLineaComprobantePago", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarLineaComprobantePago()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }

    @Override
    protected LineaComprobantePagoDTO mapearModelo(ResultSet rs) throws SQLException {
        LineaComprobantePagoDTO linea = new LineaComprobantePagoDTO();
        
        linea.setId(rs.getInt("idLineaComprobantePago"));
        linea.setMontoPagado(rs.getDouble("montoPagado"));
        linea.setActivoInt(rs.getInt("activo"));
        linea.setIdComprobantePago(rs.getInt("ComprobantePago_idComprobante"));
        linea.setCodigo(rs.getInt("codigo"));
        linea.setCantidad(rs.getInt("cantidad"));
        linea.setSubTotal(rs.getDouble("subtotal"));
        
        return linea;
    }

    @Override
    public List<LineaComprobantePagoDTO> listarPorIdComprobante(int id){
        return ejecutarComando(conn -> listarPorIdComprobante(conn, id));
    }
    
    protected PreparedStatement comandoListarPorIdComprobante(Connection conn, int id) throws SQLException{
        String sql = "{CALL listarLineasComprobantePagoPorIdComprobante(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idComprobante", id);
        
        return cmd;
    }
    
    @Override
    public List<LineaComprobantePagoDTO> listarPorIdComprobante(Connection conn, int id) {
        try(PreparedStatement cmd = this.comandoListarPorIdComprobante(conn, id)){
            ResultSet rs = cmd.executeQuery();
            
            List<LineaComprobantePagoDTO> modelos = new ArrayList<>();
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
