package pe.edu.pucp.inf30.softprogpruebas;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.spi.DirObjectFactory;
import pe.edu.pucp.inf30.softprog.dbmanager.*;

/**
 *
 * @author Cristhian Horacio
 */
public class SoftProgPruebas {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        DBManager dbManager = DBFactoryProvider.getManager(); 
        try(Connection connection = dbManager.getConnection()){
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexion establecida correctamente.");
            } else {
                System.out.println("No se pudo establecer la conexion.");
            }
        }
        catch(SQLException e){
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }        
    }
}
