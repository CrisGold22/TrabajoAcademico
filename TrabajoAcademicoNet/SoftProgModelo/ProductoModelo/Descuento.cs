using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.ProductoModelo
{
    public class Descuento:Registro
    {
        public double PrecioPorVolumen { get; set; }
        public int IdProducto { get; set; }
        public int CantidadMax { get; set; }
        public int CantidadMin { get; set; }

        public Descuento()
        {

        }

        public Descuento(double precioPorVolumen, int producto, int cantidadMax, int cantidadMin)
        {
            this.PrecioPorVolumen = precioPorVolumen;
            this.IdProducto = producto;
            this.CantidadMax = cantidadMax;
            this.CantidadMin = cantidadMin;
        }

        public Descuento(double precioPorVolumen, int producto, int cantidadMax, int cantidadMin, int id, bool activo):base(id, activo)
        {
            this.PrecioPorVolumen = precioPorVolumen;
            this.IdProducto = producto;
            this.CantidadMax = cantidadMax;
            this.CantidadMin = cantidadMin;
        }
    }
}
