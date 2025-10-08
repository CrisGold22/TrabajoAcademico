/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.producto.ProductoDTO;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoCarrito;

/**
 *
 * @author Cristhian Horacio
 */
public class CarritoComprasDTO extends RegistroDTO {

    private List<LineaCarritoDTO> lineasCarrito;
    private double totalParcial;
    private double totalConDescuento;
    private EstadoCarrito estado;
    private int idCliente;

    public CarritoComprasDTO() {
        lineasCarrito = new ArrayList<>();
    }

    public CarritoComprasDTO(ArrayList<LineaCarritoDTO> productos, double totalParcial, double totalConDescuento, EstadoCarrito estado, int idCliente) {
        this.lineasCarrito = productos;
        this.totalParcial = totalParcial;
        this.totalConDescuento = totalConDescuento;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    public CarritoComprasDTO(ArrayList<LineaCarritoDTO> productos, double totalParcial, double totalConDescuento, EstadoCarrito estado, int idCliente, int id, boolean activo) {
        super(id, activo);
        this.lineasCarrito = productos;
        this.totalParcial = totalParcial;
        this.totalConDescuento = totalConDescuento;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    

    public List<LineaCarritoDTO> getLineasCarritos() {
        return lineasCarrito;
    }

    public void setLineasCarrito(List<LineaCarritoDTO> productos) {
        this.lineasCarrito = productos;
    }

    public double getTotalParcial() {
        return totalParcial;
    }

    public void setTotalParcial(double totalParcial) {
        this.totalParcial = totalParcial;
    }

    public double getTotalConDescuento() {
        return totalConDescuento;
    }

    public void setTotalConDescuento(double totalConDescuento) {
        this.totalConDescuento = totalConDescuento;
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
                "CARRITO_VACIO";
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
            case "CARRITO_VACIO" ->
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
