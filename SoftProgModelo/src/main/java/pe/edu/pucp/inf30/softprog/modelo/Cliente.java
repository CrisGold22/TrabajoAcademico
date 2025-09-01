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

    private char activo;
    private boolean esRegistrado;
    private double monto;
    
    public Cliente(){
        
    }
    
    public Cliente(Integer id, String nombre, String primerApellido, String segundoApellido, 
            Integer numeroTelefono, String correo, String contrasena, TipoDocumento documento,
            char activo, boolean esRegistrado, double monto){
        super(id, nombre, primerApellido, segundoApellido, numeroTelefono, correo, contrasena, documento);
        this.activo = activo;
        this.esRegistrado = esRegistrado;
        this.monto = monto;
    }
    
    
    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
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
    
}
