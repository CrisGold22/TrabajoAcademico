/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.producto;

import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class DescuentoDTO extends RegistroDTO{
    private double precioPorVolumen;
    private ProductoDTO producto;
    private int cantidadMax;
    private int cantidadMin;

    public DescuentoDTO(double precioPorVolumen, ProductoDTO producto, int cantidadMax, int cantidadMin) {
        this.precioPorVolumen = precioPorVolumen;
        this.producto = producto;
        this.cantidadMax = cantidadMax;
        this.cantidadMin = cantidadMin;
    }

    public DescuentoDTO(double precioPorVolumen, ProductoDTO producto, int cantidadMax, int cantidadMin, int id, boolean activo) {
        super(id, activo);
        this.precioPorVolumen = precioPorVolumen;
        this.producto = producto;
        this.cantidadMax = cantidadMax;
        this.cantidadMin = cantidadMin;
    }

    public double getPrecioPorVolumen() {
        return precioPorVolumen;
    }

    public void setPrecioPorVolumen(double precioPorVolumen) {
        this.precioPorVolumen = precioPorVolumen;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public int getCantidadMax() {
        return cantidadMax;
    }

    public void setCantidadMax(int cantidadMax) {
        this.cantidadMax = cantidadMax;
    }

    public int getCantidadMin() {
        return cantidadMin;
    }

    public void setCantidadMin(int cantidadMin) {
        this.cantidadMin = cantidadMin;
    }
    
    
    
}
