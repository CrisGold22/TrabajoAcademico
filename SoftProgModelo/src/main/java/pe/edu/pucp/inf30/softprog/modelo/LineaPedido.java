/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaPedido {
    private long id;
    private Integer cantidad;
    private double precioUnitarioAplicado;
    private Integer descuento;
    private Integer totalLinea;
    
    public LineaPedido(){
        
    }

    public LineaPedido(long id, Integer cantidad, double precioUnitarioAplicado, Integer descuento, Integer totalLinea) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitarioAplicado = precioUnitarioAplicado;
        this.descuento = descuento;
        this.totalLinea = totalLinea;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitarioAplicado() {
        return precioUnitarioAplicado;
    }

    public void setPrecioUnitarioAplicado(double precioUnitarioAplicado) {
        this.precioUnitarioAplicado = precioUnitarioAplicado;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Integer getTotalLinea() {
        return totalLinea;
    }

    public void setTotalLinea(Integer totalLinea) {
        this.totalLinea = totalLinea;
    }
    
    
}
