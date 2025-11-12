/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import pe.edu.pucp.inf30.softprog.utils.TipoDB;

/**
 *
 * @author Cristhian Horacio
 */
public class Db2DBManager extends DBManager {
    private static Db2DBManager instancia;
    
    protected Db2DBManager() {}
    
    protected Db2DBManager(String host, int puerto, String esquema, 
                          String usuario, String password) {
        super(host, puerto, esquema, usuario, password, TipoDB.Db2);
    }
    
    static synchronized Db2DBManager getInstance(String host, int puerto, 
                                                   String esquema, 
                                                   String usuario, 
                                                   String password) {
        if (instancia == null) {
            instancia = new Db2DBManager(host, puerto, esquema, usuario, 
                                           password);
        }
        return instancia;
    }
    
    @Override
    public Connection getConnection() throws SQLException, 
                                             ClassNotFoundException  {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            String cadenaConexion = cadenaConexion();
            return DriverManager.getConnection(cadenaConexion, usuario, 
                                               password);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
            throw e;
        }
    }
}