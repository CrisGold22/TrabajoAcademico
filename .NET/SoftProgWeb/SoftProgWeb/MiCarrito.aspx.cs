using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.CarritoComprasWS;
using SoftProgWeb.LineaCarritoWS;
using Exception = System.Exception;

namespace SoftProgWeb
{
    public partial class MiCarrito : System.Web.UI.Page
    {
        private LineaCarritoWSClient lineaCarritoWS;
        private BindingList<lineaCarrito> lineasCarrito
        {
            get { return (BindingList<lineaCarrito>)ViewState["lineasCarrito"]; }
            set { ViewState["lineasCarrito"] = value; }
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            lineaCarritoWS = new LineaCarritoWSClient();

            if (!IsPostBack)
            {
                int idCarrito = ObtenerIdCarritoDelClienteActual();
                var lista = lineaCarritoWS.ListarLineaCarritoPorIdCarrito(idCarrito);
                lineasCarrito = lista != null
                    ? new BindingList<lineaCarrito>(new List<lineaCarrito>(lista))
                    : new BindingList<lineaCarrito>();

                gvCarrito.DataSource = lineasCarrito;
                gvCarrito.DataBind();

                ActualizarTotal();
            }
        }

        protected void gvCarrito_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int index = Convert.ToInt32(e.CommandArgument);
            var linea = lineasCarrito[index]; 

            if (e.CommandName == "Incrementar")
            {
                linea.cantidad++;
                linea.subTotal = linea.cantidad * linea.precioVolumen;
                lineaCarritoWS.ActualizarLineaCarrito(linea);
            }
            else if (e.CommandName == "Decrementar" && linea.cantidad > 1)
            {
                linea.cantidad--;
                linea.subTotal = linea.cantidad * linea.precioVolumen;
                lineaCarritoWS.ActualizarLineaCarrito(linea);
            }
            else if (e.CommandName == "Eliminar")
            {
                if (index >= 0 && index < lineasCarrito.Count)
                {
                    lineaCarritoWS.EliminarLineaCarrito(lineasCarrito[index].id);
                    lineasCarrito.RemoveAt(index);
                }
            }

            gvCarrito.DataSource = lineasCarrito;
            gvCarrito.DataBind();
            ActualizarTotal();
        }

        private void ActualizarTotal()
        {
            double subtotal = 0;
            foreach (var linea in lineasCarrito)
                subtotal += linea.subTotal;

            double descuento = subtotal * 0.01; // ejemplo: 1%
            double total = subtotal - descuento;

            lblSubtotal.Text = "S/ " + subtotal.ToString("0.00");
            lblDescuento.Text = "S/ " + descuento.ToString("0.00");
            lblTotal.Text = "S/ " + total.ToString("0.00");
        }

        private int ObtenerIdCarritoDelClienteActual()
        {
            if (Session["IdCliente"] != null)
            {
                int idCliente = (int)Session["IdCliente"];
                CarritoComprasWSClient carritoWS = new CarritoComprasWSClient();
                return carritoWS.obtenerCarritoComprasPorCliente(idCliente).id;
            }
            return 0;
        }

        protected void btnContinuar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Entrega.aspx");
        }
    }
}