/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import java.time.LocalDate;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Genero;
import java.util.Date;

/**
 *
 * @author Cristhian Horacio
 */
public class Persona extends Registro {

    private String dni;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Genero genero;
    private LocalDate fechaNacimiento;
    private int telefono;

    public Persona() {

    }

    public Persona(String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, LocalDate fechaNacimiento, int telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
    }

    public Persona(String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, LocalDate fechaNacimiento, int telefono, int id, boolean activo) {
        super(id, activo);
        this.dni = dni;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getGeneroString() {
        String cadena = "";

        switch (this.genero) {
            case HOMBRE ->
                cadena = "HOMBRE";
            case MUJER ->
                cadena = "MUJER";
            case NO_ESPECIFICADO ->
                cadena = "NO_ESPECIFICADO";
        }

        return cadena;
    }

    public void setGeneroString(String genero) {
        switch (genero) {
            case "HOMBRE" ->
                this.genero = Genero.HOMBRE;
            case "MUJER" ->
                this.genero = Genero.MUJER;
            case "NO_ESPECIFICADO" ->
                this.genero = Genero.NO_ESPECIFICADO;
        }
    }

}
