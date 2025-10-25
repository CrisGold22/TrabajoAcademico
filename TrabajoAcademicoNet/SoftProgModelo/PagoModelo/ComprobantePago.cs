using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PUCP.Inf30.SoftProg.Modelo.PagoModelo.PagoUtils;
using PUCP.Inf30.SoftProg.Modelo.PagoModelo;

namespace PUCP.Inf30.SoftProg.Modelo.PagoModelo
{
    public class ComprobantePago: Registro
    {
        public List<LineaComprobantePago> LineasComprobantes;
        public DateTime FechaEmision { get; set; }
        public int RUC { get; set; }
        public double TotalSinImpuestos { get; set; }
        public double Impuestos { get; set; }
        public double TotalFinal {  get; set; }
        public MetodoPago MetodoPago;
        public int IdOrdenCompra { get; set; }
       // public double SubTotal { get; set; }
        public ComprobantePago() {
            LineasComprobantes = new List<LineaComprobantePago>();
        }
        public ComprobantePago(List<LineaComprobantePago> lineasComprobantes,
                DateTime fechaEmision, int RUC, double totalSinImpuestos, double impuestos,
                double totalFinal, MetodoPago metodoPago, int idOrdenCompra)
        {
            this.LineasComprobantes = lineasComprobantes;
            this.FechaEmision = fechaEmision;
            this.RUC = RUC;
            this.TotalSinImpuestos = totalSinImpuestos;
            this.Impuestos = impuestos;
            this.TotalFinal = totalFinal;
            this.MetodoPago = metodoPago;
            this.IdOrdenCompra = idOrdenCompra;
        }
        public String getMetodoString()
        {
            String cadena = "";

            switch (this.MetodoPago)
            {
                case MetodoPago.CONTRA_ENTREGA:
                    cadena = "CONTRA_ENTREGA";
                    break;
                case MetodoPago.VIRTUAL:
                    cadena = "VIRTUAL";
                    break;
            }

            return cadena;
        }

        public void setMetodoString(String metodo)
        {
            switch (metodo)
            {
                case "CONTRA_ENTREGA":
                    this.MetodoPago = MetodoPago.CONTRA_ENTREGA;
                    break;
                case "VIRTUAL": 
                    this.MetodoPago = MetodoPago.VIRTUAL;
                    break;
            }
        }
    }
}
