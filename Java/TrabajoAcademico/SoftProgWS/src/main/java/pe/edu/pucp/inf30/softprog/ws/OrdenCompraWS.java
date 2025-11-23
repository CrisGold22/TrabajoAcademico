package pe.edu.pucp.inf30.softprog.ws;

import DateAdap.DateAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public OrdenCompraWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @WebMethod(operationName = "listarOrdenCompra")
    public List<OrdenCompra> listarOrdenCompra() throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<OrdenCompra> ordenCompras
                = mapper.readValue(json, new TypeReference<List<OrdenCompra>>() {
                });
        return ordenCompras;
    }

    @WebMethod(operationName = "insertarOrdenCompra")
    public void insertarOrdenCompra(@WebParam(name = "ordenCompra") OrdenCompra ordenCompra) throws JsonProcessingException, IOException, InterruptedException {
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
    public void actualizarOrdenCompra(@WebParam(name = "ordenCompra") OrdenCompra ordenCompra)
            throws JsonProcessingException, IOException, InterruptedException {

        // Asegúrate que el id esté bien seteado desde el front
        System.out.println("SOAP actualizarOrdenCompra -> id=" + ordenCompra.getId()
                + ", estado=" + ordenCompra.getEstado());

        String json = mapper.writeValueAsString(ordenCompra);
        System.out.println("JSON que se envía al REST (actualizarOrdenCompra): " + json);

        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + ordenCompra.getId();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        if (status != 200 && status != 204) {
            throw new RuntimeException(
                    "Error al actualizar OrdenCompra. HTTP " + status + " - Body: " + response.body()
            );
        }
    }

    @WebMethod(operationName = "obtenerOrdenCompra")
    public OrdenCompra obtenerOrdenCompra(@WebParam(name = "id") int id)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        //ObjectMapper mapper= new ObjectMapper();
        OrdenCompra ordenCompra = mapper.readValue(json, OrdenCompra.class);
        return ordenCompra;
    }

    @WebMethod(operationName = "eliminarOrdenCompra")
    public void eliminarOrdenCompra(@WebParam(name = "id") int id)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "consultarPedidoPorFechas")
    public List<OrdenCompra> consultarPedidoPorFechas(
            @WebParam(name = "id") Integer id,
            @WebParam(name = "fecha1") String fecha1Str,
            @WebParam(name = "fecha2") String fecha2Str)
            throws IOException, InterruptedException {

        if (fecha1Str == null || fecha2Str == null
                || fecha1Str.isBlank() || fecha2Str.isBlank()) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }

        // Formato que ESPERA el WS (lo que tú escribes en el tester)
        DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        // Formato que ESPERA el REST en la URL
        DateTimeFormatter restFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime fecha1;
        LocalDateTime fecha2;
        try {
            fecha1 = LocalDateTime.parse(fecha1Str, inputFmt);
            fecha2 = LocalDateTime.parse(fecha2Str, inputFmt);
        } catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Formato de fecha inválido. Usa yyyy-MM-dd'T'HH:mm:ss", e);
        }

        // Solo la parte de fecha para la URL del REST
        String fecha1ForRest = fecha1.toLocalDate().format(restFmt); // ej. 2025-02-01
        String fecha2ForRest = fecha2.toLocalDate().format(restFmt); // ej. 2025-02-15

        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE
                + "/consultarpedido/" + id + "/" + fecha1ForRest + "/" + fecha2ForRest;

        System.out.println("URL consultarPedidoPorFechas -> " + url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();
        if (status != 200) {
            throw new RuntimeException(
                    "Error al consultar pedidos. Código HTTP: " + status
                    + " Respuesta: " + response.body());
        }

        String json = response.body();
        return mapper.readValue(json, new TypeReference<List<OrdenCompra>>() {
        });
    }

    @WebMethod(operationName = "consultarOrdenCompraPorIdCliente")
    public List<OrdenCompra> consultarOrdenCompraPorIdCliente(@WebParam(name = "id") Integer idCliente)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/ordencliente" + "/" + idCliente;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<OrdenCompra> ordenCompras
                = mapper.readValue(json, new TypeReference<List<OrdenCompra>>() {
                });
        return ordenCompras;
    }

    @WebMethod(operationName = "desactivarOrdenCompra")
    public String desactivarOrdenCompra(@WebParam(name = "id") Integer id) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/desactivar/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.noBody()) // No se envía cuerpo si el REST no lo requiere
                .build();
        //.PUT(HttpRequest.BodyPublishers.noBody())

        System.err.println("URL : " + url);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode >= 200 && statusCode < 300) {
            return "Orden Cancelada exitosamente";
        }

        if (statusCode >= 400 && statusCode < 500) {
            throw new RuntimeException("ERROR HTTP CLIENTE");
        }

        if (statusCode >= 500) {
            throw new RuntimeException("ERROR HTTP SERVIDOR");
        }

        return "Respuesta con codigo no esperado";
    }

    @WebMethod(operationName = "listarLineasOrdenCompraPorIdOrdenCompra")
    public List<LineaOrdenCompra> listarLineasOrdenCompraPorIdOrdenCompra(@WebParam(name = "id") int id) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/listarIdOrdenCompra/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        if (statusCode == 404) {
            return new ArrayList<>(); // Retorna lista vacía si no encuentra
        }
        if (statusCode >= 400) {
            throw new RuntimeException("Error HTTP: " + statusCode + " - " + response.body());
        }
        String json = response.body();
        List<LineaOrdenCompra> lineaOrdenCompra
                = mapper.readValue(json, new TypeReference<List<LineaOrdenCompra>>() {
                });
        return lineaOrdenCompra;
    }
}
