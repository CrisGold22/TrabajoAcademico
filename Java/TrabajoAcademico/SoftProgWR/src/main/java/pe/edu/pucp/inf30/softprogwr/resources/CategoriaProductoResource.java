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
import pe.edu.pucp.inf30.softprog.modelo.producto.CategoriaProducto;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.CategoriaProductoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.producto.CategoriaProductoBOImpl;

/**
 *
 * @author Cristhian Horacio
 */
@Path("categoriaproducto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaProductoResource {
    private final  CategoriaProductoBO categoriaProductoBO;
    
    public CategoriaProductoResource(){
        categoriaProductoBO=new CategoriaProductoBOImpl();
    }
    @GET
    //Sí
    public List<CategoriaProducto> listar(){
        return this.categoriaProductoBO.listar();
    }
    @POST
    //SÍ
    public Response crear(CategoriaProducto categoriaProducto){
            if(categoriaProducto==null || categoriaProducto.getNombre()==null 
                || categoriaProducto.getNombre().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El carrito producto no esta creado")
                    .build();
        }
        this.categoriaProductoBO.insertar(categoriaProducto);
        URI location= URI.create("/SoftProgWR/api/v1/categoriaproducto/" + categoriaProducto.getId());
        return Response.created(location)
                .entity(categoriaProducto)
                .build();
    }
    //Sí
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id){
        CategoriaProducto categoriaProducto=this.categoriaProductoBO.obtener(id);
        if(categoriaProducto==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "categoriaproducto: " + id + ", no encontrada"))
                    .build();
        }
        return Response.ok(categoriaProducto).build();
    }
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id")int id){
        if (this.categoriaProductoBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("categoriaproducto: " + id + ", no encontrada")
                    .build();
        }
        this.categoriaProductoBO.eliminar(id);
        
        return Response.noContent().build();
    }
    @PUT
    @Path("{id}")
    //SÍ
    public Response actualizar(@PathParam("id") int id, CategoriaProducto categoriaProducto){
        if(categoriaProducto==null || categoriaProducto.getNombre()==null 
                || categoriaProducto.getNombre().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("categoriaproducto no esta creado")
                    .build();
        }  
        if (this.categoriaProductoBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("categoriaproducto: " + id + ", no encontrado")
                    .build();
        }
        // no se si esta bien
        categoriaProductoBO.actualizar(categoriaProducto);
        return Response.ok(categoriaProducto).build();
    }
    @GET
    @Path("listarMarcasPorcategoria/{idCategoria}")
    public List<String> listarMarcasPorCategoria(@PathParam("idCategoria") Integer idCategoria) {
        return categoriaProductoBO.obtenerMarcasPorCategoria(idCategoria);
    }
    
    @GET
    @Path("obtenerCategoriaPorNombre/{nombreCategoria}")
    public CategoriaProducto obtenerCategoriaPorNombre(@PathParam("nombreCategoria") String nombreCategoria){
        return categoriaProductoBO.obtenerCategoriaPorNombre(nombreCategoria);
    }
}