/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.persona.Cliente;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoCarrito;

/**
 *
 * @author Cristhian Horacio
 */
public class CarritoCompras extends Registro {

    private List<LineaCarrito> lineasCarrito;
    private double subtotal;
    private double descuento;
    private double montoFinal;
    private EstadoCarrito estado;
    private Cliente cliente;

    public CarritoCompras() {
        lineasCarrito = new ArrayList<>();
    }

    public CarritoCompras(List<LineaCarrito> lineasCarrito, double subtotal, double descuento, double montoFinal, EstadoCarrito estado, Cliente cliente) {
        this.lineasCarrito = lineasCarrito;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.montoFinal = montoFinal;
        this.estado = estado;
        this.cliente = cliente;
    }

    public CarritoCompras(List<LineaCarrito> lineasCarrito, double subtotal, double descuento, double montoFinal, EstadoCarrito estado, Cliente cliente, int id, boolean activo) {
        super(id, activo);
        this.lineasCarrito = lineasCarrito;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.montoFinal = montoFinal;
        this.estado = estado;
        this.cliente = cliente;
    }

    
    public List<LineaCarrito> getLineasCarritos() {
        return lineasCarrito;
    }

    public void setLineasCarrito(List<LineaCarrito> productos) {
        this.lineasCarrito = productos;
    }

    public EstadoCarrito getEstado() {
        return estado;
    }

    public void setEstado(EstadoCarrito estado) {
        this.estado = estado;
    }
    
    public String getEstadoString() {
        return switch (this.estado) {
            case VACIO ->
                "VACIO";
            case EN_PROCESO ->
                "EN_PROCESO";
            case EN_REVISION ->
                "EN_REVISION";
            case CONFIRMADO ->
                "CONFIRMADO";
            case CANCELADO ->
                "CANCELADO";
            case EXPIRADO ->
                "EXPIRADO";
            case COMPLETADO ->
                "COMPLETADO";
            case PENDIENTE ->
                "PENDIENTE";
            case PROCESADO ->
                "PROCESADO";
        };
    }

    public void setEstadoString(String estado) {
        switch (estado) {
            case "VACIO" ->
                this.estado = EstadoCarrito.VACIO;
            case "EN_PROCESO" ->
                this.estado = EstadoCarrito.EN_PROCESO;
            case "EN_REVISION" ->
                this.estado = EstadoCarrito.EN_REVISION;
            case "CONFIRMADO" ->
                this.estado = EstadoCarrito.CONFIRMADO;
            case "CANCELADO" ->
                this.estado = EstadoCarrito.CANCELADO;
            case "EXPIRADO" ->
                this.estado = EstadoCarrito.EXPIRADO;
            case "COMPLETADO" ->
                this.estado = EstadoCarrito.COMPLETADO;
            case "PENDIENTE" ->
                this.estado = EstadoCarrito.PENDIENTE;
            case "PROCESADO" ->
                this.estado = EstadoCarrito.PROCESADO;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.cliente = Cliente;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    

}
