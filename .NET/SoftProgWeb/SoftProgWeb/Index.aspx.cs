using SoftProgWeb.SoftProgWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class Index : System.Web.UI.Page
    {
        private readonly ProductoWSClient productoWS;
        private readonly LineaCarritoWSClient lineaCarritoWS;

        public Index()
        {
            productoWS = new ProductoWSClient();
            lineaCarritoWS = new LineaCarritoWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnAgregarProducto_Click(object sender, EventArgs e)
        {
            int productoId = Convert.ToInt32(Request.Form["productoId"]);
            producto producto = this.productoWS.obtenerProducto(productoId);
            //Console.WriteLine(producto.descripcion);
            lineaCarrito lineaCarrito = new lineaCarrito();
            lineaCarrito.producto = producto;
            
            this.lineaCarritoWS.ActualizarLineaCarrito(lineaCarrito);
            Response.Redirect("MiCarrito.aspx");
        }
    }
}