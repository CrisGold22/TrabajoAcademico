/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.bo.venta;

import java.sql.Date;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;
import pe.edu.pucp.inf30.softprog.negocio.bo.Gestionable;

/**
 *
 * @author Cristhian Horacio
 */
public interface OrdenCompraBO extends Gestionable<OrdenCompra> {
    List<OrdenCompra> consultarPedidoPorFechas(Integer id,Date fecha1,Date fecha2);
    List<OrdenCompra> consultarOrdenCompraPorIdCliente(Integer id);
    
    void desactivarOrdenCompra(Integer id);
}
