using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.PagoModelo
{
    public class LineaComprobantePago : Registro
    {
        public double MontoPagado { get; set; }
        public int Codigo { get; set; }
        public int Cantidad { get; set; }
        public double SubTotal { get; set; }
        public double MontoImpuesto { get; set; }
        public int IdComprobantePago { get; set; }

        public LineaComprobantePago()
        {
        }
        public LineaComprobantePago(double montoPagado, int codigo, int cantidad, double subTotal, double montoImpuesto, int idComprobantePago)
        {
            this.MontoPagado = montoPagado;
            this.Codigo = codigo;
            this.Cantidad = cantidad;
            this.SubTotal = subTotal;
            this.MontoImpuesto = montoImpuesto;
            this.IdComprobantePago = idComprobantePago;
        }
    }
}
