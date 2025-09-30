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
public class ProductoDTO extends RegistroDTO{
    private String nombre;
    private String SKU;
    private String descripcion;
    private double precioUnitario;
    private PrecioAlMayorDTO precioAlMayor;
    private int stockDisponible;
    private int stockMinimo;
    private int stockMaximo;
    
    public ProductoDTO(){
        
    }

    public ProductoDTO(String nombre, String SKU, String descripcion, double precioUnitario, PrecioAlMayorDTO precioAlMayor, int stockDisponible, int stockMinimo, int stockMaximo) {
        this.nombre = nombre;
        this.SKU = SKU;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.precioAlMayor = precioAlMayor;
        this.stockDisponible = stockDisponible;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
    }

    public ProductoDTO(String nombre, String SKU, String descripcion, double precioUnitario, PrecioAlMayorDTO precioAlMayor, int stockDisponible, int stockMinimo, int stockMaximo, int id, boolean activo) {
        super(id, activo);
        this.nombre = nombre;
        this.SKU = SKU;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.precioAlMayor = precioAlMayor;
        this.stockDisponible = stockDisponible;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public PrecioAlMayorDTO getPrecioAlMayor() {
        return precioAlMayor;
    }

    public void setPrecioAlMayor(PrecioAlMayorDTO precioAlMayor) {
        this.precioAlMayor = precioAlMayor;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
    }
    
    
    
}
