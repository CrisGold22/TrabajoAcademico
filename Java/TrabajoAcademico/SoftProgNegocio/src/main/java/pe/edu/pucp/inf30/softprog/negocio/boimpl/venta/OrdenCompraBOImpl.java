/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.venta;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.inf30.softprog.dao.pago.LineaComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.dao.venta.LineaOrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.dao.venta.OrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.LineaCarritoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.LineaOrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.OrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.dbmanager.DBFactoryProvider;
import pe.edu.pucp.inf30.softprog.dbmanager.DBManager;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;
import pe.edu.pucp.inf30.softprog.negocio.bo.venta.OrdenCompraBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.pago.ComprobantePagoBOImpl;

/**
 *
 * @author Cristhian Horacio
 */
public class OrdenCompraBOImpl implements OrdenCompraBO {

    private final OrdenCompraDAO ordenCompraDAO;
    private final LineaOrdenCompraDAO lineaOrdenCompraDAO;

    public OrdenCompraBOImpl() {
        ordenCompraDAO = new OrdenCompraDAOImpl();
        lineaOrdenCompraDAO = new LineaOrdenCompraDAOImpl();
    }

    @Override
    public List<OrdenCompra> listar() {
        return this.ordenCompraDAO.leerTodos();
    }

    @Override
    public void insertar(OrdenCompra modelo) {
        DBManager dbManager = DBFactoryProvider.getManager();

        try (Connection conn = dbManager.getConnection()) {
            conn.setAutoCommit(false);

            try {
                int idOrden = this.ordenCompraDAO.crear(modelo, conn);
                modelo.setId(idOrden);

                for (LineaOrdenCompra linea : modelo.getLineasOrden()) {
                    linea.setOrdenCompra(modelo);
                    lineaOrdenCompraDAO.crear(linea, conn);
                }

                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error guardando OrdenVenta", ex);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error de conexión al guardar OrdenVenta"
                    + "", e);
        }
    }

    @Override
    public void actualizar(OrdenCompra modelo) {
        DBManager dbManager = DBFactoryProvider.getManager();

        try (Connection conn = dbManager.getConnection()) {
            conn.setAutoCommit(false);

            try {
                ordenCompraDAO.actualizar(modelo, conn);
                for (LineaOrdenCompra linea : modelo.getLineasOrden()) {
                    if (linea.getId() == 0) {
                        linea.setOrdenCompra(modelo);
                        lineaOrdenCompraDAO.crear(linea, conn);
                    } else {
                        lineaOrdenCompraDAO.actualizar(linea, conn);
                    }
                }

                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error guardando OrdenVenta", ex);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error de conexión al guardar OrdenVenta"
                    + "", e);
        }
    }

    @Override
    public OrdenCompra obtener(int id) {
        OrdenCompra orden = ordenCompraDAO.leer(id);
        if (orden == null) {
            return null;
        }
        List<LineaOrdenCompra> lineas = lineaOrdenCompraDAO.listarPorIdOrdenCompra(id);
        orden.setLineasOrden(lineas);
        return orden;
    }

    @Override
    public void eliminar(int id) {
        DBManager dbManager = DBFactoryProvider.getManager();

        try (Connection conn = dbManager.getConnection()) {
            conn.setAutoCommit(false);

            try {
                // 1. Eliminar las líneas de la orden
                List<LineaOrdenCompra> lineas = lineaOrdenCompraDAO.listarPorIdOrdenCompra(conn, id);
                for (LineaOrdenCompra linea : lineas) {
                    // por seguridad verificas que la línea pertenece a esa orden
                    if (linea.getOrdenCompra() != null
                            && linea.getOrdenCompra().getId() == id) {
                        lineaOrdenCompraDAO.eliminar(linea.getId(), conn);
                    }
                }

                // 2. Eliminar la orden de compra (cabecera)
                if (!ordenCompraDAO.eliminar(id, conn)) {   // 👈 AQUÍ el DAO correcto
                    throw new RuntimeException("El comprobante: " + id + ", no se pudo eliminar");
                }

                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error eliminando comprobante con id=" + id, ex);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrdenCompraBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<OrdenCompra> consultarPedidoPorFechas(Integer id, LocalDateTime fecha1, LocalDateTime fecha2) {
        return this.ordenCompraDAO.consultarPedidoPorFechas(id, fecha1, fecha2);
    }

    @Override
    public List<OrdenCompra> consultarOrdenCompraPorIdCliente(Integer id) {
        return this.ordenCompraDAO.consultarOrdenCompraPorIdCliente(id);
    }

    @Override
    public void desactivarOrdenCompra(Integer id) {
        String mensaje = this.ordenCompraDAO.desactivarOrdenCompra(id);
        System.out.println(mensaje);
    }

    @Override
    public List<LineaOrdenCompra> listarLineasOrdenCompraPorIdOrdenCompra(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de orden compra inválido");
        }        
        OrdenCompra orden = ordenCompraDAO.leer(id);
        
        if(orden == null) return new ArrayList<>();
        List<LineaOrdenCompra> lista = lineaOrdenCompraDAO.listarPorIdOrdenCompra(id);

        return lista;
    }

    @Override
    public void desactivarOrdenCompraPorHorario(Integer id) {
        String mensaje = this.ordenCompraDAO.desactivarOrdenCompraPorHorario(id);
        System.out.println(mensaje);
        
    }
}
