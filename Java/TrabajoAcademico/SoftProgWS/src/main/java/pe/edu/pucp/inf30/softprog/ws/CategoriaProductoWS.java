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

    private final CategoriaProductoBO categoriaProductoBO;

    public CategoriaProductoWS() {
        this.categoriaProductoBO = new CategoriaProductoBOImpl();
    }
    
    @WebMethod(operationName = "listarCategoriaProducto")
    public List<CategoriaProducto> listarCategoriaProducto()  {
        return categoriaProductoBO.listar();
    }
    @WebMethod(operationName = "insertarCategoriaProducto")
    public void insertarCategoriaProducto(@WebParam(name = "categoriaProducto")
            CategoriaProducto categoriaProducto)  {
        categoriaProductoBO.insertar(categoriaProducto);
    }
    @WebMethod(operationName = "actualizarCategoriaProducto")
    public void actualizarCategoriaProducto(@WebParam(name = "categoriaProducto")
            CategoriaProducto categoriaProducto)  {
        categoriaProductoBO.actualizar(categoriaProducto);
    }
    @WebMethod(operationName = "obtenerCategoriaProducto")
    public CategoriaProducto obtenerCategoriaProducto(@WebParam(name = "id")
            int id) {
        return categoriaProductoBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarCategoriaProducto")
    public void eliminarCategoriaProducto(@WebParam(name = "id")int id)  {
        categoriaProductoBO.eliminar(id);
    }
}