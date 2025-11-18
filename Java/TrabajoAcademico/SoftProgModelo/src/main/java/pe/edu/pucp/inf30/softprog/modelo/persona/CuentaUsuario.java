/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import java.security.Timestamp;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;

/**
 *
 * @author Cristhian Horacio
 */
public class CuentaUsuario extends Registro{
    private String username;
    private String password;
    private String resetToken;      // token temporal
    private Timestamp resetTokenExpira;

    public CuentaUsuario(){
        
    }

    public CuentaUsuario(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public CuentaUsuario(String username, String password, int id, boolean activo) {
        super(id, activo);
        this.username = username;
        this.password = password;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Timestamp getResetTokenExpira() {
        return resetTokenExpira;
    }

    public void setResetTokenExpira(Timestamp resetTokenExpira) {
        this.resetTokenExpira = resetTokenExpira;
    }
  
}
