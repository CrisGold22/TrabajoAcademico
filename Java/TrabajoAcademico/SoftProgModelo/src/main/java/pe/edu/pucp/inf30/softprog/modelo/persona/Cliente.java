/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import java.time.LocalDate;
import java.util.Date;
import pe.edu.pucp.inf30.softprog.modelo.base.Registro;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.CategoriaCliente;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.Genero;

/**
 *
 * @author Cristhian Horacio
 */
public class Cliente extends Persona {

    private double lineaCredito;
    private CategoriaCliente categoria;
    private int numeroPedidosHistorico;
    private int numeroPedidosMensualPro;
    private CuentaUsuario cuenta;

    public Cliente() {

    }

    public Cliente(double lineaCredito, CategoriaCliente categoria, int numeroPedidosHistorico, int numeroPedidosMensualPro, CuentaUsuario cuenta) {
        this.lineaCredito = lineaCredito;
        this.categoria = categoria;
        this.numeroPedidosHistorico = numeroPedidosHistorico;
        this.numeroPedidosMensualPro = numeroPedidosMensualPro;
        this.cuenta = cuenta;
    }

    public Cliente(double lineaCredito, CategoriaCliente categoria, int numeroPedidosHistorico, int numeroPedidosMensualPro, CuentaUsuario cuenta, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, LocalDate fechaNacimiento, int telefono) {
        super(dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono);
        this.lineaCredito = lineaCredito;
        this.categoria = categoria;
        this.numeroPedidosHistorico = numeroPedidosHistorico;
        this.numeroPedidosMensualPro = numeroPedidosMensualPro;
        this.cuenta = cuenta;
    }

    public Cliente(double lineaCredito, CategoriaCliente categoria, int numeroPedidosHistorico, int numeroPedidosMensualPro, CuentaUsuario cuenta, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, LocalDate fechaNacimiento, int telefono, int id, boolean activo) {
        super(dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono, id, activo);
        this.lineaCredito = lineaCredito;
        this.categoria = categoria;
        this.numeroPedidosHistorico = numeroPedidosHistorico;
        this.numeroPedidosMensualPro = numeroPedidosMensualPro;
        this.cuenta = cuenta;
    }
    
    public double getLineaCredito() {
        return lineaCredito;
    }

    public void setLineaCredito(double lineaCredito) {
        this.lineaCredito = lineaCredito;
    }

    public CategoriaCliente getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCliente categoria) {
        this.categoria = categoria;
    }

    public int getNumeroPedidosHistorico() {
        return numeroPedidosHistorico;
    }

    public void setNumeroPedidosHistorico(int numeroPedidosHistorico) {
        this.numeroPedidosHistorico = numeroPedidosHistorico;
    }

    public int getNumeroPedidosMensualPro() {
        return numeroPedidosMensualPro;
    }

    public void setNumeroPedidosMensualPro(int numeroPedidosMensualPro) {
        this.numeroPedidosMensualPro = numeroPedidosMensualPro;
    }

    public String getCategoriaCliente() {
        String cadena = "";

        switch (this.categoria) {
            case CATERING ->
                cadena = "CATERING";
            case REVENDEDOR ->
                cadena = "REVENDEDOR";
            case RESTAURANTE ->
                cadena = "RESTAURANTE";
            case HOTEL ->
                cadena = "HOTEL";
            case GOBIERNO ->
                cadena = "GOBIERNO";
            case DISTRIBUIDOR ->
                cadena = "DISTRIBUIDOR";
        }
        return cadena;
    }

    public void setCategoriaCliente(String categoria) {
        switch (categoria) {
            case "CATERING" ->
                this.categoria = CategoriaCliente.CATERING;
            case "REVENDEDOR" ->
                this.categoria = CategoriaCliente.REVENDEDOR;
            case "RESTAURANTE" ->
                this.categoria = CategoriaCliente.RESTAURANTE;
            case "HOTEL" ->
                this.categoria = CategoriaCliente.HOTEL;
            case "GOBIERNO" ->
                this.categoria = CategoriaCliente.GOBIERNO;
            case "DISTRIBUIDOR" ->
                this.categoria = CategoriaCliente.DISTRIBUIDOR;
        }
    }

    public CuentaUsuario getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaUsuario cuenta) {
        this.cuenta = cuenta;
    }
    
    
}
