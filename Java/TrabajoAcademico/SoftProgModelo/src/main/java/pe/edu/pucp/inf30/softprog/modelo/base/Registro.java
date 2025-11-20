/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.base;

/**
 *
 * @author Cristhian Horacio
 */
public class Registro { 
    private int id;
    private boolean activo;
    
    public Registro(){
        
    }

    public Registro(int id, boolean activo) {
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

    // Métodos auxiliares para 0/1 (para la BD)
    public int getActivoInt() {      // <-- renombrado
        return this.activo ? 1 : 0;
    }
    
    public void setActivoInt(int activo) {
        this.activo = (activo == 1);
    }
}
