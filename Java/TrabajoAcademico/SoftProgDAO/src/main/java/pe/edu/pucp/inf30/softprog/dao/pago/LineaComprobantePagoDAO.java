/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dao.pago;

import java.sql.Connection;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.Persistible;
import pe.edu.pucp.inf30.softprog.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.softprog.modelo.pago.LineaComprobantePago;

/**
 *
 * @author Cristhian Horacio
 */
public interface LineaComprobantePagoDAO extends PersistibleTransaccional<LineaComprobantePago, Integer> {
    List<LineaComprobantePago> listarPorIdComprobante(Connection conn, int id);
    List<LineaComprobantePago> listarPorIdComprobante(int id);
}
