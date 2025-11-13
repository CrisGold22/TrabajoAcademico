/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.persona.Empresa;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.EmpresaBO;
import pe.edu.pucp.inf30.softprog.negocio.boimpl.persona.EmpresaBOImpl;

/**
 *
 * @author PC
 */

@WebService(serviceName = "EmpresaWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class EmpresaWS {
    private final EmpresaBO empresaBO;
    
    public EmpresaWS(){
        this.empresaBO = new EmpresaBOImpl();
    }

    @WebMethod(operationName = "listarEmpresa")
    public List<Empresa> listarEmpresa() {
        return empresaBO.listar();
    }
    @WebMethod(operationName = "insertarEmpresa")
    public void insertarEmpresa(@WebParam(name = "empresa")
            Empresa empresa) {
        empresaBO.insertar(empresa);
    }
    @WebMethod(operationName = "actualizarEmpresa")
    public void actualizarEmpresa(@WebParam(name = "empresa")
            Empresa empresa)  {
        empresaBO.actualizar(empresa);
    }
    @WebMethod(operationName = "obtenerEmpresa")
    public Empresa obtenerEmpresa(@WebParam(name = "id")int id) {
        return empresaBO.obtener(id);
    }
    @WebMethod(operationName = "eliminarEmpresa")
    public void eliminarEmpresa(@WebParam(name = "id")int id)  {
        empresaBO.eliminar(id);
    }
    
    @WebMethod(operationName = "listarEmpresasActivos")
    public List<Empresa> listarEmpresasActivos()  {
        return empresaBO.listarEmpresasActivos();
    }
    
    @WebMethod(operationName = "listarEmpresasNoActivos")
    public List<Empresa> listarEmpresasNoActivos()  {
        return empresaBO.listarEmpresasNoActivos();
    }
    
    @WebMethod(operationName = "listarEmpresasPorCliente")
    public List<Empresa> listarEmpresasPorCliente(@WebParam(name = "id")int id)  {
        return empresaBO.listarEmpresasPorCliente(id);
    }
    
    @WebMethod(operationName = "listarEmpresasPorClienteActivas")
    public List<Empresa> listarEmpresasPorClienteActivas(@WebParam(name = "id")int id)  {
        return empresaBO.listarEmpresasPorClienteActivas(id);
    }
    
    @WebMethod(operationName = "listarEmpresasPorClientesNoActivas")
    public List<Empresa> listarEmpresasPorClienteNoActivas(@WebParam(name = "id")int id)  {
        return empresaBO.listarEmpresasPorClienteNoActivas(id);
    }
    
    @WebMethod(operationName = "validarEmpresa")
        public void validarEmpresa(@WebParam (name = "id") int id){
            empresaBO.validarEmpresa(id);
        }

}
