/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.producto;

import pe.edu.pucp.inf30.softprog.modelo.producto.utils.MedidaAlMayor;
import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;

/**
 *
 * @author Cristhian Horacio
 */
public class PrecioAlMayorDTO extends RegistroDTO{
    private double precioPorVolumen;
    private ProductoDTO producto;
    private MedidaAlMayor medidaAlMayor;
    
    public PrecioAlMayorDTO(){
        
    }

    public PrecioAlMayorDTO(double precioPorVolumen, ProductoDTO producto, MedidaAlMayor medidaAlMayor) {
        this.precioPorVolumen = precioPorVolumen;
        this.producto = producto;
        this.medidaAlMayor = medidaAlMayor;
    }

    public PrecioAlMayorDTO(double precioPorVolumen, ProductoDTO producto, MedidaAlMayor medidaAlMayor, int id, boolean activo) {
        super(id, activo);
        this.precioPorVolumen = precioPorVolumen;
        this.producto = producto;
        this.medidaAlMayor = medidaAlMayor;
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

    public MedidaAlMayor getMedidaAlMayor() {
        return medidaAlMayor;
    }

    public void setMedidaAlMayor(MedidaAlMayor medidaAlMayor) {
        this.medidaAlMayor = medidaAlMayor;
    }
    
    
    
}
