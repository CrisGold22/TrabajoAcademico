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
    private int id;
    private Producto producto;
    private Integer cantidad;
    private double precioUnitarioAplicado;
    private double totalLinea;
    
    public LineaPedido(){
        
    }

    public LineaPedido(Producto producto, int id, Integer cantidad, double precioUnitarioAplicado, double totalLinea) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitarioAplicado = precioUnitarioAplicado;
        this.totalLinea = totalLinea;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getTotalLinea() {
        return totalLinea;
    }

    public void setTotalLinea(double totalLinea) {
        this.totalLinea = totalLinea;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
