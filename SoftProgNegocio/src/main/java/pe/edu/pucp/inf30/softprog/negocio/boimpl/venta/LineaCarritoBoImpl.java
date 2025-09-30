/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.venta;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaCarritoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.LineaCarritoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarritoDTO;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.LineaCarritoBO;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaCarritoBOImpl implements LineaCarritoBO {
    private final LineaCarritoDAO lineaCarritoDAO;
    
    public LineaCarritoBOImpl(){
        lineaCarritoDAO = new LineaCarritoDAOImpl();
    }
    @Override
    public List<LineaCarritoDTO> listar() {
        return this.lineaCarritoDAO.leerTodos();
    }

    @Override
    public void insertar(LineaCarritoDTO modelo) {
        this.lineaCarritoDAO.crear(modelo);
    }

    @Override
    public void actualizar(LineaCarritoDTO modelo) {
        this.lineaCarritoDAO.actualizar(modelo);
    }

    @Override
    public LineaCarritoDTO obtener(int id) {
        return this.lineaCarritoDAO.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.lineaCarritoDAO.eliminar(id);
    }
    
}
