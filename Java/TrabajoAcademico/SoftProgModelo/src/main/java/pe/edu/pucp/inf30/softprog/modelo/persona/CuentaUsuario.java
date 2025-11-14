/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import pe.edu.pucp.inf30.softprog.modelo.base.Registro;

/**
 *
 * @author Cristhian Horacio
 */
public class CuentaUsuario extends Registro{
    private String username;
    private String password;

    public CuentaUsuario(){
        
    }

    public CuentaUsuario(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public CuentaUsuario(String username, String password, int id, boolean activo) {
        super(id, activo);
        this.username = username;
        this.password = password;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
  
}
