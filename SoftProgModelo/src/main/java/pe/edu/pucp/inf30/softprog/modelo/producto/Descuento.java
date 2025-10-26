/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.producto;

import pe.edu.pucp.inf30.softprog.modelo.base.Registro;

/**
 *
 * @author Cristhian Horacio
 */
public class Descuento extends Registro{
    private double precioPorVolumen;
    private int idProducto;
    private int cantidadMax;
    private int cantidadMin;

    public Descuento(){
        
    }
    
    public Descuento(double precioPorVolumen, int producto, int cantidadMax, int cantidadMin) {
        this.precioPorVolumen = precioPorVolumen;
        this.idProducto = producto;
        this.cantidadMax = cantidadMax;
        this.cantidadMin = cantidadMin;
    }

    public Descuento(double precioPorVolumen, int producto, int cantidadMax, int cantidadMin, int id, boolean activo) {
        super(id, activo);
        this.precioPorVolumen = precioPorVolumen;
        this.idProducto = producto;
        this.cantidadMax = cantidadMax;
        this.cantidadMin = cantidadMin;
    }

    public double getPrecioPorVolumen() {
        return precioPorVolumen;
    }

    public void setPrecioPorVolumen(double precioPorVolumen) {
        this.precioPorVolumen = precioPorVolumen;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int producto) {
        this.idProducto = producto;
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
