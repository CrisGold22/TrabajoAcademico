/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.producto;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.producto.ProductoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.ProductoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.ProductoBO;

/**
 *
 * @author Cristhian Horacio
 */
public class ProductoBOImpl implements ProductoBO{
    private final ProductoDAO productoDAO;
    
    public ProductoBOImpl(){
        productoDAO = new ProductoDAOImpl();
    }
    
    @Override
    public List<Producto> listar() {
        return this.productoDAO.leerTodos();
    }

    @Override
    public void insertar(Producto modelo) {
        this.productoDAO.crear(modelo);
    }

    @Override
    public void actualizar(Producto modelo) {
        this.productoDAO.actualizar(modelo);
    }

    @Override
    public Producto obtener(int id) {
        return this.productoDAO.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.productoDAO.eliminar(id);
    }

    @Override
    public Producto obtenerPorSku(String sku) {
        return this.productoDAO.obtenerPorSku(sku);
    }

    @Override
    public List<Producto> filtrarProductoPorPrecio(Integer id, double RangoPrecio1, double RangoPrecio2) {
        return this.productoDAO.filtrarProductoPorPrecio(id,RangoPrecio1, RangoPrecio2);
    }

    @Override
    public List<Producto> filtrarProductoPorMarca(Integer id, String marca) {
        return this.productoDAO.filtrarProductoPorMarca(id, marca);
    }

    @Override
    public List<Producto> filtrarProductoPorDescuento(Integer id, String nombre) {
        return this.productoDAO.filtrarProductoPorDescuento(id, nombre);
    }
    
}
