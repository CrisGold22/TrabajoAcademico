/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.persona;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.persona.ClienteDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.ClienteDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.Cliente;
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
    public List<Cliente> listar() {
        return this.clienteDAO.leerTodos();
    }

    @Override
    public void insertar(Cliente modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("El Cliente  no puede ser nulo");
        }         
        
        this.clienteDAO.crear(modelo);
    }

    @Override
    public void actualizar(Cliente modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("El Cliente  no puede ser nulo");
        }  

        if(modelo.getId() <= 0){
            throw new IllegalArgumentException("Cliente con ID inválido");            
        }
        
        Cliente cliente = this.clienteDAO.leer(modelo.getId());
        
        if(cliente == null){
            throw new RuntimeException("No se encontro al Cliente con ID: "+ modelo.getId());
        }
        
        this.clienteDAO.actualizar(modelo);
    }

    @Override
    public Cliente obtener(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Cliente con ID inválido");            
        }
        
        Cliente cliente = this.clienteDAO.leer(id);
        
        if(cliente == null){
            throw new RuntimeException("No se encontro al Cliente con ID: "+ id);
        }        
        
        return cliente;
    }

    @Override
    public void eliminar(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("Cliente con ID inválido");            
        }
        
        Cliente cliente = this.clienteDAO.leer(id);
        
        if(cliente == null){
            throw new RuntimeException("No se encontro al Cliente con ID: "+ id);
        }  
        
        this.clienteDAO.eliminar(id);
    }

    @Override
    public Cliente buscarPorDNI(String dni) {
        if(dni.isBlank()){
            throw new IllegalArgumentException("El DNI no puede ser nulo");
        }
        
        if(dni.isEmpty()){
            throw new IllegalArgumentException("El DNI no puede estar vacio");
        }
        
        Cliente cliente = this.clienteDAO.buscarPorDNI(dni);
        
        if(cliente == null){
            throw new RuntimeException("No se encontro al Cliente con DNI: "+ dni);
        }
        
        return cliente;
    }

    @Override
    public Cliente buscarPorDni(String dni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
