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
import pe.edu.pucp.inf30.softprog.dao.persona.AdministradorSistemaDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.persona.AdministradorSistema;

/**
 *
 * @author Cristhian Horacio
 */
public class AdministradorSistemaDAOImpl extends BaseDAO<AdministradorSistema> implements AdministradorSistemaDAO {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, AdministradorSistema modelo) throws SQLException {
        String sql = "{CALL insertarAdministrador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_cargo", modelo.getCargoString());
        cmd.setString("p_rango", modelo.getJerarquiaString());
        cmd.setString("p_dni", modelo.getDni());
        cmd.setString("p_nombre", modelo.getNombre());
        cmd.setString("p_apellidoPaterno", modelo.getApellidoPaterno());
        cmd.setString("p_apellidoMaterno", modelo.getApellidoMaterno());
        cmd.setString("p_genero", modelo.getGeneroString());
        cmd.setDate("p_fechaNacimiento", java.sql.Date.valueOf(modelo.getFechaNacimiento()));
        cmd.setInt("p_telefono", modelo.getTelefono());
        cmd.setDouble("p_Sueldo", modelo.getSueldo());
        cmd.setInt("p_Activo", modelo.getActivoInt());
        cmd.setInt("p_idCuentaUsuario", modelo.getCuenta().getId());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, AdministradorSistema modelo) throws SQLException {
        String sql = "{CALL modificarAdministrador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idAdministrador", modelo.getId());
        cmd.setString("p_cargo", modelo.getCargoString());
        cmd.setString("p_rango", modelo.getJerarquiaString());
        cmd.setString("p_dni", modelo.getDni());
        cmd.setString("p_nombre", modelo.getNombre());
        cmd.setString("p_apellidoPaterno", modelo.getApellidoPaterno());
        cmd.setString("p_apellidoMaterno", modelo.getApellidoMaterno());
        cmd.setString("p_genero", modelo.getGeneroString());
        cmd.setDate("p_fechaNacimiento", java.sql.Date.valueOf(modelo.getFechaNacimiento()));
        cmd.setInt("p_telefono", modelo.getTelefono());
        cmd.setDouble("p_Sueldo", modelo.getSueldo());
        cmd.setInt("p_Activo", modelo.getActivoInt());
        cmd.setInt("p_idCuentaUsuario", modelo.getCuenta().getId());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarAdministrador(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idAdministrador", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarAdministradorPorId(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idAdministrador", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarAdministradores()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected AdministradorSistema mapearModelo(ResultSet rs) throws SQLException {
        AdministradorSistema administrador = new AdministradorSistema();
        
        administrador.setId(rs.getInt("idAdministrador"));
        administrador.setCargoString(rs.getString("cargo"));
        administrador.setJerarquiaString(rs.getString("jerarquia"));
        administrador.setDni(rs.getString("dni"));
        administrador.setNombre(rs.getString("nombre"));
        administrador.setApellidoPaterno(rs.getString("apellidoPaterno"));
        administrador.setApellidoMaterno(rs.getString("apellidoMaterno"));
        administrador.setGeneroString(rs.getString("genero"));
        administrador.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
        administrador.setTelefono(rs.getInt("telefono"));
        administrador.setSueldo(rs.getDouble("Sueldo"));
        administrador.setActivoInt(rs.getInt("activo"));
        administrador.setCuenta(new CuentaUsuarioDAOImpl().leer(rs.getInt("idCuentaUsuario")));
        
        return administrador;
    }
    
}
