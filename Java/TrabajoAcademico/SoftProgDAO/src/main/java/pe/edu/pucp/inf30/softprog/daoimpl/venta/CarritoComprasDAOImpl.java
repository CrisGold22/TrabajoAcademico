/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.daoimpl.venta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.softprog.dao.venta.CarritoComprasDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.ClienteDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoCompras;

/**
 *
 * @author Cristhian Horacio
 */
public class CarritoComprasDAOImpl extends TransaccionalBaseDAO<CarritoCompras> implements CarritoComprasDAO  {

    @Override
    protected PreparedStatement comandoCrear(Connection conn, CarritoCompras modelo) throws SQLException {
        String sql = "CALL insertarCarritoDeCompras(?, ?, ?, ?, ?, ?)";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_Id_CarritoDeCompras", modelo.getId());
        cmd.setDouble("p_Total_Parcial", modelo.getTotalParcial());
        cmd.setString("p_Estado", modelo.getEstadoString());
        cmd.setDouble("p_total_con_descuento", modelo.getTotalConDescuento());
        cmd.setInt("p_cliente_idCliente", modelo.getCliente().getId());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoActualizar(Connection conn, CarritoCompras modelo) throws SQLException {
        String sql = "CALL modificarCarritoDeCompras(?, ?, ?, ?, ?)";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_Id_CarritoDeCompras", modelo.getId());
        cmd.setDouble("p_Total_Parcial", modelo.getTotalParcial());
        cmd.setString("p_Estado", modelo.getEstadoString());
        cmd.setDouble("p_total_con_descuento", modelo.getTotalConDescuento());
        cmd.setInt("p_cliente_idCliente", modelo.getCliente().getId());
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "CALL eliminarCarritoDeCompras(?)";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_Id_CarritoDeCompras", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "CALL buscarCarritoDeComprasPorId(?)";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_Id_CarritoDeCompras", id);
        
        return cmd;
    }

    @Override
    protected PreparedStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "CALL listarCarritosDeCompras()";
        
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }

    @Override
    protected CarritoCompras mapearModelo(ResultSet rs) throws SQLException {
        CarritoCompras carrito = new CarritoCompras();
        
        carrito.setId(rs.getInt("Id_CarritoDeCompras"));
        carrito.setTotalParcial(rs.getDouble("Total_Parcial"));
        carrito.setEstadoString(rs.getString("Estado"));
        carrito.setTotalConDescuento(rs.getDouble("total_con_descuento"));
        carrito.setCliente(new ClienteDAOImpl().leer(rs.getInt("cliente_idCliente")));
        
        return carrito;
    }

    public CarritoCompras obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
