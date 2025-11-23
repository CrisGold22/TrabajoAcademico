/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.persona.Cliente;
import pe.edu.pucp.inf30.softprog.modelo.persona.Empresa;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.LocalDateTimeAdapter;

/**
 *
 * @author Cristhian Horacio
 */
public class OrdenCompra extends Registro {

    private List<LineaOrdenCompra> lineasOrden;
    private LocalDateTime fechaCreacion;   
    private double totalParcial;
    private double totalFinal;
    private double descuentoTotal;
    private CarritoCompras carritoCompras;
    private Cliente cliente;
    private EstadoOrdenCompra estado;
    private Empresa empresa;
    private String estadoString;

    public OrdenCompra() {
        lineasOrden = new ArrayList<>();
    }

    public OrdenCompra(List<LineaOrdenCompra> lineasOrden, LocalDateTime fechaCreacion, double totalParcial, double totalFinal, double descuentoTotal, CarritoCompras carritoCompras, Cliente cliente, EstadoOrdenCompra estado, Empresa empresa, String estadoString) {
        this.lineasOrden = lineasOrden;
        this.fechaCreacion = fechaCreacion;
        this.totalParcial = totalParcial;
        this.totalFinal = totalFinal;
        this.descuentoTotal = descuentoTotal;
        this.carritoCompras = carritoCompras;
        this.cliente = cliente;
        this.estado = estado;
        this.empresa = empresa;
        this.estadoString = estadoString;
    }

    public OrdenCompra(List<LineaOrdenCompra> lineasOrden, LocalDateTime fechaCreacion, double totalParcial, double totalFinal, double descuentoTotal, CarritoCompras carritoCompras, Cliente cliente, EstadoOrdenCompra estado, Empresa empresa, String estadoString, int id, boolean activo) {
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
        this.estadoString = estadoString;
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
        // mantener sincronizado
        this.estadoString = (estado != null ? estado.name() : null);
    }

    public String getEstadoString() {
        if (estado != null) return estado.name();
        return estadoString;
    }

    public void setEstadoString(String estadoString) {
        this.estadoString = estadoString;
        if (estadoString != null) {
            this.estado = EstadoOrdenCompra.valueOf(estadoString);
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
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
