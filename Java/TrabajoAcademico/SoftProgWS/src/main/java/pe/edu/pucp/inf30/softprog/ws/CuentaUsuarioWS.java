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
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;

/**
 *
 * @author chris
 */
@WebService(serviceName = "CuentaUsuarioWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class CuentaUsuarioWS {
     private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "cuentas";
    private final ObjectMapper mapper;
    
    public CuentaUsuarioWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        this.mapper= new ObjectMapper();
        SimpleDateFormat df = 
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.mapper.setDateFormat(df);
    }
    
    @WebMethod(operationName = "listarCuentaUsuario")
    public List<CuentaUsuario> listarCuentaUsuario() throws IOException, InterruptedException{
       String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<CuentaUsuario> cuentaUsuarios = 
                mapper.readValue(json, new TypeReference<List<CuentaUsuario>>() {});
        return cuentaUsuarios;
    }
    
    @WebMethod(operationName = "insertarCuentaUsuario")
    public void insertarCuentaUsuario(@WebParam(name = "cuentaUsuario")
                                        CuentaUsuario cuentaUsuario) throws JsonProcessingException, IOException, InterruptedException{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cuentaUsuario);
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
    
    @WebMethod(operationName = "actualizarCuentaUsuario")
    public void actualizarCuentaUsuario(@WebParam(name = "cuentaUsuario")
                                        CuentaUsuario cuentaUsuario) throws JsonProcessingException, IOException, InterruptedException{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cuentaUsuario);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + cuentaUsuario.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "obtenerCuentaUsuario")
    public CuentaUsuario obtenerCuentaUsuario(@WebParam(name = "id")int id) 
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
        CuentaUsuario cuentaUsuario = mapper.readValue(json, CuentaUsuario.class);
        return cuentaUsuario;
    }
    @WebMethod(operationName = "eliminarCuentaUsuario")
    public void eliminarCuentaUsuario(@WebParam(name = "id")int id)
         throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    @WebMethod(operationName = "login")
    public boolean login(
            @WebParam(name = "email")String email,
            @WebParam(name = "password")String password
    )throws IOException, InterruptedException {
        
        CuentaUsuario cuenta = new CuentaUsuario();
        cuenta.setUsername(email);
        cuenta.setPassword(password);
        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cuenta);
        
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/login";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        
        HttpResponse<String> response = 
                client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response.statusCode() == 200;
    }
    
    @WebMethod(operationName = "cambiarPassword")
    public boolean cambiarPassword(
        @WebParam(name = "username") String username,
        @WebParam(name = "passwordActual") String passwordActual,
        @WebParam(name = "passwordNueva") String passwordNueva
    ) {
        return cuentaUsuarioBO.cambiarPassword(username, passwordActual, passwordNueva);
    }
}
