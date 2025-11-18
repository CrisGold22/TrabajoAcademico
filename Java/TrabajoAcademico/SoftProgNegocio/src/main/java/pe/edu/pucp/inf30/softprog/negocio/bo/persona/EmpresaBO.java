/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.bo.persona;

import java.util.List;
import pe.edu.pucp.inf30.softprog.modelo.persona.Empresa;
import pe.edu.pucp.inf30.softprog.negocio.bo.Gestionable;

/**
 *
 * @author PC
 */
public interface EmpresaBO extends Gestionable<Empresa>{
    List<Empresa> listarEmpresasActivos();
    List<Empresa> listarEmpresasNoActivos();
    List<Empresa> listarEmpresasPorCliente(int id);
    List<Empresa> listarEmpresasPorClienteActivas(int id);
    List<Empresa> listarEmpresasPorClienteNoActivas(int id);
    boolean validarEmpresa(int id);
}