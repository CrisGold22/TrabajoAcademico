package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.producto.CategoriaProducto;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.CategoriaProductoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.producto.CategoriaProductoBOImpl;

/**
 *
 * @author chris
 */
@WebService(serviceName = "CategoriaProductoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class CategoriaProductoWS {

    private final CategoriaProductoBO CategoriaProductoBO;

    public CategoriaProductoWS() {
        this.CategoriaProductoBO = new CategoriaProductoBOImpl();
    }
    
    @WebMethod(operationName = "listarCategoriaProducto")
    public List<CategoriaProducto> listarCategoriaProducto()  {
        return CategoriaProductoBO.listar();
    }
    @WebMethod(operationName = "insertarCategoriaProducto")
    public void insertarCategoriaProducto(@WebParam(name = "categoriaProducto")
            CategoriaProducto categoriaProducto)  {
        CategoriaProductoBO.insertar(categoriaProducto);
    }
    @WebMethod(operationName = "actualizarCategoriaProducto")
    public void actualizarCategoriaProducto(@WebParam(name = "categoriaProducto")
            CategoriaProducto categoriaProducto)  {
        CategoriaProductoBO.actualizar(categoriaProducto);
    }
    @WebMethod(operationName = "obtenerCategoriaProducto")
    public CategoriaProducto obtenerCategoriaProducto(@WebParam(name = "id")
            int id) {
        return CategoriaProductoBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarCategoriaProducto")
    public void eliminarCategoriaProducto(@WebParam(name = "id")int id)  {
        CategoriaProductoBO.eliminar(id);
    }
}