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
public class Entrega {
    private long idEntrega;
    private OrdenCompra pedido;
    private Date fechaEnvio;
    private String transportista;
    private String estadoEnvio;
    
    public Entrega(){
        
    }

    public Entrega(long id, OrdenCompra pedido, Date fechaEnvio, String transportista, String estadoEnvio) {
        this.idEntrega = id;
        this.pedido = pedido;
        this.fechaEnvio = fechaEnvio;
        this.transportista = transportista;
        this.estadoEnvio = estadoEnvio;
    }

    public long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(long id) {
        this.idEntrega = id;
    }

    public OrdenCompra getPedido() {
        return pedido;
    }

    public void setPedido(OrdenCompra pedido) {
        this.pedido = pedido;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getTransportista() {
        return transportista;
    }

    public void setTransportista(String transportista) {
        this.transportista = transportista;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }
    
    
    
}
