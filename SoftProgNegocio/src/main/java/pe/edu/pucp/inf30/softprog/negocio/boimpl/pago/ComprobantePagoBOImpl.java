/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.pago;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.pago.ComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.ComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.pago.ComprobantePagoDTO;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.ComprobantePagoBO;

/**
 *
 * @author Cristhian Horacio
 */
public class ComprobantePagoBOImpl implements ComprobantePagoBO{

    private final ComprobantePagoDAO comprobantePagoDAO;
    
    public ComprobantePagoBOImpl(){
        comprobantePagoDAO = new ComprobantePagoDAOImpl();
    }
    
    @Override
    public List<ComprobantePagoDTO> listar() {
        return this.comprobantePagoDAO.leerTodos();
    }

    @Override
    public void insertar(ComprobantePagoDTO modelo) {
        this.comprobantePagoDAO.crear(modelo);
    }

    @Override
    public void actualizar(ComprobantePagoDTO modelo) {
        this.comprobantePagoDAO.actualizar(modelo);
    }

    @Override
    public ComprobantePagoDTO obtener(int id) {
        return this.comprobantePagoDAO.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.comprobantePagoDAO.eliminar(id);
    }
    
}
