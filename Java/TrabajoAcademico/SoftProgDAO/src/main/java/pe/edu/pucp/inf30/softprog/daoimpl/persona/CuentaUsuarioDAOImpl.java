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
import java.sql.Timestamp;
import java.sql.Types;
import pe.edu.pucp.inf30.softprog.dao.persona.CuentaUsuarioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.dbmanager.DBFactoryProvider;
import pe.edu.pucp.inf30.softprog.dbmanager.DBManager;
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;

/**
 *
 * @author Cristhian Horacio
 */
public class CuentaUsuarioDAOImpl extends BaseDAO<CuentaUsuario> implements CuentaUsuarioDAO {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, CuentaUsuario modelo) throws SQLException {
        String sql = "{CALL insertarCuentaUsuario(?, ?, ?, ?, ?)}";

        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setString("p_userName", modelo.getUsername());
        cmd.setString("p_password", modelo.getPassword());
        cmd.setInt("p_activo", modelo.getActivoInt());
        cmd.setString("p_correo", modelo.getCorreo());
        cmd.registerOutParameter("p_id", Types.INTEGER);

        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, CuentaUsuario modelo) throws SQLException {
        String sql = "{CALL modificarCuentaUsuario(?, ?, ?, ?, ?)}";

        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idCuentaUsuario", modelo.getId());
        cmd.setString("p_userName", modelo.getUsername());
        cmd.setString("p_password", modelo.getPassword());
        cmd.setInt("p_activo", modelo.getActivoInt());
        cmd.setString("p_correo", modelo.getCorreo());

        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarCuentaUsuario(?)}";

        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idCuentaUsuario", id);

        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarCuentaUsuarioPorId(?)}";

        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idCuentaUsuario", id);

        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarCuentaUsuarios()}";

        CallableStatement cmd = conn.prepareCall(sql);

        return cmd;
    }

    @Override
    protected CuentaUsuario mapearModelo(ResultSet rs) throws SQLException {
        CuentaUsuario cuenta = new CuentaUsuario();

        cuenta.setId(rs.getInt("idCuentaUsuario"));
        cuenta.setUsername(rs.getString("userName"));
        cuenta.setPassword(rs.getString("password"));
        cuenta.setActivoInt(rs.getInt("activo"));

        // Nuevos campos
        cuenta.setCorreo(rs.getString("correo"));
        cuenta.setResetToken(rs.getString("reset_token"));
        cuenta.setResetTokenExpira(rs.getTimestamp("reset_token_expira"));

        return cuenta;
    }

    public CuentaUsuario obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected PreparedStatement comandoLogin(Connection conn, String email,
            String password) throws SQLException {
        String sql = "{CALL loginUsuario(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_correo", email);
        cmd.setString("p_password", password);
        cmd.registerOutParameter("p_valido", Types.BOOLEAN);

        return cmd;
    }

    @Override
    public boolean login(String email, String password) {
        return ejecutarComando(conn -> {
            //String contrasena = PasswordUtils.hashPassword(password);
            try (PreparedStatement cmd = this.comandoLogin(conn, email, password)) {
                if (cmd instanceof CallableStatement callableCmd) {
                    callableCmd.execute();
                    boolean valido = callableCmd.getBoolean("p_valido");
                    if (!valido) {
                        System.err.println("No se encontro el registro con"
                                + "email: " + email + ", password");
                    }
                    return valido;
                }
                return false;
            }
        });
    }

    @Override
    public CuentaUsuario obtenerPorUsername(String username) {
        String sql = "SELECT * FROM cuentausuario WHERE username = ?";
        DBManager dbManager = DBFactoryProvider.getManager();
        try (Connection conn = dbManager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearModelo(rs); // ya está en BaseDAO
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error obteniendo CuentaUsuario por username", ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(CuentaUsuarioDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    @Override
    public boolean actualizarPassword(int idCuentaUsuario, String nuevoPasswordHash) {
        String sql = "UPDATE cuentausuario SET password = ? WHERE idCuentaUsuario = ?";
        DBManager dbManager = DBFactoryProvider.getManager();
        try (Connection conn = dbManager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoPasswordHash);
            ps.setInt(2, idCuentaUsuario);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Error actualizando contraseña", ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(CuentaUsuarioDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public CuentaUsuario obtenerPorResetToken(String token) {
        String sql = "SELECT * FROM cuentausuario WHERE reset_token = ?";
        DBManager dbManager = DBFactoryProvider.getManager();
        try (Connection conn = dbManager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearModelo(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error obteniendo usuario por token", ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(CuentaUsuarioDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    @Override
    public boolean actualizarTokenRecuperacion(int idCuentaUsuario, String token, Timestamp expira) {
        DBManager dbManager = DBFactoryProvider.getManager();
        String sql = "UPDATE cuentausuario SET reset_token = ?, reset_token_expira = ? WHERE idCuentaUsuario = ?";
        try (Connection conn = dbManager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, token);
            ps.setTimestamp(2, expira);
            ps.setInt(3, idCuentaUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Error actualizando token de recuperación", ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(CuentaUsuarioDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public boolean actualizarPasswordYLimpiarToken(int idCuentaUsuario, String hash) {
        String sql = "UPDATE cuentausuario "
                + "SET password = ?, reset_token = NULL, reset_token_expira = NULL "
                + "WHERE idCuentaUsuario = ?";
        DBManager dbManager = DBFactoryProvider.getManager();
        try (Connection conn = dbManager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hash);
            ps.setInt(2, idCuentaUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Error actualizando contraseña", ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(CuentaUsuarioDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public CuentaUsuario obtenerPorCorreo(String correo) {
        String sql = "SELECT * FROM cuentausuario WHERE correo = ?";
        DBManager dbManager = DBFactoryProvider.getManager();
        try (Connection conn = dbManager.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearModelo(rs); // ya está en BaseDAO
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error obteniendo CuentaUsuario por username", ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(CuentaUsuarioDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

}

