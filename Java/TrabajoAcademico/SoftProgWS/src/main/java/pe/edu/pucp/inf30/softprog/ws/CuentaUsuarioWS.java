package pe.edu.pucp.inf30.softprog.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.CuentaUsuarioBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.CuentaUsuarioBOImpl;

/**
 *
 * @author chris
 */
@WebService(serviceName = "CuentaUsuarioWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class CuentaUsuarioWS {

    private final CuentaUsuarioBO cuentaBO;
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "cuentas";
    private final ObjectMapper mapper;

    public static class LoginRequestDTO {

        public String email;
        public String password;

        public LoginRequestDTO() {
        }

        public LoginRequestDTO(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public CuentaUsuarioWS() {
        cuentaBO = new CuentaUsuarioBOImpl();
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @WebMethod(operationName = "listarCuentaUsuario")
    public List<CuentaUsuario> listarCuentaUsuario() throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<CuentaUsuario> cuentaUsuarios
                = mapper.readValue(json, new TypeReference<List<CuentaUsuario>>() {
                });
        return cuentaUsuarios;
    }

    @WebMethod(operationName = "insertarCuentaUsuario")
    public void insertarCuentaUsuario(@WebParam(name = "cuentaUsuario") CuentaUsuario cuentaUsuario) throws JsonProcessingException, IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
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
    public void actualizarCuentaUsuario(@WebParam(name = "cuentaUsuario") CuentaUsuario cuentaUsuario) throws JsonProcessingException, IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
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
    public CuentaUsuario obtenerCuentaUsuario(@WebParam(name = "id") int id)
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
        CuentaUsuario cuentaUsuario = mapper.readValue(json, CuentaUsuario.class);
        return cuentaUsuario;
    }

    @WebMethod(operationName = "eliminarCuentaUsuario")
    public String eliminarCuentaUsuario(@WebParam(name = "id") int id)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode == 204) {
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

    @WebMethod(operationName = "login")
    public CuentaUsuario login(
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password
    ) throws IOException, InterruptedException {

        LoginRequestDTO dto = new LoginRequestDTO(email, password);
        String json = mapper.writeValueAsString(dto);

        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/login";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();
        String body = response.body();

        if (status == 401 || status == 404) {
            // credenciales inválidas o usuario no encontrado
            return null;
        }

        if (status != 200) {
            throw new RuntimeException(
                    "Error en login REST. HTTP " + status + " - Body: " + body
            );
        }

        // parsear cuenta sin password
        CuentaUsuario cuenta
                = mapper.readValue(body, CuentaUsuario.class);

        return cuenta;
    }

    @WebMethod(operationName = "cambiarPassword")
    public boolean cambiarPassword(
            @WebParam(name = "username") String username,
            @WebParam(name = "passwordActual") String passwordActual,
            @WebParam(name = "passwordNueva") String passwordNueva
    ) {
        return cuentaBO.cambiarPassword(username, passwordActual, passwordNueva);
    }

    @WebMethod(operationName = "solicitarRecuperacionPassword")
    public void solicitarRecuperacionPassword(
            @WebParam(name = "correo") String correo
    ) {
        cuentaBO.solicitarRecuperacionPassword(correo);
    }

    @WebMethod(operationName = "obtenerCuentaUsuarioPorCorreo")
    public CuentaUsuario obtenerCuentaUsuarioPorCorreo(
            @WebParam(name = "correo") String correo
    ) {
        return cuentaBO.obtenerCuentaUsuarioPorCorreo(correo);
    }

    @WebMethod(operationName = "obtenerCuentaUsuarioPorUserName")
    public CuentaUsuario obtenerCuentaUsuarioPorUserName(
            @WebParam(name = "username") String username
    ) {
        return cuentaBO.obtenerCuentaUsuarioPorUserName(username);
    }

    @WebMethod(operationName = "obtenerPorResetToken")
    public CuentaUsuario obtenerPorResetToken(
            @WebParam(name = "token") String token
    ) {
        return cuentaBO.obtenerPorResetToken(token);
    }

    @WebMethod(operationName = "resetPasswordConToken")
    public void resetPasswordConToken(
            @WebParam(name = "token") String token,
            @WebParam(name = "nuevaPassword") String nuevaPassword
    ) {
        cuentaBO.resetPasswordConToken(token, nuevaPassword);
    }

    @WebMethod(operationName = "validarTokenRecuperacion")
    public boolean validarTokenRecuperacion(
            @WebParam(name = "token") String token
    ) {
        return cuentaBO.validarTokenRecuperacion(token);
    }
}
