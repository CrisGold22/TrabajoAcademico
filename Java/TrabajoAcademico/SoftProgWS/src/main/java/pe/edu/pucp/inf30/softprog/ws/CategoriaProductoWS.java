package pe.edu.pucp.inf30.softprog.ws;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import pe.edu.pucp.inf30.softprog.modelo.producto.CategoriaProducto;

/**
 *
 * @author chris
 */
@WebService(serviceName = "CategoriaProductoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class CategoriaProductoWS {
private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "categoriaproducto";
    private final ObjectMapper mapper;

    public CategoriaProductoWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        this.mapper= new ObjectMapper();
        SimpleDateFormat df = 
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.mapper.setDateFormat(df);
    }
    
    @WebMethod(operationName = "listarCategoriaProducto")
    public List<CategoriaProducto> listarCategoriaProducto() throws InterruptedException, IOException  {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<CategoriaProducto> categoriaProductos = 
                mapper.readValue(json, new TypeReference<List<CategoriaProducto>>() {});
        return categoriaProductos;
    }
    
    @WebMethod(operationName = "insertarCategoriaProducto")
    public void insertarCategoriaProducto(@WebParam(name = "categoriaProducto")
            CategoriaProducto categoriaProducto) throws IOException, InterruptedException  {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(categoriaProducto);
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
    
    @WebMethod(operationName = "actualizarCategoriaProducto")
    public void actualizarCategoriaProducto(@WebParam(name = "categoriaProducto")
            CategoriaProducto categoriaProducto) throws JsonProcessingException, IOException, InterruptedException  {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(categoriaProducto);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + categoriaProducto.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());  
    }
    
    @WebMethod(operationName = "obtenerCategoriaProducto")
    public CategoriaProducto obtenerCategoriaProducto(@WebParam(name = "id")
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
        //ObjectMapper mapper= new ObjectMapper();
        CategoriaProducto categoriaProducto = mapper.readValue(json, CategoriaProducto.class);
        return categoriaProducto;
    }
    
    @WebMethod(operationName = "eliminarCategoriaProducto")
    public void eliminarCategoriaProducto(@WebParam(name = "id")int id)  
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}