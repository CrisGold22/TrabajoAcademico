/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import java.util.Date;
import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito;

/**
 *
 * @author Cristhian Horacio
 */
public class DetalleEnvioDTO extends RegistroDTO{
    private String descripcion;
    private Date fechaEntrega;
    private Date horarioEntrega;
    private String direccion;
    private Distrito distrito;
    
    public DetalleEnvioDTO(){
        
    }

    public DetalleEnvioDTO(String descripcion, Date fechaEntrega, Date horarioEntrega, String direccion, Distrito distrito) {
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.horarioEntrega = horarioEntrega;
        this.direccion = direccion;
        this.distrito = distrito;
    }

    public DetalleEnvioDTO(String descripcion, Date fechaEntrega, Date horarioEntrega, String direccion, Distrito distrito, int id, boolean activo) {
        super(id, activo);
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.horarioEntrega = horarioEntrega;
        this.direccion = direccion;
        this.distrito = distrito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(Date horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }
    
    
}
