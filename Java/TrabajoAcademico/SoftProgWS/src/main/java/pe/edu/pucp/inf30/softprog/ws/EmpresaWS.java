/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
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
import pe.edu.pucp.inf30.softprog.modelo.persona.Empresa;

/**
 *
 * @author
 */
@WebService(serviceName = "EmpresaWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class EmpresaWS {

    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String NOMBRE_RESOURCE = "empresa";
    private final ObjectMapper mapper;

    public EmpresaWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @WebMethod(operationName = "listarEmpresa")
    public List<Empresa> listarEmpresa()
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Empresa> empresa
                = mapper.readValue(json, new TypeReference<List<Empresa>>() {
                });
        return empresa;
    }

    @WebMethod(operationName = "insertarEmpresa")
    public void insertarEmpresa(@WebParam(name = "empresa") Empresa empresa) throws JsonProcessingException, IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(empresa);
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

    @WebMethod(operationName = "crearEmpresaValidandoCodigoPostal")
    public void crearEmpresaValidandoCodigoPostal(@WebParam(name = "empresa") Empresa empresa) throws JsonProcessingException, IOException, InterruptedException {

        String json = mapper.writeValueAsString(empresa);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/crearValidandoCodigoPostal";
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "actualizarEmpresa")
    public void actualizarEmpresa(@WebParam(name = "empresa") Empresa empresa) throws JsonProcessingException, IOException, InterruptedException {
        //ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(empresa);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + empresa.getId();
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "obtenerEmpresa")
    public Empresa obtenerEmpresa(@WebParam(name = "id") int id) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        //ObjectMapper mapper= new ObjectMapper();
        Empresa empresa = mapper.readValue(json, Empresa.class);
        return empresa;
    }

    @WebMethod(operationName = "eliminarEmpresa")
    public String eliminarEmpresa(@WebParam(name = "id") int id)
            throws IOException, InterruptedException {

        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("DELETE", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 204) {
            return "Empresa desactivada correctamente.";
        }

        throw new RuntimeException("Error al eliminar: " + response.body());
    }

    @WebMethod(operationName = "listarEmpresasActivos")
    public List<Empresa> listarEmpresasActivos()
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/activos";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Empresa> empresa
                = mapper.readValue(json, new TypeReference<List<Empresa>>() {
                });
        return empresa;
    }

    @WebMethod(operationName = "listarEmpresasNoActivos")
    public List<Empresa> listarEmpresasNoActivos()
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/noactivos";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Empresa> empresa
                = mapper.readValue(json, new TypeReference<List<Empresa>>() {
                });
        return empresa;
    }

    @WebMethod(operationName = "listarEmpresasPorCliente")
    public List<Empresa> listarEmpresasPorCliente(@WebParam(name = "id") int id)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/empresaporcliente/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Empresa> empresa
                = mapper.readValue(json, new TypeReference<List<Empresa>>() {
                });
        return empresa;
    }

    @WebMethod(operationName = "listarEmpresasPorClienteActivas")
    public List<Empresa> listarEmpresasPorClienteActivas(@WebParam(name = "id") int id)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/listaclienteactivo/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Empresa> empresa
                = mapper.readValue(json, new TypeReference<List<Empresa>>() {
                });
        return empresa;
    }

    @WebMethod(operationName = "listarEmpresasPorClienteNoActivas")
    public List<Empresa> listarEmpresasPorClienteNoActivas(@WebParam(name = "id") int id)
            throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/listaclientenoactivo/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response
                = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Empresa> empresa
                = mapper.readValue(json, new TypeReference<List<Empresa>>() {
                });
        return empresa;
    }

    @WebMethod(operationName = "validarEmpresa")
    public boolean validarEmpresa(@WebParam(name = "id") int id) throws IOException, InterruptedException {
        String url = this.urlBase + "/" + this.NOMBRE_RESOURCE + "/validarEmpresa/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody()) // :v
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Boolean.parseBoolean(response.body());
    }

//    @Override
//    public List<Empresa> listarEmpresasActivos() {
//        return this.empresaDAO.listarEmpresasActivos();
//    }
//
//    @Override
//    public List<Empresa> listarEmpresasNoActivos() {
//        return this.empresaDAO.listarEmpresasNoActivos();
//    }
//    @Override
//    public List<Empresa> listarEmpresasPorCliente(int id) {
//        return this.empresaDAO.listarEmpresasPorCliente(id);
//    }
//    @Override
//    public List<Empresa> listarEmpresasPorClienteActivas(int id) {
//        return this.empresaDAO.listarEmpresasPorClienteActivas(id);
//    }
//    @Override
//    public List<Empresa> listarEmpresasPorClienteNoActivas(int id) {
//        return this.empresaDAO.listarEmpresasPorClienteNoActivas(id);
//    }
}
