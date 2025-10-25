using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using PUCP.Inf30.SoftProg.Modelo.Venta.VentaUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.VentaModelo
{
    public class OrdenCompra:Registro
    {
        public List<LineaOrdenCompra> LineasOrden {  get; set; }
        public DateTime FechaCreacion { get; set; }
        public double TotalParcial { get; set; }
        public double TotalFinal { get; set; }
        public double DescuentoTotal { get; set; }
        public int IdDetalleEnvio { get; set; }
        public EstadoOrdenCompra Estado;

        public OrdenCompra()
        {
            LineasOrden = new List<LineaOrdenCompra>();
        }

        public OrdenCompra(List<LineaOrdenCompra> lineasOrden, DateTime fechaCreacion, double totalParcial, 
            double totalFinal, double descuentoTotal, int idDetalleEnvio, EstadoOrdenCompra estado)
        {
            this.LineasOrden = LineasOrden;
            this.FechaCreacion = fechaCreacion;
            this.TotalParcial = totalParcial;
            this.TotalFinal = totalFinal;
            this.DescuentoTotal = descuentoTotal;
            this.IdDetalleEnvio = idDetalleEnvio;
            this.Estado = estado;
        }

        public OrdenCompra(List<LineaOrdenCompra> lineasOrden, DateTime fechaCreacion, double totalParcial, 
            double totalFinal, double descuentoTotal, int idDetalleEnvio, EstadoOrdenCompra estado, int id, bool activo) :base(id,activo)
        {
            this.LineasOrden = lineasOrden;
            this.FechaCreacion = fechaCreacion;
            this.TotalParcial = totalParcial;
            this.TotalFinal = totalFinal;
            this.DescuentoTotal = descuentoTotal;
            this.IdDetalleEnvio = idDetalleEnvio;
            this.Estado = estado;
        }
        public String getEstadoString()
        {
            String estado=" ";
            switch (this.Estado)
            {
                case EstadoOrdenCompra.PAGADO:
                    estado = "PAGADO";
                    break;
                case EstadoOrdenCompra.EN_PREPARACION:
                    estado = "EN_PREPARACION";
                    break;
                case EstadoOrdenCompra.ENVIADO:
                    estado = "ENVIADO";
                    break;
                case EstadoOrdenCompra.ENTREGADO:
                    estado = "ENTREGADO";
                    break;
                case EstadoOrdenCompra.CANCELADO:
                    estado = "CANCELADO";
                    break;
                case EstadoOrdenCompra.REEMBOLSADO:
                    estado = "REEMBOLSADO";
                    break;
                case EstadoOrdenCompra.EXPIRADO:
                    estado = "EXPIRADO";
                    break;
            }
            return estado;
        }

        public void setEstadoString(String estado)
        {
            switch (estado)
            {
                case "PAGADO":
                    this.Estado = EstadoOrdenCompra.PAGADO;
                    break;
                case "EN_PREPARACION":
                    this.Estado = EstadoOrdenCompra.EN_PREPARACION;
                    break;
                case "ENVIADO":
                    this.Estado = EstadoOrdenCompra.ENVIADO;
                    break;
                case "ENTREGADO":
                    this.Estado = EstadoOrdenCompra.ENTREGADO;
                    break;
                case "CANCELADO":
                    this.Estado = EstadoOrdenCompra.CANCELADO;
                    break;
                case "REEMBOLSADO":
                    this.Estado = EstadoOrdenCompra.REEMBOLSADO;
                    break;
                case "EXPIRADO":
                    this.Estado = EstadoOrdenCompra.EXPIRADO;
                    break;
            }
        }
    }
}
