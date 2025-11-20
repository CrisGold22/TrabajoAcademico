/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import java.sql.Timestamp;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;

/**
 *
 * @author Cristhian Horacio
 */
public class CuentaUsuario extends Registro{
    private String username;
    private String correo;
    private String password;
    private String resetToken;      // token temporal
    private Timestamp resetTokenExpira;
    
    public CuentaUsuario(){
        
    }
    
    public CuentaUsuario(String username, String correo, String password, String resetToken, Timestamp resetTokenExpira) {
        this.username = username;
        this.correo = correo;
        this.password = password;
        this.resetToken = resetToken;
        this.resetTokenExpira = resetTokenExpira;
    }

    public CuentaUsuario(String username, String correo, String password, String resetToken, Timestamp resetTokenExpira, int id, boolean activo) {
        super(id, activo);
        this.username = username;
        this.correo = correo;
        this.password = password;
        this.resetToken = resetToken;
        this.resetTokenExpira = resetTokenExpira;
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
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public String getCorreo(){
        return this.correo;
    }
  
}