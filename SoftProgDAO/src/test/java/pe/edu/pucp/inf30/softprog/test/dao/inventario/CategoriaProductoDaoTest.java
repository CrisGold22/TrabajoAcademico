package pe.edu.pucp.inf30.softprog.test.dao.inventario;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import pe.edu.pucp.inf30.softprog.dao.inventario.CategoriaProductoDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.inventario.CategoriaProductoDAOImpl;
import pe.edu.pucp.inf30.softprogmodelo.inventario.CategoriaProducto;
import pe.edu.pucp.inf30.softprogmodelo.inventario.TipoCategoria;
import pe.edu.pucp.inf30.softprog.test.dao.PersistibleProbable;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaProductoDaoTest implements PersistibleProbable {
    private int testId;
    private final int idIncorrecto = 99999;
    
    @Test
    @Order(1)
    @Override
    public void debeCrear() {
        CategoriaProductoDAO categoriaProductoDao = new CategoriaProductoDAOImpl();
        CategoriaProducto categoriaProducto = new CategoriaProducto();
        categoriaProducto.setNombre(TipoCategoria.ELECTRONICA); // Asumiendo que existe este valor en el enum
        categoriaProducto.setDescripcion("Categoria de productos electronicos para pruebas");
        categoriaProducto.setActivo(true);
        
        this.testId = categoriaProductoDao.crear(categoriaProducto);
        assertTrue(this.testId > 0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeActualizarSiIdExiste() {
        CategoriaProductoDAO categoriaProductoDao = new CategoriaProductoDAOImpl();
        CategoriaProducto categoriaProducto = new CategoriaProducto();
        categoriaProducto.setId(this.testId);
        categoriaProducto.setNombre(TipoCategoria.ROPA); // Asumiendo que existe este valor en el enum
        categoriaProducto.setDescripcion("Categoria de ropa y accesorios modificada para pruebas");
        categoriaProducto.setActivo(false);
        
        boolean modifico = categoriaProductoDao.actualizar(categoriaProducto);
        assertTrue(modifico);
        
        CategoriaProducto categoriaModificada = categoriaProductoDao.leer(this.testId);
        assertEquals(TipoCategoria.ROPA, categoriaModificada.getNombre());
        assertEquals("Categoria de ropa y accesorios modificada para pruebas", categoriaModificada.getDescripcion());
        assertFalse(categoriaModificada.isActivo());
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeActualizarSiIdNoExiste() {
        CategoriaProductoDAO categoriaProductoDao = new CategoriaProductoDAOImpl();
        CategoriaProducto categoriaProducto = new CategoriaProducto();
        categoriaProducto.setId(this.idIncorrecto);
        categoriaProducto.setNombre(TipoCategoria.HOGAR); // Asumiendo que existe este valor en el enum
        categoriaProducto.setDescripcion("Esta categoria no debería actualizarse");
        categoriaProducto.setActivo(true);
        
        boolean modifico = categoriaProductoDao.actualizar(categoriaProducto);
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        CategoriaProductoDAO categoriaProductoDao = new CategoriaProductoDAOImpl();
        boolean elimino = categoriaProductoDao.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }
    
    @Test
    @Order(5)
    @Override
    public void debeLeerSiIdExiste() {
        CategoriaProductoDAO categoriaProductoDao = new CategoriaProductoDAOImpl();
        CategoriaProducto categoriaProducto = categoriaProductoDao.leer(this.testId);
        assertNotNull(categoriaProducto);
        assertEquals(this.testId, categoriaProducto.getId());
        assertNotNull(categoriaProducto.getNombre());
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeLeerSiIdNoExiste() {
        CategoriaProductoDAO categoriaProductoDao = new CategoriaProductoDAOImpl();
        CategoriaProducto categoriaProducto = categoriaProductoDao.leer(this.idIncorrecto);
        assertNull(categoriaProducto);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeLeerTodos() {
        CategoriaProductoDAO categoriaProductoDao = new CategoriaProductoDAOImpl();
        List<CategoriaProducto> categorias = categoriaProductoDao.leerTodos();
        
        assertNotNull(categorias);
        assertFalse(categorias.isEmpty());
        
        // Verificar que nuestra categoria de prueba está en la lista
        boolean encontrado = categorias.stream()
                .anyMatch(c -> c.getId() == this.testId);
        assertTrue(encontrado);
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        CategoriaProductoDAO categoriaProductoDao = new CategoriaProductoDAOImpl();
        boolean elimino = categoriaProductoDao.eliminar(this.testId);
        assertTrue(elimino);
        
        // Verificar que la categoria ya no existe
        CategoriaProducto categoriaEliminada = categoriaProductoDao.leer(this.testId);
        assertNull(categoriaEliminada);
    }
}