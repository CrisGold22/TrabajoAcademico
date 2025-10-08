/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public class OrdenCompraDTO extends RegistroDTO {
    private ArrayList<LineaOrdenCompraDTO> lineasOrden;
    private Date fechaCreacion;
    private double totalParcial;
    private double totalFinal;
    private double descuentoTotal;
    private EstadoOrdenCompra estado;

    public OrdenCompraDTO(){
        
    }

    public OrdenCompraDTO(ArrayList<LineaOrdenCompraDTO> lineasOrden, Date fechaCreacion, double totalParcial, double totalFinal, double descuentoTotal, EstadoOrdenCompra estado) {
        this.lineasOrden = lineasOrden;
        this.fechaCreacion = fechaCreacion;
        this.totalParcial = totalParcial;
        this.totalFinal = totalFinal;
        this.descuentoTotal = descuentoTotal;
        this.estado = estado;
    }

    public OrdenCompraDTO(ArrayList<LineaOrdenCompraDTO> lineasOrden, Date fechaCreacion, double totalParcial, double totalFinal, double descuentoTotal, EstadoOrdenCompra estado, int id, boolean activo) {
        super(id, activo);
        this.lineasOrden = lineasOrden;
        this.fechaCreacion = fechaCreacion;
        this.totalParcial = totalParcial;
        this.totalFinal = totalFinal;
        this.descuentoTotal = descuentoTotal;
        this.estado = estado;
    }

    public ArrayList<LineaOrdenCompraDTO> getLineasOrden() {
        return lineasOrden;
    }

    public void setLineasOrden(ArrayList<LineaOrdenCompraDTO> lineasOrden) {
        this.lineasOrden = lineasOrden;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getTotalParcial() {
        return totalParcial;
    }

    public void setTotalParcial(double totalParcial) {
        this.totalParcial = totalParcial;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

    public double getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(double descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public EstadoOrdenCompra getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrdenCompra estado) {
        this.estado = estado;
    }
    
    public String getEstadoString() {
        return switch (this.estado) {
            case PAGADO         -> "PAGADO";
            case EN_PREPARACION -> "EN_PREPARACION";
            case ENVIADO        -> "ENVIADO";
            case ENTREGADO      -> "ENTREGADO";
            case CANCELADO      -> "CANCELADO";
            case REEMBOLSADO    -> "REEMBOLSADO";
            case EXPIRADO       -> "EXPIRADO";
        };
    }
}
