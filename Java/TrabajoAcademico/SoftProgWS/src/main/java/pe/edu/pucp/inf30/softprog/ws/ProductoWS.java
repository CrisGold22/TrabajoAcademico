
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

    private final ProductoBO productoBO;
    public ProductoWS(){
        this.productoBO = new ProductoBOImpl();
    }
           
    @WebMethod(operationName = "listarProducto")
    public List<Producto> listarProducto()  {
        return productoBO.listar();
    }
    @WebMethod(operationName = "insertarProducto")
    public void insertarProducto(@WebParam(name = "producto")Producto producto)  {
        productoBO.insertar(producto);
    }
    @WebMethod(operationName = "actualizarProducto")
    public void actualizarProducto(@WebParam(name = "producto")Producto producto) {
        productoBO.actualizar(producto);
    }
    @WebMethod(operationName = "obtenerProducto")
    public Producto obtenerProducto(@WebParam(name = "id")int id) {
        return productoBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarProducto")
    public void eliminarProducto(@WebParam(name = "id")int id)  {
        productoBO.eliminar(id);
    }
    @WebMethod(operationName = "obtenerPorSku")
    
    public Producto obtenerPorSku(@WebParam(name = "sku")String sku)  {
        return productoBO.obtenerPorSku(sku);
    }
    @WebMethod(operationName = "listarfiltrarProductoPorPrecio")
    
    public List<Producto> listarfiltrarProductoPorPrecio(@WebParam(name = "id")
            Integer id,@WebParam(name = "rangoPrecio1")double RangoPrecio1,
            @WebParam(name = "rangoPrecio2")double RangoPrecio2)  {
        return productoBO.filtrarProductoPorPrecio(id, RangoPrecio1, RangoPrecio2);
    }
    
    
    @WebMethod(operationName = "listarfiltrarProductoPorMarca")
    public List<Producto> listarfiltrarProductoPorMarca(@WebParam(name = "id")
            Integer id,@WebParam(name = "marca")String marca)  {
        return productoBO.filtrarProductoPorMarca(id, marca);
    }
    
    
    @WebMethod(operationName = "listarfiltrarProductoPorDescuento")
    public List<Producto> filtrarProductoPorDescuento(@WebParam(name = "id")
            Integer id,@WebParam(name = "nombre")String nombre) {
        return productoBO.filtrarProductoPorDescuento(id, nombre);
    }

    @WebMethod(operationName = "verificarStockSuficientePorID")
    public boolean verificarStockSuficientePorID(@WebParam(name = "id")
            Integer id,@WebParam(name = "cantidadSolicitada")Integer cantidadSolicitada) {
        return productoBO.verificarStockSuficientePorID(id, cantidadSolicitada);
    }
    @WebMethod(operationName = "verificarStockSuficientePorSKU")
    public boolean verificarStockSuficientePorSKU(@WebParam(name = "sku")
            String sku,@WebParam(name = "cantidadSolicitada")Integer cantidadSolicitada) {

        return productoBO.verificarStockSuficientePorSKU(sku, cantidadSolicitada);
    }
}
