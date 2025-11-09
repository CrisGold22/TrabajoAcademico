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
    private final AdministradorSistemaBO AdministradorSistemaBO;
    public AdministradorSistemaWS(){
        this.AdministradorSistemaBO = new AdministradorSistemaBOImpl();
    }
    @WebMethod(operationName = "listarTodosAdminstradores")
    public List<AdministradorSistema>listarTodosAdminstradores(){
        return AdministradorSistemaBO.listar();
    }
    @WebMethod(operationName = "insertarAdministrador")
    public void insertarAdministrador(@WebParam(name = "administradorsistema")
            AdministradorSistema administradorsistema) {
        AdministradorSistemaBO.insertar(administradorsistema);
    }
    @WebMethod(operationName = "actualizarAdministradorPorId")
    public void actualizarAdministradorPorId(@WebParam(name = "admistradorsistema") 
            AdministradorSistema admistradorsistema)  {
        AdministradorSistemaBO.actualizar(admistradorsistema);
    }
    @WebMethod(operationName = "obtenerAdministradorPorId")
    public AdministradorSistema obtenerAdministradorPorId(@WebParam(name = "id") 
            int id){
        return AdministradorSistemaBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarAdministradorPorId")
    public void eliminarAdministradorPorId(@WebParam(name = "id") int id){
        AdministradorSistemaBO.eliminar(id);
    }
       
}
