/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaOrdenCompra extends Registro{
    private int cantidad;
    private double precio;
    private double subTotal;
    private Producto producto;
    private OrdenCompra ordenCompra;
    
    public LineaOrdenCompra(){
        
    }

    public LineaOrdenCompra(int cantidad, double precio, double subTotal, Producto producto, OrdenCompra ordenCompra) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
        this.producto = producto;
        this.ordenCompra = ordenCompra;
    }

    public LineaOrdenCompra(int cantidad, double precio, double subTotal, Producto producto, OrdenCompra ordenCompra, int id, boolean activo) {
        super(id, activo);
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
        this.producto = producto;
        this.ordenCompra = ordenCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    
    
    
    
}
