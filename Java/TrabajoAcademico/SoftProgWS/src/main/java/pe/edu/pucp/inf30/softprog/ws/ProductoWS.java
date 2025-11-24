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
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import pe.edu.pucp.inf30.softprog.modelo.producto.Producto;

/**
 *
 * @author chris
 */
@WebService(serviceName = "ProductoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class ProductoWS {

    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "producto";
    private final ObjectMapper mapper;

    public ProductoWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @WebMethod(operationName = "listarProducto")
    public List<Producto> listarProducto() throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Producto> productos
                = mapper.readValue(json, new TypeReference<List<Producto>>() {
                });
        return productos;
    }

    @WebMethod(operationName = "insertarProducto")
    public void insertarProducto(@WebParam(name = "producto") Producto producto) throws IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
        // Serializar a JSON
        String json = mapper.writeValueAsString(producto);
        System.out.println("JSON enviado a REST (insertarProducto): " + json);

        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();

        // En tu REST, el POST devuelve:
        // return Response.created(location).entity(producto).build();
        // → eso es 201 (CREATED)
        if (status != 201 && status != 200) {
            throw new RuntimeException(
                    "Error al insertar producto. Código HTTP: " + status
                    + " Respuesta: " + response.body()
            );
        }

        // OPCIONAL: si quisieras leer el producto creado (con ID generado), podrías hacer:
        // Producto creado = mapper.readValue(response.body(), Producto.class);
        // producto.setId(creado.getId());
    }

    @WebMethod(operationName = "actualizarProducto")
    public void actualizarProducto(@WebParam(name = "producto") Producto producto) throws JsonProcessingException, IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(producto);

        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + producto.getId();

        String jsonResponse = mapper.writeValueAsString(producto);
        System.out.println("JSON enviado a REST (actualizarProducto): " + jsonResponse);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();
        if (status != 200 && status != 204) {
            // Esto hará que el SOAP lance una Fault y tu C# la capture
            throw new RuntimeException(
                    "Error al actualizar producto. Código HTTP: " + status
                    + " Respuesta: " + response.body()
            );
        }

    }

    @WebMethod(operationName = "obtenerProducto")
    public Producto obtenerProducto(@WebParam(name = "id") int id)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        if (statusCode == 404) {
            throw new RuntimeException("Producto con ID " + id + " no encontrado");
        }
        if (statusCode >= 400) {
            throw new RuntimeException("Error HTTP: " + statusCode);
        }

        String json = response.body();
        //ObjectMapper mapper= new ObjectMapper();
        Producto producto = mapper.readValue(json, Producto.class);
        return producto;
    }

    @WebMethod(operationName = "eliminarProducto")
    public String eliminarProducto(@WebParam(name = "id") int id) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();

        if (statusCode == 204) {
            return ("Producto ID " + id + " eliminada exitosamente (HTTP 204).");
        }
        if (statusCode == 404) {
            return ("Error 404: Producto con ID " + id + " no encontrada.");
            //throw new RuntimeException(mensajeError);
        }
        if (statusCode >= 400) {
            return ("Fallo en el servicio. Código HTTP: " + statusCode + ". Detalle: " + response.body());
        }
        return "Operacion Completa";
    }
    //si sirve
    @WebMethod(operationName = "obtenerPorSku")
    public Producto obtenerPorSku(@WebParam(name = "sku") String sku) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/obtenerPorSku/" + sku;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Producto producto = mapper.readValue(json, Producto.class);
        return producto;
    }
    
    @WebMethod(operationName = "listarfiltrarProductoPorPrecioRegular")
    public List<Producto> listarfiltrarProductoPorPrecioRegular(@WebParam(name = "id") Integer id, @WebParam(name = "RangoPrecio1") double RangoPrecio1,
            @WebParam(name = "RangoPrecio2") double RangoPrecio2) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/filtrarproductoprecio/" + id + "/" + RangoPrecio1 + "/" + RangoPrecio2;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Producto> productos
                = mapper.readValue(json, new TypeReference<List<Producto>>() {
                });
        return productos;
    }

    @WebMethod(operationName = "listarfiltrarProductoPorMarca")
    public List<Producto> listarfiltrarProductoPorMarca(@WebParam(name = "id") Integer id, @WebParam(name = "marca") String marca) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/filtrarproductomarca/" + id + "/" + marca;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Producto> productos
                = mapper.readValue(json, new TypeReference<List<Producto>>() {
                });
        return productos;
    }

    @WebMethod(operationName = "listarfiltrarProductoPorDescuento")
    public List<Producto> listarfiltrarProductoPorDescuento(@WebParam(name = "idCategoria") Integer idCategoria, @WebParam(name = "nombreCategoria") String nombreCategoria) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/filtrarproductodescuento/" + idCategoria + "/" + nombreCategoria;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Producto> productos
                = mapper.readValue(json, new TypeReference<List<Producto>>() {
                });
        return productos;
    }

    @WebMethod(operationName = "verificarStockSuficientePorID")
    public boolean verificarStockSuficientePorID(@WebParam(name = "id") Integer id, @WebParam(name = "cantidadSolicitada") Integer cantidadSolicitada) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/verificarstock/" + id + "/" + cantidadSolicitada;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Boolean.parseBoolean(response.body());
    }

    @WebMethod(operationName = "verificarStockSuficientePorSKU")
    public boolean verificarStockSuficientePorSKU(@WebParam(name = "sku") String sku, @WebParam(name = "cantidadSolicitada") Integer cantidadSolicitada) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/verificarstocksuficientePorSKU/" + sku + "/" + cantidadSolicitada;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Boolean.parseBoolean(response.body());
    }
    
    @WebMethod(operationName = "listarfiltrarProductoPorPrecioAlMayor")
    public List<Producto> listarfiltrarProductoPorPrecioAlMayor(@WebParam(name = "id") Integer id, @WebParam(name = "RangoPrecio1") double RangoPrecio1,
            @WebParam(name = "RangoPrecio2") double RangoPrecio2) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/filtrarproductoprecioAlmayor/" + id + "/" + RangoPrecio1 + "/" + RangoPrecio2;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Producto> productos
                = mapper.readValue(json, new TypeReference<List<Producto>>() {
                });
        return productos;
    }

    @WebMethod(operationName = "obtenerProductosPorCategoria")
    public List<Producto> obtenerProductosPorCategoria(@WebParam(name = "idCategoria") Integer idCategoria) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/obtenerProductosPorCategoria/" + idCategoria;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Producto> productos
                = mapper.readValue(json, new TypeReference<List<Producto>>() {
                });
        return productos;
    }

    @WebMethod(operationName = "listaProductosPorCategoria")
    public List<Producto> listaProductosPorCategoria(@WebParam(name = "nombreCategoria") String nombre) throws IOException, InterruptedException {

        String categoriaEncoded = URLEncoder.encode(nombre, StandardCharsets.UTF_8);

        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/listaProductosPorCategoria/" + categoriaEncoded;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();
        String json = response.body();

        if (status != 200) {
            throw new RuntimeException("Error al llamar REST: HTTP " + status + " - Body: " + json);
        }

        return mapper.readValue(json, new TypeReference<List<Producto>>() {
        });
    }

}