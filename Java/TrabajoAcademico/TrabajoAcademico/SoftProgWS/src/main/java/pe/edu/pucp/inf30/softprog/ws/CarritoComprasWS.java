package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoCompras;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.CarritoComprasBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.CarritoComprasBOImpl;

/**
 *
 * @author chris
 */
@WebService(serviceName = "CarritoComprasWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class CarritoComprasWS {
    private final CarritoComprasBO carritoComprasBO;
    public CarritoComprasWS(){
        this.carritoComprasBO = new CarritoComprasBOImpl();
    }
    
    @WebMethod(operationName = "listarTodosCarritoCompras")
    public List<CarritoCompras>listarTodosCarritoCompras() {
        return carritoComprasBO.listar();
    }
    
    @WebMethod(operationName = "insertarCarritoCompras")
    public void insertarCarritoCompras(@WebParam(name = "carritoCompras") 
            CarritoCompras carritoCompras)  {
        carritoComprasBO.insertar(carritoCompras);
    }
    
    @WebMethod(operationName = "actualizarCarritoCompras")
    public void actualizarCarritoCompras(@WebParam(name = "carritoCompras") 
            CarritoCompras carritoCompras) {
        carritoComprasBO.listar();
    }
    @WebMethod(operationName = "obtenerCarritoCompras")
    public CarritoCompras obtenerCarritoCompras(@WebParam(name = "id") 
            int id){
        return carritoComprasBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarCarritoCompras")
    public void eliminarCarritoCompras(@WebParam(name = "id") 
            int id) {
        carritoComprasBO.eliminar(id);
    }
    @WebMethod(operationName = "obtenerCarritoComprasPorCliente")
    public CarritoCompras obtenerCarritoComprasPorCliente(@WebParam(name = "id") 
            int id){
        return carritoComprasBO.obtenerPorCliente(id);
    }
}