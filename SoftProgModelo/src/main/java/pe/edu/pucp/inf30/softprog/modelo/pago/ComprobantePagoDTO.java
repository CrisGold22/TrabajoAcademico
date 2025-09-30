/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.pago;

import java.time.LocalDate;
import java.util.ArrayList;
import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.pago.utils.MetodoPago;

/**
 *
 * @author Cristhian Horacio
 */
public class ComprobantePagoDTO extends RegistroDTO{
    private ArrayList<LineaComprobantePagoDTO> lineasComprobantes;
    private LocalDate fechaEmision;
    private int RUC;
    private double totalSinImpuestos;
    private double impuestos;
    private double totalFinal;
    private MetodoPago metodoPago;
    
    public ComprobantePagoDTO(){
        
    }

    public ComprobantePagoDTO(ArrayList<LineaComprobantePagoDTO> lineasComprobantes, LocalDate fechaEmision, int RUC, double totalSinImpuestos, double impuestos, double totalFinal, MetodoPago metodoPago) {
        this.lineasComprobantes = lineasComprobantes;
        this.fechaEmision = fechaEmision;
        this.RUC = RUC;
        this.totalSinImpuestos = totalSinImpuestos;
        this.impuestos = impuestos;
        this.totalFinal = totalFinal;
        this.metodoPago = metodoPago;
    }

    public ComprobantePagoDTO(ArrayList<LineaComprobantePagoDTO> lineasComprobantes, LocalDate fechaEmision, int RUC, double totalSinImpuestos, double impuestos, double totalFinal, MetodoPago metodoPago, int id, boolean activo) {
        super(id, activo);
        this.lineasComprobantes = lineasComprobantes;
        this.fechaEmision = fechaEmision;
        this.RUC = RUC;
        this.totalSinImpuestos = totalSinImpuestos;
        this.impuestos = impuestos;
        this.totalFinal = totalFinal;
        this.metodoPago = metodoPago;
    }

    public ArrayList<LineaComprobantePagoDTO> getLineasComprobantes() {
        return lineasComprobantes;
    }

    public void setLineasComprobantes(ArrayList<LineaComprobantePagoDTO> lineasComprobantes) {
        this.lineasComprobantes = lineasComprobantes;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
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
    
    
}
