using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.VentaModelo
{
    public class LineaCarrito: Registro
    {
        public int IdProducto {  get; set; }
        public int IdCarritoCompras { get; set; }
        public int Cantidad { get; set; }
        public double PrecioVolumen { get; set; }
        public double SubTotal { get; set; }

        public LineaCarrito()
        {

        }

        public LineaCarrito(int idProducto, int idCarritoCompras, int cantidad, double precioVolumen, double subTotal)
        {
            this.IdProducto = idProducto;
            this.IdCarritoCompras = idCarritoCompras;
            this.Cantidad = cantidad;
            this.PrecioVolumen = precioVolumen;
            this.SubTotal = subTotal;
        }

        public LineaCarrito(int idProducto, int idCarritoCompras, int cantidad, double precioVolumen, double subTotal, 
            int id, bool activo):base(id,activo)
        {
            
            this.IdProducto = idProducto;
            this.IdCarritoCompras = idCarritoCompras;
            this.Cantidad = cantidad;
            this.PrecioVolumen = precioVolumen;
            this.SubTotal = subTotal;
        }
    }
}
