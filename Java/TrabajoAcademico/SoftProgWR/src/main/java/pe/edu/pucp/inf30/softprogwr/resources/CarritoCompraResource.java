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
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoCompras;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarrito;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.CarritoComprasBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.CarritoComprasBOImpl;
/**
 *
 * @author BRI
 */
@Path("carritocompras")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarritoCompraResource {
    private final CarritoComprasBO carritoComprasBO;
    
    public CarritoCompraResource(){
        carritoComprasBO=new CarritoComprasBOImpl();
    }
    //Sí
    @GET
    public List<CarritoCompras> listar(){
        return this.carritoComprasBO.listar();
    }
    //falta probar
    @POST
    public Response crear(CarritoCompras carritocompras){
        if(carritocompras==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El carrito de compras no estacreado")
                    .build();
        }
        this.carritoComprasBO.insertar(carritocompras);
        URI location= URI.create("/SoftProgWR/api/v1/carritocompras/" + carritocompras.getId());
        return Response.created(location)
                .entity(carritocompras)
                .build();
    }
    //Sí
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id){
        CarritoCompras carritoCompras=this.carritoComprasBO.obtener(id);
        if(carritoCompras==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "carritoCompras: " + id + ", no encontrada"))
                    .build();
        }
        return Response.ok(carritoCompras).build();
    }
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id")int id){
        if (this.carritoComprasBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("carritoCompras: " + id + ", no encontrada")
                    .build();
        }
        this.carritoComprasBO.eliminar(id);
        
        return Response.noContent().build();
    }
    //Sí
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, CarritoCompras carritocompras){
        if(carritocompras==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El carrito de compras no esta creado")
                    .build();
        }  
        if (this.carritoComprasBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("carritoCompras: " + id + ", no encontrado")
                    .build();
        }
        // no se si esta bien
        carritoComprasBO.actualizar(carritocompras);
        //falta guardar
        return Response.ok(carritocompras).build();
    }
    ////si funciona
    @GET
    @Path("IdCarrito/{id}")
    public List<LineaCarrito> listarLineaCarritoPorIdCarrito(@PathParam("id") int id){
        return carritoComprasBO.listarLineaCarritoPorIdCarrito(id);
    }
    ///Sí
    @GET
    @Path("obtenerCarritoIDCliente/{idCliente}")
    public Response obtenerCarritoDeCliente(@PathParam("idCliente") int idCliente){
        CarritoCompras carritoCompras=carritoComprasBO.obtenerCarritoComprasPorIdCliente(idCliente);
        if(carritoCompras==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "carritoCompras: " + idCliente + ", no encontrada"))
                    .build();
        }
        return Response.ok(carritoCompras).build();
    }
}
