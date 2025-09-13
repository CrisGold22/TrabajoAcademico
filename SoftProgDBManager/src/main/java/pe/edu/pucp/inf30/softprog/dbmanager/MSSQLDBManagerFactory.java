/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dbmanager;

/**
 *
 * @author Cristhian Horacio
 */
public class MSSQLDBManagerFactory extends DBManagerFactory{

    @Override
    public DBManager crearDBManager(String host, int puerto, String esquema,
            String usuario, String password) {
        return MSSQLDBManager.getInstance(host, puerto, esquema, usuario, password);
    }
    
}