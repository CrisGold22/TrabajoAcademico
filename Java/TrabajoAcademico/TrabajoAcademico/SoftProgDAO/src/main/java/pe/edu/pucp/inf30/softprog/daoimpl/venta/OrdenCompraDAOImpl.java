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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.OrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.ClienteDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public class OrdenCompraDAOImpl extends TransaccionalBaseDAO<OrdenCompra> implements OrdenCompraDAO {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, OrdenCompra modelo) throws SQLException {
        String sql = "{CALL insertarOrdenCompra(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_IdPedido", modelo.getId());
        cmd.setDate("p_FechaCreacion", (Date) modelo.getFechaCreacion());
        cmd.setDouble("p_total_parcial", modelo.getTotalParcial());
        cmd.setDouble("p_total_final", modelo.getTotalFinal());
        cmd.setDouble("p_descuentoTotal", modelo.getDescuentoTotal());
        cmd.setString("p_Estado", modelo.getEstadoString());
        cmd.setInt("p_DetalleDeEnvio_id_DetalleEnvio", modelo.getDetalleEnvio().getId());
        cmd.setInt("p_Activo", modelo.getActivo());
        cmd.setInt("p_Cliente", modelo.getCliente().getId());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, OrdenCompra modelo) throws SQLException {
        String sql = "{CALL modificarOrdenCompra(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_IdPedido", modelo.getId());
        cmd.setDate("p_FechaCreacion", (Date) modelo.getFechaCreacion());
        cmd.setDouble("p_total_parcial", modelo.getTotalParcial());
        cmd.setDouble("p_total_final", modelo.getTotalFinal());
        cmd.setDouble("p_descuentoTotal", modelo.getDescuentoTotal());
        cmd.setString("p_Estado", modelo.getEstadoString());
        cmd.setInt("p_DetalleDeEnvio_id_DetalleEnvio", modelo.getDetalleEnvio().getId());
        cmd.setInt("p_Activo", modelo.getActivo());
        cmd.setInt("p_Cliente", modelo.getCliente().getId());
        
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
        orden.setDetalleEnvio(new DetalleEnvioDAOImpl().leer(rs.getInt("DetalleDeEnvio_id_DetalleEnvio")));
        orden.setActivoInt(rs.getInt("Activo"));
        orden.setCliente(new ClienteDAOImpl().leer(rs.getInt("cliente_idCliente")));
        
        return orden;
    }

    
    protected PreparedStatement comandoconsultarPedidoPorFechas(Connection conn,Integer id,
            Date fecha1, Date fecha2) throws SQLException {
        
        String sql = "{CALL consultarPedidoPorFechas(?,?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_Cliente", id);
        cmd.setDate("p_Filtro1", fecha1);
        cmd.setDate("p_Filtro2", fecha2);
        
        return cmd;
    }    
    
    @Override
    public List<OrdenCompra> consultarPedidoPorFechas(Integer id, Date fecha1, Date fecha2) {
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoconsultarPedidoPorFechas(conn,id,fecha1,fecha2)) {
                ResultSet rs = cmd.executeQuery();

                List<OrdenCompra> modelos = new ArrayList<>();
                while (rs.next()) {
                    modelos.add(this.mapearModelo(rs));
                }

                return modelos;
            }
        });          
    }
    
    
    protected PreparedStatement comandoconsultarOrdenCompraPorIdCliente(Connection conn,Integer id) throws SQLException {
        
        String sql = "{CALL consultarOrdenCompraPorIdCliente(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_Cliente", id);
        
        return cmd;
    }        

    @Override
    public List<OrdenCompra> consultarOrdenCompraPorIdCliente(Integer id) {
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoconsultarOrdenCompraPorIdCliente(conn,id)) {
                ResultSet rs = cmd.executeQuery();

                List<OrdenCompra> modelos = new ArrayList<>();
                while (rs.next()) {
                    modelos.add(this.mapearModelo(rs));
                }

                return modelos;
            }
        });  
    }
    
}
