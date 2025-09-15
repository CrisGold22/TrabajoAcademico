/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.dbmanager;

import java.util.ResourceBundle;
import pe.edu.pucp.inf30.softprog.utils.TipoDB;

/**
 *
 * @author Cristhian Horacio
 */
public class DBFactoryProvider {
    private static DBManager instancia;

    public static synchronized DBManager getManager() {
        if (instancia == null) {
            ResourceBundle properties = ResourceBundle.getBundle("jdbc");

            String host = properties.getString("db.host");
            int puerto = Integer.parseInt(properties.getString("db.puerto"));
            String esquema = properties.getString("db.esquema");
            String usuario = properties.getString("db.usuario");
            String password = properties.getString("db.password");
            TipoDB tipo = TipoDB.valueOf(properties.getString("db.tipo"));

            DBManagerFactory factory;
            switch (tipo) {
                case MySQL -> factory = new MySQLDBManagerFactory();
                case MSSQL -> factory = new MSSQLDBManagerFactory();
                default -> throw new IllegalArgumentException("Tipo de DB no "
                        + "soportado: " + tipo);
            }

            instancia = factory.crearDBManager(host, puerto, esquema, usuario, 
                                               password);
        }
        
        return instancia;
    }
}
