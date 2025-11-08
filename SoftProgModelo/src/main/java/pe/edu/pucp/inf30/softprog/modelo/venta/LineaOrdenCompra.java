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
    private double montoPagado;
    private int codigo;
    private int cantidad;
    private double precioUnitario;
    private double subTotal;
    private Producto producto;
    private OrdenCompra ordenCompra;
    private CarritoCompras carritoCompras;
    
    public LineaOrdenCompra(){
        
    }

    public LineaOrdenCompra(double montoPagado, int codigo, int cantidad, double precioUnitario, double subTotal, Producto producto, OrdenCompra ordenCompra, CarritoCompras carritoCompras) {
        this.montoPagado = montoPagado;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.producto = producto;
        this.ordenCompra = ordenCompra;
        this.carritoCompras = carritoCompras;
    }

    public LineaOrdenCompra(double montoPagado, int codigo, int cantidad, double precioUnitario, double subTotal, Producto producto, OrdenCompra ordenCompra, CarritoCompras carritoCompras, int id, boolean activo) {
        super(id, activo);
        this.montoPagado = montoPagado;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.producto = producto;
        this.ordenCompra = ordenCompra;
        this.carritoCompras = carritoCompras;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
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

    public CarritoCompras getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(CarritoCompras carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    
    
    
}
