/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprogwr.resources;

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
import java.util.List;
import java.util.Map;
import pe.edu.pucp.inf30.softprog.modelo.venta.DetalleEnvio;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.DetalleEnvioBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.DetalleEnvioBOImpl;

/**
 *
 * @author BRI
 */

@Path("detalle")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DetalleEnvioResource {
    private final  DetalleEnvioBO detalleEnvioBO;
    
    public DetalleEnvioResource(){
        detalleEnvioBO=new DetalleEnvioBOImpl();
    }
    @GET
    public List<DetalleEnvio> listar(){
        return this.detalleEnvioBO.listar();
    }
    @POST
    public Response crear(DetalleEnvio detalleEnvio){
            if(detalleEnvio==null || detalleEnvio.getDescripcion()==null || detalleEnvio.getDescripcion().isBlank()
                || detalleEnvio.getDireccion().isEmpty() || detalleEnvio.getDireccion()==null
                    || detalleEnvio.getDistritoString()==null || detalleEnvio.getDistritoString().isBlank()
            ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El detalle envio no esta creado")
                    .build();
            }
        //faltar guardar
        this.detalleEnvioBO.insertar(detalleEnvio);
        URI location= URI.create("/SoftProgWR/api/v1/detalle/" + detalleEnvio.getId());
        return Response.created(location)
                .entity(detalleEnvio)
                .build();
    }
    //si
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id){
        DetalleEnvio detalleEnvio=this.detalleEnvioBO.obtener(id);
        if(detalleEnvio==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "detalle: " + id + ", no encontrada"))
                    .build();
        }
        return Response.ok(detalleEnvio).build();
    }
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id")int id){
        if (this.detalleEnvioBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("detalle: " + id + ", no encontrada")
                    .build();
        }
        this.detalleEnvioBO.eliminar(id);
        
        return Response.noContent().build();
    }
    @PUT
    //si
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, DetalleEnvio detalleEnvio){
         if(detalleEnvio==null || detalleEnvio.getDescripcion()==null || detalleEnvio.getDescripcion().isBlank()
                || detalleEnvio.getDireccion().isEmpty() || detalleEnvio.getDireccion()==null
                    || detalleEnvio.getDistritoString()==null || detalleEnvio.getDistritoString().isBlank()
            ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El detalle envio no esta creado")
                    .build();
            }

        // no se si esta bien
        detalleEnvioBO.actualizar(detalleEnvio);
        //falta guardar
        return Response.ok(detalleEnvio).build();
    } 
}