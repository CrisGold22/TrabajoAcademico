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
public class LineaCarrito extends Registro{
    private Producto producto;
    //private CarritoCompras carritoCompras;//////
    private int cantidad;
    private double precioVolumen;
    private double subTotal;
    
    public LineaCarrito(){
        
    }

    public LineaCarrito(Producto producto, CarritoCompras carritoCompras, int cantidad, double precioVolumen, double subTotal) {
        this.producto = producto;
        //this.carritoCompras = carritoCompras;
        this.cantidad = cantidad;
        this.precioVolumen = precioVolumen;
        this.subTotal = subTotal;
    }

    public LineaCarrito(Producto producto, CarritoCompras carritoCompras, int cantidad, double precioVolumen, double subTotal, int id, boolean activo) {
        super(id, activo);
        this.producto = producto;
        //this.carritoCompras = carritoCompras;
        this.cantidad = cantidad;
        this.precioVolumen = precioVolumen;
        this.subTotal = subTotal;
    }

    

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVolumen() {
        return precioVolumen;
    }

    public void setPrecioVolumen(double precioVolumen) {
        this.precioVolumen = precioVolumen;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

//    public CarritoCompras getCarritoCompras() {
//        return carritoCompras;
//    }
//
//    public void setCarritoCompras(CarritoCompras idCarritoCompras) {
//        this.carritoCompras = idCarritoCompras;
//    }
    
    
}
