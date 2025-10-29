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
    
    private final ComprobantePagoBO ComprobantePagoBO;
    public ComprobantePagoWS(){
        this.ComprobantePagoBO = new ComprobantePagoBOImpl();
    }
    @WebMethod(operationName = "listarComprobantePago")
    public List<ComprobantePago> listarComprobantePago()  {
        return ComprobantePagoBO.listar();
    }
    @WebMethod(operationName = "insertarComprobantePago")
    public void insertarComprobantePago(@WebParam(name = "comprobantePago")
            ComprobantePago comprobantePago) {
        ComprobantePagoBO.insertar(comprobantePago);
    }
    @WebMethod(operationName = "actualizarComprobantePago")
    public void actualizarComprobantePago(@WebParam(name = "comprobantePago")
            ComprobantePago comprobantePago)  {
        ComprobantePagoBO.actualizar(comprobantePago);
    }
    @WebMethod(operationName = "obtenerComprobantePago")
    public ComprobantePago obtenerComprobantePago(@WebParam(name = "id")int id){
        return ComprobantePagoBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarComprobantePago")
    public void eliminarComprobantePago(@WebParam(name = "id")int id)  {
         ComprobantePagoBO.eliminar(id);
    }
}
