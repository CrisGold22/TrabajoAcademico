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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoCompras;

/**
 *
 * @author chris
 */
@WebService(serviceName = "CarritoComprasWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class CarritoComprasWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "carritocompras";
    private final ObjectMapper mapper;
    
    
    public CarritoComprasWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        this.mapper= new ObjectMapper();
        SimpleDateFormat df = 
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.mapper.setDateFormat(df);
    }
    
    @WebMethod(operationName = "listarTodosCarritoCompras")
    public List<CarritoCompras>listarTodosCarritoCompras() throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<CarritoCompras> carritoCompras = 
                mapper.readValue(json, new TypeReference<List<CarritoCompras>>() {});
        return carritoCompras;
    }
    
    @WebMethod(operationName = "obtenerCarritoCompras")
    public CarritoCompras obtenerCarritoCompras(@WebParam(name = "id") 
            int id)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        CarritoCompras carritoCompras = mapper.readValue(json, CarritoCompras.class);
        return carritoCompras;
    }
    @WebMethod(operationName = "insertarCarritoCompras")
    public void insertarCarritoCompras(@WebParam(name = "carritoCompras") 
            CarritoCompras carritoCompras) throws IOException, InterruptedException  {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(carritoCompras);
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
    @WebMethod(operationName = "eliminarCarritoCompras")
    public void eliminarCarritoCompras(@WebParam(name = "id") 
            int id)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

//    
    @WebMethod(operationName = "actualizarCarritoCompras")
    public void actualizarCarritoCompras(@WebParam(name = "carritoCompras") 
            CarritoCompras carritoCompras) throws JsonProcessingException, IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(carritoCompras);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + carritoCompras.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
     @WebMethod(operationName = "listarLineaCarritoPorIdCarrito")
    public List<CarritoCompras>listarLineaCarritoPorIdCarrito(@WebParam(name = "id") 
            int id) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE +"/IdCarrito/"+id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<CarritoCompras> carritoCompras = 
                mapper.readValue(json, new TypeReference<List<CarritoCompras>>() {});
        return carritoCompras;
    }
    @WebMethod(operationName = "obtenerCarritoDeCliente")
    public CarritoCompras obtenerCarritoDeCliente(@WebParam(name = "idCliente") 
            int idCliente)  throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/obtenerCarritoIDCliente/" + idCliente;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        CarritoCompras carritoCompras = mapper.readValue(json, CarritoCompras.class);
        return carritoCompras;
    }
}