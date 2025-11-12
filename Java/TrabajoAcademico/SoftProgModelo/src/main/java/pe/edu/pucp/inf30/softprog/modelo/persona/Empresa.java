/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito.CALLAO;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito.LA_VICTORIA;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito.LINCE;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito.PUEBLO_LIBRE;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito.SAN_BORJA;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito.SAN_LUIS;
import static pe.edu.pucp.inf30.softprog.modelo.venta.utils.Distrito.SAN_MIGUEL;

/**
 *
 * @author PC
 */
public class Empresa extends Registro{
    private String RUC;
    private String razonSocial;
    private String direccionFiscal;
    private String departamento;
    private String provincia;
    private Distrito distrito;
    private String telefono;
    private String email;
    private String codigoPostal;
    private Cliente cliente;

    public Empresa(){
        
    }
    
    public Empresa(String RUC, String razonSocial, String direccionFiscal, String departamento, String provincia, Distrito distrito, String telefono, String email, String codigoPostal, Cliente cliente) {
        this.RUC = RUC;
        this.razonSocial = razonSocial;
        this.direccionFiscal = direccionFiscal;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.telefono = telefono;
        this.email = email;
        this.codigoPostal = codigoPostal;
        this.cliente = cliente;
    }

    public Empresa(String RUC, String razonSocial, String direccionFiscal, String departamento, String provincia, Distrito distrito, String telefono, String email, String codigoPostal, Cliente cliente, int id, boolean activo) {
        super(id, activo);
        this.RUC = RUC;
        this.razonSocial = razonSocial;
        this.direccionFiscal = direccionFiscal;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.telefono = telefono;
        this.email = email;
        this.codigoPostal = codigoPostal;
        this.cliente = cliente;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccionFiscal() {
        return direccionFiscal;
    }

    public void setDireccionFiscal(String direccionFiscal) {
        this.direccionFiscal = direccionFiscal;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String getDistritoString() {
        String cadena = "";

        switch (this.distrito) {
            case PUEBLO_LIBRE ->
                cadena = "PUEBLO_LIBRE";
            case SAN_MIGUEL ->
                cadena = "SAN_MIGUEL";
            case SAN_BORJA ->
                cadena = "SAN_BORJA";
            case SAN_LUIS ->
                cadena = "SAN_LUIS";
            case LINCE ->
                cadena = "LINCE";
            case CALLAO ->
                cadena = "CALLAO";
            case LA_VICTORIA ->
                cadena = "LA_VICTORIA";
        }

        return cadena;
    }

    public void setDistritoString(String distrito) {
        switch (distrito) {
            case "PUEBLO_LIBRE" ->
                this.distrito = Distrito.PUEBLO_LIBRE;
            case "SAN_MIGUEL" ->
                this.distrito = Distrito.SAN_MIGUEL;
            case "SAN_BORJA" ->
                this.distrito = Distrito.SAN_BORJA;
            case "SAN_LUIS" ->
                this.distrito = Distrito.SAN_LUIS;
            case "LINCE" ->
                this.distrito = Distrito.LINCE;
            case "CALLAO" ->
                this.distrito = Distrito.CALLAO;
            case "LA_VICTORIA" ->
                this.distrito = Distrito.LA_VICTORIA;
        }
    }
}
