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
import pe.edu.pucp.inf30.softprog.modelo.persona.Empresa;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.EmpresaBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.EmpresaBOImpl;

/**
 *
 * @author efe
 */

@Path("empresa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmpresaResources {
    private final  EmpresaBO empresaBO;
    
    public EmpresaResources(){
        empresaBO=new EmpresaBOImpl();
    }
    @GET
    //si
    public List<Empresa> listar(){
        return this.empresaBO.listar();
    }
    @POST
    public Response crear(Empresa empresa){
            if(empresa==null || 
                    empresa.getCodigoPostal()==null || 
                    empresa.getDepartamento()==null || empresa.getDepartamento().isBlank() ||
                    empresa.getDireccionFiscal()==null || empresa.getDireccionFiscal().isBlank()||
                    empresa.getDistrito()==null || empresa.getDistrito().isBlank()||
                    empresa.getEmail()==null || empresa.getEmail().isBlank()||
                    empresa.getProvincia()==null || empresa.getProvincia().isBlank()||
                    empresa.getRuc()==null || empresa.getRuc().isBlank()||
                    empresa.getRazonSocial()==null || empresa.getRazonSocial().isBlank()||
                    empresa.getTelefono()==null || empresa.getTelefono().isBlank()
                    ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La empresa no esta creado")
                    .build();
            }
        //faltar guardar
        this.empresaBO.insertar(empresa);
        URI location= URI.create("/SoftProgWR/api/v1/empresa/" + empresa.getId());
        return Response.created(location)
                .entity(empresa)
                .build();
    }
    //si
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id){
        Empresa empresa=this.empresaBO.obtener(id);
        if(empresa==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", " La empresa: " + id + ", no encontrada"))
                    .build();
        }
        return Response.ok(empresa).build();
    }
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id")int id){
        if (this.empresaBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("detalle: " + id + ", no encontrada")
                    .build();
        }
        this.empresaBO.eliminar(id);
        
        return Response.noContent().build();
    }
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, Empresa empresa){
            if(empresa==null || 
                    empresa.getCodigoPostal()==null || 
                    empresa.getDepartamento()==null || empresa.getDepartamento().isBlank() ||
                    empresa.getDireccionFiscal()==null || empresa.getDireccionFiscal().isBlank()||
                    empresa.getDistrito()==null || empresa.getDistrito().isBlank()||
                    empresa.getEmail()==null || empresa.getEmail().isBlank()||
                    empresa.getProvincia()==null || empresa.getProvincia().isBlank()||
                    empresa.getRuc()==null || empresa.getRuc().isBlank()||
                    empresa.getRazonSocial()==null || empresa.getRazonSocial().isBlank()||
                    empresa.getTelefono()==null || empresa.getTelefono().isBlank()
                    ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El detalle envio no esta creado")
                    .build();
            }

        // no se si esta bien
        empresaBO.actualizar(empresa);
        //falta guardar
        return Response.ok(empresa).build();
    }
    //sí
    @GET
    @Path("activos")
    public List<Empresa> listarEmpresasActivos(){
        return this.empresaBO.listarEmpresasActivos();
    }
    //sí
    @GET
    @Path("noactivos")
    public List<Empresa> listarEmpresasNoActivos(){
        return this.empresaBO.listarEmpresasNoActivos();
    }
    //sí
    @GET
    @Path("empresaporcliente/{id}")
    public List<Empresa> listarEmpresasPorCliente(@PathParam("id") int id){
        return this.empresaBO.listarEmpresasPorCliente(id);
    }
    //sí
    @GET
    @Path("listaclienteactivo/{id}")
    public List<Empresa> listarEmpresasPorClienteActivas(@PathParam("id") int id){
        return this.empresaBO.listarEmpresasPorClienteActivas(id);
    }
    @GET
    //sí
    @Path("listaclientenoactivo/{id}")
    public List<Empresa> listarEmpresasPorClienteNoActivas(@PathParam("id") int id){
        return this.empresaBO.listarEmpresasPorClienteNoActivas(id);
    }
    //sí
    @PUT
    @Path("validarEmpresa/{id}")
    public Response validarEmpresa(@PathParam("id") int id){
        boolean resultado = empresaBO.validarEmpresa(id);
        return Response.ok(resultado).build(); // :v     true o false
    }
    
    
//    @POST
//    @Path("crearValidandoCodigoPostal")
//    public Response crearValidandoCodigoPostal(Empresa empresa){
//            if(empresa==null || 
//                    empresa.getCodigoPostal()==null || 
//                    empresa.getDepartamento()==null || empresa.getDepartamento().isBlank() ||
//                    empresa.getDireccionFiscal()==null || empresa.getDireccionFiscal().isBlank()||
//                    empresa.getDistrito()==null || empresa.getDistrito().isBlank()||
//                    empresa.getEmail()==null || empresa.getEmail().isBlank()||
//                    empresa.getProvincia()==null || empresa.getProvincia().isBlank()||
//                    empresa.getRuc()==null || empresa.getRuc().isBlank()||
//                    empresa.getRazonSocial()==null || empresa.getRazonSocial().isBlank()||
//                    empresa.getTelefono()==null || empresa.getTelefono().isBlank()
//                    ){
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity("La empresa no esta creado")
//                    .build();
//            }
//        //faltar guardar
//        this.empresaBO.insertarEmpresaValidandoCodigoPostal(empresa);
//        
//        return Response.status(Response.Status.CREATED)
//                .entity(empresa)
//                .build();
//        //URI location= URI.create("/SoftProgWR/api/v1/empresa/" + empresa.getId());
////        return Response.created(location)
////                .entity(empresa)
////                .build();
//    }    
  //  @Override
//    public List<Empresa> listarEmpresasActivos() {
//        return this.empresaDAO.listarEmpresasActivos();
//    }
//
//    @Override
//    public List<Empresa> listarEmpresasNoActivos() {
//        return this.empresaDAO.listarEmpresasNoActivos();
//    }
//    @Override
//    public List<Empresa> listarEmpresasPorCliente(int id) {
//        return this.empresaDAO.listarEmpresasPorCliente(id);
//    }
//    @Override
//    public List<Empresa> listarEmpresasPorClienteActivas(int id) {
//        return this.empresaDAO.listarEmpresasPorClienteActivas(id);
//    }
//    @Override
//    public List<Empresa> listarEmpresasPorClienteNoActivas(int id) {
//        return this.empresaDAO.listarEmpresasPorClienteNoActivas(id);
//    }
}