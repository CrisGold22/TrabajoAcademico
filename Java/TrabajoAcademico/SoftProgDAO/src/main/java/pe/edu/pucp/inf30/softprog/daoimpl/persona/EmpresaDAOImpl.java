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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.persona.EmpresaDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.modelo.persona.Empresa;

/**
 *
 * @author PC
 */
public class EmpresaDAOImpl extends BaseDAO<Empresa> implements EmpresaDAO{

    @Override
    protected PreparedStatement comandoCrear(Connection conn, Empresa modelo) throws SQLException {
        String sql = "{CALL insertarEmpresa(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_ruc", modelo.getRuc());
        cmd.setString("p_razonsocial", modelo.getRazonSocial());
        cmd.setString("p_direccionFiscal", modelo.getDireccionFiscal());
        cmd.setString("p_departamento", modelo.getDepartamento());
        cmd.setString("p_provincia", modelo.getProvincia());
        cmd.setString("p_distrito", modelo.getDistrito());
        cmd.setString("p_telefono", modelo.getTelefono());
        cmd.setString("p_email", modelo.getEmail());
        cmd.setString("p_codigoPostal", modelo.getCodigoPostal());
        cmd.setInt("p_Activo", modelo.getActivoInt());
        cmd.setInt("p_idCliente", modelo.getCliente().getId());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, Empresa modelo) throws SQLException {
        String sql = "{CALL modificarEmpresa(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idEmpresa", modelo.getId());
        cmd.setString("p_ruc", modelo.getRuc());
        cmd.setString("p_razonsocial", modelo.getRazonSocial());
        cmd.setString("p_direccionFiscal", modelo.getDireccionFiscal());
        cmd.setString("p_departamento", modelo.getDepartamento());
        cmd.setString("p_provincia", modelo.getProvincia());
        cmd.setString("p_distrito", modelo.getDistrito());
        cmd.setString("p_telefono", modelo.getTelefono());
        cmd.setString("p_email", modelo.getEmail());
        cmd.setString("p_codigoPostal", modelo.getCodigoPostal());
        cmd.setInt("p_Activo", modelo.getActivoInt());
        cmd.setInt("p_idCliente", modelo.getCliente().getId());
        
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL eliminarEmpresa(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idEmpresa", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL buscarEmpresaPorId(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idEmpresa", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL listarEmpresas()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }

    @Override
    protected Empresa mapearModelo(ResultSet rs) throws SQLException {
        Empresa empresa = new Empresa();
        
        empresa.setId(rs.getInt("idEmpresa"));
        empresa.setRuc(rs.getString("ruc"));
        empresa.setRazonSocial(rs.getString("razonSocial"));
        empresa.setDireccionFiscal(rs.getString("direccionFiscal"));
        empresa.setDepartamento(rs.getString("departamento"));
        empresa.setProvincia(rs.getString("provincia"));
        empresa.setDistrito(rs.getString("distrito"));
        empresa.setTelefono(rs.getString("telefono"));
        empresa.setEmail(rs.getString("email"));
        empresa.setActivoInt(rs.getInt("activo"));
        empresa.setCodigoPostal(rs.getString("codigoPostal"));
        empresa.setCliente(new ClienteDAOImpl().leer(rs.getInt("cliente_idCliente")));
        
        return empresa;
    }

    protected PreparedStatement comandoListarEmpresasActivos(Connection conn) throws SQLException{
        String sql = "{CALL listarEmpresasActivos()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }
    
    protected PreparedStatement comandoListarEmpresasNoActivos(Connection conn) throws SQLException{
        String sql = "{CALL listarEmpresasNoActivos()}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }
    
    protected PreparedStatement comandoListarEmpresasPorCliente(Connection conn, int id) throws SQLException{
        String sql = "{CALL listarEmpresasPorCliente(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCliente", id);
        
        return cmd;
    }
    
    protected PreparedStatement comandoListarEmpresasPorClienteActivas(Connection conn, int id) throws SQLException{
        String sql = "{CALL listarEmpresasPorClienteActivas(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCliente", id);
        
        return cmd;
    }
    
    protected PreparedStatement comandoListarEmpresasPorClienteNoActivas(Connection conn, int id) throws SQLException{
        String sql = "{CALL listarEmpresasPorClienteNoActivas(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idCliente", id);
        
        return cmd;
    }
    
    
    @Override
    public List<Empresa> listarEmpresasActivos() {
        return ejecutarComando(conn -> {
            try(PreparedStatement cmd = this.comandoListarEmpresasActivos(conn)){
                ResultSet rs = cmd.executeQuery();
                List<Empresa> listaEmpresas = new ArrayList<>();
                
                while(rs.next()){
                    listaEmpresas.add(this.mapearModelo(rs));
                }
                
                return listaEmpresas;
            }
        });
    }

    @Override
    public boolean validarEmpresa(int id) {
        return ejecutarComando(conn -> {
            try(PreparedStatement cmd = this.comandoValidarEmpresa(conn, id)){
                
                return cmd.executeUpdate()>0;
            }
            catch (SQLException e) {
                System.err.println("Error SQL: " + e.getMessage());
            throw new RuntimeException(e);
            }
        });    
    }
    
    protected PreparedStatement comandoValidarEmpresa(Connection conn, int id) throws SQLException{
        String sql = "{CALL validarEmpresa(?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_id_empresa", id);
        
        return cmd;
    }
    
    @Override
    public List<Empresa> listarEmpresasNoActivos() {
        return ejecutarComando(conn -> {
            try(PreparedStatement cmd = this.comandoListarEmpresasNoActivos(conn)){
                ResultSet rs = cmd.executeQuery();
                List<Empresa> listaEmpresas = new ArrayList<>();
                
                while(rs.next()){
                    listaEmpresas.add(this.mapearModelo(rs));
                }
                
                return listaEmpresas;
            }
        });
    }

    @Override
    public List<Empresa> listarEmpresasPorCliente(int id) {
        return ejecutarComando(conn -> {
            try(PreparedStatement cmd = this.comandoListarEmpresasPorCliente(conn, id)){
                ResultSet rs = cmd.executeQuery();
                List<Empresa> listaEmpresas = new ArrayList<>();
                
                while(rs.next()){
                    listaEmpresas.add(this.mapearModelo(rs));
                }
                
                return listaEmpresas;
            }
        });
    }

    @Override
    public List<Empresa> listarEmpresasPorClienteActivas(int id) {
        return ejecutarComando(conn -> {
            try(PreparedStatement cmd = this.comandoListarEmpresasPorClienteActivas(conn, id)){
                ResultSet rs = cmd.executeQuery();
                List<Empresa> listaEmpresas = new ArrayList<>();
                
                while(rs.next()){
                    listaEmpresas.add(this.mapearModelo(rs));
                }
                
                return listaEmpresas;
            }
        });
    }

    @Override
    public List<Empresa> listarEmpresasPorClienteNoActivas(int id) {
        return ejecutarComando(conn -> {
            try(PreparedStatement cmd = this.comandoListarEmpresasPorClienteNoActivas(conn, id)){
                ResultSet rs = cmd.executeQuery();
                List<Empresa> listaEmpresas = new ArrayList<>();
                
                while(rs.next()){
                    listaEmpresas.add(this.mapearModelo(rs));
                }
                
                return listaEmpresas;
            }
        });
    }

    
    
    protected PreparedStatement comandoinsertarEmpresaValidandoCodigoPostal(Connection conn,Empresa modelo) throws SQLException{
        String sql = "{CALL insertarEmpresaValidandoCodigoPostal(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_ruc", modelo.getRuc());
        cmd.setString("p_razonsocial", modelo.getRazonSocial());
        cmd.setString("p_direccionFiscal", modelo.getDireccionFiscal());
        cmd.setString("p_departamento", modelo.getDepartamento());
        cmd.setString("p_provincia", modelo.getProvincia());
        cmd.setString("p_distrito", modelo.getDistrito());
        cmd.setString("p_telefono", modelo.getTelefono());
        cmd.setString("p_email", modelo.getEmail());
        cmd.setString("p_codigoPostal", modelo.getCodigoPostal());
        cmd.setInt("p_Activo", modelo.getActivoInt());
        cmd.setInt("p_idCliente", modelo.getCliente().getId());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;        
    }
    
    
    @Override
    public Integer insertarEmpresaValidandoCodigoPostal(Empresa modelo) {
        return ejecutarComando(conn -> insertarEmpresaValidandoCodigoPostal(conn, modelo));
    }

    @Override
    public Integer insertarEmpresaValidandoCodigoPostal(Connection conn, Empresa modelo) {
        try (PreparedStatement cmd = this.comandoinsertarEmpresaValidandoCodigoPostal(conn, modelo)) {
            if (cmd.executeUpdate() == 0) {
                return null;
            }

            if (cmd instanceof CallableStatement callableCmd) {
                return callableCmd.getInt("p_id");
            }

            try (ResultSet rs = cmd.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            return null;
        } 
        catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            throw new RuntimeException(e);
        }    
    }
    
}
