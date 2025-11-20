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
    private final  OrdenCompraBO ordenCompraBO;
    
    public OrdenCompraResource(){
        ordenCompraBO=new OrdenCompraBOImpl();
    }
    @GET
    public List<OrdenCompra> listar(){
        return this.ordenCompraBO.listar();
    }
    @POST
    public Response crear(OrdenCompra ordenCompra){
            if(ordenCompra==null || ordenCompra.getCliente()==null
                    || ordenCompra.getEstadoString()==null
                    || ordenCompra.getEstadoString().isEmpty()
                    ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La ordencompra no esta creado")
                    .build();
            }
        //faltar guardar
        this.ordenCompraBO.insertar(ordenCompra);
        URI location= URI.create("/SoftProgWR/api/v1/ordencompra/" + ordenCompra.getId());
        return Response.created(location)
                .entity(ordenCompra)
                .build();
    }
    //si
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id){
        OrdenCompra ordenCompra=this.ordenCompraBO.obtener(id);
        if(ordenCompra==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "ordencompra: " + id + ", no encontrada"))
                    .build();
        }
        return Response.ok(ordenCompra).build();
    }
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id")int id){
        if (this.ordenCompraBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("ordencompra: " + id + ", no encontrada")
                    .build();
        }
        this.ordenCompraBO.eliminar(id);
        
        return Response.noContent().build();
    }
    @PUT
    //si
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, OrdenCompra ordenCompra){
         if(ordenCompra==null || ordenCompra.getCliente()==null
                    || ordenCompra.getEstadoString()==null
                    || ordenCompra.getEstadoString().isEmpty()
                    ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La orden de compra no esta creado")
                    .build();
            }  
        if (this.ordenCompraBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Ordencompra: " + id + ", no encontrado")
                    .build();
        }
        // no se si esta bien
        ordenCompraBO.actualizar(ordenCompra);
        //falta guardar
        return Response.ok(ordenCompra).build();
    }
    @GET
    //si
    @Path("consultarpedido/{id}/{fecha1}/{fecha2}")
    public List<OrdenCompra> consultarPedidoPorFechas(@PathParam("id") int id,@PathParam("fecha1") Date fecha1,@PathParam("fecha2") Date fecha2){
        return this.ordenCompraBO.consultarPedidoPorFechas(id, fecha1, fecha2);
    }
    //si funca
    @GET
    @Path("ordencliente/{idCliente}")
    public List<OrdenCompra> consultarOrdenCompraPorIdCliente(@PathParam("idCliente") int idCliente){
        return this.ordenCompraBO.consultarOrdenCompraPorIdCliente(idCliente);
    }
    //si 
    @PUT
    @Path("desactivar/{id}")
    public Response desactivarOrdenCompra(@PathParam("id") Integer id){
        ordenCompraBO.desactivarOrdenCompra(id);
        return Response.ok(id).build();
    }
    //si
    @GET
    @Path("listarIdOrdenCompra/{idCliente}")
    public Response listarLineasOrdenCompraPorIdOrdenCompra(@PathParam("id")int id){
        List<LineaOrdenCompra> lineas = ordenCompraBO.listarLineasOrdenCompraPorIdOrdenCompra(id);
        
        return Response.ok(lineas).build();
    }

}