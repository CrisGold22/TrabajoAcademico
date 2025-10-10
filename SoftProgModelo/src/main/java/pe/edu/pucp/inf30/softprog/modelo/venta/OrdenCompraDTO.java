/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public class OrdenCompraDTO extends RegistroDTO {

    private List<LineaOrdenCompraDTO> lineasOrden;
    private Date fechaCreacion;
    private double totalParcial;
    private double totalFinal;
    private double descuentoTotal;
    private int idDetalleEnvio;
    private EstadoOrdenCompra estado;

    public OrdenCompraDTO() {
        lineasOrden = new ArrayList<>();
    }

    public OrdenCompraDTO(ArrayList<LineaOrdenCompraDTO> lineasOrden, Date fechaCreacion, double totalParcial, double totalFinal, double descuentoTotal, int idDetalleEnvio, EstadoOrdenCompra estado) {
        this.lineasOrden = lineasOrden;
        this.fechaCreacion = fechaCreacion;
        this.totalParcial = totalParcial;
        this.totalFinal = totalFinal;
        this.descuentoTotal = descuentoTotal;
        this.idDetalleEnvio = idDetalleEnvio;
        this.estado = estado;
    }

    public OrdenCompraDTO(ArrayList<LineaOrdenCompraDTO> lineasOrden, Date fechaCreacion, double totalParcial, double totalFinal, double descuentoTotal, int idDetalleEnvio, EstadoOrdenCompra estado, int id, boolean activo) {
        super(id, activo);
        this.lineasOrden = lineasOrden;
        this.fechaCreacion = fechaCreacion;
        this.totalParcial = totalParcial;
        this.totalFinal = totalFinal;
        this.descuentoTotal = descuentoTotal;
        this.idDetalleEnvio = idDetalleEnvio;
        this.estado = estado;
    }

    public List<LineaOrdenCompraDTO> getLineasOrden() {
        return lineasOrden;
    }

    public void setLineasOrden(List<LineaOrdenCompraDTO> lineasOrden) {
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
            case PAGADO ->
                "PAGADO";
            case EN_PREPARACION ->
                "EN_PREPARACION";
            case ENVIADO ->
                "ENVIADO";
            case ENTREGADO ->
                "ENTREGADO";
            case CANCELADO ->
                "CANCELADO";
            case REEMBOLSADO ->
                "REEMBOLSADO";
            case EXPIRADO ->
                "EXPIRADO";
        };
    }

    public void setEstadoString(String estado) {
        switch (estado) {
            case "PAGADO" ->
                this.estado = EstadoOrdenCompra.PAGADO;
            case "EN_PREPARACION" ->
                this.estado = EstadoOrdenCompra.EN_PREPARACION;
            case "ENVIADO" ->
                this.estado = EstadoOrdenCompra.ENVIADO;
            case "ENTREGADO" ->
                this.estado = EstadoOrdenCompra.ENTREGADO;
            case "CANCELADO" ->
                this.estado = EstadoOrdenCompra.CANCELADO;
            case "REEMBOLSADO" ->
                this.estado = EstadoOrdenCompra.REEMBOLSADO;
            case "EXPIRADO" ->
                this.estado = EstadoOrdenCompra.EXPIRADO;
        }
    }

    public int getIdDetalleEnvio() {
        return idDetalleEnvio;
    }

    public void setIdDetalleEnvio(int idDetalleEnvio) {
        this.idDetalleEnvio = idDetalleEnvio;
    }

}
