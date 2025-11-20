/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.bo.producto;

import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.negocio.bo.Gestionable;

/**
 *
 * @author Cristhian Horacio
 */
public interface ProductoBO extends Gestionable<Producto> {
    Producto obtenerPorSku(String sku);
    List<Producto> filtrarProductoPorPrecioRegular(Integer id,double RangoPrecio1,double RangoPrecio2);
    List<Producto> filtrarProductoPorMarca(Integer id,String marca);    
    List<Producto> filtrarProductoPorDescuento(Integer id,String nombre);
    boolean verificarStockSuficientePorID(Integer id, Integer cantidadSolicitada);
    boolean verificarStockSuficientePorSKU(String sku, Integer cantidadSolicitada);
    List<Producto> filtrarProductoPorPrecioAlMayor(Integer id,double RangoPrecio1,double RangoPrecio2);
    List<Producto> obtenerProductosPorCategoria(Integer idCategoria);
    List<Producto> listaProductosPorCategoria(String nombre);
}
