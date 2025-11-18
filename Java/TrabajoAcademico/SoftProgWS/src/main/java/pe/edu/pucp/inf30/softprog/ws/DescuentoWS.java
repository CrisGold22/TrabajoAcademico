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
        SimpleDateFormat df = 
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.mapper.setDateFormat(df);
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
        ObjectMapper mapper = new ObjectMapper();
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
        ObjectMapper mapper = new ObjectMapper();
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
        ObjectMapper mapper= new ObjectMapper();
        Descuento descuento = mapper.readValue(json, Descuento.class);
        return descuento;
    }
    @WebMethod(operationName = "eliminarDescuento")
    public void eliminarDescuento(@WebParam(name = "id")int id)
        throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    @WebMethod(operationName = "actualizarPrecioDescuentoProducto")
    public void actualizarPrecioDescuentoProducto(@WebParam(name = "id")Integer id,@WebParam(name = "nuevoprecio")double nuevoprecio)
        throws IOException, InterruptedException {
        
        Descuento descuento=new Descuento();
        Producto producto=new Producto();
        producto.setId(id);
        descuento.setProducto(producto);
        descuento.setPrecioPorVolumen(nuevoprecio);
        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(descuento);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/actualizardescuento/" + id + "/"+nuevoprecio;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
        
    }
}
