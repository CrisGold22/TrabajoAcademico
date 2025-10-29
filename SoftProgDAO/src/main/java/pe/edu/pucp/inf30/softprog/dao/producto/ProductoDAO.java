/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dao.producto;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.Persistible;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;

/**
 *
 * @author Cristhian Horacio
 */
public interface ProductoDAO extends Persistible<Producto, Integer> {
    Producto obtenerPorSku(String sku);
    List<Producto> filtrarProductoPorPrecio(Integer id,double RangoPrecio1,double RangoPrecio2);
    List<Producto> filtrarProductoPorMarca(Integer id,String marca);
    List<Producto> filtrarProductoPorDescuento(Integer id,String nombre);
}
