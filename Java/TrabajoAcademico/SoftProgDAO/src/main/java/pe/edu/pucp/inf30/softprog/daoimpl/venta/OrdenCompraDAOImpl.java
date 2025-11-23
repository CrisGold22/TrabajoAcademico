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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.OrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.ClienteDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.EmpresaDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;
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
        
        cmd.setObject("p_FechaCreacion", modelo.getFechaCreacion());
        cmd.setDouble("p_total_parcial", modelo.getTotalParcial());
        cmd.setDouble("p_total_final", modelo.getTotalFinal());
        cmd.setDouble("p_descuentoTotal", modelo.getDescuentoTotal());
        cmd.setString("p_Estado", modelo.getEstadoString());
        cmd.setInt("p_carrito_idCarrito", modelo.getCarritoCompras().getId());
        cmd.setInt("p_Activo", modelo.getActivoInt());
        cmd.setInt("p_Cliente", modelo.getCliente().getId());
        cmd.setInt("p_Empresa", modelo.getEmpresa().getId());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, OrdenCompra modelo) throws SQLException {
        String sql = "{CALL modificarOrdenCompra(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idOrdenCompra", modelo.getId());
        cmd.setObject("p_FechaCreacion", modelo.getFechaCreacion());
        cmd.setDouble("p_total_parcial", modelo.getTotalParcial());
        cmd.setDouble("p_total_final", modelo.getTotalFinal());
        cmd.setDouble("p_descuentoTotal", modelo.getDescuentoTotal());
        cmd.setString("p_Estado", modelo.getEstadoString());
        cmd.setInt("p_carrito_idCarrito", modelo.getCarritoCompras().getId());
        cmd.setInt("p_Activo", modelo.getActivoInt());
        cmd.setInt("p_Cliente", modelo.getCliente().getId());
        cmd.setInt("p_Empresa", modelo.getEmpresa().getId());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarOrdenCompra(?)}"; // <-- ERROR 1: Usa '?' (índice)
     
        CallableStatement cmd = conn.prepareCall(sql);
     
        cmd.setInt("p_idOrdenCompra", id); // <-- ERROR 2: Asigna por nombre
    
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarOrdenCompraPorId(?)}";

        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idOrdenCompra", id); 

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
        
        orden.setId(rs.getInt("idOrdenCompra"));
        orden.setFechaCreacion(rs.getObject("fechaCreacion", LocalDateTime.class));
        orden.setTotalParcial(rs.getDouble("total_parcial"));
        orden.setTotalFinal(rs.getDouble("total_final"));
        orden.setDescuentoTotal(rs.getDouble("descuentoTotal"));
        orden.setEstadoString(rs.getString("estado"));
        orden.setCarritoCompras(new CarritoComprasDAOImpl().leer(rs.getInt("carrito_idCarrito")));
        orden.setActivoInt(rs.getInt("activo"));
        orden.setCliente(new ClienteDAOImpl().leer(rs.getInt("cliente_idCliente")));
        orden.setEmpresa(new EmpresaDAOImpl().leer(rs.getInt("empresa_idEmpresa")));
        
        return orden;
    }

    
    protected PreparedStatement comandoconsultarPedidoPorFechas(Connection conn,Integer id,
            LocalDateTime fecha1, LocalDateTime fecha2) throws SQLException {
        
        String sql = "{CALL consultarPedidoPorFechas(?,?,?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_Cliente", id);
        cmd.setObject("p_Filtro1", fecha1);
        cmd.setObject("p_Filtro2", fecha2);
        
        return cmd;
    }    
    
    @Override
    public List<OrdenCompra> consultarPedidoPorFechas(Integer id, LocalDateTime fecha1, LocalDateTime fecha2) {
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

    protected CallableStatement comandoDesactivarOrdenCompra(Connection conn,Integer id) throws SQLException {
        
        String sql = "{CALL DesactivarOrdenCompra(?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_IdPedido", id);
        cmd.registerOutParameter("p_Mensaje", Types.VARCHAR);
        
        return cmd;
    }  
    
    
    @Override
    public String desactivarOrdenCompra(Integer id) {
        return ejecutarComando(conn -> desactivarOrdenCompra(id, conn));
    }

    @Override
    public String desactivarOrdenCompra(Integer id, Connection conn) {
        try(CallableStatement cmd = this.comandoDesactivarOrdenCompra(conn, id)){
            cmd.execute();
            String mensaje = cmd.getString("p_Mensaje");
            
            return mensaje;
            
        } catch (SQLException ex) {
            System.err.println("Error SQL: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }


}
