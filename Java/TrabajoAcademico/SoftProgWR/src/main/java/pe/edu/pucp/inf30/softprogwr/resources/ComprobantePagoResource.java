package pe.edu.pucp.inf30.softprogwr.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.inf30.softprog.modelo.pago.ComprobantePago;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.ComprobantePagoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.pago.ComprobantePagoBOImpl;

@Path("comprobante")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComprobantePagoResource {

    private final ComprobantePagoBO comprobanteBO;

    public ComprobantePagoResource() {
        this.comprobanteBO = new ComprobantePagoBOImpl();
    }

    @POST
    @Path("pagar")
    public Response procesarPago(ComprobantePago comprobante) {
        try {
            comprobanteBO.insertar(comprobante);

            return Response.ok()
                    .entity("{\"mensaje\": \"Pago confirmado y registrado exitosamente.\", \"id\": " + comprobante.getId() + "}")
                    .build();

        } catch (RuntimeException ex) {
            return Response.status(Response.Status.PAYMENT_REQUIRED)
                    .entity("{\"error\": \"" + ex.getMessage() + "\"}")
                    .build();
        } catch (Exception ex) {
            return Response.serverError()
                    .entity("{\"error\": \"Error interno del servidor.\"}")
                    .build();
        }
    }
}