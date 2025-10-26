package pe.edu.pucp.inf30.softprog.test.dao.inventario;

import java.util.List;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;


import pe.edu.pucp.inf30.softprog.dao.producto.ProductoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.producto.ProductoDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;
import pe.edu.pucp.inf30.softprog.test.dao.PersistibleProbable;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class ProductoDaoTest implements PersistibleProbable {
//    private int testId;
//    private final int idIncorrecto = 99999;
//   
//    @Test
//    @Order(1)
//    @Override
//    public void debeCrear() {
//        ProductoDAO productoDao = new ProductoDAOImpl();
//        Producto producto = new Producto();
//        producto.setNombre("Producto de Prueba");
//        producto.setDescripcion("Descripcion de prueba para el producto");
//        producto.setPrecioUnitario(99.99);
//        producto.setStockDisponible(50);
//        producto.setStockMinimo(10);
//        producto.setStockMaximo(100);
//        producto.setActivo(true);
//        
//        this.testId = productoDao.crear(producto);
//        assertTrue(this.testId > 0);
//    }
//    
//    @Test
//    @Order(2)
//    @Override
//    public void debeActualizarSiIdExiste() {
//        ProductoDAO productoDao = new ProductoDAOImpl();
//        Producto producto = new Producto();
//        producto.setId(this.testId);
//        producto.setNombre("Producto de Prueba Modificado");
//        producto.setDescripcion("Descripcion modificada del producto de prueba");
//        producto.setPrecioUnitario(149.99);
//        producto.setStockDisponible(25);
//        producto.setStockMinimo(5);
//        producto.setStockMaximo(75);
//        producto.setActivo(false);
//        
//        boolean modifico = productoDao.actualizar(producto);
//        assertTrue(modifico);
//        
//        Producto productoModificado = productoDao.leer(this.testId);
//        assertEquals("Producto de Prueba Modificado", productoModificado.getNombre());
//        assertEquals("Descripcion modificada del producto de prueba", productoModificado.getDescripcion());
//        assertEquals(149.99, productoModificado.getPrecioUnitario());
//        assertEquals(25, productoModificado.getStockDisponible());
//        assertEquals(5, productoModificado.getStockMinimo());
//        assertEquals(75, productoModificado.getStockMaximo());
//        assertFalse(productoModificado.isActivo());
//    }
//    
//    @Test
//    @Order(3)
//    @Override
//    public void noDebeActualizarSiIdNoExiste() {
//        ProductoDAO productoDao = new ProductoDAOImpl();
//        Producto producto = new Producto();
//        producto.setId(this.idIncorrecto);
//        producto.setNombre("Producto Inexistente");
//        producto.setDescripcion("Este producto no debería actualizarse");
//        producto.setPrecioUnitario(999.99);
//        producto.setStockDisponible(0);
//        producto.setStockMinimo(0);
//        producto.setStockMaximo(0);
//        producto.setActivo(true);
//        
//        boolean modifico = productoDao.actualizar(producto);
//        assertFalse(modifico);
//    }
//    
//    @Test
//    @Order(4)
//    @Override
//    public void noDebeEliminarSiIdNoExiste() {
//        ProductoDAO productoDao = new ProductoDAOImpl();
//        boolean elimino = productoDao.eliminar(this.idIncorrecto);
//        assertFalse(elimino);
//    }
//    
//    @Test
//    @Order(5)
//    @Override
//    public void debeLeerSiIdExiste() {
//        ProductoDAO productoDao = new ProductoDAOImpl();
//        Producto producto = productoDao.leer(this.testId);
//        assertNotNull(producto);
//        assertEquals(this.testId, producto.getId());
//        assertNotNull(producto.getNombre());
//        assertTrue(producto.getPrecioUnitario() > 0);
//        assertTrue(producto.getStockDisponible() >= 0);
//    }
//    
//    @Test
//    @Order(6)
//    @Override
//    public void noDebeLeerSiIdNoExiste() {
//        ProductoDAO productoDao = new ProductoDAOImpl();
//        Producto producto = productoDao.leer(this.idIncorrecto);
//        assertNull(producto);
//    }
//    
//    @Test
//    @Order(7)
//    @Override
//    public void debeLeerTodos() {
//        ProductoDAO productoDao = new ProductoDAOImpl();
//        List<ProductoDTO> productos = productoDao.leerTodos();
//        
//        assertNotNull(productos);
//        assertFalse(productos.isEmpty());
//        
//        // Verificar que nuestro producto de prueba está en la lista
//        boolean encontrado = productos.stream()
//                .anyMatch(p -> p.getId() == this.testId);
//        assertTrue(encontrado);
//    }
//    
//    @Test
//    @Order(8)
//    @Override
//    public void debeEliminarSiIdExiste() {
//        ProductoDAO productoDao = new ProductoDAOImpl();
//        boolean elimino = productoDao.eliminar(this.testId);
//        assertTrue(elimino);
//        
//        // Verificar que el producto ya no existe
//        Producto productoEliminado = productoDao.leer(this.testId);
//        assertNull(productoEliminado);
//    }
//}