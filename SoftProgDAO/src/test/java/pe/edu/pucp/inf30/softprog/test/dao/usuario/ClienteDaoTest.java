package pe.edu.pucp.inf30.softprog.test.dao.usuario;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
import pe.edu.pucp.inf30.softprog.dao.usuario.ClienteDAO;
import pe.edu.pucp.inf30.softprog.dao.usuario.CuentaUsuarioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.usuario.ClienteDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.usuario.CuentaUsuarioDAOImpl;
import pe.edu.pucp.inf30.softprogmodelo.promociones.CategoriaCliente;
import pe.edu.pucp.inf30.softprogmodelo.usuario.Cliente;
import pe.edu.pucp.inf30.softprogmodelo.usuario.CuentaUsuario;
import pe.edu.pucp.inf30.softprogmodelo.usuario.Genero;
import pe.edu.pucp.inf30.softprog.test.dao.PersistibleProbable;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteDaoTest implements PersistibleProbable {
    private int testId;
    private int testCuentaUsuarioId;
    private final int idIncorrecto = 99999;
    
    @BeforeAll
    public void inicializar() {
        CuentaUsuarioDAO cuentaUsuarioDao = new CuentaUsuarioDAOImpl();
        CuentaUsuario cuentaUsuario = new CuentaUsuario();
        cuentaUsuario.setUsername("cliente_prueba");
        cuentaUsuario.setPassword("password123");
        
        this.testCuentaUsuarioId = cuentaUsuarioDao.crear(cuentaUsuario);
    }
    
    @AfterAll
    public void limpiar() {
        CuentaUsuarioDAOImpl cuentaUsuarioDao = new CuentaUsuarioDAOImpl();
        cuentaUsuarioDao.eliminar(testCuentaUsuarioId);
    }
    
    @Test
    @Order(1)
    @Override
    public void debeCrear() {
        ClienteDAO clienteDao = new ClienteDAOImpl();
        Cliente cliente = new Cliente();
        
        // Datos de Persona (herencia)
        cliente.setDni("12345678");
        cliente.setNombre("Juan Carlos");
        cliente.setApellidoPaterno("Pérez");
        cliente.setApellidoMaterno("García");
        cliente.setGenero(Genero.HOMBRE);
        cliente.setFechaNacimiento(new GregorianCalendar(1985, 
                Calendar.MARCH, 15).getTime());
        cliente.setTelefono(987654321);
        
        // Datos específicos de Cliente
        cliente.setLineaCredito(5000.00);
        cliente.setCategoria(CategoriaCliente.DISTRIBUIDOR);
        cliente.setCuenta(new CuentaUsuarioDAOImpl().leer(this.testCuentaUsuarioId));
        cliente.setActivo(true);
        
        this.testId = clienteDao.crear(cliente);
        assertTrue(this.testId > 0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeActualizarSiIdExiste() {
        ClienteDAO clienteDao = new ClienteDAOImpl();
        Cliente cliente = new Cliente();
        
        cliente.setId(this.testId);
        
        // Datos de Persona modificados
        cliente.setDni("87654321");
        cliente.setNombre("María Elena");
        cliente.setApellidoPaterno("López");
        cliente.setApellidoMaterno("Martínez");
        cliente.setGenero(Genero.MUJER);
        cliente.setFechaNacimiento(new GregorianCalendar(1990, 
                Calendar.JULY, 20).getTime());
        cliente.setTelefono(123456789);
        
        // Datos específicos de Cliente modificados
        cliente.setLineaCredito(7500.00);
        cliente.setCategoria(CategoriaCliente.RESTAURANTE);
        cliente.setCuenta(new CuentaUsuarioDAOImpl().leer(this.testCuentaUsuarioId));
        cliente.setActivo(false);
        
        boolean modifico = clienteDao.actualizar(cliente);
        assertTrue(modifico);
        
        Cliente clienteModificado = clienteDao.leer(this.testId);
        assertEquals("87654321", clienteModificado.getDni());
        assertEquals("María Elena", clienteModificado.getNombre());
        assertEquals("López", clienteModificado.getApellidoPaterno());
        assertEquals("Martínez", clienteModificado.getApellidoMaterno());
        assertEquals(Genero.MUJER, clienteModificado.getGenero());
        assertEquals(new GregorianCalendar(1990, 
                Calendar.JULY, 20).getTime(), clienteModificado.getFechaNacimiento());
        assertEquals(123456789, clienteModificado.getTelefono());
        assertEquals(7500.00, clienteModificado.getLineaCredito());
        assertEquals(CategoriaCliente.RESTAURANTE, clienteModificado.getCategoria());
        assertFalse(clienteModificado.isActivo());
        assertNotNull(clienteModificado.getCuenta());
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeActualizarSiIdNoExiste() {
        ClienteDAO clienteDao = new ClienteDAOImpl();
        Cliente cliente = new Cliente();
        
        cliente.setId(this.idIncorrecto);
        cliente.setDni("99999999");
        cliente.setNombre("Cliente Inexistente");
        cliente.setApellidoPaterno("Apellido");
        cliente.setApellidoMaterno("Materno");
        cliente.setGenero(Genero.NO_ESPECIFICADO);
        cliente.setFechaNacimiento(new GregorianCalendar(2000, 
                Calendar.JANUARY, 1).getTime());
        cliente.setTelefono(999999999);
        cliente.setLineaCredito(0.00);
        cliente.setCategoria(CategoriaCliente.GOBIERNO);
        cliente.setCuenta(new CuentaUsuarioDAOImpl().leer(this.testCuentaUsuarioId));
        cliente.setActivo(true);
        
        boolean modifico = clienteDao.actualizar(cliente);
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        ClienteDAO clienteDao = new ClienteDAOImpl();
        boolean elimino = clienteDao.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }
    
    @Test
    @Order(5)
    @Override
    public void debeLeerSiIdExiste() {
        ClienteDAO clienteDao = new ClienteDAOImpl();
        Cliente cliente = clienteDao.leer(this.testId);
        assertNotNull(cliente);
        assertEquals(this.testId, cliente.getId());
        assertNotNull(cliente.getDni());
        assertNotNull(cliente.getNombre());
        assertNotNull(cliente.getCategoria());
        assertNotNull(cliente.getCuenta());
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeLeerSiIdNoExiste() {
        ClienteDAO clienteDao = new ClienteDAOImpl();
        Cliente cliente = clienteDao.leer(this.idIncorrecto);
        assertNull(cliente);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeLeerTodos() {
        ClienteDAO clienteDao = new ClienteDAOImpl();
        List<Cliente> clientes = clienteDao.leerTodos();
        
        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
        
        // Verificar que nuestro cliente de prueba está en la lista
        boolean encontrado = clientes.stream()
                .anyMatch(c -> c.getId() == this.testId);
        assertTrue(encontrado);
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        ClienteDAO clienteDao = new ClienteDAOImpl();
        boolean elimino = clienteDao.eliminar(this.testId);
        assertTrue(elimino);
        
        // Verificar que el cliente ya no existe
        Cliente clienteEliminado = clienteDao.leer(this.testId);
        assertNull(clienteEliminado);
    }
}