/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.persona.Cliente;
import pe.edu.pucp.inf30.softprog.modelo.persona.Empresa;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.LocalDateAdapter;

/**
 *
 * @author Cristhian Horacio
 */
public class OrdenCompra extends Registro {

    private List<LineaOrdenCompra> lineasOrden;
    private LocalDate fechaCreacion;   
    private double totalParcial;
    private double totalFinal;
    private double descuentoTotal;
    private CarritoCompras carritoCompras;
    private Cliente cliente;
    private EstadoOrdenCompra estado;
    private Empresa empresa;

    public OrdenCompra() {
        lineasOrden = new ArrayList<>();
    }

    public OrdenCompra(List<LineaOrdenCompra> lineasOrden, LocalDate fechaCreacion, double totalParcial, double totalFinal, double descuentoTotal, CarritoCompras carritoCompras, Cliente cliente, EstadoOrdenCompra estado, Empresa empresa) {
        this.lineasOrden = lineasOrden;
        this.fechaCreacion = fechaCreacion;
        this.totalParcial = totalParcial;
        this.totalFinal = totalFinal;
        this.descuentoTotal = descuentoTotal;
        this.carritoCompras = carritoCompras;
        this.cliente = cliente;
        this.estado = estado;
        this.empresa = empresa;
    }

    public OrdenCompra(List<LineaOrdenCompra> lineasOrden, LocalDate fechaCreacion, double totalParcial, double totalFinal, double descuentoTotal, CarritoCompras carritoCompras, Cliente cliente, EstadoOrdenCompra estado, Empresa empresa, int id, boolean activo) {
        super(id, activo);
        this.lineasOrden = lineasOrden;
        this.fechaCreacion = fechaCreacion;
        this.totalParcial = totalParcial;
        this.totalFinal = totalFinal;
        this.descuentoTotal = descuentoTotal;
        this.carritoCompras = carritoCompras;
        this.cliente = cliente;
        this.estado = estado;
        this.empresa = empresa;
    }

    
    
    public List<LineaOrdenCompra> getLineasOrden() {
        return lineasOrden;
    }

    public void setLineasOrden(List<LineaOrdenCompra> lineasOrden) {
        this.lineasOrden = lineasOrden;
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
        if (this.estado == null)
                    return null;
                    
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public CarritoCompras getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(CarritoCompras carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    

}
