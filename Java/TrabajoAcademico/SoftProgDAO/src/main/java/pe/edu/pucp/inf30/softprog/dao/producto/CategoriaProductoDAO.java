/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dao.producto;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.Persistible;
import pe.edu.pucp.inf30.softprog.modelo.producto.CategoriaProducto;

/**
 *
 * @author Cristhian Horacio
 */
public interface CategoriaProductoDAO extends Persistible<CategoriaProducto, Integer> {
    List<String>obtenerMarcasPorCategoria(Integer idCategoria);
    CategoriaProducto obtenerCategoriaPorNombre(String nombreCategoria);
}
