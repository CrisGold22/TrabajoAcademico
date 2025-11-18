
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
import pe.edu.pucp.inf30.softprog.modelo.persona.AdministradorSistema;

/**
 *
 * @author chris
 */
@WebService(serviceName = "AdministradorSistemaWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class AdministradorSistemaWS {
     private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "administrador";
    private final ObjectMapper mapper;
    public AdministradorSistemaWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        this.mapper= new ObjectMapper();
        SimpleDateFormat df = 
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.mapper.setDateFormat(df);
    }
    @WebMethod(operationName = "listarTodosAdminstradores")
    public List<AdministradorSistema>listarTodosAdminstradores() throws IOException, InterruptedException{
       String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<AdministradorSistema> administradorSistemas = 
                mapper.readValue(json, new TypeReference<List<AdministradorSistema>>() {});
        return administradorSistemas;
    }
    
    // el guardar es una funcion entre actualizar y crear
    @WebMethod(operationName = "insertarAdministrador")
    public void insertarAdministrador(@WebParam(name = "administradorsistema")
            AdministradorSistema administradorsistema) throws JsonProcessingException, IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(administradorsistema);
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
    
    @WebMethod(operationName = "actualizarAdministradorPorId")
    public void actualizarAdministradorPorId(@WebParam(name = "admistradorsistema") 
            AdministradorSistema admistradorsistema) throws JsonProcessingException, IOException, InterruptedException  {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(admistradorsistema);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + admistradorsistema.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    @WebMethod(operationName = "obtenerAdministradorPorId")
    public AdministradorSistema obtenerAdministradorPorId(@WebParam(name = "id") 
            int id) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        AdministradorSistema administradorSistema = mapper.readValue(json, AdministradorSistema.class);
        return administradorSistema;
    }
    @WebMethod(operationName = "eliminarAdministradorPorId")
    public void eliminarAdministradorPorId(@WebParam(name = "id") int id) throws IOException, InterruptedException{
       
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
       
}
