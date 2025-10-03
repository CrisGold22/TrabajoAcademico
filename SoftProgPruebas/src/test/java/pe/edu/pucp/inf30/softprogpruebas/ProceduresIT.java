
package pe.edu.pucp.inf30.softprogpruebas;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import pe.edu.pucp.inf30.softprog.dbmanager.DBFactoryProvider;
import pe.edu.pucp.inf30.softprog.dbmanager.DBManager;

/*
 * Pruebas de humo para procedimientos DB2 en el esquema DBRESCOM.
 * Requiere que las tablas y procedimientos ya estén creados en DB2.
 * Variables de entorno admitidas:
 *  - DB_HOST, DB_PORT, DB_USER, DB_PASS, DB_SCHEMA (opcional, default DBRESCOM)
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProceduresIT {

    static DBManager db;
    static String schema = System.getenv().getOrDefault("DB2REDCOM", "DB2REDCOM");
    static int catId = 9999;
    static int prodId = 7777;

    @BeforeAll
    static void open() throws Exception {
        db = DBFactoryProvider.getManager(); // Usa Db2DBManager según configuración del proyecto
        try (Connection c = db.getConnection()) {
            assertNotNull(c);
        }
    }

    @Test
    @Order(1)
    void insertarCategoriaProducto_debeCrear() throws Exception {
        try (Connection c = db.getConnection()) {
            try (CallableStatement cs = c.prepareCall(
                "CALL " + schema + ".insertarCategoriaProducto(?, ?, ?, ?, ?)")) {
                cs.setInt(1, catId);
                cs.setString(2, "TEST-CATEGORIA");
                cs.setString(3, "TEST-DESC");
                cs.setNull(4, Types.INTEGER); // Catalogo_idCatalogo (FK omitida)
                cs.setInt(5, 1); // Activo
                cs.execute();
            }
        }
    }

    @Test
    @Order(2)
    void insertarProducto_debeCrear() throws Exception {
        try (Connection c = db.getConnection()) {
            try (CallableStatement cs = c.prepareCall(
                "CALL " + schema + ".insertarProducto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                int i=1;
                cs.setInt(i++, prodId);
                cs.setString(i++, "PRODUCTO-TEST");
                cs.setString(i++, "SKU-TEST-001");
                cs.setString(i++, "Desc corta");
                cs.setDouble(i++, 10.50);
                cs.setDouble(i++, 9.00);
                cs.setString(i++, "UNIDAD");
                cs.setInt(i++, 100);
                cs.setString(i++, "5");
                cs.setString(i++, "500");
                cs.setInt(i++, 1);
                cs.setInt(i++, catId);
                cs.execute();
            }
        }
    }

    @Test
    @Order(3)
    void buscarProductoPorId_debeRetornarFila() throws Exception {
        try (Connection c = db.getConnection()) {
            try (CallableStatement cs = c.prepareCall(
                "CALL " + schema + ".buscarProductoPorId(?)")) {
                cs.setInt(1, prodId);
                boolean hasResult = cs.execute();
                assertTrue(hasResult, "El procedimiento debe devolver un result set");
                try (ResultSet rs = cs.getResultSet()) {
                    assertNotNull(rs);
                    assertTrue(rs.next(), "Debe encontrar el producto insertado");
                    assertEquals(prodId, rs.getInt("ID_Producto"));
                }
            }
        }
    }

    @Test
    @Order(4)
    void listarProductos_debeTraerAlMenosUno() throws Exception {
        try (Connection c = db.getConnection();
             CallableStatement cs = c.prepareCall("CALL " + schema + ".listarProductos()")) {
            boolean hasResult = cs.execute();
            assertTrue(hasResult);
            try (ResultSet rs = cs.getResultSet()) {
                boolean found = false;
                while (rs.next()) {
                    if (rs.getInt("ID_Producto") == prodId) {
                        found = true; break;
                    }
                }
                assertTrue(found, "Debe listar el producto insertado");
            }
        }
    }

    @Test
    @Order(5)
    void eliminarProducto_debeBorrar() throws Exception {
        try (Connection c = db.getConnection();
             CallableStatement cs = c.prepareCall("CALL " + schema + ".eliminarProducto(?)")) {
            cs.setInt(1, prodId);
            cs.execute();
        }
        // opcional: verificar que ya no existe
        try (Connection c = db.getConnection();
             CallableStatement cs = c.prepareCall("CALL " + schema + ".buscarProductoPorId(?)")) {
            cs.setInt(1, prodId);
            boolean hasResult = cs.execute();
            assertTrue(hasResult);
            try (ResultSet rs = cs.getResultSet()) {
                if (rs != null) {
                    // Puede devolver 0 filas
                    assertFalse(rs.next(), "El producto debería haber sido eliminado");
                }
            }
        }
    }
}
