
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
    private final OrdenCompraBO OrdenCompraBO;
    public OrdenCompraWS(){
        this.OrdenCompraBO = new OrdenCompraBOImpl();
    }
    @WebMethod(operationName = "listarOrdenCompra")
    public List<OrdenCompra> listarOrdenCompra()  {
        return OrdenCompraBO.listar();
    }
    @WebMethod(operationName = "insertarOrdenCompra")
    public void insertarOrdenCompra(@WebParam(name = "ordenCompra")
            OrdenCompra ordenCompra) {
        OrdenCompraBO.insertar(ordenCompra);
    }
    @WebMethod(operationName = "actualizarOrdenCompra")
    public void actualizarOrdenCompra(@WebParam(name = "ordenCompra")
            OrdenCompra ordenCompra) {
        OrdenCompraBO.actualizar(ordenCompra);
    }
    @WebMethod(operationName = "obtenerOrdenCompra")
    public OrdenCompra obtenerOrdenCompra(@WebParam(name = "id")int id)  {
        return OrdenCompraBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarOrdenCompra")
    public void eliminarOrdenCompra(@WebParam(name = "id")int id)  {
        OrdenCompraBO.eliminar(id);
    }
}
