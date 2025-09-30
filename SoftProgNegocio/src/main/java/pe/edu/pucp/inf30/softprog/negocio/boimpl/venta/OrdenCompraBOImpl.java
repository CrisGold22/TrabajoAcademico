/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.venta;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.OrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.OrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompraDTO;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.OrdenCompraBO;

/**
 *
 * @author Cristhian Horacio
 */
public class OrdenCompraBOImpl implements OrdenCompraBO{
    private final OrdenCompraDAO ordenCompraDAO;
    
    public OrdenCompraBOImpl(){
        ordenCompraDAO = new OrdenCompraDAOImpl();
    }
    
    @Override
    public List<OrdenCompraDTO> listar() {
        return this.ordenCompraDAO.leerTodos();
    }

    @Override
    public void insertar(OrdenCompraDTO modelo) {
        this.ordenCompraDAO.crear(modelo);
    }

    @Override
    public void actualizar(OrdenCompraDTO modelo) {
        this.ordenCompraDAO.actualizar(modelo);
    }

    @Override
    public OrdenCompraDTO obtener(int id) {
        return this.ordenCompraDAO.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.ordenCompraDAO.eliminar(id);
    }
    
}
