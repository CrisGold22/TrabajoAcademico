/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo;

import java.util.ArrayList;

/**
 *
 * @author Cristhian Horacio
 */
public class CarritoDeCompras {
    private ArrayList<DetalleOrdenCompra> productos;
    private double total;
    private EstadoCarrito estado;
    
    public CarritoDeCompras(){
        productos = new ArrayList<DetalleOrdenCompra>();
        total = 0;
    }

    public CarritoDeCompras(ArrayList<DetalleOrdenCompra> productos, double total, EstadoCarrito estado) {
        this.productos = productos;
        this.total = total;
        this.estado = estado;
    }

    public ArrayList<DetalleOrdenCompra> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<DetalleOrdenCompra> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
