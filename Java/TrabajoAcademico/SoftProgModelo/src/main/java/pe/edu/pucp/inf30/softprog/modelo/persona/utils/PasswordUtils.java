/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona.utils;

/**
 *
 * @author Cristhian Horacio
 */

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Genera un hash seguro
    public static String hashPassword(String plainPassword) {
        String salt = BCrypt.gensalt(12); // costo 12 como ejemplo
        return BCrypt.hashpw(plainPassword, salt);
    }

    // Verifica contraseña ingresada vs hash almacenado
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) return false;
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}