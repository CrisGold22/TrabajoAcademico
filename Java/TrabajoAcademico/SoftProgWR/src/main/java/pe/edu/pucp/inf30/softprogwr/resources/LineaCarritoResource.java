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
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarrito;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.LineaCarritoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.LineaCarritoBOImpl;

/**
 *
 * @author BRI
 */

@Path("lineacarrito")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LineaCarritoResource {
    private final  LineaCarritoBO lineaCarritoBO;
    
    public LineaCarritoResource(){
        lineaCarritoBO=new LineaCarritoBOImpl();
    }
    @GET
    public List<LineaCarrito> listar(){
        return this.lineaCarritoBO.listar();
    }
    //si
    @POST
    public Response crear(LineaCarrito lineaCarrito){
            if(lineaCarrito==null
                    ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Linea carrito no esta creado")
                    .build();
            }
        this.lineaCarritoBO.insertar(lineaCarrito);
        URI location= URI.create("/SoftProgWR/api/v1/lineaCarrito/" + lineaCarrito.getId());
        return Response.created(location)
                .entity(lineaCarrito)
                .build();
    }
    //si
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id){
        LineaCarrito comprobantePago=this.lineaCarritoBO.obtener(id);
        if(comprobantePago==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "lineaCarrito: " + id + ", no encontrada"))
                    .build();
        }
        return Response.ok(comprobantePago).build();
    }
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id")int id){
        if (this.lineaCarritoBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("lineaCarrito: " + id + ", no encontrada")
                    .build();
        }
        this.lineaCarritoBO.eliminar(id);
        
        return Response.noContent().build();
    }
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, LineaCarrito lineaCarrito){
        if(lineaCarrito==null
                    ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Linea carrito no esta creado")
                    .build();
            }
        if (this.lineaCarritoBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Linea carrito: " + id + ", no encontrado")
                    .build();
        }
        // no se si esta bien
        lineaCarritoBO.actualizar(lineaCarrito);
        //falta guardar
        return Response.ok(lineaCarrito).build();
    } 

//falta implementar listarPorIdCarrito
}