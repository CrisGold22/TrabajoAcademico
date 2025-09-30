/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaOrdenCompraDTO extends RegistroDTO{
    private double montoPagado;
    private int codigo;
    private int cantidad;
    private double subTotal;
    
    public LineaOrdenCompraDTO(){
        
    }

    public LineaOrdenCompraDTO(double montoPagado, int codigo, int cantidad, double subTotal) {
        this.montoPagado = montoPagado;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public LineaOrdenCompraDTO(double montoPagado, int codigo, int cantidad, double subTotal, int id, boolean activo) {
        super(id, activo);
        this.montoPagado = montoPagado;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
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
    
    
}
