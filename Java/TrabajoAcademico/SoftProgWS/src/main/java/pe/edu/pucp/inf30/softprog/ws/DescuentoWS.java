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

    private final DescuentoBO descuentoBO;
    public DescuentoWS(){
        this.descuentoBO = new DescuentoBOImpl();
    }
    
    @WebMethod(operationName = "listarDescuento")
    public List<Descuento> listarDescuento() {
        return descuentoBO.listar();
    }
    @WebMethod(operationName = "insertarDescuento")
    public void insertarDescuento(@WebParam(name = "descuento")
            Descuento descuento)  {
        descuentoBO.insertar(descuento);
    }
    @WebMethod(operationName = "actualizarDescuento")
    public void actualizarDescuento(@WebParam(name = "descuento")
            Descuento descuento) {
        descuentoBO.actualizar(descuento);
    }
    @WebMethod(operationName = "obtenerDescuento")
    public Descuento obtenerDescuento(@WebParam(name = "id")int id) {
        return descuentoBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarDescuento")
    public void eliminarDescuento(@WebParam(name = "id")int id) {
        descuentoBO.eliminar(id);
    }
    @WebMethod(operationName ="actualizarPrecioDescuentoProducto")
    public void actualizarPrecioDescuentoProducto(@WebParam(name="id") int id,@WebParam(name="nuevo_precio") int nuevo_precio){
        descuentoBO.actualizarPrecioDescuentoProducto(id, nuevo_precio);
    }
}
