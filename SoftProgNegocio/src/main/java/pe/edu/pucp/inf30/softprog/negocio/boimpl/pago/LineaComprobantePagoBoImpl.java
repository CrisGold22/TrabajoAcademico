/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.pago;

import java.util.List;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.LineaComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.pago.LineaComprobantePago;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.LineaComprobantePagoBO;
import pe.edu.pucp.inf30.softprog.dao.pago.LineaComprobantePagoDAO;
/**
 *
 * @author Cristhian Horacio
 */
public class LineaComprobantePagoBOImpl implements LineaComprobantePagoBO{
    private final LineaComprobantePagoDAO lineaComprobantePagoDAO;
    
    public LineaComprobantePagoBOImpl(){
        lineaComprobantePagoDAO = new LineaComprobantePagoDAOImpl();
    }

    @Override
    public List<LineaComprobantePago> listar() {
        return this.lineaComprobantePagoDAO.leerTodos();
    }

    @Override
    public void insertar(LineaComprobantePago modelo) {
        this.lineaComprobantePagoDAO.crear(modelo);
    }

    @Override
    public void actualizar(LineaComprobantePago modelo) {
        this.lineaComprobantePagoDAO.actualizar(modelo);
    }

    @Override
    public LineaComprobantePago obtener(int id) {
        return this.lineaComprobantePagoDAO.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.lineaComprobantePagoDAO.eliminar(id);
    }
    
}
