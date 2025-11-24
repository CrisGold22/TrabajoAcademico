package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.pago.ComprobantePago;
import pe.edu.pucp.inf30.softprog.negocio.bo.pago.ComprobantePagoBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.pago.ComprobantePagoBOImpl;

@WebService(serviceName = "ComprobantePagoWS")
public class ComprobantePagoWS {

    private ComprobantePagoBO comprobanteBO;

    public ComprobantePagoWS() {
        // Instanciamos la implementación que tiene la lógica Cripto/Blockchain
        this.comprobanteBO = new ComprobantePagoBOImpl();
    }

    @WebMethod(operationName = "insertarComprobantePago")
    public int insertarComprobantePago(@WebParam(name = "comprobantePago") ComprobantePago comprobantePago) {
        try {
            // Validar que lleguen líneas (Regla de negocio básica)
            if (comprobantePago.getLineasComprobantes() == null || comprobantePago.getLineasComprobantes().isEmpty()) {
                System.err.println("Error: El comprobante no tiene líneas de detalle.");
                return 0;
            }
            
            // Llamada a la lógica fuerte (Blockchain + BD)
            comprobanteBO.insertar(comprobantePago);
            
            // Retornar ID generado
            return comprobantePago.getId();
            
        } catch (Exception ex) {
            // Imprimir error en consola de Glassfish/Payara
            System.err.println("Error en WS Comprobante: " + ex.getMessage());
            ex.printStackTrace();
            return -1; // Código de error
        }
    }
    
    @WebMethod(operationName = "listarComprobantes")
    public List<ComprobantePago> listarComprobantes() {
        return comprobanteBO.listar();
    }
}