
package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.OrdenCompraBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.OrdenCompraBOImpl;

/**
 *
 * @author chris
 */
@WebService(serviceName = "OrdenCompraWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class OrdenCompraWS {
    private final OrdenCompraBO ordenCompraBO;
    public OrdenCompraWS(){
        this.ordenCompraBO = new OrdenCompraBOImpl();
    }
    @WebMethod(operationName = "listarOrdenCompra")
    public List<OrdenCompra> listarOrdenCompra()  {
        return ordenCompraBO.listar();
    }
    @WebMethod(operationName = "insertarOrdenCompra")
    public void insertarOrdenCompra(@WebParam(name = "ordenCompra")
            OrdenCompra ordenCompra) {
        ordenCompraBO.insertar(ordenCompra);
    }
    @WebMethod(operationName = "actualizarOrdenCompra")
    public void actualizarOrdenCompra(@WebParam(name = "ordenCompra")
            OrdenCompra ordenCompra) {
        ordenCompraBO.actualizar(ordenCompra);
    }
    @WebMethod(operationName = "obtenerOrdenCompra")
    public OrdenCompra obtenerOrdenCompra(@WebParam(name = "id")int id)  {
        return ordenCompraBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarOrdenCompra")
    public void eliminarOrdenCompra(@WebParam(name = "id")int id)  {
        ordenCompraBO.eliminar(id);
    }
    
    @WebMethod(operationName = "desactivarOrdenCompra")
    public void desactivarOrdenCompra(@WebParam(name = "id")int id)  {
        ordenCompraBO.desactivarOrdenCompra(id);
    }
}
