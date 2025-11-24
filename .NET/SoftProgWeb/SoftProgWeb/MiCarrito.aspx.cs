using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Reflection;
using System.Web.ModelBinding;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class MiCarrito : System.Web.UI.Page
    {
        private LineaCarritoWSClient lineaWS;
        private CarritoComprasWSClient carritoWS;

        private BindingList<lineaCarrito> lineasCarrito
        {
            get { return (BindingList<lineaCarrito>)ViewState["lineasCarrito"]; }
            set { ViewState["lineasCarrito"] = value; }
        }



        protected void Page_Load(object sender, EventArgs e)
        {
            lineaWS = new LineaCarritoWSClient();
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
            int index = Convert.ToInt32(e.CommandArgument);
            var linea = lineasCarrito[index];

            if (e.CommandName == "Incrementar" || e.CommandName == "Decrementar")
            {
                // Recuperar la línea completa desde el WS

                var lineaCompleta = lineaWS.obtenerLineaCarrito(linea.id);

                //var lineaCompleta = lineasCarrito[index];

                // Actualizar cantidad
                if (e.CommandName == "Incrementar")
                    lineaCompleta.cantidad++;
                else if (e.CommandName == "Decrementar" && lineaCompleta.cantidad > 1)
                    lineaCompleta.cantidad--;

                if(lineaCompleta.cantidad > 5)
                {
                    lineaCompleta.precio = linea.producto.precioAlMayor;
                }
                else {
                    lineaCompleta.precio = linea.producto.precioRegular;
                }

                lineaCompleta.subTotal = lineaCompleta.cantidad * lineaCompleta.precio;

                // Actualizar en BD 
                lineaWS.actualizarLineaCarrito(lineaCompleta);

                // Reemplazar en memoria
                lineasCarrito[index] = lineaCompleta;

                // Refrescar tabla
                gvCarrito.DataSource = lineasCarrito;
                gvCarrito.DataBind();

                ActualizarTotal();
            }
            else if (e.CommandName == "Eliminar")
            {
                int idLinea = Convert.ToInt32(e.CommandArgument);

                lineaWS.eliminarLineaCarrito(idLinea);
                lineasCarrito.RemoveAt(index);

                gvCarrito.DataSource = lineasCarrito;
                gvCarrito.DataBind();

                ActualizarTotal();
            }
        }

        private void ActualizarTotal()
        {
            double subtotal = 0;
            double montoRegular = 0;

            foreach (var linea in lineasCarrito)
            {
                subtotal += linea.subTotal;
                montoRegular += linea.cantidad * linea.producto.precioRegular;
            }

            //double subtotal = lineasCarrito.Sum(l => l.subTotal);
            double descuento = montoRegular - subtotal;
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