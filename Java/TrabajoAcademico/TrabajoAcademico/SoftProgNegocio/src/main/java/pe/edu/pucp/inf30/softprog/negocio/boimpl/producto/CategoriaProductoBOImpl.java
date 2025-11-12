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
        this.categoriaProductoDAO.crear(modelo);
    }

    @Override
    public void actualizar(CategoriaProducto modelo) {
        this.categoriaProductoDAO.actualizar(modelo);
    }

    @Override
    public CategoriaProducto obtener(int id) {
        return this.categoriaProductoDAO.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.categoriaProductoDAO.eliminar(id);
    }
    
}
