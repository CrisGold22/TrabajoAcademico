/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.pago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.pago.utils.MetodoPago;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public class ComprobantePago extends Registro{
    private List<LineaComprobantePago> lineasComprobantes;
    private Date fechaEmision;
    private String ruc;
    private double totalSinImpuestos;
    private double impuestos;
    private double totalFinal;
    private MetodoPago metodoPago;
    private OrdenCompra ordenCompra;
    
    public ComprobantePago(){
        lineasComprobantes = new ArrayList<>();
    }

    public ComprobantePago(List<LineaComprobantePago> lineasComprobantes, Date fechaEmision, String ruc, double totalSinImpuestos, double impuestos, double totalFinal, MetodoPago metodoPago, OrdenCompra ordenCompra) {
        this.lineasComprobantes = lineasComprobantes;
        this.fechaEmision = fechaEmision;
        this.ruc = ruc;
        this.totalSinImpuestos = totalSinImpuestos;
        this.impuestos = impuestos;
        this.totalFinal = totalFinal;
        this.metodoPago = metodoPago;
        this.ordenCompra = ordenCompra;
    }

    public ComprobantePago(List<LineaComprobantePago> lineasComprobantes, Date fechaEmision, String ruc, double totalSinImpuestos, double impuestos, double totalFinal, MetodoPago metodoPago, OrdenCompra ordenCompra, int id, boolean activo) {
        super(id, activo);
        this.lineasComprobantes = lineasComprobantes;
        this.fechaEmision = fechaEmision;
        this.ruc = ruc;
        this.totalSinImpuestos = totalSinImpuestos;
        this.impuestos = impuestos;
        this.totalFinal = totalFinal;
        this.metodoPago = metodoPago;
        this.ordenCompra = ordenCompra;
    }
    
    

    public List<LineaComprobantePago> getLineasComprobantes() {
        return lineasComprobantes;
    }

    public void setLineasComprobantes(List<LineaComprobantePago> lineasComprobantes) {
        this.lineasComprobantes = lineasComprobantes;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getruc() {
        return ruc;
    }

    public void setruc(String ruc) {
        this.ruc = ruc;
    }

    public double getTotalSinImpuestos() {
        return totalSinImpuestos;
    }

    public void setTotalSinImpuestos(double totalSinImpuestos) {
        this.totalSinImpuestos = totalSinImpuestos;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    public String getMetodoString() {
            if (this.metodoPago == null) return null;

            String cadena = "";
            switch(this.metodoPago){
                case CONTRA_ENTREGA -> cadena = "CONTRA_ENTREGA";
                case VIRTUAL -> cadena = "VIRTUAL";
                case CRIPTO -> cadena = "CRIPTO";
            }
            return cadena;
        }

        public void setMetodoString(String metodo) {
            if (metodo == null) {
                this.metodoPago = null;
                return;
            }

            switch (metodo) {
                case "CONTRA_ENTREGA" -> this.metodoPago = MetodoPago.CONTRA_ENTREGA;
                case "VIRTUAL" -> this.metodoPago = MetodoPago.VIRTUAL;
                case "CRIPTO" -> this.metodoPago = MetodoPago.CRIPTO;
                default -> this.metodoPago = null;
            }
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }
    
}
