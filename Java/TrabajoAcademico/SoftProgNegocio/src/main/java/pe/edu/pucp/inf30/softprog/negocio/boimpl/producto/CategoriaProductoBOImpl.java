/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.producto;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.producto.CategoriaProductoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.CategoriaProductoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.producto.CategoriaProducto;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.CategoriaProductoBO;

/**
 *
 * @author Cristhian Horacio
 */
public class CategoriaProductoBOImpl implements CategoriaProductoBO {
    private final CategoriaProductoDAO categoriaProductoDAO;
    
    public CategoriaProductoBOImpl(){
        categoriaProductoDAO = new CategoriaProductoDAOImpl();
    }
    
    @Override
    public List<CategoriaProducto> listar() {
        return this.categoriaProductoDAO.leerTodos();
    }

    @Override
    public void insertar(CategoriaProducto modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Categoria Producto no puede ser nula");
        } 
        
        this.categoriaProductoDAO.crear(modelo);
    }

    @Override
    public void actualizar(CategoriaProducto modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Categoria Producto no puede ser nula");
        }         
        
        if(modelo.getId()<=0){
            throw new IllegalArgumentException("Categoria Producto con ID inválido");       
        }
        
        CategoriaProducto categoria = this.categoriaProductoDAO.leer(modelo.getId());
        
        if(categoria == null){
            throw new RuntimeException("No se encontro a la Categoria Producto con ID: "+ modelo.getId());
        }
        
        this.categoriaProductoDAO.actualizar(modelo);
    }

    @Override
    public CategoriaProducto obtener(int id) {
        if(id<=0){
            throw new IllegalArgumentException("Categoria Producto con ID inválido");       
        }
        
        CategoriaProducto categoria = this.categoriaProductoDAO.leer(id);
        
        if(categoria == null){
            throw new RuntimeException("No se encontro a la Categoria Producto con ID: "+ id);
        }   
        
        return categoria;
    }

    @Override
    public void eliminar(int id) {
        if(id<=0){
            throw new IllegalArgumentException("Categoria Producto con ID inválido");       
        }
        
        CategoriaProducto categoria = this.categoriaProductoDAO.leer(id);
        
        if(categoria == null){
            throw new RuntimeException("No se encontro a la Categoria Producto con ID: "+ id);
        }
        
        this.categoriaProductoDAO.eliminar(id);
    }

    @Override
    public List<String> obtenerMarcasPorCategoria(Integer idCategoria) {
        if(idCategoria<=0){
            throw new IllegalArgumentException("Categoria Producto con ID inválido");       
        }
        
        CategoriaProducto categoria = this.categoriaProductoDAO.leer(idCategoria);
        
        if(categoria == null){
            throw new RuntimeException("No se encontro a la Categoria Producto con ID: "+ idCategoria);
        }
        
        return this.categoriaProductoDAO.obtenerMarcasPorCategoria(idCategoria);
    }
    
    
    @Override
    public CategoriaProducto obtenerCategoriaPorNombre(String nombreCategoria) {
        return this.categoriaProductoDAO.obtenerCategoriaPorNombre(nombreCategoria);
    }
}
