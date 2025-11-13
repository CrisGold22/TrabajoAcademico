package pe.edu.pucp.inf30.softprog.negocio.bo;

import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cristhian Horacio
 */
public interface Gestionable<T>{
    List<T> listar();
    void insertar(T modelo);
    void actualizar(T modelo);
    T obtener(int id);
    void eliminar(int id);
}
