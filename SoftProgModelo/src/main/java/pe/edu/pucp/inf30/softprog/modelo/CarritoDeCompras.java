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
    private ArrayList<DetallePedido> productos;
    private double total;
    
    public CarritoDeCompras(){
        productos = new ArrayList<DetallePedido>();
        total = 0;
    }

    public CarritoDeCompras(ArrayList<DetallePedido> productos, double total) {
        this.productos = productos;
        this.total = total;
    }

    public ArrayList<DetallePedido> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<DetallePedido> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
