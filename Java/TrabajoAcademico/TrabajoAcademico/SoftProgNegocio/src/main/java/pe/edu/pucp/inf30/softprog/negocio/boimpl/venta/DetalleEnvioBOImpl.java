/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.venta;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.DetalleEnvioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.DetalleEnvioDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.DetalleEnvio;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.DetalleEnvioBO;

/**
 *
 * @author Cristhian Horacio
 */
public class DetalleEnvioBOImpl implements DetalleEnvioBO{
    private final DetalleEnvioDAO detalleEnvioDAO;
    
    public DetalleEnvioBOImpl(){
        detalleEnvioDAO = new DetalleEnvioDAOImpl();
    }
    
    @Override
    public List<DetalleEnvio> listar() {
        return this.detalleEnvioDAO.leerTodos();
    }

    @Override
    public void insertar(DetalleEnvio modelo) {
        this.detalleEnvioDAO.crear(modelo);
    }

    @Override
    public void actualizar(DetalleEnvio modelo) {
        this.detalleEnvioDAO.actualizar(modelo);
    }

    @Override
    public DetalleEnvio obtener(int id) {
        return this.detalleEnvioDAO.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.detalleEnvioDAO.eliminar(id);
    }
    
}
