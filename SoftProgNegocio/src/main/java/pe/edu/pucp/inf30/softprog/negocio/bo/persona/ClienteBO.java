/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.bo.persona;

import pe.edu.pucp.inf30.softprog.modelo.persona.ClienteDTO;
import pe.edu.pucp.inf30.softprog.negocio.bo.Gestionable;

/**
 *
 * @author Cristhian Horacio
 */
public interface ClienteBO extends Gestionable<ClienteDTO> {
    ClienteDTO buscarPorDNI(String dni);
}
