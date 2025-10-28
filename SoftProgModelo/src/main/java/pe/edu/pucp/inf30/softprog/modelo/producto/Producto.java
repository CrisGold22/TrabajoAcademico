/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.producto;

import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.producto.utils.UnidadMedida;

/**
 *
 * @author Cristhian Horacio
 */
public class Producto extends Registro {

    private String nombre;
    private String SKU;
    private String descripcion;
    private double precioUnitario;
    private double precioAlMayor;
    private UnidadMedida medidaAlMayor;
    private int stockDisponible;
    private int stockMinimo;
    private int stockMaximo;
    private int idCategoria;
    private String marca;
    
    public Producto() {

    }

    public Producto(String nombre, String SKU, String descripcion, double precioUnitario, double precioAlMayor, UnidadMedida medidaAlMayor, int stockDisponible, int stockMinimo, int stockMaximo, int idCategoria) {
        this.nombre = nombre;
        this.SKU = SKU;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.precioAlMayor = precioAlMayor;
        this.medidaAlMayor = medidaAlMayor;
        this.stockDisponible = stockDisponible;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.idCategoria = idCategoria;
    }

    public Producto(String nombre, String SKU, String descripcion, double precioUnitario, double precioAlMayor, UnidadMedida medidaAlMayor, int stockDisponible, int stockMinimo, int stockMaximo, int idCategoria, int id, boolean activo) {
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
        this.idCategoria = idCategoria;
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

    public String getMedidaAlMayorString() {
        String cadena = "";

        switch (this.medidaAlMayor) {
            case BOTELLA ->
                cadena = "BOTELLA";
            case CAJA ->
                cadena = "CAJA";
            case GALON ->
                cadena = "GALON";
            case KILOGRAMO ->
                cadena = "KILOGRAMO";
            case LITRO ->
                cadena = "LITRO";
            case PACK ->
                cadena = "PACK";
            case PAQUETE ->
                cadena = "PAQUETE";
            case SACO ->
                cadena = "SACO";
        }
        return cadena;
    }

    public void setMedidaAlMayorString(String medida) {
        switch (medida) {
            case "BOTELLA" ->
                this.medidaAlMayor = UnidadMedida.BOTELLA;
            case "CAJA" ->
                this.medidaAlMayor = UnidadMedida.CAJA;
            case "GALON" ->
                this.medidaAlMayor = UnidadMedida.GALON;
            case "KILOGRAMO" ->
                this.medidaAlMayor = UnidadMedida.KILOGRAMO;
            case "LITRO" ->
                this.medidaAlMayor = UnidadMedida.LITRO;
            case "PACK" ->
                this.medidaAlMayor = UnidadMedida.PACK;
            case "PAQUETE" ->
                this.medidaAlMayor = UnidadMedida.PAQUETE;
            case "SACO" ->
                this.medidaAlMayor = UnidadMedida.SACO;
        }
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }


    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }
    
}
