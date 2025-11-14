/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import java.time.LocalDate;
import java.util.Date;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito;

/**
 *
 * @author Cristhian Horacio
 */
public class DetalleEnvio extends Registro {
    private String descripcion;
    private LocalDate fechaEntrega;
    private LocalDate horarioEntrega;
    private String direccion;
    private Distrito distrito;
    private OrdenCompra ordenCompra;

    public DetalleEnvio() {

    }

    public DetalleEnvio(String descripcion, LocalDate fechaEntrega, LocalDate horarioEntrega, String direccion, Distrito distrito, OrdenCompra ordenCompra) {
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.horarioEntrega = horarioEntrega;
        this.direccion = direccion;
        this.distrito = distrito;
        this.ordenCompra = ordenCompra;
    }

    public DetalleEnvio(String descripcion, LocalDate fechaEntrega, LocalDate horarioEntrega, String direccion, Distrito distrito, OrdenCompra ordenCompra, int id, boolean activo) {
        super(id, activo);
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.horarioEntrega = horarioEntrega;
        this.direccion = direccion;
        this.distrito = distrito;
        this.ordenCompra = ordenCompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getDistritoString() {
        String cadena = "";
        if (this.distrito == null) {
                    return null;
                }
        switch (this.distrito) {
            case PUEBLO_LIBRE ->
                cadena = "PUEBLO_LIBRE";
            case SAN_MIGUEL ->
                cadena = "SAN_MIGUEL";
            case SAN_BORJA ->
                cadena = "SAN_BORJA";
            case SAN_LUIS ->
                cadena = "SAN_LUIS";
            case LINCE ->
                cadena = "LINCE";
            case CALLAO ->
                cadena = "CALLAO";
            case LA_VICTORIA ->
                cadena = "LA_VICTORIA";
        }

        return cadena;
    }

    public void setDistritoString(String distrito) {
        switch (distrito) {
            case "PUEBLO_LIBRE" ->
                this.distrito = Distrito.PUEBLO_LIBRE;
            case "SAN_MIGUEL" ->
                this.distrito = Distrito.SAN_MIGUEL;
            case "SAN_BORJA" ->
                this.distrito = Distrito.SAN_BORJA;
            case "SAN_LUIS" ->
                this.distrito = Distrito.SAN_LUIS;
            case "LINCE" ->
                this.distrito = Distrito.LINCE;
            case "CALLAO" ->
                this.distrito = Distrito.CALLAO;
            case "LA_VICTORIA" ->
                this.distrito = Distrito.LA_VICTORIA;
        }
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDate getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(LocalDate horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }
    
    
}
