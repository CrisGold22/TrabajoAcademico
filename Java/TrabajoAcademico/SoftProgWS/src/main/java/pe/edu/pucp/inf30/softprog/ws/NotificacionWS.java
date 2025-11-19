/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.CuentaUsuarioBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.Notificacion;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.NotificacionImpl;

/**
 *
 * @author Cristhian Horacio
 */
@WebService(serviceName = "NotificacionWS", 
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class NotificacionWS {
    private final Notificacion notificacion;
    
    public NotificacionWS() {
        this.notificacion = new NotificacionImpl();
    }
    
    @WebMethod(operationName = "enviarMensajeOrdenCompra")
    public void enviarMensajeOrdenCompra(
            @WebParam(name = "orden") OrdenCompra orden, 
        @WebParam(name = "correo") String correo) {
        this.notificacion.enviarMensajeOrdenCompra(orden, correo);
    }
    
    
}