/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dao.venta;

import java.sql.Connection;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.Persistible;
import pe.edu.pucp.inf30.softprog.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public interface LineaOrdenCompraDAO extends PersistibleTransaccional<LineaOrdenCompra, Integer> {
    List<LineaOrdenCompra> listarPorIdOrdenCompra(int id);
    List<LineaOrdenCompra> listarPorIdOrdenCompra(Connection conn, int id);
}
