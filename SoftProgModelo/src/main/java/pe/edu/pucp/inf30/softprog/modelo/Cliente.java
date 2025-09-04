/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo;

/**
 *
 * @author Cristhian Horacio
 */
public class Cliente extends Persona {

    private boolean esRegistrado;
    private double monto;
    private String direccion;
    
    public Cliente(){
        
    }
    
    public Cliente(Integer id, String nombre, String primerApellido, String segundoApellido, 
            Integer numeroTelefono, String correo, String contrasena, TipoDocumento documento,
            boolean esRegistrado, double monto, String direccion){
        super(id, nombre, primerApellido, segundoApellido, numeroTelefono, correo, contrasena, documento);
        this.esRegistrado = esRegistrado;
        this.monto = monto;
        this.direccion = direccion;
    }

    public boolean isEsRegistrado() {
        return esRegistrado;
    }

    public void setEsRegistrado(boolean esRegistrado) {
        this.esRegistrado = esRegistrado;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    public String getDireccion(){
        return direccion;
    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
}
