/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.daoimpl.persona;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.inf30.softprog.dao.persona.ClienteDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.persona.ClienteDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class ClienteDAOImpl extends BaseDAO<ClienteDTO> implements ClienteDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, ClienteDTO modelo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, ClienteDTO modelo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected ClienteDTO mapearModelo(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected PreparedStatement comandoBuscarPorDni(Connection conn, String dni) 
            throws SQLException {
        
        String sql = "{call buscarClientePorDni(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_dni", dni);
        
        return cmd;
    }
    
    @Override
    public ClienteDTO buscarPorDNI(String dni) {
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
    
}
