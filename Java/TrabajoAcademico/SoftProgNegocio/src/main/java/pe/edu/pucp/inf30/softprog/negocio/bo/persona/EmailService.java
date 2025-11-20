/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.bo.persona;

/**
 *
 * @author Cristhian Horacio
 */
public interface EmailService {
    void enviarCorreo(String para, String asunto, String cuerpoHtml);
}
