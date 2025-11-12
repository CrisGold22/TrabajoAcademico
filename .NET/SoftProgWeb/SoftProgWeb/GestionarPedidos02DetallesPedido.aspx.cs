using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarPedidos02DetallesPedido : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string pedidoId = Request.QueryString["id"];

                if (!string.IsNullOrEmpty(pedidoId))
                {
                    CargarDatosDeEjemplo(pedidoId);
                }
            }
        }

        private void CargarDatosDeEjemplo(string pedidoId)
        {
            lblNroOrden.Text = pedidoId;
            lblFecha.Text = "04/08/2022";
            lblRazonSocial.Text = "TELEFONICA DEL PERU S.A.A.";
            lblRUC.Text = "20131412038";

            var productos = new List<object>
            {
                new { Cantidad = 1, Codigo = "PROD001", Descripcion = "Producto A", PrecioUnitario = 100.00, Subtotal = 100.00 },
                new { Cantidad = 2, Codigo = "PROD002", Descripcion = "Producto B", PrecioUnitario = 135.59, Subtotal = 271.18 }
            };
            gvProductos.DataSource = productos;
            gvProductos.DataBind();

            double subtotal = 371.18;
            double igv = subtotal * 0.18;
            double total = subtotal + igv;

            lblSubtotal.Text = subtotal.ToString("C");
            lblIGV.Text = igv.ToString("C");
            lblTotal.Text = total.ToString("C");
        }
    }
}