/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.producto;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.producto.DescuentoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.DescuentoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.producto.Descuento;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.DescuentoBO;

/**
 *
 * @author Cristhian Horacio
 */
public class DescuentoBOImpl implements DescuentoBO{    
    private final DescuentoDAO descuentoDAO;

    public DescuentoBOImpl(){
        descuentoDAO = new DescuentoDAOImpl();
    }
    
    @Override
    public List<Descuento> listar() {
        return this.descuentoDAO.leerTodos();
    }

    @Override
    public void insertar(Descuento modelo) {
        this.descuentoDAO.crear(modelo);
    }

    @Override
    public void actualizar(Descuento modelo) {
        this.descuentoDAO.actualizar(modelo);
    }

    @Override
    public Descuento obtener(int id) {
        return this.descuentoDAO.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.descuentoDAO.eliminar(id);
    }
    
}
