/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import pe.edu.pucp.inf30.softprog.modelo.base.Registro;

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
    private int idProducto;
    private int idOrdenCompra;
    private int idCarrito;
    
    public LineaOrdenCompra(){
        
    }

    public LineaOrdenCompra(double montoPagado, int codigo, int cantidad, double precioUnitario, double subTotal, int idProducto, int idOrdenCompra, int idCarrito) {
        this.montoPagado = montoPagado;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.idProducto = idProducto;
        this.idOrdenCompra = idOrdenCompra;
        this.idCarrito = idCarrito;
    }

    public LineaOrdenCompra(double montoPagado, int codigo, int cantidad, double precioUnitario, double subTotal, int idProducto, int idOrdenCompra, int idCarrito, int id, boolean activo) {
        super(id, activo);
        this.montoPagado = montoPagado;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
        this.idProducto = idProducto;
        this.idOrdenCompra = idOrdenCompra;
        this.idCarrito = idCarrito;
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

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(int idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }
    
    
}
