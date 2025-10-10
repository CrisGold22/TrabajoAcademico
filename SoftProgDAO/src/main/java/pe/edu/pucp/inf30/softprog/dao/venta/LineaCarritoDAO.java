/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dao.venta;

import java.sql.Connection;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.Persistible;
import pe.edu.pucp.inf30.softprog.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarritoDTO;

/**
 *
 * @author Cristhian Horacio
 */
public interface LineaCarritoDAO extends PersistibleTransaccional<LineaCarritoDTO, Integer> {
    List<LineaCarritoDTO> listarPorIdCarrito(int id);
    List<LineaCarritoDTO> listarPorIdCarrito(int id, Connection conn);
}
