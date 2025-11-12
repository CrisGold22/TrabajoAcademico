/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.persona;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.persona.AdministradorSistemaDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.AdministradorSistemaDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.AdministradorSistema;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.AdministradorSistemaBO;

/**
 *
 * @author Cristhian Horacio
 */
public class AdministradorSistemaBOImpl implements AdministradorSistemaBO{
    private final AdministradorSistemaDAO administrador;
    
    public AdministradorSistemaBOImpl(){
        administrador = new AdministradorSistemaDAOImpl();
    }
    
    @Override
    public List<AdministradorSistema> listar() {
        return this.administrador.leerTodos();
    }

    @Override
    public void insertar(AdministradorSistema modelo) {
        this.administrador.crear(modelo);
    }

    @Override
    public void actualizar(AdministradorSistema modelo) {
        this.administrador.actualizar(modelo);
    }

    @Override
    public AdministradorSistema obtener(int id) {
        return this.administrador.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.administrador.eliminar(id);
    }
    
}
