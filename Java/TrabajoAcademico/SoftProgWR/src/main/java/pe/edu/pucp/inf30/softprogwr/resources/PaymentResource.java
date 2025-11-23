/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.inf30.softprogwr.resources;

/**
 *
 * @author BRI
 */
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/payments")
public class PaymentResource {

    private final PaypalService payPalService = new PaypalService();

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPayment(Map<String, String> request) {
        String total = request.get("total");
        if (total == null || total.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El total es obligatorio")
                    .build();
        }

        try {
            Payment payment = payPalService.createOrder(total);

            // Buscar URL de aprobación
            String approvalLink = payment.getLinks().stream()
                    .filter(link -> link.getRel().equalsIgnoreCase("approval_url"))
                    .findFirst()
                    .map(link -> link.getHref())
                    .orElse("");

            Map<String, String> response = new HashMap<>();
            response.put("paymentId", payment.getId());
            response.put("approvalUrl", approvalLink);

            return Response.ok(response).build();
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el pago")
                    .build();
        }
    }
    @POST
    @Path("/execute")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response executePayment(Map<String, String> request) {
        String paymentId = request.get("paymentId");
        String payerId = request.get("payerId");

        if (paymentId == null || payerId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("paymentId y payerId son obligatorios")
                .build();
        }
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            Map<String, String> response = new HashMap<>();
            response.put("paymentId", payment.getId());
            response.put("status", payment.getState());
            return Response.ok(response).build();
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error al ejecutar el pago")
                .build();
        }
    }
}