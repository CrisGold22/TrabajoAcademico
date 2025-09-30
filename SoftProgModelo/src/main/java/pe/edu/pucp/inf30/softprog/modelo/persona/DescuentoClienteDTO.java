/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.TipoDescuento;

/**
 *
 * @author Cristhian Horacio
 */
public class DescuentoClienteDTO extends RegistroDTO{
    private double descuento;
    private ClienteDTO cliente;
    private TipoDescuento tipoDescuento;

    public DescuentoClienteDTO(){
        
    }

    public DescuentoClienteDTO(double descuento, ClienteDTO cliente, TipoDescuento tipoDescuento) {
        this.descuento = descuento;
        this.cliente = cliente;
        this.tipoDescuento = tipoDescuento;
    }

    public DescuentoClienteDTO(double descuento, ClienteDTO cliente, TipoDescuento tipoDescuento, int id, boolean activo) {
        super(id, activo);
        this.descuento = descuento;
        this.cliente = cliente;
        this.tipoDescuento = tipoDescuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public TipoDescuento getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(TipoDescuento tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }
    
    
}
