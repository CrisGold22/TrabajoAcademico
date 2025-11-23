/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dao.venta;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.Persistible;
import pe.edu.pucp.inf30.softprog.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public interface OrdenCompraDAO extends PersistibleTransaccional<OrdenCompra, Integer>{
    List<OrdenCompra> consultarPedidoPorFechas(Integer id,LocalDateTime fecha1,LocalDateTime fecha2);
    List<OrdenCompra> consultarOrdenCompraPorIdCliente(Integer id);
    
    
    String desactivarOrdenCompra(Integer id);
    String desactivarOrdenCompra(Integer id, Connection conn);
    
}
