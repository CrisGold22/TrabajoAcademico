using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using PUCP.Inf30.SoftProg.Modelo.Venta.VentaUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.VentaModelo
{
    public class CarritoCompras:Registro
    {
        public List<LineaCarrito> LineasCarrito {  get; set; }
        public double TotalParcial { get; set; }
        public double TotalConDescuento { get; set; }
        public EstadoCarrito Estado;
        public int IdCliente { get; set; }

        public CarritoCompras()
        {
            LineasCarrito = new List<LineaCarrito>();
        }

        public CarritoCompras(List<LineaCarrito> productos, double totalParcial, double totalConDescuento, EstadoCarrito estado, int idCliente)
        {
            this.LineasCarrito = productos;
            this.TotalParcial = totalParcial;
            this.TotalConDescuento = totalConDescuento;
            this.Estado = estado;
            this.IdCliente = idCliente;
        }

        public CarritoCompras(List<LineaCarrito> productos, double totalParcial, double totalConDescuento, 
            EstadoCarrito estado, int idCliente, int id, bool activo) : base(id, activo)
        {
           
            this.LineasCarrito = productos;
            this.TotalParcial = totalParcial;
            this.TotalConDescuento = totalConDescuento;
            this.Estado = estado;
            this.IdCliente = idCliente;
        }
        public String getEstadoString()
        {
            String estado = " ";
            switch (this.Estado)
            {
                case EstadoCarrito.VACIO:
                    estado = "CARRITO_VACIO";
                    break;  
                case EstadoCarrito.EN_PROCESO:
                    estado = "EN_PROCESO";
                    break;
                case EstadoCarrito.EN_REVISION:
                    estado = "EN_REVISION";
                    break;
                case EstadoCarrito.CONFIRMADO:
                    estado = "CONFIRMADO";
                    break;
                case EstadoCarrito.CANCELADO:
                    estado = "CANCELADO";
                    break;
                case EstadoCarrito.EXPIRADO:
                    estado = "EXPIRADO";
                    break;
                case EstadoCarrito.COMPLETADO:
                    estado = "COMPLETADO";
                    break;
                case EstadoCarrito.PENDIENTE:
                    estado = "PENDIENTE"; 
                    break;
                case EstadoCarrito.PROCESADO:
                    estado = "PROCESADO";
                    break;
            }
            return estado;
        }

        public void setEstadoString(String estado)
        {
            switch (estado)
            {
                case "CARRITO_VACIO":
                    this.Estado = EstadoCarrito.VACIO;
                    break;
                case "EN_PROCESO":
                    this.Estado = EstadoCarrito.EN_PROCESO;
                    break;
                case "EN_REVISION":
                    this.Estado = EstadoCarrito.EN_REVISION;
                    break;
                case "CONFIRMADO":
                    this.Estado = EstadoCarrito.CONFIRMADO;
                    break;
                case "CANCELADO":
                    this.Estado = EstadoCarrito.CANCELADO;
                    break;
                case "EXPIRADO":
                    this.Estado = EstadoCarrito.EXPIRADO;
                    break;
                case "COMPLETADO":
                    this.Estado = EstadoCarrito.COMPLETADO;
                    break;
                case "PENDIENTE":
                    this.Estado = EstadoCarrito.PENDIENTE;
                    break;
                case "PROCESADO":
                    this.Estado = EstadoCarrito.PROCESADO;
                    break;
            }
        }
    }
}
