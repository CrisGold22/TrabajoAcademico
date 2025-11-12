package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.pago.ComprobantePago;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.ComprobantePagoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.pago.ComprobantePagoBOImpl;

/**
 *
 * @author chris
 */
@WebService(serviceName = "ComprobantePagoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class ComprobantePagoWS {
    
    private final ComprobantePagoBO comprobantePagoBO;
    public ComprobantePagoWS(){
        this.comprobantePagoBO = new ComprobantePagoBOImpl();
    }
    @WebMethod(operationName = "listarComprobantePago")
    public List<ComprobantePago> listarComprobantePago()  {
        return comprobantePagoBO.listar();
    }
    @WebMethod(operationName = "insertarComprobantePago")
    public void insertarComprobantePago(@WebParam(name = "comprobantePago")
            ComprobantePago comprobantePago) {
        comprobantePagoBO.insertar(comprobantePago);
    }
    @WebMethod(operationName = "actualizarComprobantePago")
    public void actualizarComprobantePago(@WebParam(name = "comprobantePago")
            ComprobantePago comprobantePago)  {
        comprobantePagoBO.actualizar(comprobantePago);
    }
    @WebMethod(operationName = "obtenerComprobantePago")
    public ComprobantePago obtenerComprobantePago(@WebParam(name = "id")int id){
        return comprobantePagoBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarComprobantePago")
    public void eliminarComprobantePago(@WebParam(name = "id")int id)  {
         comprobantePagoBO.eliminar(id);
    }
}
