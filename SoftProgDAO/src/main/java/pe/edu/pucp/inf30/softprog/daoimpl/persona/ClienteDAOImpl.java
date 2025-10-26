/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.daoimpl.persona;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.softprog.dao.persona.ClienteDAO;
import pe.edu.pucp.inf30.softprog.dao.persona.CuentaUsuarioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.persona.Cliente;

/**
 *
 * @author Cristhian Horacio
 */
public class ClienteDAOImpl extends BaseDAO<Cliente> implements ClienteDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, Cliente modelo) throws SQLException {
        String sql = "{call insertarCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCliente", modelo.getId());
        cmd.setDouble("p_lineaCredito", modelo.getLineaCredito());
        cmd.setString("p_Categoria", modelo.getCategoriaCliente());
        cmd.setInt("p_numeroDePedido_Historico", modelo.getNumeroPedidosHistorico());
        cmd.setInt("p_numeroDePedido_MensualPro", modelo.getNumeroPedidosMensualPro());
        cmd.setString("p_dni", modelo.getDni());
        cmd.setString("p_nombre", modelo.getNombre());
        cmd.setString("p_apellidoPaterno", modelo.getApellidoPaterno());
        cmd.setString("p_apellidoMaterno", modelo.getApellidoMaterno());
        cmd.setString("p_genero", modelo.getGeneroString());
        cmd.setDate("p_fechaNacimiento", (Date) modelo.getFechaNacimiento());
        cmd.setInt("p_telefono", modelo.getTelefono());
        cmd.setInt("p_Activo", modelo.getActivo());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, Cliente modelo) throws SQLException {
        String sql = "{call modificarCliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCliente", modelo.getId());
        cmd.setDouble("p_lineaCredito", modelo.getLineaCredito());
        cmd.setString("p_Categoria", modelo.getCategoriaCliente());
        cmd.setInt("p_numeroDePedido_Historico", modelo.getNumeroPedidosHistorico());
        cmd.setInt("p_numeroDePedido_MensualPro", modelo.getNumeroPedidosMensualPro());
        cmd.setString("p_dni", modelo.getDni());
        cmd.setString("p_nombre", modelo.getNombre());
        cmd.setString("p_apellidoPaterno", modelo.getApellidoPaterno());
        cmd.setString("p_apellidoMaterno", modelo.getApellidoMaterno());
        cmd.setString("p_genero", modelo.getGeneroString());
        cmd.setDate("p_fechaNacimiento", (Date) modelo.getFechaNacimiento());
        cmd.setInt("p_telefono", modelo.getTelefono());
        cmd.setInt("p_Activo", modelo.getActivo());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{call eliminarCliente(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCliente", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{call buscarClientePorId(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCliente", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{call listarClientes()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
  
        
        return cmd;
    }

    @Override
    protected Cliente mapearModelo(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        
        cliente.setId(rs.getInt("idCliente"));
        cliente.setCategoriaCliente(rs.getString("Categoria"));
        cliente.setNumeroPedidosHistorico(rs.getInt("numeroDePedido_Historico"));
        cliente.setNumeroPedidosMensualPro(rs.getInt("numeroDePedido_MensualPro"));
        cliente.setDni(rs.getString("dni"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellidoPaterno(rs.getString("apellidoPaterno"));
        cliente.setApellidoMaterno(rs.getString("apellidoMaterno"));
        cliente.setGeneroString(rs.getString("genero"));
        cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
        cliente.setTelefono(rs.getInt("telefono"));
        cliente.setActivoInt(rs.getInt("Activo"));
        cliente.setLineaCredito(rs.getDouble("lineaCredito"));
        
        return cliente;
    }

    protected PreparedStatement comandoBuscarPorDni(Connection conn, String dni) 
            throws SQLException {
        
        String sql = "{call buscarClientePorDni(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_dni", dni);
        
        return cmd;
    }
    
    @Override
    public Cliente buscarPorDNI(String dni) {
        return ejecutarComando(conn -> {
            try (PreparedStatement cmd = this.comandoBuscarPorDni(conn, dni)){
                ResultSet rs = cmd.executeQuery();
                
                if (!rs.next()) {
                    System.err.println("No se encontro el registro con "
                            + "dni: " + dni);
                    return null;
                }
                
                return this.mapearModelo(rs);
            }           
        });
    }

    public Cliente obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
