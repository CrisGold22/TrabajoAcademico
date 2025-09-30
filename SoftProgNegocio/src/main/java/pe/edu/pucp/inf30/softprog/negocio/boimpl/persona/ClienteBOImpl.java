/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.persona;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.persona.ClienteDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.ClienteDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.ClienteDTO;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.ClienteBO;

/**
 *
 * @author Cristhian Horacio
 */
public class ClienteBOImpl implements ClienteBO{
    private final ClienteDAO clienteDAO;
    
    public ClienteBOImpl(){
        this.clienteDAO = new ClienteDAOImpl();
    }
    
    @Override
    public List<ClienteDTO> listar() {
        return this.clienteDAO.leerTodos();
    }

    @Override
    public void insertar(ClienteDTO modelo) {
        this.clienteDAO.crear(modelo);
    }

    @Override
    public void actualizar(ClienteDTO modelo) {
        this.clienteDAO.actualizar(modelo);
    }

    @Override
    public ClienteDTO obtener(int id) {
        return this.clienteDAO.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.clienteDAO.eliminar(id);
    }

    @Override
    public ClienteDTO buscarPorDNI(String dni) {
        return this.clienteDAO.buscarPorDNI(dni);
    }
    
}
