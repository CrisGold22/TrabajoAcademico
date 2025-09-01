/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo;

/**
 *
 * @author Cristhian Horacio
 */
public class Inventario {
    private long idPedido;
    private Producto producto;
    private Integer cantidadDisponible;
    private Integer cantidadReservada;
    
    public Inventario(){
        
    }

    public Inventario(long idPedido, Producto producto, Integer cantidadDisponible, Integer cantidadReservada) {
        this.idPedido = idPedido;
        this.producto = producto;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadReservada = cantidadReservada;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Integer getCantidadReservada() {
        return cantidadReservada;
    }

    public void setCantidadReservada(Integer cantidadReservada) {
        this.cantidadReservada = cantidadReservada;
    }
    
    
}
