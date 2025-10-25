using PUCP.Inf30.SoftProg.Modelo.PersonaModelo.PersonaUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.PersonaModelo
{
    public class Cliente:Persona
    {
        public double LineaCredito { get; set; }
        public CategoriaCliente Categoria;
        public int NumeroPedidosHistorico { get; set; }
        public int NumeroPedidosMensualPro { get; set; }

        public Cliente()
        {

        }

        public Cliente(double lineaCredito, CategoriaCliente categoria, int numeroPedidosHistorico, int numeroPedidosMensualPro)
        {
            this.LineaCredito = lineaCredito;
            this.Categoria = categoria;
            //        this.cuentaUsuario = cuentaUsuario;
            this.NumeroPedidosHistorico = numeroPedidosHistorico;
            this.NumeroPedidosMensualPro = numeroPedidosMensualPro;
        }

        public Cliente(double lineaCredito, CategoriaCliente categoria, int numeroPedidosHistorico, 
            int numeroPedidosMensualPro, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, 
            Genero genero, DateTime fechaNacimiento, int telefono):base(dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono)
        {
            
            this.LineaCredito = lineaCredito;
            this.Categoria = categoria;
            //        this.cuentaUsuario = cuentaUsuario;
            this.NumeroPedidosHistorico = numeroPedidosHistorico;
            this.NumeroPedidosMensualPro = numeroPedidosMensualPro;
        }

        public Cliente(double lineaCredito, CategoriaCliente categoria, int numeroPedidosHistorico, int numeroPedidosMensualPro,
            String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Genero genero, DateTime fechaNacimiento, 
            int telefono, int id, bool activo): base(dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono, id, activo)
        {
           
            this.LineaCredito = lineaCredito;
            this.Categoria = categoria;
            //        this.cuentaUsuario = cuentaUsuario;
            this.NumeroPedidosHistorico = numeroPedidosHistorico;
            this.NumeroPedidosMensualPro = numeroPedidosMensualPro;
        }
    }
}
