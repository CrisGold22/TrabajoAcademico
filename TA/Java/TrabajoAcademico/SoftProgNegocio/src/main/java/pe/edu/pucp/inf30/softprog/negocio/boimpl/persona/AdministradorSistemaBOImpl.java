/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.persona;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.persona.AdministradorSistemaDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.AdministradorSistemaDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.AdministradorSistema;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.AdministradorSistemaBO;

/**
 *
 * @author Cristhian Horacio
 */
public class AdministradorSistemaBOImpl implements AdministradorSistemaBO{
    private final AdministradorSistemaDAO administrador;
    
    public AdministradorSistemaBOImpl(){
        administrador = new AdministradorSistemaDAOImpl();
    }
    
    @Override
    public List<AdministradorSistema> listar() {
        return this.administrador.leerTodos();
    }

    @Override
    public void insertar(AdministradorSistema modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("El Administrador Sistema no puede ser nulo");
        } 
        
        
        this.administrador.crear(modelo);
    }

    @Override
    public void actualizar(AdministradorSistema modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("El AdministradorSistema no puede ser nulo");
        }         

        if(modelo.getId() <= 0){
            throw new IllegalArgumentException("Administrador Sistema con ID inválido");
        }        
        
        AdministradorSistema admin = this.administrador.leer(modelo.getId());
        
        if(admin == null){
            throw new RuntimeException("No se encontro al Administrador Sistema con ID: "+ modelo.getId());
        }
        
        this.administrador.actualizar(modelo);
    }

    @Override
    public AdministradorSistema obtener(int id) {
        if( id<=0){
            throw new IllegalArgumentException("Administrador Sistema con ID inválido");
        }
        
        AdministradorSistema admin = this.administrador.leer(id);
        
        if(admin == null){
            throw new RuntimeException("No se encontro al Administrador Sistema con ID: "+ id);
        }
        
        return admin;
    }

    @Override
    public void eliminar(int id) {
        if( id<=0){
            throw new IllegalArgumentException("Administrador Sistema con ID inválido");
        }
        
        AdministradorSistema admin = this.administrador.leer(id);
        
        if(admin == null){
            throw new RuntimeException("No se encontro al Administrador Sistema con ID: "+ id);
        }       
        
        this.administrador.eliminar(id);
    }
    
}
