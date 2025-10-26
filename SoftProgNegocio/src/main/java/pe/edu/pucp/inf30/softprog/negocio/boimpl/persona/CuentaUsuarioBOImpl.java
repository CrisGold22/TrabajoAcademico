/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.persona;

import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.persona.CuentaUsuarioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.CuentaUsuarioDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.CuentaUsuarioBO;

/**
 *
 * @author Cristhian Horacio
 */
public class CuentaUsuarioBOImpl implements CuentaUsuarioBO{
    private final CuentaUsuarioDAO cuenta;
    
    public CuentaUsuarioBOImpl(){
        cuenta = new CuentaUsuarioDAOImpl();
    }
    
    @Override
    public List<CuentaUsuario> listar() {
        return this.cuenta.leerTodos();
    }

    @Override
    public void insertar(CuentaUsuario modelo) {
        this.cuenta.crear(modelo);
    }

    @Override
    public void actualizar(CuentaUsuario modelo) {
        this.cuenta.actualizar(modelo);
    }

    @Override
    public CuentaUsuario obtener(int id) {
        return this.cuenta.leer(id);
    }

    @Override
    public void eliminar(int id) {
        this.cuenta.eliminar(id);
    }
    
}
