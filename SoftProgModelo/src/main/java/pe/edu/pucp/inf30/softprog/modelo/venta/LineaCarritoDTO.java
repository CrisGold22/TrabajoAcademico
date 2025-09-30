/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.producto.ProductoDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaCarritoDTO extends RegistroDTO{
    private ProductoDTO producto;
    private int cantidad;
    private double precioVolumen;
    private double subTotal;
    
    public LineaCarritoDTO(){
        
    }

    public LineaCarritoDTO(ProductoDTO producto, int cantidad, double precioVolumen, double subTotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVolumen = precioVolumen;
        this.subTotal = subTotal;
    }

    public LineaCarritoDTO(ProductoDTO producto, int cantidad, double precioVolumen, double subTotal, int id, boolean activo) {
        super(id, activo);
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVolumen = precioVolumen;
        this.subTotal = subTotal;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
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
    
    
}
