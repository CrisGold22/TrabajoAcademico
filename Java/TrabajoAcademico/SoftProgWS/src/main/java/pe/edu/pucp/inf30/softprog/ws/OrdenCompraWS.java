
package pe.edu.pucp.inf30.softprog.ws;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaOrdenCompra;
import pe.edu.pucp.inf30.softprog.modelo.venta.OrdenCompra;

/**
 *
 * @author chris
 */
@WebService(serviceName = "OrdenCompraWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class OrdenCompraWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "ordencompra";
    private final ObjectMapper mapper;
    public OrdenCompraWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        this.mapper= new ObjectMapper();
        SimpleDateFormat df = 
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.mapper.setDateFormat(df);
    }
    @WebMethod(operationName = "listarOrdenCompra")
    public List<OrdenCompra> listarOrdenCompra()  throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<OrdenCompra> ordenCompras = 
                mapper.readValue(json, new TypeReference<List<OrdenCompra>>() {});
        return ordenCompras;
    }
    @WebMethod(operationName = "insertarOrdenCompra")
    public void insertarOrdenCompra(@WebParam(name = "ordenCompra")
            OrdenCompra ordenCompra) throws JsonProcessingException, IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(ordenCompra);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    @WebMethod(operationName = "actualizarOrdenCompra")
    public void actualizarOrdenCompra(@WebParam(name = "ordenCompra")
            OrdenCompra ordenCompra) throws JsonProcessingException, IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(ordenCompra);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    @WebMethod(operationName = "obtenerOrdenCompra")
    public OrdenCompra obtenerOrdenCompra(@WebParam(name = "id")int id)  
        throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        //ObjectMapper mapper= new ObjectMapper();
        OrdenCompra ordenCompra = mapper.readValue(json, OrdenCompra.class);
        return ordenCompra;
    }
    @WebMethod(operationName = "eliminarOrdenCompra")
    public void eliminarOrdenCompra(@WebParam(name = "id")int id)  
         throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    
    @WebMethod(operationName = "consultarPedidoPorFechas")
    public List<OrdenCompra> consultarPedidoPorFechas(@WebParam(name = "id")Integer id,@WebParam(name = "fecha1")Date fecha1,@WebParam(name = "fecha2")Date fecha2) 
         throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE+"/consultarpedido/"+id+"/"+fecha1+"/"+fecha2;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<OrdenCompra> ordenCompras = 
                mapper.readValue(json, new TypeReference<List<OrdenCompra>>() {});
        return ordenCompras;
    }
    @WebMethod(operationName = "consultarOrdenCompraPorIdCliente")
    public List<OrdenCompra> consultarOrdenCompraPorIdCliente(@WebParam(name = "id")Integer idCliente)  
         throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE+"/ordencliente"+"/"+ idCliente;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<OrdenCompra> ordenCompras = 
                mapper.readValue(json, new TypeReference<List<OrdenCompra>>() {});
        return ordenCompras;
    }
    @WebMethod(operationName = "desactivarOrdenCompra")
    public void desactivarOrdenCompra(@WebParam(name = "id") Integer id) throws IOException, InterruptedException {
    String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/desactivar/" + id;
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .PUT(HttpRequest.BodyPublishers.noBody()) // No se envía cuerpo si el REST no lo requiere
            .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    @WebMethod(operationName = "listarLineasOrdenCompraPorIdOrdenCompra")
    public List<LineaOrdenCompra> listarLineasOrdenCompraPorIdOrdenCompra(@WebParam(name = "id")int id) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE +"/listarIdOrdenCompra/"+id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<LineaOrdenCompra> lineaOrdenCompra = 
               mapper.readValue(json, new TypeReference<List<LineaOrdenCompra>>() {});
        return lineaOrdenCompra;
    }
}
