/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprogwr.resources;

import jakarta.jws.WebParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.OrdenCompraBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.OrdenCompraBOImpl;

/**
 *
 * @author BRI
 */
@Path("ordencompra")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdenCompraResource {

    private final OrdenCompraBO ordenCompraBO;

    public OrdenCompraResource() {
        ordenCompraBO = new OrdenCompraBOImpl();
    }

    @GET
    public List<OrdenCompra> listar() {
        return this.ordenCompraBO.listar();
    }

    @POST
    public Response crear(OrdenCompra ordenCompra) {
        if (ordenCompra == null || ordenCompra.getCliente() == null
                || ordenCompra.getEstadoString() == null
                || ordenCompra.getEstadoString().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La ordencompra no esta creado")
                    .build();
        }
        //faltar guardar
        this.ordenCompraBO.insertar(ordenCompra);
        URI location = URI.create("/SoftProgWR/api/v1/ordencompra/" + ordenCompra.getId());
        return Response.created(location)
                .entity(ordenCompra)
                .build();
    }

    //si
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        OrdenCompra ordenCompra = this.ordenCompraBO.obtener(id);
        if (ordenCompra == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "ordencompra: " + id + ", no encontrada"))
                    .build();
        }
        return Response.ok(ordenCompra).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (this.ordenCompraBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("ordencompra: " + id + ", no encontrada")
                    .build();
        }
        this.ordenCompraBO.eliminar(id);

        return Response.noContent().build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") int id, OrdenCompra ordenCompra) {

        System.out.println("REST actualizarOrdenCompra -> id=" + id
                + ", estado=" + ordenCompra.getEstado()
                + ", estadoString=" + ordenCompra.getEstadoString());

        if (ordenCompra == null
                || ordenCompra.getCliente() == null
                || ordenCompra.getEstado() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La orden de compra no tiene datos completos o estado.")
                    .build();
        }

        OrdenCompra existente = this.ordenCompraBO.obtener(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("OrdenCompra: " + id + " no encontrada")
                    .build();
        }

        // aseguramos que el id del path manda
        ordenCompra.setId(id);

        // Por si por alguna razón estadoString vino null:
        if (ordenCompra.getEstado() != null && ordenCompra.getEstadoString() == null) {
            ordenCompra.setEstadoString(ordenCompra.getEstado().name());
        }

        ordenCompraBO.actualizar(ordenCompra);
        return Response.ok(ordenCompra).build();
    }

    @GET
    @Path("consultarpedido/{id}/{fecha1}/{fecha2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPedidoPorFechas(
            @PathParam("id") int id,
            @PathParam("fecha1") String fecha1Str,
            @PathParam("fecha2") String fecha2Str) {

        if (fecha1Str == null || fecha2Str == null
                || fecha1Str.isBlank() || fecha2Str.isBlank()) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Las fechas no pueden ser nulas")
                    .build();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fecha1;
        LocalDate fecha2;

        try {
            fecha1 = LocalDate.parse(fecha1Str, formatter);
            fecha2 = LocalDate.parse(fecha2Str, formatter);
        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Formato inválido. Debe ser yyyy-MM-dd")
                    .build();
        }

        // Convertir a LocalDateTime para el BO
        LocalDateTime inicio = fecha1.atStartOfDay();       // 00:00:00
        LocalDateTime fin = fecha2.atTime(23, 59, 59);   // 23:59:59

        List<OrdenCompra> lista = ordenCompraBO.consultarPedidoPorFechas(id, inicio, fin);
        return Response.ok(lista).build();
    }

    //si funca
    @GET
    @Path("ordencliente/{idCliente}")
    public List<OrdenCompra> consultarOrdenCompraPorIdCliente(@PathParam("idCliente") int idCliente) {
        return this.ordenCompraBO.consultarOrdenCompraPorIdCliente(idCliente);
    }

    //si 
    @PUT
    @Path("desactivar/{id}")
    public Response desactivarOrdenCompra(@PathParam("id") Integer id) {
        ordenCompraBO.desactivarOrdenCompra(id);
        return Response.ok(id).build();
    }

    @PUT
    @Path("desactivarPorHorario/{id}")
    public Response desactivarPorHorario(@PathParam("id") Integer id){
        ordenCompraBO.desactivarOrdenCompraPorHorario(id);
        return Response.ok(id).build();
    }
    
    
    //si
    @GET
    @Path("listarIdOrdenCompra/{id}")
    public Response listarLineasOrdenCompraPorIdOrdenCompra(@PathParam("id") int id) {
        List<LineaOrdenCompra> lineas = ordenCompraBO.listarLineasOrdenCompraPorIdOrdenCompra(id);

        return Response.ok(lineas).build();
    }

}
