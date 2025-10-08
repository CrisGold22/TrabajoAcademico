/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import java.util.Date;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Cargo;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Jerarquia;
import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Genero;

/**
 *
 * @author Cristhian Horacio
 */
public class AdministradorSistemaDTO extends PersonaDTO {

    private Cargo cargo;
    private double sueldo;
    private Jerarquia rango;
    private CuentaUsuarioDTO cuentaUsuario;

    public AdministradorSistemaDTO() {

    }

    public AdministradorSistemaDTO(Cargo cargo, double sueldo, Jerarquia rango, CuentaUsuarioDTO cuentaUsuario) {
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.rango = rango;
        this.cuentaUsuario = cuentaUsuario;
    }

    public AdministradorSistemaDTO(Cargo cargo, double sueldo, Jerarquia rango, CuentaUsuarioDTO cuentaUsuario, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, Date fechaNacimiento, int telefono) {
        super(dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono);
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.rango = rango;
        this.cuentaUsuario = cuentaUsuario;
    }

    public AdministradorSistemaDTO(Cargo cargo, double sueldo, Jerarquia rango, CuentaUsuarioDTO cuentaUsuario, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, Date fechaNacimiento, int telefono, int id, boolean activo) {
        super(dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono, id, activo);
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.rango = rango;
        this.cuentaUsuario = cuentaUsuario;
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

    public Jerarquia getRango() {
        return rango;
    }

    public void setRango(Jerarquia rango) {
        this.rango = rango;
    }

    public CuentaUsuarioDTO getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setCuentaUsuario(CuentaUsuarioDTO cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
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

    public String getRangoString() {
        String cadena = "";

        switch (this.rango) {
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

    public void setRangoString(String rango) {
        switch (rango) {
            case "COMPLETO" ->
                this.rango = Jerarquia.COMPLETO;
            case "PARCIAL" ->
                this.rango = Jerarquia.PARCIAL;
        }
    }

}
