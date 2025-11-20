/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.bo.venta;

import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoCompras;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarrito;
import pe.edu.pucp.inf30.softprog.negocio.bo.Gestionable;

/**
 *
 * @author Cristhian Horacio
 */
public interface CarritoComprasBO extends Gestionable<CarritoCompras>{
    List<LineaCarrito> listarLineaCarritoPorIdCarrito(int idCarritoCompras);
    CarritoCompras obtenerCarritoComprasPorIdCliente(int idCliente);
}
