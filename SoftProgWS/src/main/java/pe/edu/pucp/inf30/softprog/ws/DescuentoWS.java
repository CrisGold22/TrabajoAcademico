package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.producto.Descuento;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.DescuentoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.producto.DescuentoBOImpl;

/**
 *
 * @author chris
 */
@WebService(serviceName = "DescuentoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class DescuentoWS {

    private final DescuentoBO DescuentoBO;
    public DescuentoWS(){
        this.DescuentoBO = new DescuentoBOImpl();
    }
    
    @WebMethod(operationName = "listarDescuento")
    public List<Descuento> listarDescuento() {
        return DescuentoBO.listar();
    }
    @WebMethod(operationName = "insertarDescuento")
    public void insertarDescuento(@WebParam(name = "descuento")
            Descuento descuento)  {
        DescuentoBO.insertar(descuento);
    }
    @WebMethod(operationName = "actualizarDescuento")
    public void actualizarDescuento(@WebParam(name = "descuento")
            Descuento descuento) {
        DescuentoBO.actualizar(descuento);
    }
    @WebMethod(operationName = "obtenerDescuento")
    public Descuento obtenerDescuento(@WebParam(name = "id")int id) {
        return DescuentoBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarDescuento")
    public void eliminarDescuento(@WebParam(name = "id")int id) {
        DescuentoBO.eliminar(id);
    }
}
