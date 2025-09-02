/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author Cristhian Horacio
 */
public class Inventario {
    private long idPedido;
    private Map<Producto, Integer> productosDisponibles;
    private Map<Producto, Integer> productosReservados;
    private Date fechaActualizacion;

    public Inventario() {
    }

    public Inventario(long idPedido, Map<Producto, Integer> productosDisponibles, Map<Producto, Integer> productosReservados, Date fechaActualizacion) {
        this.idPedido = idPedido;
        this.productosDisponibles = productosDisponibles;
        this.productosReservados = productosReservados;
        this.fechaActualizacion = fechaActualizacion;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public Map<Producto, Integer> getProductosDisponibles() {
        return productosDisponibles;
    }

    public void setProductosDisponibles(Map<Producto, Integer> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }

    public Map<Producto, Integer> getProductosReservados() {
        return productosReservados;
    }

    public void setProductosReservados(Map<Producto, Integer> productosReservados) {
        this.productosReservados = productosReservados;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    
}
