/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.producto;

import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.producto.utils.TipoCategoria;
import pe.edu.pucp.inf30.softprog.modelo.producto.utils.TipoDescuento;

/**
 *
 * @author Cristhian Horacio
 */
public class Descuento extends Registro {

    private double precioPorVolumen;
    private Producto producto;
    private int cantidadMax;
    private int cantidadMin;

    public Descuento() {

    }

    public Descuento(double precioPorVolumen, Producto producto, int cantidadMax, int cantidadMin) {
        this.precioPorVolumen = precioPorVolumen;
        this.producto = producto;
        this.cantidadMax = cantidadMax;
        this.cantidadMin = cantidadMin;
    }

    public Descuento(double precioPorVolumen, Producto producto, int cantidadMax, int cantidadMin, int id, boolean activo) {
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
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

//    public TipoDescuento getTipoDescuento() {
//        return tipoDescuento;
//    }
//
//    public void setTipoDescuento(TipoDescuento tipoDescuento) {
//        this.tipoDescuento = tipoDescuento;
//    }
//
//    public String getTipoDescuentoString() {
//        String cadena = "";
//
//        switch (this.tipoDescuento) {
//            case PRIMERA_COMPRA ->
//                cadena = "PRIMERA_COMPRA";
//            case DESC_TEMPORADA ->
//                cadena = "DESC_TEMPORADA";
//            case CLIENTE_FRECUENTE ->
//                cadena = "CLIENTE_FRECUENTE";
//            case DESC_PERSONALIZADO ->
//                cadena = "DESC_PERSONALIZADO";
//            case SIN_DESCUENTO ->
//                cadena = "SIN_DESCUENTO";
//        }
//
//        return cadena;
//    }
//
//    public void setTipoDescuentoString(String tipoDescuento) {
//        switch (tipoDescuento) {
//            case "PRIMERA_COMPRA" ->
//                this.tipoDescuento = TipoDescuento.PRIMERA_COMPRA;
//            case "DESC_TEMPORADA" ->
//                this.tipoDescuento = TipoDescuento.DESC_TEMPORADA;
//            case "CLIENTE_FRECUENTE" ->
//                this.tipoDescuento = TipoDescuento.CLIENTE_FRECUENTE;
//            case "DESC_PERSONALIZADO" ->
//                this.tipoDescuento = TipoDescuento.DESC_PERSONALIZADO;
//            case "SIN_DESCUENTO" ->
//                this.tipoDescuento = TipoDescuento.SIN_DESCUENTO;
//        }
//    }

}
