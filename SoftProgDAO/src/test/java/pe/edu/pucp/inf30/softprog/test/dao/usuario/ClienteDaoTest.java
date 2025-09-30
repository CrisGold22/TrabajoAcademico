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


import pe.edu.pucp.inf30.softprog.dao.persona.ClienteDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.ClienteDAOImpl;
import pe.edu.pucp.inf30.softprog.dao.persona.CuentaUsuarioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.CuentaUsuarioDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.CategoriaCliente;
import pe.edu.pucp.inf30.softprog.modelo.persona.ClienteDTO;
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuarioDTO;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Genero;

import pe.edu.pucp.inf30.softprog.test.dao.PersistibleProbable;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class ClienteDaoTest implements PersistibleProbable {
//    private int testId;
//    private int testCuentaUsuarioId;
//    private final int idIncorrecto = 99999;
//    
//    @BeforeAll
//    public void inicializar() {
//        CuentaUsuarioDAO cuentaUsuarioDao = new CuentaUsuarioDAOImpl();
//        CuentaUsuarioDTO cuentaUsuario = new CuentaUsuarioDTO();
//        cuentaUsuario.setUsername("cliente_prueba");
//        cuentaUsuario.setPassword("password123");
//        
//        this.testCuentaUsuarioId = cuentaUsuarioDao.crear(cuentaUsuario);
//    }
//    
//    @AfterAll
//    public void limpiar() {
//        CuentaUsuarioDAOImpl cuentaUsuarioDao = new CuentaUsuarioDAOImpl();
//        cuentaUsuarioDao.eliminar(testCuentaUsuarioId);
//    }
//    
//    @Test
//    @Order(1)
//    @Override
//    public void debeCrear() {
//        ClienteDAO clienteDao = new ClienteDAOImpl();
//        ClienteDTO cliente = new ClienteDTO();
//        
//        // Datos de Persona (herencia)
//        cliente.setDni("12345678");
//        cliente.setNombre("Juan Carlos");
//        cliente.setApellidoPaterno("Pérez");
//        cliente.setApellidoMaterno("García");
//        cliente.setGenero(Genero.HOMBRE);
//        cliente.setFechaNacimiento(new GregorianCalendar(1985, 
//                Calendar.MARCH, 15).getTime());
//        cliente.setTelefono(987654321);
//        
//        // Datos específicos de Cliente
//        cliente.setLineaCredito(5000.00);
//        cliente.setCategoria(CategoriaCliente.DISTRIBUIDOR);
//        cliente.setCuentaUsuario(new CuentaUsuarioDAOImpl().leer(this.testCuentaUsuarioId));
//        cliente.setActivo(true);
//        
//        this.testId = clienteDao.crear(cliente);
//        assertTrue(this.testId > 0);
//    }
//    
//    @Test
//    @Order(2)
//    @Override
//    public void debeActualizarSiIdExiste() {
//        ClienteDAO clienteDao = new ClienteDAOImpl();
//        ClienteDTO cliente = new ClienteDTO();
//        
//        cliente.setId(this.testId);
//        
//        // Datos de Persona modificados
//        cliente.setDni("87654321");
//        cliente.setNombre("María Elena");
//        cliente.setApellidoPaterno("López");
//        cliente.setApellidoMaterno("Martínez");
//        cliente.setGenero(Genero.MUJER);
//        cliente.setFechaNacimiento(new GregorianCalendar(1990, 
//                Calendar.JULY, 20).getTime());
//        cliente.setTelefono(123456789);
//        
//        // Datos específicos de Cliente modificados
//        cliente.setLineaCredito(7500.00);
//        cliente.setCategoria(CategoriaCliente.RESTAURANTE);
//        cliente.setCuentaUsuario(new CuentaUsuarioDAOImpl().leer(this.testCuentaUsuarioId));
//        cliente.setActivo(false);
//        
//        boolean modifico = clienteDao.actualizar(cliente);
//        assertTrue(modifico);
//        
//        ClienteDTO clienteModificado = clienteDao.leer(this.testId);
//        assertEquals("87654321", clienteModificado.getDni());
//        assertEquals("María Elena", clienteModificado.getNombre());
//        assertEquals("López", clienteModificado.getApellidoPaterno());
//        assertEquals("Martínez", clienteModificado.getApellidoMaterno());
//        assertEquals(Genero.MUJER, clienteModificado.getGenero());
//        assertEquals(new GregorianCalendar(1990, 
//                Calendar.JULY, 20).getTime(), clienteModificado.getFechaNacimiento());
//        assertEquals(123456789, clienteModificado.getTelefono());
//        assertEquals(7500.00, clienteModificado.getLineaCredito());
//        assertEquals(CategoriaCliente.RESTAURANTE, clienteModificado.getCategoria());
//        assertFalse(clienteModificado.isActivo());
//        assertNotNull(clienteModificado.getCuentaUsuario());
//    }
//    
//    @Test
//    @Order(3)
//    @Override
//    public void noDebeActualizarSiIdNoExiste() {
//        ClienteDAO clienteDao = new ClienteDAOImpl();
//        ClienteDTO cliente = new ClienteDTO();
//        
//        cliente.setId(this.idIncorrecto);
//        cliente.setDni("99999999");
//        cliente.setNombre("Cliente Inexistente");
//        cliente.setApellidoPaterno("Apellido");
//        cliente.setApellidoMaterno("Materno");
//        cliente.setGenero(Genero.NO_ESPECIFICADO);
//        cliente.setFechaNacimiento(new GregorianCalendar(2000, 
//                Calendar.JANUARY, 1).getTime());
//        cliente.setTelefono(999999999);
//        cliente.setLineaCredito(0.00);
//        cliente.setCategoria(CategoriaCliente.GOBIERNO);
//        cliente.setCuentaUsuario(new CuentaUsuarioDAOImpl().leer(this.testCuentaUsuarioId));
//        cliente.setActivo(true);
//        
//        boolean modifico = clienteDao.actualizar(cliente);
//        assertFalse(modifico);
//    }
//    
//    @Test
//    @Order(4)
//    @Override
//    public void noDebeEliminarSiIdNoExiste() {
//        ClienteDAO clienteDao = new ClienteDAOImpl();
//        boolean elimino = clienteDao.eliminar(this.idIncorrecto);
//        assertFalse(elimino);
//    }
//    
//    @Test
//    @Order(5)
//    @Override
//    public void debeLeerSiIdExiste() {
//        ClienteDAO clienteDao = new ClienteDAOImpl();
//        ClienteDTO cliente = clienteDao.leer(this.testId);
//        assertNotNull(cliente);
//        assertEquals(this.testId, cliente.getId());
//        assertNotNull(cliente.getDni());
//        assertNotNull(cliente.getNombre());
//        assertNotNull(cliente.getCategoria());
//        assertNotNull(cliente.getCuentaUsuario());
//    }
//    
//    @Test
//    @Order(6)
//    @Override
//    public void noDebeLeerSiIdNoExiste() {
//        ClienteDAO clienteDao = new ClienteDAOImpl();
//        ClienteDTO cliente = clienteDao.leer(this.idIncorrecto);
//        assertNull(cliente);
//    }
//    
//    @Test
//    @Order(7)
//    @Override
//    public void debeLeerTodos() {
//        ClienteDAO clienteDao = new ClienteDAOImpl();
//        List<ClienteDTO> clientes = clienteDao.leerTodos();
//        
//        assertNotNull(clientes);
//        assertFalse(clientes.isEmpty());
//        
//        // Verificar que nuestro cliente de prueba está en la lista
//        boolean encontrado = clientes.stream()
//                .anyMatch(c -> c.getId() == this.testId);
//        assertTrue(encontrado);
//    }
//    
//    @Test
//    @Order(8)
//    @Override
//    public void debeEliminarSiIdExiste() {
//        ClienteDAO clienteDao = new ClienteDAOImpl();
//        boolean elimino = clienteDao.eliminar(this.testId);
//        assertTrue(elimino);
//        
//        // Verificar que el cliente ya no existe
//        ClienteDTO clienteEliminado = clienteDao.leer(this.testId);
//        assertNull(clienteEliminado);
//    }
//}