/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.bo.persona;

import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;
import pe.edu.pucp.inf30.softprog.negocio.bo.Gestionable;

/**
 *
 * @author Cristhian Horacio
 */
public interface CuentaUsuarioBO extends Gestionable<CuentaUsuario> {
    boolean login(String email, String password);
    void solicitarRecuperacionPassword(String correo);
    CuentaUsuario obtenerCuentaUsuarioPorCorreo(String correo);
    CuentaUsuario obtenerCuentaUsuarioPorUserName(String username);
    CuentaUsuario obtenerPorResetToken(String token);
    boolean cambiarPassword(String username, String passwordActual, String passwordNueva);
    void resetPasswordConToken(String token, String nuevaPassword);
    boolean validarTokenRecuperacion(String token);
}
