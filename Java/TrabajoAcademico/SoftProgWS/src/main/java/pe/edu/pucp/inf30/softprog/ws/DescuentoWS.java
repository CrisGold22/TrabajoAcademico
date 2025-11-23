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
import pe.edu.pucp.inf30.softprog.modelo.producto.Descuento;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;

/**
 *
 * @author chris
 */
@WebService(serviceName = "DescuentoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class DescuentoWS {

    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "descuento";
    private final ObjectMapper mapper;
    public DescuentoWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        this.mapper= new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }
    
    @WebMethod(operationName = "listarDescuento")
    public List<Descuento> listarDescuento() throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Descuento> descuentos = 
                mapper.readValue(json, new TypeReference<List<Descuento>>() {});
        return descuentos;
    }
    @WebMethod(operationName = "insertarDescuento")
    public void insertarDescuento(@WebParam(name = "descuento")
            Descuento descuento) throws JsonProcessingException, IOException, InterruptedException  {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(descuento);
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
    @WebMethod(operationName = "actualizarDescuento")
    public void actualizarDescuento(@WebParam(name = "descuento")
            Descuento descuento) throws JsonProcessingException, IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(descuento);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + descuento.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
        
    }
    @WebMethod(operationName = "obtenerDescuento")
    public Descuento obtenerDescuento(@WebParam(name = "id")int id) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        //ObjectMapper mapper= new ObjectMapper();
        Descuento descuento = mapper.readValue(json, Descuento.class);
        return descuento;
    }
    @WebMethod(operationName = "eliminarDescuento")
    public String eliminarDescuento(@WebParam(name = "id")int id)
        throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        HttpResponse<String>response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        
        if(statusCode == 204){
            return ("LineaCarrito ID " + id + " eliminada exitosamente (HTTP 204).");
        }
        if (statusCode == 404) {
            return ("Error 404: LineaCarrito con ID " + id + " no encontrada.");
            //throw new RuntimeException(mensajeError);
        }       
        if (statusCode >= 400) {
            return ("Fallo en el servicio. Código HTTP: " + statusCode + ". Detalle: " + response.body());
        }
        return "Operacion Completa";        
    }
   
    @WebMethod(operationName = "actualizarDescuentoPorIdProducto")
    public boolean actualizarDescuentoPorIdProducto(@WebParam(name = "id_producto")Integer id_producto,@WebParam(name = "descuento")
            Descuento descuento)
        throws IOException, InterruptedException {
        String json = mapper.writeValueAsString(descuento);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/actualizarDescuentoPorIdProducto/" + id_producto;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        return Boolean.parseBoolean(response.body());
    }
    
    
}
