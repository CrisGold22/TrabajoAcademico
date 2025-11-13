
package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.LineaComprobantePagoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.pago.LineaComprobantePagoBOImpl;
import pe.edu.pucp.inf30.softprog.modelo.pago.LineaComprobantePago;
/**
 *
 * @author chris
 */
@WebService(serviceName = "LineaComprobantePagoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class LineaComprobantePagoWS {
    private final LineaComprobantePagoBO lineaComprobantePagoBO;
    public LineaComprobantePagoWS(){
        this.lineaComprobantePagoBO = new LineaComprobantePagoBOImpl();
    }
    @WebMethod(operationName = "listarLineaComprobantePago")
    public List<LineaComprobantePago> listarCuentaUsuario()  {
        return lineaComprobantePagoBO.listar();
    }
    @WebMethod(operationName = "insertarLineaComprobantePago")
    public void insertarLineaComprobantePago(@WebParam(name = "lineaComprobantePago")
            LineaComprobantePago lineaComprobantePago) {
        lineaComprobantePagoBO.insertar(lineaComprobantePago);
    }
    @WebMethod(operationName = "actualizarLineaComprobantePago")
    public void actualizarLineaComprobantePago(@WebParam(name = "lineaComprobantePago")
            LineaComprobantePago lineaComprobantePago)  {
        lineaComprobantePagoBO.actualizar(lineaComprobantePago);
    }
    @WebMethod(operationName = "obtenerLineaComprobantePago")
    public LineaComprobantePago obtenerLineaComprobantePago(@WebParam(name = "id")int id)  {
        return lineaComprobantePagoBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarLineaComprobantePago")
    public void eliminarLineaComprobantePago(@WebParam(name = "id")int id) {
        lineaComprobantePagoBO.eliminar(id);
    }
  
}
