/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Genero;
import java.util.Date;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.LocalDateTimeAdapter;

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
    private LocalDateTime fechaNacimiento;
    private int telefono;

    public Persona() {

    }

    public Persona(String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, LocalDateTime fechaNacimiento, int telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
    }

    public Persona(String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, LocalDateTime fechaNacimiento, int telefono, int id, boolean activo) {
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

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @JsonIgnore
    public String getGeneroString() {
        if (this.genero == null) {
            return null;  // o "" si prefieres cadena vacía
        }
        return this.genero.name(); // "MASCULINO", "FEMENINO", etc.
    }

    public void setGeneroString(String generoStr) {
        if (generoStr == null || generoStr.isEmpty()) {
            this.genero = null;
        } else {
            this.genero = Genero.valueOf(generoStr); // asegúrate que coincide con los nombres del enum
        }
    }

}
