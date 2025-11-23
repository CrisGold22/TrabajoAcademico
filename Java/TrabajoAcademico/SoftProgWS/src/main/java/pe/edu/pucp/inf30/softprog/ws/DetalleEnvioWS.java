package pe.edu.pucp.inf30.softprog.ws;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import pe.edu.pucp.inf30.softprog.modelo.venta.DetalleEnvio;

/**
 *
 * @author chris
 */
@WebService(serviceName = "DetalleEnvioWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class DetalleEnvioWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "detalle";
    private final ObjectMapper mapper;
    public DetalleEnvioWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        this.mapper= new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @WebMethod(operationName = "listarDetalleEnvio")
    public List<DetalleEnvio> listarDetalleEnvio() 
         throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<DetalleEnvio> detalleEnvios = 
                mapper.readValue(json, new TypeReference<List<DetalleEnvio>>() {});
        return detalleEnvios;
    }
    @WebMethod(operationName = "insertarDetalleEnvio")
    public void insertarDetalleEnvio(@WebParam(name = "detalleEnvio")
            DetalleEnvio detalleEnvio) throws JsonProcessingException, IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(detalleEnvio);
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
    @WebMethod(operationName = "actualizarDetalleEnvio")
    public void actualizarDetalleEnvio(@WebParam(name = "detalleEnvio")
            DetalleEnvio detalleEnvio) throws JsonProcessingException, IOException, InterruptedException  {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(detalleEnvio);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + detalleEnvio.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
   
    
    @WebMethod(operationName = "obtenerDetalleEnvio")
    public DetalleEnvio obtenerDetalleEnvio(@WebParam(name = "id")int id)throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        //ObjectMapper mapper= new ObjectMapper();
        DetalleEnvio detalleEnvio = mapper.readValue(json, DetalleEnvio.class);
        return detalleEnvio;
    }
    @WebMethod(operationName = "eliminarDetalleEnvio")
    public String eliminarDetalleEnvio(@WebParam(name = "id")int id)  throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        
        HttpResponse<String>response = client.send(request, HttpResponse.BodyHandlers.ofString());
       
        int statusCode = response.statusCode();
        
        if(statusCode == 204){
            return ("DetalleEnvio ID " + id + " eliminada exitosamente (HTTP 204).");
        }
        if (statusCode == 404) {
            return ("Error 404: DetalleEnvio con ID " + id + " no encontrada.");
            //throw new RuntimeException(mensajeError);
        }       
        if (statusCode >= 400) {
            return ("Fallo en el servicio. Código HTTP: " + statusCode + ". Detalle: " + response.body());
        }
        return "Operacion Completa";          
    }
}
