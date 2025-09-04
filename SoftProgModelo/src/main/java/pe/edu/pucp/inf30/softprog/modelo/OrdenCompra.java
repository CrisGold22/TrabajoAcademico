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
public class OrdenCompra {
    private long idOrdenCompra;
    private Date fechaCreacion;
    private Date fechaEntraga;
    private EstadoOrdenCompra estado;
    private double descuentoTotal;
    private double impuestos;
    private double total;
//    private EstadoOrdenCompra estado;
    
    public OrdenCompra(){
        
    }

    public OrdenCompra(long idPedido, Date fechaCreacion, Date fechaEntraga, 
                double descuentoTotal, double impuestos, double total, EstadoOrdenCompra estado) {
        this.idOrdenCompra = idPedido;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntraga = fechaEntraga;
        this.estado = estado;
        this.descuentoTotal = descuentoTotal;
        this.impuestos = impuestos;
        this.total = total;
//        this.estado = estado;
    }

    public long getIdPedido() {
        return idOrdenCompra;
    }

    public void setIdPedido(long idPedido) {
        this.idOrdenCompra = idPedido;
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

    public EstadoOrdenCompra getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrdenCompra estado) {
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

    public long getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(long idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }
    
    
}
