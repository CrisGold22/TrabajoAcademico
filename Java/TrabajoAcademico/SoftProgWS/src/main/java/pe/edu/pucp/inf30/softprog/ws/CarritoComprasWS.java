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
import pe.edu.pucp.inf30.softprog.modelo.venta.CarritoCompras;
import pe.edu.pucp.inf30.softprog.modelo.venta.LineaCarrito;

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

    public CarritoComprasWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @WebMethod(operationName = "listarTodosCarritoCompras")
    public List<CarritoCompras> listarTodosCarritoCompras() throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<CarritoCompras> carritoCompras
                = mapper.readValue(json, new TypeReference<List<CarritoCompras>>() {
                });
        return carritoCompras;
    }

    @WebMethod(operationName = "obtenerCarritoCompras")
    public CarritoCompras obtenerCarritoCompras(@WebParam(name = "id") int id)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ////http://localhost:8080/SoftProgWR/api/v1/administrador/1
        CarritoCompras carritoCompras = mapper.readValue(json, CarritoCompras.class);
        return carritoCompras;
    }

    @WebMethod(operationName = "insertarCarritoCompras")
    public void insertarCarritoCompras(@WebParam(name = "carritoCompras") CarritoCompras carritoCompras) throws IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
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
    public String eliminarCarritoCompras(@WebParam(name = "id") int id)
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

//    
    @WebMethod(operationName = "actualizarCarritoCompras")
    public void actualizarCarritoCompras(@WebParam(name = "carritoCompras") CarritoCompras carritoCompras) throws JsonProcessingException, IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
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
    public List<LineaCarrito> listarLineaCarritoPorIdCarrito(@WebParam(name = "id") int id) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/IdCarrito/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<LineaCarrito> carritoCompras
                = mapper.readValue(json, new TypeReference<List<LineaCarrito>>() {
                });
        return carritoCompras;
    }

    @WebMethod(operationName = "obtenerCarritoDeCliente")
    public CarritoCompras obtenerCarritoDeCliente(@WebParam(name = "idCliente") int idCliente) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE
                + "/obtenerCarritoIDCliente/" + idCliente;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();
        String json = response.body();

        if (status == 404) {
            // Podrías loguear el error si quieres:
            System.out.println("No se encontró carrito para cliente " + idCliente
                    + ". Respuesta WR: " + json);
            return null;
        }

        if (status >= 200 && status < 300) {
            return mapper.readValue(json, CarritoCompras.class);
        }

        throw new RuntimeException("Error al obtener carrito del cliente "
                + idCliente + ". Código HTTP: " + status + ", cuerpo: " + json);
    }
}
