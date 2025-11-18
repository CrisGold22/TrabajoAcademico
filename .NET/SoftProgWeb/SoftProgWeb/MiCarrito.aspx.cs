using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class MiCarrito : System.Web.UI.Page
    {
        private CarritoComprasWSClient carritoWS;

        private BindingList<lineaCarrito> lineasCarrito
        {
            get { return (BindingList<lineaCarrito>)ViewState["lineasCarrito"]; }
            set { ViewState["lineasCarrito"] = value; }
        }



        protected void Page_Load(object sender, EventArgs e)
        {

            carritoWS = new CarritoComprasWSClient();

            if (!IsPostBack)
            {
                CargarCarrito();
            }
        }

        private void CargarCarrito()
        {
            int idCarrito = ObtenerIdCarritoDelClienteActual();

            var lista = carritoWS.listarLineaCarritoPorIdCarrito(idCarrito);

            lineasCarrito = lista != null
                ? new BindingList<lineaCarrito>(lista.ToList())
                : new BindingList<lineaCarrito>();

            gvCarrito.DataSource = lineasCarrito;
            gvCarrito.DataBind();

            ActualizarTotal();
        }

        protected void gvCarrito_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Incrementar" || e.CommandName == "Decrementar")
            {
                int index = Convert.ToInt32(e.CommandArgument);
                var linea = lineasCarrito[index];

                if (e.CommandName == "Incrementar")
                {
                    linea.cantidad++;
                }
                else if (e.CommandName == "Decrementar" && linea.cantidad > 1)
                {
                    linea.cantidad--;
                }

                // Recalcular subtotal
                linea.subTotal = linea.cantidad * linea.precio;

                // Actualizar en BD 
                carritoWS.actualizarCarritoCompras(linea.carritoCompras);

                // Refrescar tabla
                gvCarrito.DataSource = lineasCarrito;
                gvCarrito.DataBind();

                ActualizarTotal();
            }
            else if (e.CommandName == "Eliminar")
            {
                int idLinea = Convert.ToInt32(e.CommandArgument);

                // Nuevo método en CarritoComprasWS
                carritoWS.eliminarCarritoCompras(idLinea);

                // Quitar de lista local
                var linea = lineasCarrito.First(x => x.id == idLinea);
                lineasCarrito.Remove(linea);

                gvCarrito.DataSource = lineasCarrito;
                gvCarrito.DataBind();

                ActualizarTotal();
            }
        }

        private void ActualizarTotal()
        {
            double subtotal = lineasCarrito.Sum(l => l.subTotal);
            double descuento = subtotal * 0.01;
            double total = subtotal - descuento;

            lblSubtotal.Text = $"S/ {subtotal:0.00}";
            lblDescuento.Text = $"S/ {descuento:0.00}";
            lblTotal.Text = $"S/ {total:0.00}";
        }

        private int ObtenerIdCarritoDelClienteActual()
        {
            if (Session["IdCliente"] != null)
            {
                int idCliente = (int)Session["IdCliente"];

                carritoWS = new CarritoComprasWSClient();

                var carrito = carritoWS.obtenerCarritoDeCliente(idCliente);

                return carrito.id;
            }

            throw new System.Exception("No se encontró el IdCliente en sesión.");
        }
        protected void btnContinuar_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Entrega.aspx");
        }

    }
}