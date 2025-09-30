/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.persona;

import pe.edu.pucp.inf30.softprog.modelo.base.RegistroDTO;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.CategoriaCliente;

/**
 *
 * @author Cristhian Horacio
 */
public class ClienteDTO extends RegistroDTO{
    private double lineaCredito;
    private CategoriaCliente categoria;
    private CuentaUsuarioDTO cuentaUsuario;
    private int numeroPedidosHistorico;
    private int numeroPedidosMensualPro;
    
    public ClienteDTO(){
        
    }

    public ClienteDTO(double lineaCredito, CategoriaCliente categoria, CuentaUsuarioDTO cuentaUsuario, int numeroPedidosHistorico, int numeroPedidosMensualPro) {
        this.lineaCredito = lineaCredito;
        this.categoria = categoria;
        this.cuentaUsuario = cuentaUsuario;
        this.numeroPedidosHistorico = numeroPedidosHistorico;
        this.numeroPedidosMensualPro = numeroPedidosMensualPro;
    }

    public ClienteDTO(double lineaCredito, CategoriaCliente categoria, CuentaUsuarioDTO cuentaUsuario, int numeroPedidosHistorico, int numeroPedidosMensualPro, int id, boolean activo) {
        super(id, activo);
        this.lineaCredito = lineaCredito;
        this.categoria = categoria;
        this.cuentaUsuario = cuentaUsuario;
        this.numeroPedidosHistorico = numeroPedidosHistorico;
        this.numeroPedidosMensualPro = numeroPedidosMensualPro;
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

    public CuentaUsuarioDTO getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setCuentaUsuario(CuentaUsuarioDTO cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
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
    
    
}
