using PUCP.Inf30.SoftProg.Modelo.BaseModelo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PUCP.Inf30.SoftProg.Modelo.VentaModelo
{
    public class LineaOrdenCompra:Registro
    {
        public double MontoPagado {  get; set; }
        public int Codigo { get; set; }
        public int Cantidad { get; set; }
        public double PrecioUnitario { get; set; }
        public double SubTotal { get; set; }
        public int IdProducto { get; set; }
        public int IdOrdenCompra { get; set; }
        public int IdCarrito { get; set; }

        public LineaOrdenCompra()
        {

        }

        public LineaOrdenCompra(double montoPagado, int codigo, int cantidad, double precioUnitario, double subTotal, 
            int idProducto, int idOrdenCompra, int idCarrito)
        {
            this.MontoPagado = montoPagado;
            this.Codigo = codigo;
            this.Cantidad = cantidad;
            this.PrecioUnitario = precioUnitario;
            this.SubTotal = subTotal;
            this.IdProducto = idProducto;
            this.IdOrdenCompra = idOrdenCompra;
            this.IdCarrito = idCarrito;
        }

        public LineaOrdenCompra(double montoPagado, int codigo, int cantidad, double precioUnitario, double subTotal, 
            int idProducto, int idOrdenCompra, int idCarrito, int id, bool activo): base(id,activo)
        {
            this.MontoPagado = montoPagado;
            this.Codigo = codigo;
            this.Cantidad = cantidad;
            this.PrecioUnitario = precioUnitario;
            this.SubTotal = subTotal;
            this.IdProducto = idProducto;
            this.IdOrdenCompra = idOrdenCompra;
            this.IdCarrito = idCarrito;
        }
    }
}
