/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import java.time.LocalDate;
import java.util.Date;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Cargo;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Jerarquia;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Genero;

/**
 *
 * @author Cristhian Horacio
 */
public class AdministradorSistema extends Persona {

    private Cargo cargo;
    private double sueldo;
    private Jerarquia jerarquia;
    private CuentaUsuario cuenta;

    public AdministradorSistema() {

    }

    public AdministradorSistema(Cargo cargo, double sueldo, Jerarquia jerarquia, CuentaUsuario cuenta) {
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.jerarquia = jerarquia;
        this.cuenta = cuenta;
    }

    public AdministradorSistema(Cargo cargo, double sueldo, Jerarquia jerarquia, CuentaUsuario cuenta, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, LocalDate fechaNacimiento, int telefono) {
        super(dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono);
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.jerarquia = jerarquia;
        this.cuenta = cuenta;
    }

    public AdministradorSistema(Cargo cargo, double sueldo, Jerarquia jerarquia, CuentaUsuario cuenta, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, LocalDate fechaNacimiento, int telefono, int id, boolean activo) {
        super(dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono, id, activo);
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.jerarquia = jerarquia;
        this.cuenta = cuenta;
    }

    
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getCargoString() {
        String cadena = "";

        switch (this.cargo) {
            case ADMINISTRADOR ->
                cadena = "ADMINISTRADOR";
            case GESTOR_PEDIDOS ->
                cadena = "GESTOR_PEDIDOS";
            case GESTOR_PRODUCTOS ->
                cadena = "GESTOR_PRODUCTOS";
        }

        return cadena;
    }

    public String getJerarquiaString() {
        String cadena = "";

        switch (this.jerarquia) {
            case COMPLETO ->
                cadena = "COMPLETO";
            case PARCIAL ->
                cadena = "PARCIAL";
        }

        return cadena;
    }

    public void setCargoString(String cargo) {
        switch (cargo) {
            case "ADMINISTRADOR" ->
                this.cargo = Cargo.ADMINISTRADOR;
            case "GESTOR_PEDIDOS" ->
                this.cargo = Cargo.GESTOR_PEDIDOS;
            case "GESTOR_PRODUCTOS" ->
                this.cargo = Cargo.GESTOR_PRODUCTOS;
        }
    }

    public void setJerarquiaString(String jerarquia) {
        switch (jerarquia) {
            case "COMPLETO" ->
                this.jerarquia = Jerarquia.COMPLETO;
            case "PARCIAL" ->
                this.jerarquia = Jerarquia.PARCIAL;
        }
    }

    public Jerarquia getJerarquia() {
        return jerarquia;
    }

    public void setJerarquia(Jerarquia jerarquia) {
        this.jerarquia = jerarquia;
    }

    public CuentaUsuario getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaUsuario cuenta) {
        this.cuenta = cuenta;
    }
    
    
}
