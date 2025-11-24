/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.pago;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.inf30.softprog.dao.pago.ComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.dao.pago.LineaComprobantePagoDAO;
import pe.edu.pucp.inf30.softprog.dao.venta.OrdenCompraDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.ComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.pago.LineaComprobantePagoDAOImpl;
import pe.edu.pucp.inf30.softprog.daoimpl.venta.OrdenCompraDAOImpl;
import pe.edu.pucp.inf30.softprog.dbmanager.DBFactoryProvider;
import pe.edu.pucp.inf30.softprog.dbmanager.DBManager;
import pe.edu.pucp.inf30.softprog.modelo.pago.ComprobantePago;
import pe.edu.pucp.inf30.softprog.modelo.pago.LineaComprobantePago;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.EstadoOrdenCompra;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.BlockchainBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.ComprobantePagoBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.CriptoMonedaBO;

/**
 *
 * @author Cristhian Horacio
 */
public class ComprobantePagoBOImpl implements ComprobantePagoBO {

    private final ComprobantePagoDAO comprobantePagoDAO;
    private final LineaComprobantePagoDAO lineaComprobantePagoDAO;
    
    // Servicios auxiliares
    private final OrdenCompraDAO ordenCompraDAO; // Para actualizar el estado de la orden
    private final BlockchainBO blockchainBO;     // Para verificar transacciones en Etherscan
    private final CriptoMonedaBO criptoBO;       // Para obtener precio de CoinGecko

    public ComprobantePagoBOImpl() {
        this.comprobantePagoDAO = new ComprobantePagoDAOImpl();
        this.lineaComprobantePagoDAO = new LineaComprobantePagoDAOImpl();
        
        // Inicialización de servicios auxiliares
        this.ordenCompraDAO = new OrdenCompraDAOImpl();
        this.blockchainBO = new BlockchainBO();
        this.criptoBO = new CriptoMonedaBO();
    }
    
    @Override
    public List<ComprobantePago> listar() {
        return this.comprobantePagoDAO.leerTodos();
    }

    @Override
    public void insertar(ComprobantePago modelo) {
        
        String hashTransaccionConfirmada = null;
        
        // 1. Lógica de Verificación Cripto (Solo si aplica)
        if ("CRIPTO".equals(modelo.getMetodoString()) || "VIRTUAL".equals(modelo.getMetodoString())) { 

            System.out.println("--- INICIANDO PROCESO DE PAGO CRIPTO ---");

            // Obtener precio real
            double precioETH = criptoBO.obtenerPrecioBitcoinEnDolares(); 
            double montoA_PagarETH = modelo.getTotalFinal() / precioETH;
            
            System.out.println("Monto de la venta (S/.): " + modelo.getTotalFinal());
            System.out.println("Tipo de cambio actual: $" + precioETH);
            System.out.println("MONTO A PAGAR EN CRIPTO: " + String.format("%.6f", montoA_PagarETH) + " ETH");
            System.out.println("Esperando confirmación de la Blockchain (Sepolia)...");

            // Polling (Espera activa)
            for (int i = 0; i < 6; i++) {
                try {
                    Thread.sleep(5000); // Esperar 5 seg
                    System.out.println("Verificando red blockchain... Intento " + (i + 1));
                    
                    hashTransaccionConfirmada = blockchainBO.verificarPagoEnBlockchain(montoA_PagarETH);
                    
                    if (hashTransaccionConfirmada != null) {
                        System.out.println("¡PAGO CONFIRMADO! Hash: " + hashTransaccionConfirmada);
                        break; 
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Error en la espera del pago", e);
                }
            }

            if (hashTransaccionConfirmada == null) {
                throw new RuntimeException("TIEMPO AGOTADO: No se detectó el pago en la Blockchain. Operación cancelada.");
            }
        }

        // 2. Transacción de Base de Datos
        DBManager dbManager = DBFactoryProvider.getManager();
        try (Connection conn = dbManager.getConnection()) {
            conn.setAutoCommit(false);

            try {
                // A. Insertar Cabecera
                int idOrden = this.comprobantePagoDAO.crear(modelo, conn);
                modelo.setId(idOrden);

                // B. Insertar Líneas
                for (LineaComprobantePago linea : modelo.getLineasComprobantes()) {
                    linea.setComprobantePago(modelo);
                    lineaComprobantePagoDAO.crear(linea, conn);
                }
                
                // C. Log de Hash (Opcional)
                if (hashTransaccionConfirmada != null) {
                    System.out.println("Hash de transacción vinculado al comprobante " + idOrden);
                    // Aquí podrías guardar el hash en una tabla si quisieras
                }

                // D. Actualizar Estado de la Orden de Compra
                if (modelo.getOrdenCompra() != null) {
                    OrdenCompra orden = ordenCompraDAO.leer(modelo.getOrdenCompra().getId());
                    if (orden != null) {
                        orden.setEstado(EstadoOrdenCompra.PAGADO);
                        ordenCompraDAO.actualizar(orden, conn);
                    }
                }

                conn.commit();
                
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error guardando ComprobantePago en BD", ex);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error de conexión al guardar ComprobantePago", e);
        }
    }

    @Override
    public void actualizar(ComprobantePago modelo) {
       DBManager dbManager = DBFactoryProvider.getManager();
        try (Connection conn = dbManager.getConnection()) {
            conn.setAutoCommit(false);

            try {
                comprobantePagoDAO.actualizar(modelo, conn);
                for (LineaComprobantePago linea : modelo.getLineasComprobantes()) {
                    if(linea.getId() == 0){
                        linea.setComprobantePago(modelo);
                        lineaComprobantePagoDAO.crear(linea, conn);
                    }
                    else{
                        lineaComprobantePagoDAO.actualizar(linea, conn);
                    }
                }
                
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error actualizando ComprobantePago", ex);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error de conexión al actualizar ComprobantePago", e);
        }
    }

    @Override
    public ComprobantePago obtener(int id) {
        ComprobantePago comprobante = comprobantePagoDAO.leer(id);
        if(comprobante == null) return null;
        List<LineaComprobantePago> lineas = lineaComprobantePagoDAO.listarPorIdComprobante(id);
        comprobante.setLineasComprobantes(lineas);
        return comprobante; 
    }

    @Override
    public void eliminar(int id) {
        DBManager dbManager = DBFactoryProvider.getManager();
        
        try(Connection conn = dbManager.getConnection()){
            conn.setAutoCommit(false);
            
            try{
                List<LineaComprobantePago> lineas = lineaComprobantePagoDAO.listarPorIdComprobante(conn, id);
                for(LineaComprobantePago linea : lineas){
                    if(linea.getComprobantePago().getId() == id){
                        lineaComprobantePagoDAO.eliminar(linea.getId(), conn);
                    }
                }
                
                if(!comprobantePagoDAO.eliminar(id, conn)){
                    throw new RuntimeException("El comprobante: " + id + ", no se pudo eliminar");
                }
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw new RuntimeException("Error eliminando comprobante con id=" + id, ex);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ComprobantePagoBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}