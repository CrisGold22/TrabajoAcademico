/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta;

import java.util.ArrayList;
import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.producto.ProductoDTO;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoCarrito;

/**
 *
 * @author Cristhian Horacio
 */
public class CarritoComprasDTO extends RegistroDTO{
    private ArrayList<ProductoDTO> productos;
    private double totalParcial;
    private double totalConDescuento;
    private EstadoCarrito estado;
    
    public CarritoComprasDTO(){
        
    }

    public CarritoComprasDTO(ArrayList<ProductoDTO> productos, double totalParcial, double totalConDescuento, EstadoCarrito estado) {
        this.productos = productos;
        this.totalParcial = totalParcial;
        this.totalConDescuento = totalConDescuento;
        this.estado = estado;
    }

    public CarritoComprasDTO(ArrayList<ProductoDTO> productos, double totalParcial, double totalConDescuento, EstadoCarrito estado, int id, boolean activo) {
        super(id, activo);
        this.productos = productos;
        this.totalParcial = totalParcial;
        this.totalConDescuento = totalConDescuento;
        this.estado = estado;
    }

    public ArrayList<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ProductoDTO> productos) {
        this.productos = productos;
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
    
    
}
