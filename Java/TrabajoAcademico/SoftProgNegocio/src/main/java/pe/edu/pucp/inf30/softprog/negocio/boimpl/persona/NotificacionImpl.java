/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.persona;

import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra.CANCELADO;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra.ENTREGADO;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra.EXPIRADO;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra.PAGADO;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra.REEMBOLSADO;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.Notificacion;

/**
 *
 * @author Cristhian Horacio
 */
public class NotificacionImpl implements Notificacion {

    private EmailServiceImpl emailService;

    public NotificacionImpl(){
        emailService = new EmailServiceImpl();
    }
    
    @Override
    public void enviarMensajeOrdenCompra(OrdenCompra orden, String correo) {
        if (orden == null) {
            throw new IllegalArgumentException("La orden de compra no tiene que ser nula.");
        }

        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("Tiene que ser un correo valido.");
        }

        String asunto = "";
        String cuerpo = "";

        // valores útiles
        String nombreCliente = orden.getCliente() != null ? orden.getCliente().getNombre() : "Cliente";
        String nombreEmpresa = orden.getEmpresa() != null ? orden.getEmpresa().getRazonSocial() : "Empresa asociada";
        String fecha = orden.getFechaCreacion() != null ? orden.getFechaCreacion().toString() : "(sin fecha)";

        double totalParcial = orden.getTotalParcial();
        double descuento = orden.getDescuentoTotal();
        double totalFinal = orden.getTotalFinal();

        // 1. Definir el asunto según el estado
        switch (orden.getEstado()) {
            case CANCELADO ->
                asunto = "Pedido Cancelado - Orden #" + orden.getId();

            case ENTREGADO ->
                asunto = "Pedido Entregado - Orden #" + orden.getId();

            case EXPIRADO ->
                asunto = "Orden Expirada - Orden #" + orden.getId();

            case PAGADO ->
                asunto = "Orden Pagada - Orden #" + orden.getId();

            case REEMBOLSADO ->
                asunto = "Orden Reembolsada - Orden #" + orden.getId();
        }

        // 2. Construir el cuerpo (HTML)
        cuerpo
                = "<html>"
                + "<body style='font-family: Arial, sans-serif;'>"
                + "<h2>" + asunto + "</h2>"
                + "<p>Estimado(a) <strong>" + nombreCliente + "</strong>,</p>"
                + switchMensaje(orden)
                + // texto personalizado por estado
                "<h3>Detalles de la Orden</h3>"
                + "<table style='border-collapse: collapse;'>"
                + "  <tr><td><strong>ID de Orden:</strong></td><td>" + orden.getId() + "</td></tr>"
                + "  <tr><td><strong>Empresa:</strong></td><td>" + nombreEmpresa + "</td></tr>"
                + "  <tr><td><strong>Fecha de creación:</strong></td><td>" + fecha + "</td></tr>"
                + "  <tr><td><strong>Total Parcial:</strong></td><td>S/. " + totalParcial + "</td></tr>"
                + "  <tr><td><strong>Descuento:</strong></td><td>S/. " + descuento + "</td></tr>"
                + "  <tr><td><strong>Total Final:</strong></td><td><strong>S/. " + totalFinal + "</strong></td></tr>"
                + "</table>"
                + "<br><p>Gracias por confiar en nosotros.</p>"
                + "<p>Atentamente,<br><strong>" + nombreEmpresa + "</strong></p>"
                + "</body>"
                + "</html>";

        emailService.enviarCorreo(correo, asunto, cuerpo);
    }

    private String switchMensaje(OrdenCompra orden) {
        return switch (orden.getEstado()) {
            case CANCELADO ->
                "<p>Te informamos que tu pedido ha sido <strong>cancelado</strong>. "
                + "Si crees que esto es un error, por favor contáctanos.</p>";

            case ENTREGADO ->
                "<p>Nos alegra informarte que tu pedido ha sido <strong>entregado</strong> exitosamente. "
                + "Esperamos que disfrutes de tu compra.</p>";

            case EXPIRADO ->
                "<p>Tu orden ha <strong>expirado</strong> debido a que no se realizó el pago dentro del tiempo establecido.</p>";

            case PAGADO ->
                "<p>Hemos recibido tu pago. Tu orden está ahora <strong>confirmada y en proceso de envío</strong>.</p>";

            case REEMBOLSADO ->
                "<p>Tu orden ha sido <strong>reembolsada</strong>. "
                + "El monto será reflejado en tu método de pago en un plazo de 3 a 7 días hábiles.</p>";

            default ->
                "<p>Actualización del estado de tu pedido.</p>";
        };
    }


}
