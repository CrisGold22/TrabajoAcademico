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
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.ProductoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.producto.ProductoBOImpl;

/**
 *
 * @author BRI
 */

@Path("producto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoResource {
    private final  ProductoBO productoBO;
    
    public ProductoResource(){
        productoBO=new ProductoBOImpl();
    }
    //SÍ FUNCIONA
    @GET
    public List<Producto> listar(){
        return this.productoBO.listar();
    }
    @POST
    public Response crear( Producto producto){
            if(producto==null || producto.getCategoria()==null
                    || producto.getDescripcion().isBlank() || producto.getDescripcion()==null
                    || producto.getMarca().isBlank() || producto.getMarca()==null
                    || producto.getSKU().isBlank() || producto.getSKU()==null
                    ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Producto  no esta creado")
                    .build();
            }
        //faltar guardar
        this.productoBO.insertar(producto);
        URI location= URI.create("/SoftProgWR/api/v1/producto/" + producto.getId());
        return Response.created(location)
                .entity(producto)
                .build();
    }
    //Sí funciona
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id){
        Producto comprobantePago=this.productoBO.obtener(id);
        if(comprobantePago==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "producto: " + id + ", no encontrada"))
                    .build();
        }
        return Response.ok(comprobantePago).build();
    }
    //si funciona
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id")int id){
        if (this.productoBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("producto: " + id + ", no encontrada")
                    .build();
        }
        this.productoBO.eliminar(id);
        
        return Response.noContent().build();
    }
    @PUT//ok
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Producto producto){
         if(producto==null || producto.getCategoria()==null
                    || producto.getDescripcion().isBlank() || producto.getDescripcion()==null
                    || producto.getMarca().isBlank() || producto.getMarca()==null
                    || producto.getSKU().isBlank() || producto.getSKU()==null
                    ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Producto no esta creado")
                    .build();
            } 
        if (this.productoBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Producto: " + id + ", no encontrado")
                    .build();
        }
        // no se si esta bien
        productoBO.actualizar(producto);
        //falta guardar
        return Response.ok(producto).build();
    }
    @GET
//    Si funciona
    @Path("filtrarproductoprecio/{id}/{RangoPrecio1}/{RangoPrecio2}")
    public List<Producto> listarfiltrarProductoPorPrecioRegula(@PathParam("id")Integer id,@PathParam("RangoPrecio1")double RangoPrecio1,@PathParam("RangoPrecio2")double RangoPrecio2){
        return this.productoBO.filtrarProductoPorPrecioRegular(id, RangoPrecio1, RangoPrecio2);
    }
    
    //si funciona
    @GET
    @Path("filtrarproductomarca/{id}/{marca}")
    public List<Producto> listarfiltrarProductoPorMarca(@PathParam("id")Integer id,@PathParam("marca")String marca){
        return this.productoBO.filtrarProductoPorMarca(id, marca);
    }
    //hay error
    @GET
    @Path("filtrarproductodescuento/{idCategoria}/{nombreCategoria}")
    public List<Producto> listarfiltrarProductoPorDescuento(@PathParam("idCategoria")Integer idCategoria,@PathParam("nombreCategoria")String nombreCategoria){
        return this.productoBO.filtrarProductoPorDescuento(idCategoria, nombreCategoria);
    }
    @GET
    //si
    @Path("verificarstock/{id}/{cantidadSolicitada}")
    public Response verificarStockSuficientePorID(@PathParam("id") Integer id,@PathParam("cantidadSolicitada") Integer cantidadSolicitada){
        Boolean stocksuficienteID=this.productoBO.verificarStockSuficientePorID(id, cantidadSolicitada);
        return Response.ok(stocksuficienteID).build();
    }
    //si funciona, solo era ingresar bien un string aunque tengo que hacer mas vadidaciones
    @GET
    @Path("sku/{sku}/{cantidadSolicitada}")
    public Response verificarStockSuficientePorSKU(@PathParam("sku") String sku,@PathParam("cantidadSolicitada") Integer cantidadSolicitada){
        Boolean stocksuficiente=this.productoBO.verificarStockSuficientePorSKU(sku, cantidadSolicitada);
        return Response.ok(stocksuficiente).build();
    }
    //si funciona y lista
    @GET
    @Path("obtenerPorSku/{sku}")
    public Response obtenerPorSku(@PathParam("sku") String sku){
        Producto comprobantePago=this.productoBO.obtenerPorSku(sku);
        if(comprobantePago==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "producto: " + sku + ", no encontrada"))
                    .build();
        }
        return Response.ok(comprobantePago).build();
    }
    
    //falta implementar dos metodos
    @GET
    @Path("filtrarproductoprecioAlmayor/{id}/{RangoPrecio1}/{RangoPrecio2}")
    public List<Producto> listarfiltrarproductoprecioAlmayor(@PathParam("id")Integer id,@PathParam("RangoPrecio1")double RangoPrecio1,@PathParam("RangoPrecio2")double RangoPrecio2){
        return productoBO.filtrarProductoPorPrecioAlMayor(id, RangoPrecio1, RangoPrecio2);
    }
    @GET
    @Path("productoporcategoria/{idCategoria}")
    public List<Producto> listarobtenerProductosPorCategoria(@PathParam("idCategoria")Integer idCategoria){
        return productoBO.obtenerProductosPorCategoria(idCategoria);
    }
}