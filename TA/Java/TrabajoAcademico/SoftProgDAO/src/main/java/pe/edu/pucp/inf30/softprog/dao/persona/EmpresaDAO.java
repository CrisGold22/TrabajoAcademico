/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dao.persona;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.Persistible;
import pe.edu.pucp.inf30.softprog.modelo.persona.Empresa;

/**
 *
 * @author PC
 */
public interface EmpresaDAO extends Persistible<Empresa, Integer>{
    List<Empresa> listarEmpresasActivos();
    List<Empresa> listarEmpresasNoActivos();
    List<Empresa> listarEmpresasPorCliente(int id);
    List<Empresa> listarEmpresasPorClienteActivas(int id);
    List<Empresa> listarEmpresasPorClienteNoActivas(int id);
    public boolean validarEmpresa(int id) ;
}
