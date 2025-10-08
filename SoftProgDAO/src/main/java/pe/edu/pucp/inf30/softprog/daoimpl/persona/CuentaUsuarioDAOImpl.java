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
import pe.edu.pucp.inf30.softprog.dao.persona.CuentaUsuarioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuarioDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class CuentaUsuarioDAOImpl extends BaseDAO<CuentaUsuarioDTO> implements CuentaUsuarioDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, CuentaUsuarioDTO modelo) throws SQLException {
        String sql = "{insertarCuentaUsuario(?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCuentaUsuario", modelo.getId());
        cmd.setString("p_userName", modelo.getUsername());
        cmd.setString("p_password", modelo.getPassword());
        cmd.setInt("p_idUsuario", modelo.getIdUsuario());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, CuentaUsuarioDTO modelo) throws SQLException {
        String sql = "{modificarCuentaUsuario(?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCuentaUsuario", modelo.getId());
        cmd.setString("p_userName", modelo.getUsername());
        cmd.setString("p_password", modelo.getPassword());
        cmd.setInt("p_idUsuario", modelo.getIdUsuario());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{eliminarCuentaUsuario(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCuentaUsuario", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{buscarCuentaUsuario(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCuentaUsuario", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{listarCuentaUsuarios()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        
        return cmd;
    }

    @Override
    protected CuentaUsuarioDTO mapearModelo(ResultSet rs) throws SQLException {
        CuentaUsuarioDTO cuenta = new CuentaUsuarioDTO();
        
        cuenta.setId(rs.getInt("idCuentaUsuario"));
        cuenta.setUsername(rs.getString("userName"));
        cuenta.setPassword(rs.getString("password"));
        cuenta.setIdUsuario(rs.getInt("idUsuario"));
        
        return cuenta;
    }

    public CuentaUsuarioDTO obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
