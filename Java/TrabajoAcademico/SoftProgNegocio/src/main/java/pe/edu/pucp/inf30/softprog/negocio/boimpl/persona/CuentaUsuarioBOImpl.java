/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.persona;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.persona.CuentaUsuarioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.CuentaUsuarioDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.CuentaUsuarioBO;

/**
 *
 * @author Cristhian Horacio
 */
public class CuentaUsuarioBOImpl implements CuentaUsuarioBO{
    private final CuentaUsuarioDAO cuenta;
    
    public CuentaUsuarioBOImpl(){
        cuenta = new CuentaUsuarioDAOImpl();
    }
    
    @Override
    public List<CuentaUsuario> listar() {
        return this.cuenta.leerTodos();
    }

    @Override
    public void insertar(CuentaUsuario modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Cuenta Usuario  no puede ser nula");
        }     
        
        this.cuenta.crear(modelo);
    }

    @Override
    public void actualizar(CuentaUsuario modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Cuenta Usuario  no puede ser nula");
        } 
        
        if(modelo.getId() <= 0){
             throw new IllegalArgumentException("Cuenta Usuario con ID inválido");     
        }

        CuentaUsuario cuentaModelo = this.cuenta.leer(modelo.getId());
        
        if(cuentaModelo == null){
            throw new RuntimeException("No se encontro a la Cuenta Usuario con id : "+modelo.getId());
        }
        
        this.cuenta.actualizar(modelo);
    }

    @Override
    public CuentaUsuario obtener(int id) {
        if(id <= 0){
             throw new IllegalArgumentException("Cuenta Usuario con ID inválido");     
        }
        
        CuentaUsuario cuentaModelo = this.cuenta.leer(id);
        
        if(cuentaModelo == null){
            throw new RuntimeException("No se encontro a la Cuenta Usuario con id : "+id);
        }
        
        return cuentaModelo;
    }

    @Override
    public void eliminar(int id) {
        if(id <= 0){
             throw new IllegalArgumentException("Cuenta Usuario con ID inválido");     
        }
        
        CuentaUsuario cuentaModelo = this.cuenta.leer(id);
        
        if(cuentaModelo == null){
            throw new RuntimeException("No se encontro a la Cuenta Usuario con id : "+id);
        }
                
        this.cuenta.eliminar(id);
    }
    @Override
    public boolean login(String email, String password){
        if(email.isEmpty() || email.isBlank() || password.isBlank() || password.isEmpty()){
            throw new IllegalArgumentException("Usuario o password inválido");
        }
        
        return this.cuenta.login(email, password);
    }
}
