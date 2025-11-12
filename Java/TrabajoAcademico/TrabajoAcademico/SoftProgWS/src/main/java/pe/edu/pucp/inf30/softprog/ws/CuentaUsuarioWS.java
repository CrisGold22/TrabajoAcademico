package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.CuentaUsuarioBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.CuentaUsuarioBOImpl;

/**
 *
 * @author chris
 */
@WebService(serviceName = "CuentaUsuarioWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class CuentaUsuarioWS {
    private final CuentaUsuarioBO cuentaUsuarioBO;
    public CuentaUsuarioWS(){
        this.cuentaUsuarioBO = new CuentaUsuarioBOImpl();
    }
    
    @WebMethod(operationName = "listarCuentaUsuario")
    public List<CuentaUsuario> listarCuentaUsuario(){
        return cuentaUsuarioBO.listar();
    }
    
    @WebMethod(operationName = "insertarCuentaUsuario")
    public void insertarCuentaUsuario(@WebParam(name = "cuentaUsuario")
                                        CuentaUsuario cuentaUsuario){
        cuentaUsuarioBO.insertar(cuentaUsuario);
    }
    
    @WebMethod(operationName = "actualizarCuentaUsuario")
    public void actualizarCuentaUsuario(@WebParam(name = "cuentaUsuario")
                                        CuentaUsuario cuentaUsuario){
        cuentaUsuarioBO.actualizar(cuentaUsuario);
    }
    
    @WebMethod(operationName = "obtenerCuentaUsuario")
    public CuentaUsuario obtenerCuentaUsuario(@WebParam(name = "id")int id) {
        return cuentaUsuarioBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarCuentaUsuario")
    public void eliminarCuentaUsuario(@WebParam(name = "id")int id){
        cuentaUsuarioBO.eliminar(id);
    }
    @WebMethod(operationName = "login")
    public boolean login(
            @WebParam(name = "email")String email,
            @WebParam(name = "password")String password
    ){
        return cuentaUsuarioBO.login(email, password);
    }
}
