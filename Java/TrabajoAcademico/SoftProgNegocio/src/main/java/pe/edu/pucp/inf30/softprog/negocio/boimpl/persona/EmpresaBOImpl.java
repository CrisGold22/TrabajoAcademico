/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.persona;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.persona.ClienteDAO;
import pe.edu.pucp.inf30.softprog.dao.persona.EmpresaDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.ClienteDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.EmpresaDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.Cliente;
import pe.edu.pucp.inf30.softprog.modelo.persona.Empresa;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.EmpresaBO;

/**
 *
 * @author PC
 */
public class EmpresaBOImpl implements EmpresaBO{
    private final EmpresaDAO empresaDAO;
    private final ClienteDAO clienteDAO;
    
    public EmpresaBOImpl(){
        this.empresaDAO = new EmpresaDAOImpl();
        this.clienteDAO = new ClienteDAOImpl();
    }
    
    @Override
    public List<Empresa> listar() {
        return this.empresaDAO.leerTodos();
    }

    @Override
    public void insertar(Empresa modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Empresa no puede ser nula");
        }    
        
        this.empresaDAO.crear(modelo);
    }

    @Override
    public void actualizar(Empresa modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Empresa no puede ser nula");
        }    

        if(modelo.getId()<=0){
            throw new IllegalArgumentException("Empresa con ID inválido");       
        }
        
        if(modelo.getCliente() == null){
            throw new IllegalArgumentException("El Cliente asociado no puede ser nulo"); 
        }
        
        this.empresaDAO.actualizar(modelo);
    }

    @Override
    public Empresa obtener(int id) {
        if(id <=0){
            throw new IllegalArgumentException("Empresa con ID inválido");       
        }       
        
        Empresa empresa = this.empresaDAO.leer(id);
        
        if(empresa == null){
            throw new RuntimeException("No se encontro a la Empresa con ID: "+ id);
        }
        
        return empresa;
    }

    @Override
    public void eliminar(int id) {
        if(id <=0){
            throw new IllegalArgumentException("Empresa con ID inválido");       
        }       
        
        Empresa empresa = this.empresaDAO.leer(id);
        
        if(empresa == null){
            throw new RuntimeException("No se encontro a la Empresa con ID: "+ id);
        }
        
        this.empresaDAO.eliminar(id);
    }

    @Override
    public List<Empresa> listarEmpresasActivos() {
        return this.empresaDAO.listarEmpresasActivos();
    }

    @Override
    public List<Empresa> listarEmpresasNoActivos() {
        return this.empresaDAO.listarEmpresasNoActivos();
    }

    @Override
    public List<Empresa> listarEmpresasPorCliente(int id) {
        if(id <=0){
            throw new IllegalArgumentException("Empresa con ID inválido");       
        }     
        
        Cliente modelo = this.clienteDAO.leer(id);
        
        if(modelo == null){
            throw new RuntimeException("No se encontro al Cliente con ID : "+ id);
        }
        
        return this.empresaDAO.listarEmpresasPorCliente(id);
    }

    
    
    @Override
    public List<Empresa> listarEmpresasPorClienteActivas(int id) {
        if(id <=0){
            throw new IllegalArgumentException("Empresa con ID inválido");       
        }     
        
        Cliente modelo = this.clienteDAO.leer(id);
        
        if(modelo == null){
            throw new RuntimeException("No se encontro al Cliente con ID : "+ id);
        }
        
        return this.empresaDAO.listarEmpresasPorClienteActivas(id);
    }

    @Override
    public List<Empresa> listarEmpresasPorClienteNoActivas(int id) {
        if(id <=0){
            throw new IllegalArgumentException("Empresa con ID inválido");       
        }     
        
        Cliente modelo = this.clienteDAO.leer(id);
        
        if(modelo == null){
            throw new RuntimeException("No se encontro al Cliente con ID : "+ id);
        }        
        
        return this.empresaDAO.listarEmpresasPorClienteNoActivas(id);
    }

    @Override
    public boolean validarEmpresa(int id) {
        return this.empresaDAO.validarEmpresa(id);
    }



}
