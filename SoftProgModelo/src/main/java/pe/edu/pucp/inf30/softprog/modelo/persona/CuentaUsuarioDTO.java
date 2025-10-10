/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class CuentaUsuarioDTO extends RegistroDTO{
    private String username;
    private String password;
    private int idCliente;
    private int idAdministrador;
    
    public CuentaUsuarioDTO(){
        
    }

    public CuentaUsuarioDTO(String username, String password, int idCliente, int idAdministrador) {
        this.username = username;
        this.password = password;
        this.idCliente = idCliente;
        this.idAdministrador = idAdministrador;
    }

    public CuentaUsuarioDTO(String username, String password, int idCliente, int idAdministrador, int id, boolean activo) {
        super(id, activo);
        this.username = username;
        this.password = password;
        this.idCliente = idCliente;
        this.idAdministrador = idAdministrador;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    
}
