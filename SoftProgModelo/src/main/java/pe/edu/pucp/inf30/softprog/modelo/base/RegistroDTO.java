/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.base;

/**
 *
 * @author Cristhian Horacio
 */
public class RegistroDTO { 
    private int id;
    private boolean activo;
    
    public RegistroDTO(){
        
    }

    public RegistroDTO(int id, boolean activo) {
        this.id = id;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public int getActivo(){
        return this.activo ? 1 : 0;
    }
    
    public void setActivoInt(int activo){
        if(activo == 1){
            this.activo = true;
        }
        else{
            this.activo = false;
        }
    }
}
