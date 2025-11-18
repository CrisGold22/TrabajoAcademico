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
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarrito;

/**
 *
 * @author chris
 */
@WebService(serviceName = "LineaCarritoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class LineaCarritoWS {

    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "lineacarrito";
    private final ObjectMapper mapper;
    public LineaCarritoWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        this.mapper= new ObjectMapper();
        SimpleDateFormat df = 
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.mapper.setDateFormat(df);
    }
//    @WebMethod(operationName = "listarLineaCarrito")
//    public List<LineaCarrito> listarLineaCarrito() throws IOException, InterruptedException {
//        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .GET()
//                .build();
//        
//        HttpResponse<String> response = 
//                client.send(request, HttpResponse.BodyHandlers.ofString());
//        String json = response.body();
//        List<LineaCarrito> lineaCarritos = 
//                mapper.readValue(json, new TypeReference<List<LineaCarrito>>() {});
//        return lineaCarritos;
//    }
    @WebMethod(operationName = "InsertarLineaCarrito")
    public void InsertarLineaCarrito(@WebParam(name = "lineaCarrito")
            LineaCarrito lineaCarrito) throws JsonProcessingException, IOException, InterruptedException  {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lineaCarrito);
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
    @WebMethod(operationName = "ActualizarLineaCarrito")
    public void ActualizarLineaCarrito(@WebParam(name = "lineaCarrito")
            LineaCarrito lineaCarrito) throws JsonProcessingException, IOException, InterruptedException  {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lineaCarrito);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + lineaCarrito.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "ObtenerLineaCarrito")
    public LineaCarrito ObtenerLineaCarrito(@WebParam(name = "id")int id)  throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        LineaCarrito lineaCarrito = mapper.readValue(json, LineaCarrito.class);
        return lineaCarrito;
    }
    @WebMethod(operationName = "EliminarLineaCarrito")
    public void EliminarLineaCarrito(@WebParam(name = "id")int id)   throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
   
   
}
