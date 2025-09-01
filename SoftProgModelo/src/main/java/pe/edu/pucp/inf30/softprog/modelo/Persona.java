/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo;

/**
 *
 * @author Cristhian Horacio
 */
public class Persona {
    private int id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private int numeroTelefono;
    private String correo;
    private String contrasena;
    private TipoDocumento numDocumento;
    
    public Persona(){
        
    }
    
    public Persona(Integer id, String nombre, String primerApellido, String segundoApellido, Integer numeroTelefono, String correo, String contrasena, TipoDocumento documento){
        this.id = id;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.numeroTelefono = numeroTelefono;
        this.correo = correo;
        this.contrasena = contrasena;
        this.numDocumento = documento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }
    
    public void setNumeroTelefono(int numeroTelofono) {
        this.numeroTelefono = numeroTelofono;
    }

    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public TipoDocumento getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(TipoDocumento numDocumento) {
        this.numDocumento = numDocumento;
    }
    
}
