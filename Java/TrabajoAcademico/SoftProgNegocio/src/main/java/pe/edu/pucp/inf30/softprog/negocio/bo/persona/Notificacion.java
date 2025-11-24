/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.bo.persona;

import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;

/**
 *
 * @author Cristhian Horacio
 */
public interface Notificacion {
    void enviarMensajeOrdenCompra(OrdenCompra orden, String correo);
    void enviarMensajeGenerico(String asunto, String cuerpo, String correo);
}
