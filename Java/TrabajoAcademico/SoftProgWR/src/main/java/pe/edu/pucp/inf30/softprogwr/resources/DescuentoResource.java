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
import pe.edu.pucp.inf30.softprog.modelo.producto.Descuento;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.DescuentoBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.ProductoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.producto.DescuentoBOImpl;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.producto.ProductoBOImpl;

/**
 *
 * @author BRI
 */

@Path("descuento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DescuentoResource {
    private final  DescuentoBO descuentoBO;
    private final ProductoBO productoBO;
   // private final OrdenCompraBO ordenCompraBO;
    //private final Orden ordenVentaBO;
    public DescuentoResource(){
        this.descuentoBO=new DescuentoBOImpl();
        this.productoBO=new ProductoBOImpl();
 //       this.ordenCompraBO=new OrdenCompraBOImpl();
    }
    @GET //es un select
    public List<Descuento> listar(){
        return this.descuentoBO.listar();
    }
    //sí
    @GET
    @Path("{id}")
    public Response obtener (@PathParam("id") int id){
        Descuento descuento=this.descuentoBO.obtener(id);
        if(descuento==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Error","descuento: "+id+", no encontrada"))
                    .build();
        }
        return Response.ok(descuento).build();
    }
    @POST
    public Response crear(Descuento descuento){
        if (descuento == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La cuenta no es valida")
                    .build();
        }
        //falta corregir
    //    this.cuentaUsuarioBO.guardar(cuenta, Estado.Nuevo);
        URI location = 
                URI.create("/SoftProgWR/api/v1/descuento/" + descuento.getId());
        
        return Response.created(location)
                .entity(descuento)
                .build();
    }
    //Sí
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Descuento descuento) {
        if (descuento == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El descuento no es valida")
                    .build();
        }
        
        if (this.descuentoBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cuenta: " + id + ", el descuento")
                    .build();
        }
        
        this.descuentoBO.actualizar(descuento);
        
        return Response.ok(descuento).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (this.descuentoBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("descuento: " + id + ", no encontrado")
                    .build();
        }
        this.descuentoBO.eliminar(id);
        
        return Response.noContent().build();
    }
    @PUT
    //sí
    @Path("actualizardescuento/{id}/{nuevoprecio}")
    public Response actualizarPrecioDescuentoProducto(@PathParam("id") int id,@PathParam("nuevoprecio")double nuevo_precio){
        if(this.productoBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("producto: " + id + ", no encontrado"+" y el descuento"+nuevo_precio+"no se puede aplicar")
                    .build();
        }
        this.descuentoBO.actualizarPrecioDescuentoProducto(id, nuevo_precio);
        return Response.noContent().build();
    }
//    @POST
//    @Path("login")
//    public Response login(CuentaUsuario cuenta) {
//        boolean success = 
//                this.cde.login(
//                        cuenta.getUsername(), 
//                        cuenta.getPassword());
//        
//        if (success) {
//            return Response.status(Response.Status.OK)
//                    .entity("Login exitoso")
//                    .build();
//        }
//        
//        return Response.status(Response.Status.UNAUTHORIZED)
//                    .entity("Usuario o password incorrectos")
//                    .build();
//    }
    
   // @GET
//    @Path("{cuenta}/ordenesventa")
//    public Response listarPorCuenta(@PathParam("cuenta") String cuenta) {
//        List<OrdenCompra> ordenes = 
//                this.ordenCompraBO.listar(cuenta);
//        
//        if (ordenes == null || ordenes.isEmpty()) {
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity("La cuenta no es valida")
//                    .build();
//        }
//        
//        return Response.ok(ordenes).build();
//    }
}