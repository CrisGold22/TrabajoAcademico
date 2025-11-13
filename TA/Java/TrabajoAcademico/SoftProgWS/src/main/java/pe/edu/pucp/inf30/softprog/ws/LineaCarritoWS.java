package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarrito;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.LineaCarritoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.venta.LineaCarritoBOImpl;

/**
 *
 * @author chris
 */
@WebService(serviceName = "LineaCarritoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class LineaCarritoWS {

    private final LineaCarritoBO lineaCarritoBO;
    public LineaCarritoWS(){
        this.lineaCarritoBO = new LineaCarritoBOImpl();
    }
    @WebMethod(operationName = "listarLineaCarrito")
    public List<LineaCarrito> listarLineaCarrito() {
        return lineaCarritoBO.listar();
    }
    @WebMethod(operationName = "InsertarLineaCarrito")
    public void InsertarLineaCarrito(@WebParam(name = "lineaCarrito")
            LineaCarrito lineaCarrito)  {
        lineaCarritoBO.insertar(lineaCarrito);
    }
    @WebMethod(operationName = "ActualizarLineaCarrito")
    public void ActualizarLineaCarrito(@WebParam(name = "lineaCarrito")
            LineaCarrito lineaCarrito)  {
        lineaCarritoBO.actualizar(lineaCarrito);
    }
    @WebMethod(operationName = "ObtenerLineaCarrito")
    public LineaCarrito ObtenerLineaCarrito(@WebParam(name = "id")int id)  {
        return lineaCarritoBO.obtener(id);
    }
    @WebMethod(operationName = "EliminarLineaCarrito")
    public void EliminarLineaCarrito(@WebParam(name = "id")int id)  {
        lineaCarritoBO.eliminar(id);
    }
    @WebMethod(operationName = "ListarLineaCarritoPorIdCarrito")
    public List<LineaCarrito> ListarLineaCarritoPorIdCarrito(@WebParam(name = "id")int id) {
        return lineaCarritoBO.listarPorIdCarrito(id);
    }
}
