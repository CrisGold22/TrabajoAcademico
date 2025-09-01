/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo;

/**
 *
 * @author Cristhian Horacio
 */
public class Administrador extends Persona {

    private char activo;

    public Administrador() {

    }

    public Administrador(Integer id, String nombre, String primerApellido, String segundoApellido,
            Integer numeroTelefono, String correo, String contrasena, TipoDocumento documento,
            char activo, boolean esRegistrado, double monto) {
        super(id, nombre, primerApellido, segundoApellido, numeroTelefono, correo, contrasena, documento);
        this.activo = activo;
    }
    
    public void setActivo(char activo){
       this.activo = activo;
    }
    
    public char getActivo(){
        return activo;
    }
}
