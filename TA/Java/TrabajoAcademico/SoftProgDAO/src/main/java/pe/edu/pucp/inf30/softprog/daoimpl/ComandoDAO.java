/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.daoimpl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Cristhian Horacio
 */
public interface ComandoDAO<R> {
    R ejecutar(Connection conn) throws SQLException;
}
