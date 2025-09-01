/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo;

import java.util.Date;

/**
 *
 * @author Cristhian Horacio
 */
public class ComprobantePago {
    private String idComprobante;
    private Pedido pedido;
    private Date fechaEmision;
    
    public ComprobantePago(){
        
    }

    public ComprobantePago(String idComprobante, Pedido pedido, Date fechaEmision) {
        this.idComprobante = idComprobante;
        this.pedido = pedido;
        this.fechaEmision = fechaEmision;
    }

    public String getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(String idComprobante) {
        this.idComprobante = idComprobante;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}
