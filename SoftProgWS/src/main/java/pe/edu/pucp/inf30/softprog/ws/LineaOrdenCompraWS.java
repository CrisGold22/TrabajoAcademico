package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.LineaOrdenCompraBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.LineaOrdenCompraBOImpl;

/**
 *
 * @author chris
 */
@WebService(serviceName = "LineaOrdenCompraWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class LineaOrdenCompraWS {

    private final LineaOrdenCompraBO LineaOrdenCompraBO;
    public LineaOrdenCompraWS(){
        this.LineaOrdenCompraBO = new LineaOrdenCompraBOImpl();
    }
    @WebMethod(operationName = "listarLineaOrdenCompra")
    public List<LineaOrdenCompra> listarLineaOrdenCompra() {
        return LineaOrdenCompraBO.listar();
    }
    @WebMethod(operationName = "insertarLineaOrdenCompra")
    public void insertarLineaOrdenCompra(@WebParam(name = "lineaordenCompra")
            LineaOrdenCompra lineaordenCompra)  {
        LineaOrdenCompraBO.insertar(lineaordenCompra);
    }
    @WebMethod(operationName = "actualizarLineaOrdenCompra")
    public void actualizarLineaOrdenCompra(@WebParam(name = "lineaordenCompra")
            LineaOrdenCompra lineaordenCompra)  {
        LineaOrdenCompraBO.actualizar(lineaordenCompra);
    }
    @WebMethod(operationName = "obtenerLineaOrdenCompra")
    public LineaOrdenCompra obtenerLineaOrdenCompra(@WebParam(name = "id")int id)  {
        return LineaOrdenCompraBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarLineaOrdenCompra")
    public void eliminarLineaOrdenCompra(@WebParam(name = "id")int id) {
        LineaOrdenCompraBO.eliminar(id);
    }
}

