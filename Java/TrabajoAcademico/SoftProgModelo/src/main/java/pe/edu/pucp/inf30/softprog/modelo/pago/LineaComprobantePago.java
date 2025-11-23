/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.pago;

import pe.edu.pucp.inf30.softprog.modelo.base.Registro;

/**
 *
 * @author Cristhian Horacio
 */
public class LineaComprobantePago extends Registro{
    private double montoPagado;
    private int codigo;
    private int cantidad;
    private double subTotal;
    private double montoImpuesto;
    private ComprobantePago comprobantePago;
    
    public LineaComprobantePago(){
        
    }

    public LineaComprobantePago(double montoPagado, int codigo, int cantidad, double subTotal, double montoImpuesto, ComprobantePago comprobantePago) {
        this.montoPagado = montoPagado;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.montoImpuesto = montoImpuesto;
        this.comprobantePago = comprobantePago;
    }

    public LineaComprobantePago(double montoPagado, int codigo, int cantidad, double subTotal, double montoImpuesto, ComprobantePago comprobantePago, int id, boolean activo) {
        super(id, activo);
        this.montoPagado = montoPagado;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.montoImpuesto = montoImpuesto;
        this.comprobantePago = comprobantePago;
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

    public ComprobantePago getComprobantePago() {
        return comprobantePago;
    }

    public void setComprobantePago(ComprobantePago comprobantePago) {
        this.comprobantePago = comprobantePago;
    }

    public double getMontoImpuesto() {
        return montoImpuesto;
    }

    public void setMontoImpuesto(double montoImpuesto) {
        this.montoImpuesto = montoImpuesto;
    }
    
    
}
