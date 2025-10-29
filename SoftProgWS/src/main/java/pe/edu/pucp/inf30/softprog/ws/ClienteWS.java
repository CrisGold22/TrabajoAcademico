package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.ClienteBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.ClienteBOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.Cliente;
/**
 *
 * @author chris
 */
@WebService(serviceName = "ClienteWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class ClienteWS {
    private final ClienteBO ClienteBO;
    public ClienteWS(){
        this.ClienteBO = new ClienteBOImpl();
    }
    @WebMethod(operationName = "listarCliente")
    public List<Cliente> listarCliente() throws Exception {
        return ClienteBO.listar();
    }
    
    @WebMethod(operationName = "insertarCliente")
    public void insertarCliente(@WebParam(name = "cliente")Cliente cliente){
        ClienteBO.insertar(cliente);
    }
    
    @WebMethod(operationName = "actualizarCliente")
    public void actualizarCliente(@WebParam(name = "cliente")Cliente cliente)  {
        ClienteBO.actualizar(cliente);
    }
    
    @WebMethod(operationName = "obtenerCliente")
    public Cliente obtenerCliente(@WebParam(name = "id")int id) {
        return ClienteBO.obtener(id);
    }
    
    @WebMethod(operationName = "eEliminarCliente")
    public void eliminarCliente(@WebParam(name = "id")int id)  {
        ClienteBO.eliminar(id);
    }
    
    @WebMethod(operationName = "buscarPorDni")
    public Cliente buscarPorDni(@WebParam(name = "dni")String dni) {
        return ClienteBO.buscarPorDNI(dni);
    }
}
