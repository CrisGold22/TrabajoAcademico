/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.producto;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.producto.CategoriaProductoDAO;
import pe.edu.pucp.inf30.softprog.dao.producto.ProductoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.CategoriaProductoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.ProductoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.producto.CategoriaProducto;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.ProductoBO;

/**
 *
 * @author Cristhian Horacio
 */
public class ProductoBOImpl implements ProductoBO{
    private final ProductoDAO productoDAO;
    private final CategoriaProductoDAO categoriaDAO;
    
    public ProductoBOImpl(){
        productoDAO = new ProductoDAOImpl();
        categoriaDAO = new CategoriaProductoDAOImpl();
    }
    
    @Override
    public List<Producto> listar() {
        return this.productoDAO.leerTodos();
    }

    @Override
    public void insertar(Producto modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("El Producto no puede ser nulo");
        } 
        
        this.productoDAO.crear(modelo);
    }

    @Override
    public void actualizar(Producto modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("El Producto no puede ser nulo");
        } 
        
        if(modelo.getId() <= 0){
            throw new IllegalArgumentException("Producto con ID inválido");
        }
        
        Producto producto = this.productoDAO.leer(modelo.getId());
        
        if(producto == null){
            throw new RuntimeException("No se encontro el Producto con ID: "+ modelo.getId());
        }
        
        this.productoDAO.actualizar(modelo);
    }

    @Override
    public Producto obtener(int id) {        
        if( id <= 0){
            throw new IllegalArgumentException("Producto con ID inválido");
        }
        
        Producto producto = this.productoDAO.leer(id);
        
        if(producto == null){
            throw new RuntimeException("No se encontro el Producto con ID: "+ id);
        }        
        
        return producto;
    }

    @Override
    public void eliminar(int id) {
        if( id <= 0){
            throw new IllegalArgumentException("Producto con ID inválido");
        }
        
        Producto producto = this.productoDAO.leer(id);
        
        if(producto == null){
            throw new RuntimeException("No se encontro el Producto con ID: "+ id);
        }    
        
        this.productoDAO.eliminar(id);
    }

    @Override
    public Producto obtenerPorSku(String sku) {
        if(sku.isBlank()){
            throw new IllegalArgumentException("SKU en blanco");
        }
        
        if(sku.isEmpty()){
            throw new IllegalArgumentException("SKU vacio");
        }
        
        Producto producto = this.productoDAO.obtenerPorSku(sku);
        
        if(producto == null){
            throw new RuntimeException("No se encontro el Producto con SKU: "+ sku);
        }
        
        return producto;
    }

    @Override
    public List<Producto> filtrarProductoPorPrecio(Integer id, double RangoPrecio1, double RangoPrecio2) {
        if(id <= 0){
            throw new IllegalArgumentException("Categoria Producto con ID invalido");
        }
        
        CategoriaProducto categoria = this.categoriaDAO.leer(id);
        
        if(categoria == null){
            throw new RuntimeException("No se encontro la Categoria Producto a buscar con ID : "+ id);
        }
        
        return this.productoDAO.filtrarProductoPorPrecio(id,RangoPrecio1, RangoPrecio2);
    }

    @Override
    public List<Producto> filtrarProductoPorMarca(Integer id, String marca) {
        if(id <= 0){
            throw new IllegalArgumentException("Categoria Producto con ID invalido");
        }
        
        CategoriaProducto categoria = this.categoriaDAO.leer(id);
        
        if(categoria == null){
            throw new RuntimeException("No se encontro la Categoria Producto a buscar con ID : "+ id);
        }        
        
        return this.productoDAO.filtrarProductoPorMarca(id, marca);
    }

    @Override
    public List<Producto> filtrarProductoPorDescuento(Integer id, String nombre) {
        if(id <= 0){
            throw new IllegalArgumentException("Categoria Producto con ID invalido");
        }
        
        CategoriaProducto categoria = this.categoriaDAO.leer(id);
        
        if(categoria == null){
            throw new RuntimeException("No se encontro la Categoria Producto a buscar con ID : "+ id);
        } 
        
        return this.productoDAO.filtrarProductoPorDescuento(id, nombre);
    }

    @Override
    public boolean verificarStockSuficientePorID(Integer id, Integer cantidadSolicitada) {
        if(id <= 0){
            throw new IllegalArgumentException("Producto con ID invalido");
        }
        
        Producto producto = this.productoDAO.leer(id);
        
        if(producto == null){
            throw new RuntimeException("No se encontro el Producto a buscar con ID : "+ id);
        }
        
        return this.productoDAO.verificarStockSuficientePorID(id, cantidadSolicitada);
    }

    @Override
    public boolean verificarStockSuficientePorSKU(String sku, Integer cantidadSolicitada) {

        Producto producto = this.productoDAO.obtenerPorSku(sku);
        
        if(producto == null){
            throw new RuntimeException("No se encontro el Producto con SKU : "+ sku);
        }        
                
        return this.productoDAO.verificarStockSuficientePorSKU(sku, cantidadSolicitada);
    }
    
}
