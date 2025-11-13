package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.DetalleEnvioBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.DetalleEnvioBOImpl;
import pe.edu.pucp.inf30.softprog.modelo.venta.DetalleEnvio;

/**
 *
 * @author chris
 */
@WebService(serviceName = "DetalleEnvioWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class DetalleEnvioWS {
    private final DetalleEnvioBO detalleEnvioBO;
    public DetalleEnvioWS(){
        this.detalleEnvioBO = new DetalleEnvioBOImpl();
    }

    @WebMethod(operationName = "listarDetalleEnvio")
    public List<DetalleEnvio> listarDetalleEnvio() {
        return detalleEnvioBO.listar();
    }
    @WebMethod(operationName = "insertarDetalleEnvio")
    public void insertarDetalleEnvio(@WebParam(name = "detalleEnvio")
            DetalleEnvio detalleEnvio) {
        detalleEnvioBO.insertar(detalleEnvio);
    }
    @WebMethod(operationName = "actualizarDetalleEnvio")
    public void actualizarDetalleEnvio(@WebParam(name = "detalleEnvio")
            DetalleEnvio detalleEnvio)  {
        detalleEnvioBO.actualizar(detalleEnvio);
    }
    @WebMethod(operationName = "obtenerDetalleEnvio")
    public DetalleEnvio obtenerDetalleEnvio(@WebParam(name = "id")int id) {
        return detalleEnvioBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarDetalleEnvio")
    public void eliminarDetalleEnvio(@WebParam(name = "id")int id)  {
        detalleEnvioBO.eliminar(id);
    }
}
