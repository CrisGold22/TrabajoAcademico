package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.AdministradorSistemaBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.AdministradorSistemaBOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.AdministradorSistema;

/**
 *
 * @author chris
 */
@WebService(serviceName = "AdministradorSistemaWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class AdministradorSistemaWS {
    private final AdministradorSistemaBO administradorSistemaBO;
    public AdministradorSistemaWS(){
        this.administradorSistemaBO = new AdministradorSistemaBOImpl();
    }
    @WebMethod(operationName = "listarTodosAdminstradores")
    public List<AdministradorSistema>listarTodosAdminstradores(){
        return administradorSistemaBO.listar();
    }
    @WebMethod(operationName = "insertarAdministrador")
    public void insertarAdministrador(@WebParam(name = "administradorsistema")
            AdministradorSistema administradorsistema) {
        administradorSistemaBO.insertar(administradorsistema);
    }
    @WebMethod(operationName = "actualizarAdministradorPorId")
    public void actualizarAdministradorPorId(@WebParam(name = "admistradorsistema") 
            AdministradorSistema admistradorsistema)  {
        administradorSistemaBO.actualizar(admistradorsistema);
    }
    @WebMethod(operationName = "obtenerAdministradorPorId")
    public AdministradorSistema obtenerAdministradorPorId(@WebParam(name = "id") 
            int id){
        return administradorSistemaBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarAdministradorPorId")
    public void eliminarAdministradorPorId(@WebParam(name = "id") int id){
        administradorSistemaBO.eliminar(id);
    }
       
}
