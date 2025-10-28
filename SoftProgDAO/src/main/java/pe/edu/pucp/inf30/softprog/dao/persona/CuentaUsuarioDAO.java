/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dao.persona;

import pe.edu.pucp.inf30.softprog.dao.Persistible;
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;

/**
 *
 * @author Cristhian Horacio
 */
public interface CuentaUsuarioDAO extends Persistible<CuentaUsuario, Integer> {
    boolean login(String email, String password);
}
