
package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.negocio.bo.producto.ProductoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.producto.ProductoBOImpl;

/**
 *
 * @author chris
 */
@WebService(serviceName = "ProductoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class ProductoWS {

    private final ProductoBO ProductoBO;
    public ProductoWS(){
        this.ProductoBO = new ProductoBOImpl();
    }
           
    @WebMethod(operationName = "listarProducto")
    public List<Producto> listarProducto()  {
        return ProductoBO.listar();
    }
    @WebMethod(operationName = "insertarProducto")
    public void insertarProducto(@WebParam(name = "producto")Producto producto)  {
        ProductoBO.insertar(producto);
    }
    @WebMethod(operationName = "actualizarProducto")
    public void actualizarProducto(@WebParam(name = "producto")Producto producto) {
        ProductoBO.actualizar(producto);
    }
    @WebMethod(operationName = "obtenerProducto")
    public Producto obtenerProducto(@WebParam(name = "id")int id) {
        return ProductoBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarProducto")
    public void eliminarProducto(@WebParam(name = "id")int id)  {
        ProductoBO.eliminar(id);
    }
    @WebMethod(operationName = "obtenerPorSku")
    public Producto obtenerPorSku(@WebParam(name = "sku")String sku)  {
        return ProductoBO.obtenerPorSku(sku);
    }
    @WebMethod(operationName = "listarfiltrarProductoPorPrecio")
    public List<Producto> listarfiltrarProductoPorPrecio(@WebParam(name = "id")
            Integer id,@WebParam(name = "rangoPrecio1")double RangoPrecio1,
            @WebParam(name = "rangoPrecio2")double RangoPrecio2)  {
        return ProductoBO.filtrarProductoPorPrecio(id, RangoPrecio1, RangoPrecio2);
    }
    @WebMethod(operationName = "listarfiltrarProductoPorMarca")
    public List<Producto> listarfiltrarProductoPorMarca(@WebParam(name = "id")
            Integer id,@WebParam(name = "RangoPrecio1")String marca)  {
        return ProductoBO.filtrarProductoPorMarca(id, marca);
    }
}
