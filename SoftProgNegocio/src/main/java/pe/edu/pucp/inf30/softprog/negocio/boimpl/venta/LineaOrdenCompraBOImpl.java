/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.venta;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaOrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.LineaOrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompraDTO;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.LineaOrdenCompraBO;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaOrdenCompraBOImpl implements LineaOrdenCompraBO{
    private final LineaOrdenCompraDAO lineaOrdenCompraDAO;
    
    public LineaOrdenCompraBOImpl(){
        lineaOrdenCompraDAO = new LineaOrdenCompraDAOImpl();
    }
    
    @Override
    public List<LineaOrdenCompraDTO> listar() {
        return this.lineaOrdenCompraDAO.leerTodos();
    }

    @Override
    public void insertar(LineaOrdenCompraDTO modelo) {
        this.lineaOrdenCompraDAO.crear(modelo);
    }

    @Override
    public void actualizar(LineaOrdenCompraDTO modelo) {
        this.lineaOrdenCompraDAO.actualizar(modelo);
    }

    @Override
    public LineaOrdenCompraDTO obtener(int id) {
        return this.lineaOrdenCompraDAO.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.lineaOrdenCompraDAO.eliminar(id);
    }
    
}
