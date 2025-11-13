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
    private Cliente cliente;
    private AdministradorSistema administrador;
    
    public CuentaUsuario(){
        
    }

    public CuentaUsuario(String username, String password, Cliente cliente, AdministradorSistema administrador) {
        this.username = username;
        this.password = password;
        this.cliente = cliente;
        this.administrador = administrador;
    }

    public CuentaUsuario(String username, String password, Cliente cliente, AdministradorSistema administrador, int id, boolean activo) {
        super(id, activo);
        this.username = username;
        this.password = password;
        this.cliente = cliente;
        this.administrador = administrador;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public AdministradorSistema getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorSistema administrador) {
        this.administrador = administrador;
    }

    
    
}
