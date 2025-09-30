/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.producto;

import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.producto.utils.UnidadMedida;

/**
 *
 * @author Cristhian Horacio
 */
public class ProductoDTO extends RegistroDTO{
    private String nombre;
    private String SKU;
    private String descripcion;
    private double precioUnitario;
    private double precioAlMayor;
    private UnidadMedida medidaAlMayor;
    private int stockDisponible;
    private int stockMinimo;
    private int stockMaximo;
    
    public ProductoDTO(){
        
    }

    public ProductoDTO(String nombre, String SKU, String descripcion, double precioUnitario, double precioAlMayor, UnidadMedida medidaAlMayor, int stockDisponible, int stockMinimo, int stockMaximo) {
        this.nombre = nombre;
        this.SKU = SKU;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.precioAlMayor = precioAlMayor;
        this.medidaAlMayor = medidaAlMayor;
        this.stockDisponible = stockDisponible;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
    }

    public ProductoDTO(String nombre, String SKU, String descripcion, double precioUnitario, double precioAlMayor, UnidadMedida medidaAlMayor, int stockDisponible, int stockMinimo, int stockMaximo, int id, boolean activo) {
        super(id, activo);
        this.nombre = nombre;
        this.SKU = SKU;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.precioAlMayor = precioAlMayor;
        this.medidaAlMayor = medidaAlMayor;
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

    public double getPrecioAlMayor() {
        return precioAlMayor;
    }

    public void setPrecioAlMayor(double precioAlMayor) {
        this.precioAlMayor = precioAlMayor;
    }

    public UnidadMedida getMedidaAlMayor() {
        return medidaAlMayor;
    }

    public void setMedidaAlMayor(UnidadMedida medidaAlMayor) {
        this.medidaAlMayor = medidaAlMayor;
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
