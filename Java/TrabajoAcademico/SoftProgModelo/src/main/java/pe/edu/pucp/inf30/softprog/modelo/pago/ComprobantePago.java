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
    private int RUC;
    private double totalSinImpuestos;
    private double impuestos;
    private double totalFinal;
    private MetodoPago metodoPago;
    private OrdenCompra ordenCompra;
    
    public ComprobantePago(){
        lineasComprobantes = new ArrayList<>();
    }

    public ComprobantePago(List<LineaComprobantePago> lineasComprobantes, Date fechaEmision, int RUC, double totalSinImpuestos, double impuestos, double totalFinal, MetodoPago metodoPago, OrdenCompra ordenCompra) {
        this.lineasComprobantes = lineasComprobantes;
        this.fechaEmision = fechaEmision;
        this.RUC = RUC;
        this.totalSinImpuestos = totalSinImpuestos;
        this.impuestos = impuestos;
        this.totalFinal = totalFinal;
        this.metodoPago = metodoPago;
        this.ordenCompra = ordenCompra;
    }

    public ComprobantePago(List<LineaComprobantePago> lineasComprobantes, Date fechaEmision, int RUC, double totalSinImpuestos, double impuestos, double totalFinal, MetodoPago metodoPago, OrdenCompra ordenCompra, int id, boolean activo) {
        super(id, activo);
        this.lineasComprobantes = lineasComprobantes;
        this.fechaEmision = fechaEmision;
        this.RUC = RUC;
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

    public int getRUC() {
        return RUC;
    }

    public void setRUC(int RUC) {
        this.RUC = RUC;
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
    
    public String getMetodoString(){
        String cadena = "";
        
        switch(this.metodoPago){
            case CONTRA_ENTREGA -> cadena = "CONTRA_ENTREGA";
            case VIRTUAL -> cadena = "VIRTUAL";
        }
        
        return cadena;
    }
    
    public void setMetodoString(String metodo) {
        switch (metodo) {
            case "CONTRA_ENTREGA" -> this.metodoPago = MetodoPago.CONTRA_ENTREGA;
            case "VIRTUAL" -> this.metodoPago = MetodoPago.VIRTUAL;
        }
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }
    
}
