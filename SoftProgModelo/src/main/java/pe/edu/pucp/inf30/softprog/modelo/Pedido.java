/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo;

import java.util.Date;

/**
 *
 * @author Cristhian Horacio
 */
public class Pedido {
    private long idPedido;
    private Date fechaCreacion;
    private Date fechaEntraga;
    private Estado estado;
    private double descuentoTotal;
    private double impuestos;
    private double total;
    
    public Pedido(){
        
    }

    public Pedido(long idPedido, Date fechaCreacion, Date fechaEntraga, Estado estado, double descuentoTotal, double impuestos, double total) {
        this.idPedido = idPedido;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntraga = fechaEntraga;
        this.estado = estado;
        this.descuentoTotal = descuentoTotal;
        this.impuestos = impuestos;
        this.total = total;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaEntraga() {
        return fechaEntraga;
    }

    public void setFechaEntraga(Date fechaEntraga) {
        this.fechaEntraga = fechaEntraga;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(double descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
