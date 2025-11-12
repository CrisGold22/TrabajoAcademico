/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dao.venta;

import java.sql.Connection;
import pe.edu.pucp.inf30.softprog.dao.Persistible;
import pe.edu.pucp.inf30.softprog.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoCompras;

/**
 *
 * @author Cristhian Horacio
 */
public interface CarritoComprasDAO extends PersistibleTransaccional<CarritoCompras, Integer> {
    CarritoCompras obtenerPorCliente(int id);
    CarritoCompras obtenerPorCliente(int id, Connection conn);
}
