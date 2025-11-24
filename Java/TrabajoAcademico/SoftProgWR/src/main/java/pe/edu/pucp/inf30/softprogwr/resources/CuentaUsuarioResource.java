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
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.LoginRequest;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.CuentaUsuarioBO;
//import pe.edu.pucp.inf30.softprog.negocio.bo.venta.OrdenCompraBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.CuentaUsuarioBOImpl;
//import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.OrdenCompraBOImpl;

/**
 *
 * @author BRI
 */
@Path("cuentas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CuentaUsuarioResource {

    private final CuentaUsuarioBO cuentaUsuarioBO;
    // private final OrdenCompraBO ordenCompraBO;
    //private final Orden ordenVentaBO;

    public CuentaUsuarioResource() {
        this.cuentaUsuarioBO = new CuentaUsuarioBOImpl();
        //       this.ordenCompraBO=new OrdenCompraBOImpl();
    }

    @GET //es un select
    public List<CuentaUsuario> listar() {
        return this.cuentaUsuarioBO.listar();
    }

    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id") int id) {
        CuentaUsuario cuenta = this.cuentaUsuarioBO.obtener(id);
        if (cuenta == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("Error", "cuenta: " + id + ", no encontrada"))
                    .build();
        }
        return Response.ok(cuenta).build();
    }

    @POST
    public Response crear(CuentaUsuario cuenta) {
        if (cuenta == null || cuenta.getUsername() == null
                || cuenta.getUsername().isBlank()
                || cuenta.getPassword() == null
                || cuenta.getPassword().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La cuenta no es valida")
                    .build();
        }
        //falta corregir
        this.cuentaUsuarioBO.insertar(cuenta);
        URI location
                = URI.create("/SoftProgWR/api/v1/cuenta/" + cuenta.getId());

        return Response.created(location)
                .entity(cuenta)
                .build();
    }

    //SÍ
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id") int id, CuentaUsuario cuenta) {
        if (cuenta == null || cuenta.getUsername() == null
                || cuenta.getUsername().isBlank()
                || cuenta.getPassword() == null
                || cuenta.getPassword().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La cuenta no es valida")
                    .build();
        }

        if (this.cuentaUsuarioBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cuenta: " + id + ", no encontrada")
                    .build();
        }

        this.cuentaUsuarioBO.actualizar(cuenta);

        return Response.ok(cuenta).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (this.cuentaUsuarioBO.obtener(id) == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cuenta: " + id + ", no encontrada")
                    .build();
        }
        this.cuentaUsuarioBO.eliminar(id);

        return Response.noContent().build();
    }

    //Sí
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request) {
        if (request == null
                || request.getEmail()== null || request.getEmail().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Usuario o contraseña vacíos")
                    .build();
        }

        try {
            CuentaUsuario cuenta = cuentaUsuarioBO.login(
                    request.getEmail(),
                    request.getPassword()
            );

            if (cuenta == null) {
                // Usuario no existe o contraseña incorrecta
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Credenciales inválidas")
                        .build();
            }

            // No devolver el hash al front
            cuenta.setPassword(null);

            return Response.ok(cuenta).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error en el servidor: " + ex.getMessage())
                    .build();
        }
    }

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
