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
import pe.edu.pucp.inf30.softprog.dao.usuario.AdministradorSistemaDAO;
import pe.edu.pucp.inf30.softprog.dao.usuario.CuentaUsuarioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.usuario.AdministradorSistemaDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.usuario.CuentaUsuarioDAOImpl;
import pe.edu.pucp.inf30.softprogmodelo.usuario.AdministradorSistema;
import pe.edu.pucp.inf30.softprogmodelo.usuario.Cargo;
import pe.edu.pucp.inf30.softprogmodelo.usuario.CuentaUsuario;
import pe.edu.pucp.inf30.softprogmodelo.usuario.Genero;
import pe.edu.pucp.inf30.softprogmodelo.usuario.Jerarquia;
import pe.edu.pucp.inf30.softprog.test.dao.PersistibleProbable;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdministradorSistemaDaoTest implements PersistibleProbable {
    private int testId;
    private int testCuentaUsuarioId;
    private final int idIncorrecto = 99999;
    
    @BeforeAll
    public void inicializar() {
        CuentaUsuarioDAO cuentaUsuarioDao = new CuentaUsuarioDAOImpl();
        CuentaUsuario cuentaUsuario = new CuentaUsuario();
        cuentaUsuario.setUsername("admin_prueba");
        cuentaUsuario.setPassword("admin123");
        
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
        AdministradorSistemaDAO administradorDao = new AdministradorSistemaDAOImpl();
        AdministradorSistema administrador = new AdministradorSistema();
        
        // Datos de Persona (herencia)
        administrador.setDni("12345678");
        administrador.setNombre("Carlos Alberto");
        administrador.setApellidoPaterno("Rodríguez");
        administrador.setApellidoMaterno("Silva");
        administrador.setGenero(Genero.HOMBRE);
        administrador.setFechaNacimiento(new GregorianCalendar(1980, 
                Calendar.JANUARY, 10).getTime());
        administrador.setTelefono(987654321);
        
        // Datos específicos de AdministradorSistema
        administrador.setCargo(Cargo.ADMINISTRADOR);
        administrador.setSueldo(3500.00);
        administrador.setRango(Jerarquia.COMPLETO);
        administrador.setCuentaUsuario(new CuentaUsuarioDAOImpl().leer(this.testCuentaUsuarioId));
        administrador.setActivo(true);
        
        this.testId = administradorDao.crear(administrador);
        assertTrue(this.testId > 0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeActualizarSiIdExiste() {
        AdministradorSistemaDAO administradorDao = new AdministradorSistemaDAOImpl();
        AdministradorSistema administrador = new AdministradorSistema();
        
        administrador.setId(this.testId);
        
        // Datos de Persona modificados
        administrador.setDni("87654321");
        administrador.setNombre("Ana María");
        administrador.setApellidoPaterno("González");
        administrador.setApellidoMaterno("Torres");
        administrador.setGenero(Genero.MUJER);
        administrador.setFechaNacimiento(new GregorianCalendar(1985, 
                Calendar.JUNE, 25).getTime());
        administrador.setTelefono(123456789);
        
        // Datos específicos de AdministradorSistema modificados
        administrador.setCargo(Cargo.GESTOR_PRODUCTO);
        administrador.setSueldo(4200.00);
        administrador.setRango(Jerarquia.PARCIAL);
        administrador.setCuentaUsuario(new CuentaUsuarioDAOImpl().leer(this.testCuentaUsuarioId));
        administrador.setActivo(false);
        
        boolean modifico = administradorDao.actualizar(administrador);
        assertTrue(modifico);
        
        AdministradorSistema administradorModificado = administradorDao.leer(this.testId);
        assertEquals("87654321", administradorModificado.getDni());
        assertEquals("Ana María", administradorModificado.getNombre());
        assertEquals("González", administradorModificado.getApellidoPaterno());
        assertEquals("Torres", administradorModificado.getApellidoMaterno());
        assertEquals(Genero.MUJER, administradorModificado.getGenero());
        assertEquals(new GregorianCalendar(1985, 
                Calendar.JUNE, 25).getTime(), administradorModificado.getFechaNacimiento());
        assertEquals(123456789, administradorModificado.getTelefono());
        assertEquals(Cargo.GESTOR_PRODUCTO, administradorModificado.getCargo());
        assertEquals(4200.00, administradorModificado.getSueldo());
        assertEquals(Jerarquia.PARCIAL, administradorModificado.getRango());
        assertFalse(administradorModificado.isActivo());
        assertNotNull(administradorModificado.getCuentaUsuario());
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeActualizarSiIdNoExiste() {
        AdministradorSistemaDAO administradorDao = new AdministradorSistemaDAOImpl();
        AdministradorSistema administrador = new AdministradorSistema();
        
        administrador.setId(this.idIncorrecto);
        administrador.setDni("99999999");
        administrador.setNombre("Admin Inexistente");
        administrador.setApellidoPaterno("Apellido");
        administrador.setApellidoMaterno("Materno");
        administrador.setGenero(Genero.NO_ESPECIFICADO);
        administrador.setFechaNacimiento(new GregorianCalendar(1990, 
                Calendar.DECEMBER, 31).getTime());
        administrador.setTelefono(999999999);
        administrador.setCargo(Cargo.GESTOR_PEDIDOS);
        administrador.setSueldo(5000.00);
        administrador.setRango(Jerarquia.COMPLETO);
        administrador.setCuentaUsuario(new CuentaUsuarioDAOImpl().leer(this.testCuentaUsuarioId));
        administrador.setActivo(true);
        
        boolean modifico = administradorDao.actualizar(administrador);
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        AdministradorSistemaDAO administradorDao = new AdministradorSistemaDAOImpl();
        boolean elimino = administradorDao.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }
    
    @Test
    @Order(5)
    @Override
    public void debeLeerSiIdExiste() {
        AdministradorSistemaDAO administradorDao = new AdministradorSistemaDAOImpl();
        AdministradorSistema administrador = administradorDao.leer(this.testId);
        assertNotNull(administrador);
        assertEquals(this.testId, administrador.getId());
        assertNotNull(administrador.getDni());
        assertNotNull(administrador.getNombre());
        assertNotNull(administrador.getCargo());
        assertNotNull(administrador.getRango());
        assertNotNull(administrador.getCuentaUsuario());
        assertTrue(administrador.getSueldo() > 0);
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeLeerSiIdNoExiste() {
        AdministradorSistemaDAO administradorDao = new AdministradorSistemaDAOImpl();
        AdministradorSistema administrador = administradorDao.leer(this.idIncorrecto);
        assertNull(administrador);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeLeerTodos() {
        AdministradorSistemaDAO administradorDao = new AdministradorSistemaDAOImpl();
        List<AdministradorSistema> administradores = administradorDao.leerTodos();
        
        assertNotNull(administradores);
        assertFalse(administradores.isEmpty());
        
        // Verificar que nuestro administrador de prueba está en la lista
        boolean encontrado = administradores.stream()
                .anyMatch(a -> a.getId() == this.testId);
        assertTrue(encontrado);
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        AdministradorSistemaDAO administradorDao = new AdministradorSistemaDAOImpl();
        boolean elimino = administradorDao.eliminar(this.testId);
        assertTrue(elimino);
        
        // Verificar que el administrador ya no existe
        AdministradorSistema administradorEliminado = administradorDao.leer(this.testId);
        assertNull(administradorEliminado);
    }
}