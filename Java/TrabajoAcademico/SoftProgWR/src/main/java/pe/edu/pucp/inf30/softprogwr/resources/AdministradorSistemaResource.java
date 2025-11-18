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
import pe.edu.pucp.inf30.softprog.modelo.persona.AdministradorSistema;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.AdministradorSistemaBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.AdministradorSistemaBOImpl;

/**
 *
 * @author BRI
 */

@Path("administrador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdministradorSistemaResource {
    private final AdministradorSistemaBO administradorSistemaBO;
    
    public AdministradorSistemaResource(){
        administradorSistemaBO=new AdministradorSistemaBOImpl();
    }
    @GET
    public List<AdministradorSistema> listar(){
        return this.administradorSistemaBO.listar();
    }
    @POST
    public Response crear(AdministradorSistema administradorSistema){
        if(administradorSistema==null 
                || administradorSistema.getApellidoMaterno()==null 
                || administradorSistema.getApellidoPaterno()==null 
                || administradorSistema.getCargo()==null 
                || administradorSistema.getCargoString()==null 
                || administradorSistema.getDni()==null 
                || administradorSistema.getFechaNacimiento()==null 
                || (administradorSistema.getTelefono()>=1000000000 && administradorSistema.getTelefono()<=0)
                ){
            
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El administrador no esta creado")
                    .build();
        }
        this.administradorSistemaBO.insertar(administradorSistema);//
        URI location= URI.create("/SoftProgWR/api/v1/administrador/" + administradorSistema.getId());
        return Response.created(location)
                .entity(administradorSistema)
                .build();
    }
    @GET
    @Path("{id}") //si
    public Response obtener(@PathParam("id") int id){
        AdministradorSistema administradorSistema=this.administradorSistemaBO.obtener(id);
        if(administradorSistema==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "administrador: " + id + ", no encontrada"))
                    .build();
        }
        return Response.ok(administradorSistema).build();
    }
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id")int id){
        if (this.administradorSistemaBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("administrador: " + id + ", no encontrada")
                    .build();
        }
        this.administradorSistemaBO.eliminar(id);
        
        return Response.noContent().build();
    }
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, AdministradorSistema administradorSistema){
        if(administradorSistema==null 
                || administradorSistema.getApellidoMaterno()==null 
                || administradorSistema.getApellidoPaterno()==null 
                || administradorSistema.getCargo()==null 
                || administradorSistema.getCargoString()==null 
                || administradorSistema.getDni()==null 
                || administradorSistema.getFechaNacimiento()==null 
                ){
            
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El administrador no estacreado")
                    .build();
        }
        // no se si esta bien
        administradorSistemaBO.actualizar(administradorSistema);
        //falta guardar
        return Response.ok(administradorSistema).build();
    } 
}
